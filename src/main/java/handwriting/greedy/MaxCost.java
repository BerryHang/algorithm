package handwriting.greedy;

import java.util.PriorityQueue;

public class MaxCost {

    public static void main(String[] args) {
        int times = 10000;
        int length = 5;
        int range = 50;

        for (int i = 0; i < times; i++) {

            int[] arr = generator(length, range);
            int cost1 = maxCostByGreedy(arr);
            int cost2 = maxCostByForce(arr);

            if (cost1 != cost2) {
                System.out.printf("err");
            }

        }
    }

    public static int maxCostByForce(int[] arr) {

        if (arr == null || arr.length == 1) {
            return 0;
        }
        return process(arr, 0);
    }

    //成本计算逻辑   int[] arr需要处理的数据   之前的数据已经产生的成本
    public static int process(int[] arr, int pre) {
        if (arr.length == 1) {
            return pre;
        }

        int ans = Integer.MAX_VALUE;

        //双层循环进行选取两个要聚合的数字
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                //取结果集中成本最小的        处理剩余数据（复制并移除数据       当前节点产生的成本 arr[i] + arr[j] 之前的成本是 pre 进行相加 ）
                ans = Math.min(ans, process(copyAndRemove(arr, i, j), pre + arr[i] + arr[j]));
            }
        }

        return ans;
    }

    //复制原数组，移除聚合的两个元素并将聚合的结果放到数组中
    public static int[] copyAndRemove(int[] arr, int i, int j) {

        int[] newArr = new int[arr.length - 1];

        int index = 0;

        for (int k = 0; k < arr.length; k++) {

            if (k != i && k != j) {
                newArr[index++] = arr[k];
            }

        }
        newArr[index] = arr[i] + arr[j];
        return newArr;
    }

    public static int maxCostByGreedy(int[] arr) {

        if (arr == null || arr.length == 1) {
            return 0;
        }

        //使用优先队列实现数据的排序
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        //将所有数据放入优先队列中
        for (int i = 0; i < arr.length; i++) {
            queue.add(arr[i]);
        }

        //反复取出两个数字聚合后放回，往复操作知道剩余一个数字位置
        int sum = 0;
        while (queue.size() > 1) {
            Integer poll = queue.poll() + queue.poll();
            sum += poll;
            queue.add(poll);
        }

        return sum;
    }

    public static int[] generator(int length, int range) {
        length = (int) (Math.random() * length + 1);
        int[] arr = new int[length];

        for (int i = 0; i < length; i++) {
            arr[i] = (int) (Math.random() * range + 1);
        }

        return arr;
    }

}
