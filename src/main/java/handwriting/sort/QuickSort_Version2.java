package handwriting.sort;

public class QuickSort_Version2 {

    public static void main(String[] args) {
        int minValue = 0;
        int maxValue = 1000;
        int length = 50;
        int times = 10000;
        System.out.println("快速排序升级V1版本：");
        for (int i = 0; i < times; i++) {
            System.out.println("===================");

            int[] nums = generate(minValue, maxValue, length);
            System.out.printf("排序前：");
            print(nums);
            quickSort(nums);
            System.out.printf("排序后：");
            print(nums);
            System.out.println("===================");
        }

    }

    public static void quickSort(int[] nums) {

        if (nums == null || nums.length < 2) return;
        process(nums, 0, nums.length - 1);

    }

    public static void process(int[] nums, int L, int R) {
        if (L >= R) return;
        //随机选择一个基准值放到数组尾部
        swap(nums, L +(int) ( Math.random() * (R - L + 1)), R);
        int[] partition = digitalSort(nums, L, R);
        //处理基准值左侧数字
        process(nums, L, partition[0] - 1);
        //处理基准值右侧数字
        process(nums, partition[1], R);

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
