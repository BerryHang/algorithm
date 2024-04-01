package handwriting.sort;

import java.util.Arrays;

public class CountSort {

    public static void main(String[] args) {
        int minValue = 0;
        int maxValue = 50;
        int length = 50;
        int times = 10000;
        System.out.println("计数排序：");
        for (int i = 0; i < times; i++) {
            int[] origArr = generate(minValue, maxValue, length);
            int[] copyArr = copy(origArr);
            int[] copyArr1 = copy(origArr);

            countSort(copyArr);

            Arrays.sort(copyArr1);
            if (!compare(copyArr,copyArr1)){
                print(origArr);
                break;
            }
        }
    }

    public static void countSort(int[] arr) {

        if (arr==null || arr.length<2){
            return;
        }

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(arr[i], max);
        }

        int[] counts = new int[max+1];

        for (int i = 0; i < arr.length; i++) {
            counts[arr[i]]++;
        }
        int index = 0;
        for (int i = 0; i < counts.length; i++) {
            while (counts[i]-->0){
                arr[index++] =i;
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
