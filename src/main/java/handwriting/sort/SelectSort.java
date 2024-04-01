package handwriting.sort;

import java.util.Arrays;

public class SelectSort {

    public static void main(String[] args) {

        int minValue = 0;
        int maxValue = 20;
        int length = 50;
        int times = 10000;

        for (int i = 0; i < times; i++) {
            int[] origArr = generate(minValue, maxValue, length);
            int[] copyArr = copy(origArr);
            int[] copyArr1 = copy(origArr);
            selectSort(copyArr);
            Arrays.sort(copyArr1);
            if (!compare(copyArr1, copyArr)) {
                System.out.printf("排序前：");
                print(origArr);
                System.out.printf("系统排序：");
                print(copyArr1);
                System.out.printf("排序前后：");
                print(copyArr);
            }
            break;
        }

    }

    public static void selectSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                swap(arr, i, minIndex);
            }
        }

    }

    public static int[] generate(int min, int max, int length) {
        int[] arr = new int[(int) (Math.random() * length) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (max - min) + min);
        }
        return arr;
    }

    public static void swap(int[] arr, int preIndex, int sufIndex) {
        int temp = arr[preIndex];
        arr[preIndex] = arr[sufIndex];
        arr[sufIndex] = temp;
    }

    public static void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static int[] copy(int[] arr) {
        int[] copyArr = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            copyArr[i] = arr[i];
        }
        return copyArr;
    }

    public static boolean compare(int[] arr1, int[] arr2) {
        if (arr1.length != arr2.length)
            return false;

        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

}
