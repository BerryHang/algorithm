package handwriting.mergeSort;

//https://leetcode.com/problems/count-of-range-sum/  区间和
public class CountOfRangeSum {

    public static void main(String[] args) {
        int testTimes = 10000;
        int length = 10;
        int range = 50;
        for (int i = 0; i < testTimes; i++) {
            int[] arr = generate(length, range);

            int lower = (int) (Math.random() * range);

            int upper = (int) (Math.random() * range * 4);
            while (lower >= upper) {
                upper = (int) (Math.random() * range * 4);
            }
            System.out.printf("===========\n");
            System.out.printf("原数组：");
            print(arr);

            System.out.printf("区间范围[" + lower + "," + upper + "] ，区间和个数：" + countRangeSum(arr, lower, upper));
            System.out.printf("===========\n");

        }

    }

    public static int countRangeSum(int[] nums, int lower, int upper) {

        if (nums == null || nums.length == 0) return 0;

        long[] sum = new long[nums.length];
        sum[0] = nums[0];

        for (int i = 1; i < sum.length; i++) {
            sum[i] = nums[i] + sum[i - 1];
        }

        return process(sum, 0, sum.length - 1, lower, upper);
    }

    public static int process(long[] arr, int left, int right, int lower, int upper) {

        //此处判断区间位置只有一个数字时，如果在范围区间内，证明0-left位置的和满足要求
        if (left == right) {
            return arr[left] >= lower && arr[left] <= upper ? 1 : 0;
        }

        int mid = left + ((right - left) >> 1);

        return process(arr, left, mid, lower, upper)
                + process(arr, mid + 1, right, lower, upper)
                + merge(arr, left, mid, right, lower, upper);
    }


    public static int merge(long[] arr, int left, int mid, int right, int lower, int upper) {
        int count = 0;

        int windowL = left;
        int windowR = left;

        //此处统计满足区间和的数据信息，实际求的是[windowL，windowR），注意范围是左闭右开，由于数据是有序的，所以整个的指针是不会退的
        for (int i = mid + 1; i <= right; i++) {
            long min = arr[i] - upper;
            long max = arr[i] - lower;

            while (windowL <= mid && arr[windowL] < min) {
                windowL++;
            }

            while (windowR <= mid && arr[windowR] <= max) {
                windowR++;
            }
            count += windowR - windowL;
        }

        int L = left;
        int R = mid + 1;
        int index = 0;
        long[] help = new long[right - left + 1];

        while (L <= mid && R <= right) {
            help[index++] = arr[L] <= arr[R] ? arr[L++] : arr[R++];

        }

        while (L <= mid) {
            help[index++] = arr[L++];
        }

        while (R <= right) {
            help[index++] = arr[R++];
        }

        for (int i = 0; i < help.length; i++) {
            arr[left + i] = help[i];
        }

        return count;
    }

    public static int[] generate(int length, int range) {
        length = (int) (Math.random() * length) + 1;
        int[] arr = new int[length];

        for (int i = 0; i < length; i++) {
            arr[i] = (int) (Math.random() * range);
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
