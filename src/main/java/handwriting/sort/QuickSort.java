package handwriting.sort;

public class QuickSort {

    public static void main(String[] args) {
        int minValue = 0;
        int maxValue = 1000;
        int length = 5;
        int times = 10000;
        for (int i = 0; i < times; i++) {
            System.out.println("===================");

            int[] nums = generate(minValue, maxValue, length);
            System.out.printf("排序前：");
            print(nums);
            quickSort(nums, 0, nums.length - 1);
            System.out.printf("排序后：");
            print(nums);
            System.out.println("===================");
        }

    }

    public static void quickSort(int[] nums, int L, int R) {

        //左侧下标没有越过右侧下标时
        if (L < R) {
            //快速排序，并返回基准值的下标
            int partition = partition(nums, L, R);
            //快速排序基准值左侧部分
            quickSort(nums, L, partition - 1);
            //快速排序基准值右侧部分
            quickSort(nums, partition + 1, R);
        }

    }

    public static int partition(int[] nums, int L, int R) {
        // 设定基准值（pivot）默认左侧第一个
        int pivot = L;

        //保存小于基准值的最右侧下边
        int index = pivot + 1;

        //逐一扫描数据
        for (int i = index; i <= R; i++) {
            //当扫描数据小于基准值时，
            if (nums[i] < nums[pivot]) {
                //交换位置并移动小于基准值下表位置
                swap(nums, i, index++);
            }

        }
        //最后移动第一个位置的基准值
        swap(nums, pivot, --index);
        return index;
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
