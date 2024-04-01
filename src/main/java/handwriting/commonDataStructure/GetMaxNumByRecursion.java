package handwriting.commonDataStructure;

//使用递归的方式查询数组中的最大值
public class GetMaxNumByRecursion {

    public static void main(String[] args) {

        int testTimes = 10000;
        int length = 1000;
        int max = 500;
        int min = 1;
        for (int i = 0; i < testTimes; i++) {
            int[] arr = generate(min, max, length);
            int recursionResult = findMaxNumByRecursion(arr, 0, arr.length - 1);
            int loopResult = findByLoop(arr);
            if (recursionResult != loopResult) {
                System.out.print("出错：loopResult: " + loopResult + " recursionResult: " + recursionResult + "  |");
                print(arr);
            }
        }

    }

    private static int findByLoop(int[] arr) {
        int result = arr[0];
        for (int i = 1; i < arr.length ; i++) {
            if (result < arr[i]) {
                result = arr[i];
            }
        }
        return result;
    }

    public static int[] generate(int min, int max, int length) {
        int[] arr = new int[(int) (Math.random() * length) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (max - min) + min);
        }
        return arr;
    }

    public static int findMaxNumByRecursion(int[] arr, int left, int right) {

        if (left == right) {
            return arr[left];
        }
        int mid = left + ((right - left) >> 1);
        int leftNum = findMaxNumByRecursion(arr, left, mid);
        int rightNum = findMaxNumByRecursion(arr, mid + 1, right);

        return Math.max(leftNum, rightNum);
    }

    public static void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
