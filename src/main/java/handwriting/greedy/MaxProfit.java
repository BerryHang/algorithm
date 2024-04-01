package handwriting.greedy;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MaxProfit {

    public static class Program {
        public int cost;
        public int profit;

        public Program(int cost, int profit) {
            this.cost = cost;
            this.profit = profit;
        }
    }

    public static void main(String[] args) {
        int times = 10000;
        int length = 5;
        int range = 50;

        for (int i = 0; i < times; i++) {

            //初始化数据
            Program[] programs = generator(length, range);

            //初始化初始资金
            int M = (int) (Math.random() * range + 1);
            //初始化做项目的数量
            int K = (int) (Math.random() * (programs.length + 4));

            //贪心算法求最终收益
            int cost1 = maxCostByGreedy(programs, M, K);

            //暴力破解收益信息
            int cost2 = maxCostByForce(programs, M, K);

            if (cost1 != cost2) {
                System.out.printf("err");
            }

        }
    }

    //贪心算法求最终收益
    public static int maxCostByGreedy(Program[] programs, int m, int k) {

        if (programs == null) {
            return m;
        }

        //创建花费的优先级队列，花费小的排在前面
        PriorityQueue<Program> costQueue = new PriorityQueue<>(new MinCostComparator());
        //创建收益的优先级队列，收益高的排在前面
        PriorityQueue<Program> profitQueue = new PriorityQueue<>(new MaxProfitComparator());

        //将所有数据放入花费的优先级队列中
        for (int i = 0; i < programs.length; i++) {
            costQueue.add(programs[i]);
        }

        //循环需要做的项目总数
        for (int i = 0; i < k; i++) {

            //将所有满足当前资金的项目取出放到收益队列中
            while (!costQueue.isEmpty() && costQueue.peek().cost <= m) {
                profitQueue.add(costQueue.poll());
            }

            //如果收益队列空，没有能做的项目，结束循环
            if (profitQueue.isEmpty()) {
                break;
            }

            //取出收益最高的项目进行并累加收益
            m += profitQueue.poll().profit;
        }

        return m;
    }

    //暴力破解收益信息 programs 当前项目的列表 m 当前可用资金 当前剩余可以做的项目
    public static int maxCostByForce(Program[] programs, int m, int k) {

        //如果项目列表为空直接返回当前的可用资金
        if (programs == null || programs.length == 0) {
            return m;
        }

        //如果可做的项目为0 返回当前可用资金
        if (k == 0) {
            return m;
        }

        //保存当前层的成本信息
        int profit = m;

        for (int i = 0; i < programs.length; i++) {

            //当前可用资金能够做项目的情况下
            if (programs[i].cost <= m) {
                //移除已经做过的项目
                Program[] remainPrograms = copyPrograms(programs, i);

                //求出剩余项目做后的最大收益
                profit = Math.max(profit, maxCostByForce(remainPrograms, m + programs[i].profit, k - 1));
            }

        }

        return profit;
    }

    public static Program[] copyPrograms(Program[] programs, int i) {

        Program[] newPrograms = new Program[programs.length - 1];

        int index = 0;

        for (int j = 0; j < programs.length; j++) {
            if (j != i) {
                newPrograms[index++] = programs[j];
            }
        }

        return newPrograms;
    }

    public static class MinCostComparator implements Comparator<Program> {

        @Override
        public int compare(Program o1, Program o2) {
            return o1.cost - o2.cost;
        }
    }

    public static class MaxProfitComparator implements Comparator<Program> {

        @Override
        public int compare(Program o1, Program o2) {
            return o2.profit - o1.profit;
        }
    }

    public static Program[] generator(int length, int range) {

        length = (int) (Math.random() * length + 1);

        Program[] programs = new Program[length];

        for (int i = 0; i < length; i++) {

            programs[i] = new Program((int) (Math.random() * range), (int) (Math.random() * range));

        }

        return programs;
    }

}
