package handwriting.search;

import java.util.Arrays;

public class BinarySearch {

    public static void main(String[] args) {

        int minValue = 0;
        int maxValue = 20;
        int length = 50;
        int times = 10000;

        for (int i = 0; i < times; i++) {
            int[] origArr = generate(minValue, maxValue, length);
            print(origArr);
            int target =(int) (Math.random() * (maxValue - minValue) + minValue);
            System.out.println("找到数据"+target+"下标：" + find(origArr, target));
        }

    }

    public static int find(int[] arr, int num) {

        if (arr == null || arr.length == 0) {
            return -1;
        }

        int left = 0;
        int right = arr.length - 1;

        do {

            int mid = left + ((right - left) >> 1);
            if (arr[mid] == num) {
                return mid;
            } else if (arr[mid] > num) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }

        } while (left <= right);


        return -1;
    }

    public static int[] generate(int min, int max, int length) {
        int[] arr = new int[(int) (Math.random() * length) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (max - min) + min);
        }
        Arrays.sort(arr);
        return arr;
    }

    public static void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

}
