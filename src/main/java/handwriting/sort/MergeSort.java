package handwriting.sort;

import java.util.Arrays;

public class MergeSort {

    public static void main(String[] args) {

        int minValue = 0;
        int maxValue = 1000;
        int length = 50;
        int times = 10000;
        System.out.println("递归归并排序：");
        for (int i = 0; i < times; i++) {
            int[] origArr = generate(minValue, maxValue, length);
            int[] copyArr = copy(origArr);
            int[] copyArr1 = copy(origArr);
            mergeSortRecursion(copyArr);
            Arrays.sort(copyArr1);
            if (!compare(copyArr1, copyArr)) {
                System.out.printf("排序前：");
                print(origArr);
                System.out.printf("系统排序：");
                print(copyArr1);
                System.out.printf("排序前后：");
                print(copyArr);
                break;
            }
        }
        System.out.println("非递归归并排序：");
        for (int i = 0; i < times; i++) {
            int[] origArr = generate(minValue, maxValue, length);
            int[] copyArr = copy(origArr);
            int[] copyArr1 = copy(origArr);
            mergeSort(copyArr);
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

    //非递归方式进行归并排序
    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) return;

        int length = arr.length;
        int step = 1;
        while (step < length) {
            int left = 0;
            while (left < length) {

                if (step > length - left) break;
                int mid = left + step - 1;
                int right = mid + Math.min(step, length - mid - 1);
                merge(arr, left, mid, right);
                left = right + 1;
            }

            if (step > length >> 1) {
                break;
            }

            step <<= 1;
        }
    }

    //递归实现归并排序
    public static void mergeSortRecursion(int[] arr) {
        if (arr == null || arr.length < 2) return;
        process(arr, 0, arr.length - 1);
    }

    public static void process(int[] arr, int l, int r) {
        if (l == r) return;
        int m = l + ((r - l) >> 1);
        process(arr, l, m);
        process(arr, m + 1, r);
        merge(arr, l, m, r);
    }

    public static void merge(int[] arr, int left, int mid, int right) {

        int[] temp = new int[right - left + 1];
        int tempLeft = left;
        int tempRight = mid + 1;
        int i = 0;
        while (tempLeft <= mid && tempRight <= right) {
            temp[i++] = arr[tempLeft] > arr[tempRight] ? arr[tempRight++] : arr[tempLeft++];
        }

        while (tempLeft <= mid) {
            temp[i++] = arr[tempLeft++];
        }

        while (tempRight <= right) {
            temp[i++] = arr[tempRight++];
        }

        for (int j = 0; j < temp.length; j++) {
            arr[left + j] = temp[j];
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
        if (arr1.length != arr2.length) return false;

        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

}
