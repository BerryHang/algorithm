package handwriting.commonDataStructure;

public class GetMaxValueFromArray {

    public static void main(String[] args) {

        int length = 50;
        int range = 50;
        int testTimes = 1000;
        for (int i = 0; i < testTimes; i++) {
            int[] arr = generateArray((int) (Math.random() * length + 1), range);
            if (getByBinary(arr, 0, arr.length - 1) != getByLoop(arr)) {
                System.out.printf("出错");
                print(arr);
            }
        }

    }

    public static int[] generateArray(int length, int range) {
        int[] arr = new int[length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * range + 1) - (int) (Math.random() * range + 1);
        }
        return arr;
    }

    public static int getByBinary(int[] arr, int left, int right) {
        if (left == right) {
            return arr[left];
        }
        int mid = left + ((right - left) >> 1);
        int leftValue = getByBinary(arr, left, mid);
        int rightValue = getByBinary(arr, mid + 1, right);
        return Math.max(leftValue, rightValue);
    }

    public static int getByLoop(int[] arr) {
        int maxValue = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > maxValue) {
                maxValue = arr[i];
            }
        }
        return maxValue;
    }

    public static void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.printf(arr[i] + " ");
        }
        System.out.println("");
    }

}
