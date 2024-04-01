package handwriting.heap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrizePool {

    //保存获奖区用户信息
    private HeapGreater<Customer> winners;

    //保存待定区用户信息
    private HeapGreater<Customer> candidates;

    //保存ID和用户信息的映射关系
    private Map<Integer, Customer> customerMap;

    //获奖区大小
    private int limit;

    //初始化奖池信息
    public PrizePool(int limit) {
        this.limit = limit;
        //初始化获奖区和待定区的排序规则
        winners = new HeapGreater(new WinnerComparator());
        candidates = new HeapGreater(new CandidateComparator());
        customerMap = new HashMap<>();
    }

    public void operate(int id, boolean op, int time) {

        //退货操作，并且用户映射关系中没有的直接返回
        if (!op && !customerMap.containsKey(id)) {
            return;
        }

        //排除上面的情况，如果用户映射信息没有，这创建一个，并添加到映射中
        if (!customerMap.containsKey(id)) {
            customerMap.put(id, new Customer(id, 0, 0));
        }

        //重新获取用户信息
        Customer customer = customerMap.get(id);

        //根据操作进行购买数量累计
        if (op) {
            customer.number++;
        } else {
            customer.number--;
        }

        //如果购买数量为0，则移除映射关系
        if (customer.number == 0) {
            customerMap.remove(id);
        }

        //获奖区和候选区都没有当前用户时
        if (!candidates.contains(customer) && !winners.contains(customer)) {
            //如果获奖取不满则加入到获奖区中，反之加到候选区，这里一定是一个购买操作
            if (winners.size() < limit) {
                customer.enterTime = time;
                winners.push(customer);
            } else {
                customer.enterTime = time;
                candidates.push(customer);
            }

            //存在在候选区中
        } else if (candidates.contains(customer)) {
            //如果购买数量为0，直接移除
            if (customer.number == 0) {
                candidates.remove(customer);
            } else { //购买数量不为0，由于数量进行了加减操作，则需要进行一次调整
                candidates.resign(customer);
            }

            //存在在获奖区中
        } else {
            //如果购买数量为0，直接移除
            if (customer.number == 0) {
                winners.remove(customer);
            } else { //购买数量不为0，由于数量进行了加减操作，则需要进行一次调整
                winners.resign(customer);
            }
        }

        //最后进行一次最终调整，判断是否存在区域调整的用户
        adjust(time);
    }

    //调整堆，主要确认有没有用户信息需要调整所在区域的
    private void adjust(int time) {

        //如果待定区为空，不需要做任何处理
        if (candidates.isEmpty()) {
            return;
        }

        //当获取区不满时，直接从待定区取出一个放入获奖区
        if (winners.size() < limit) {
            Customer customer = candidates.pop();
            customer.enterTime = time;
            winners.push(customer);
        } else {//当获奖区满时
            //判断获奖取购买数量最小的是否小于待定区购买数量最大的，满足条件则进行交换
            if (candidates.peek().number > winners.peek().number) {
                Customer maxCandidate = candidates.pop();
                Customer minWinner = winners.pop();
                maxCandidate.enterTime = time;
                minWinner.enterTime = time;
                winners.push(maxCandidate);
                candidates.push(minWinner);
            }
        }

    }

    public List<Integer> getDaddies() {
        List<Customer> customers = winners.getAllElements();
        List<Integer> ans = new ArrayList<>();
        for (Customer c : customers) {
            ans.add(c.id);
        }
        return ans;
    }

}
