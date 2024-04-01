package handwriting.heap;

import java.util.*;

public class PrizeSystem {

    public static void main(String[] args) {

        //最大数组长度
        int length = 10;

        //最大用户ID值
        int idRange = 7;

        //最大得奖池大小
        int kRange = 3;

        //测试次数
        int testTimes = 10000;
        for (int i = 0; i < testTimes; i++) {
            //生成样本数据
            SourceData sourceData = generate(idRange, length, kRange);
            List<List<Integer>> ans1 = compare(sourceData.arr, sourceData.op, sourceData.k);
            List<List<Integer>> ans2 = topK(sourceData.arr, sourceData.op, sourceData.k);
            if (!compareAns(ans1, ans2)) {
                System.out.println("error");
                break;
            }

        }

    }

    //对比两个集合是否一样
    private static boolean compareAns(List<List<Integer>> ans1, List<List<Integer>> ans2) {
        if (ans1.size() != ans2.size()) {
            return false;
        }

        for (int i = 0; i < ans1.size(); i++) {
            List<Integer> list1 = ans1.get(i);
            List<Integer> list2 = ans2.get(i);

            if (list1.size() != list2.size()) {
                return false;
            }

            list1.sort(Comparator.comparingInt(a -> a));
            list2.sort(Comparator.comparingInt(a -> a));

            for (int j = 0; j < list1.size(); j++) {
                if (list1.get(j) != list2.get(j)) {
                    return false;
                }
            }

        }
        return true;
    }

    //使用加强堆处理
    public static List<List<Integer>> topK(int[] arr, boolean[] op, int k) {
        //存放每个时间点的获奖列表
        List<List<Integer>> ans = new ArrayList<>();

        //初始化奖池对象
        PrizePool prizePool = new PrizePool(k);

        //循环数据
        for (int i = 0; i < arr.length; i++) {

            //添加操作操作数据
            prizePool.operate(arr[i], op[i], i);

            //获取当前奖池的数据
            ans.add(prizePool.getDaddies());
        }
        return ans;
    }

    //基于对比的逻辑实现
    public static List<List<Integer>> compare(int[] arr, boolean[] op, int limit) {

        //存放每个时间点的获奖列表
        List<List<Integer>> ans = new ArrayList<>();

        //存放获奖去的用户信息
        List<Customer> winners = new ArrayList<>();

        //存放候选区的用户信息
        List<Customer> candidates = new ArrayList<>();

        //存放用户信息
        Map<Integer, Customer> customerMap = new HashMap<>();

        //循环用户数组
        for (int i = 0; i < arr.length; i++) {
            int id = arr[i];
            boolean option = op[i];

            //判断是否之前的购买数量为0，又发生了退货操作，这时不进行任何处理，直接添加中奖集合
            if (!option && !customerMap.containsKey(id)) {
                ans.add(getWinners(winners));
                continue;
            }

            //判断用户信息中是否存在，如果不存在代码是一个新用户，创建并添加到用户信息中，这里数量和时间都先不做更新，后面操作时进行赋值
            if (!customerMap.containsKey(id)) {
                customerMap.put(id, new Customer(id, 0, 0));
            }

            //取出用户信息
            Customer customer = customerMap.get(id);

            //判断是购买操作还是退货操作，并对购买数量进行累计
            if (option) {
                customer.number++;
            } else {
                customer.number--;
            }

            //如果当前用户购买数量是0 则需要移除
            if (customer.number == 0) {
                customerMap.remove(id);
            }

            //如果中奖区和候选区都不存在这个数字那么需要进行添加操作
            if (!winners.contains(customer) && !candidates.contains(customer)) {
                //更新进入区域的时间
                customer.enterTime = i;
                //如果获奖区不满则直接进入获奖区，反之进入候选区
                if (winners.size() < limit) {
                    winners.add(customer);
                } else {
                    candidates.add(customer);
                }
            }

            //清除两个区域购买数量为0 的数据
            removeZero(winners);
            removeZero(candidates);

            //重新进行排序
            winners.sort(new WinnerComparator());
            candidates.sort(new CandidateComparator());

            //
            adjust(winners, candidates, limit, i);

            ans.add(getWinners(winners));
        }

        return ans;
    }

    public static void adjust(List<Customer> winners, List<Customer> candidates, int limit, int i) {

        //如果候选区没有数据直接返回   不需要做任何处理
        if (candidates.isEmpty()) {
            return;
        }

        //当获奖区还没有达到限制数量时直接从候选区选一个进入获奖区
        if (winners.size() < limit) {
            Customer customer = candidates.get(0);
            customer.enterTime = i;
            winners.add(customer);
            candidates.remove(0);
        } else if (candidates.get(0).number > winners.get(0).number) { //当获奖取已经满的时候,如果候选区中存在购买数量大于获奖区的是客户时，进行位置交换

            //取出将要进入获奖取的候选客户
            Customer candidate = candidates.get(0);
            //取出将要进入候选区的获奖客户
            Customer winner = winners.get(0);

            //重置进入候选区和获奖区的时间
            candidate.enterTime = i;
            winner.enterTime = i;

            //在原区域中移除上面的元素
            candidates.remove(0);
            winners.remove(0);

            //添加到指定的区域
            winners.add(candidate);
            candidates.add(winner);
        }

    }

    public static void removeZero(List<Customer> list) {

        Iterator<Customer> iterator = list.iterator();

        while (iterator.hasNext()) {
            Customer customer = iterator.next();
            if (customer.number == 0) {
                iterator.remove();
            }
        }
    }

    public static List<Integer> getWinners(List<Customer> winners) {

        List<Integer> ans = new ArrayList<>();

        for (Customer customer : winners) {
            ans.add(customer.id);
        }
        return ans;
    }

    //初始化测试数据
    public static SourceData generate(int idRange, int length, int kRange) {

        //生成数组的随机长度
        length = (int) (Math.random() * length + 1);

        //初始化用户ID数组和操作数组
        int[] arr = new int[length];
        boolean[] op = new boolean[length];

        //循环生成随机的用户ID数组和用户操作数组
        for (int i = 0; i < length; i++) {
            arr[i] = (int) (Math.random() * idRange + 1);
            op[i] = Math.random() < 0.5;
        }

        //生成随机得将池大小
        int k = (int) (Math.random() * kRange + 1);
        return new SourceData(arr, op, k);
    }

    //保存初始化数据的结构体
    public static class SourceData {

        //用户ID数组
        int[] arr;
        //用户操作数组
        boolean[] op;
        //得将池大小
        int k;

        public SourceData(int[] arr, boolean[] op, int k) {
            this.arr = arr;
            this.op = op;
            this.k = k;
        }
    }

}
