package handwriting.sort;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class QuickSort_Version3 {

    public static void main(String[] args) {
        int minValue = 0;
        int maxValue = 1000;
        int length = 20;
        int times = 10000;
        for (int i = 0; i < times; i++) {
            System.out.println("===================");

            int[] nums = generate(minValue, maxValue, length);
            System.out.printf("排序前：");
            print(nums);
            quickSortWithQueue(nums);
            System.out.printf("排序后：");
            print(nums);
            System.out.println("===================");
        }

    }

    // 快排非递归版本需要的辅助类
    // 要处理的是什么范围上的排序
    public static class Op {
        public int l;
        public int r;

        public Op(int left, int right) {
            l = left;
            r = right;
        }
    }

    public static void quickSortWithStack(int[] nums) {

        if (nums == null || nums.length < 2) {
            return;
        }

        int N = nums.length;

        //随机选择一个基准值移动到数组最后
        swap(nums, (int) (Math.random() * N), N - 1);
        //根据基准值进行一次快速排序
        int[] partition = digitalSort(nums, 0, N - 1);

        Stack<Op> stack = new Stack();
        //得到两侧需要处理的数据，并进行压栈
        stack.push(new Op(0, partition[0] - 1));
        stack.push(new Op(partition[1] + 1, N - 1));

        while (!stack.isEmpty()) {
            Op pop = stack.pop();
            if (pop.l < pop.r) {
                swap(nums, pop.l + (int) (Math.random() * ((pop.r - pop.l + 1))), pop.r);
                int[] partition1 = digitalSort(nums, pop.l, pop.r);
                stack.push(new Op(pop.l, partition1[0] - 1));
                stack.push(new Op(partition1[1] + 1, pop.r));
            }

        }

    }

    public static void quickSortWithQueue(int[] nums) {

        if (nums == null || nums.length < 2) {
            return;
        }

        int N = nums.length;

        //随机选择一个基准值移动到数组最后
        swap(nums, (int) (Math.random() * N), N - 1);
        //根据基准值进行一次快速排序
        int[] partition = digitalSort(nums, 0, N - 1);

        Queue<Op> queue = new LinkedList<>();
        //得到两侧需要处理的数据，并进行压栈
        queue.offer(new Op(0, partition[0] - 1));
        queue.offer(new Op(partition[1] + 1, N - 1));

        while (!queue.isEmpty()) {
            Op pop = queue.poll();
            if (pop.l < pop.r) {
                swap(nums, pop.l + (int) (Math.random() * ((pop.r - pop.l + 1))), pop.r);
                int[] partition1 = digitalSort(nums, pop.l, pop.r);
                queue.offer(new Op(pop.l, partition1[0] - 1));
                queue.offer(new Op(partition1[1] + 1, pop.r));
            }

        }

    }

    //以数组最后一个数字位基准进行分割
    public static int[] digitalSort(int[] nums, int L, int R) {

        //下标范围异常
        if (L > R) return new int[]{-1, -1};

        //数组中只有一个数字不需要操作
        if (L == R) return new int[]{L, R};

        //小于基准值起始下标
        int less = L - 1;
        //大于基准值起始下标
        int more = R;
        //当前扫描的数据下标位置
        int index = L;

        //扫描的位置不能越过大于基准值的下标
        while (index < more) {
            //当前扫描的数字小于基准值时，当前数字和小于基准值的下一个位置交换，并移动扫描数字的下标
            if (nums[index] < nums[R]) {
                swap(nums, ++less, index++);
                //当前扫描的数字大于基准值时，大于基准值下标前移，当前扫描数字和新的大于基准值下标数字交换
                //注意这里的扫描位置不能后移，因为换过来的数字不一定小于基准值
            } else if (nums[index] > nums[R]) {
                swap(nums, index, --more);
                //相等的情况下直接后移扫描位置
            } else {
                index++;
            }

        }

        //最后把最后一个位置的基准值移动到大于基准值的下标位置
        swap(nums, more, R);

        return new int[]{less + 1, more};
    }

    public static void swap(int[] arr, int preIndex, int sufIndex) {
        int temp = arr[preIndex];
        arr[preIndex] = arr[sufIndex];
        arr[sufIndex] = temp;
    }

    public static int[] generate(int min, int max, int length) {
        int[] arr = new int[(int) (Math.random() * length) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (max - min) + min);
        }
        return arr;
    }

    public static void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
