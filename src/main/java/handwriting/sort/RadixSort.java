package handwriting.sort;

import java.util.Arrays;

public class RadixSort {

    public static void main(String[] args) {
        int minValue = 0;
        int maxValue = 50;
        int length = 50;
        int times = 10000;
        System.out.println("基数排序：");
        for (int i = 0; i < times; i++) {
            int[] origArr = generate(minValue, maxValue, length);
            int[] copyArr = copy(origArr);
            int[] copyArr1 = copy(origArr);

            radixSort(copyArr, 0, copyArr.length - 1, maxBits(copyArr));

            Arrays.sort(copyArr1);
            if (!compare(copyArr, copyArr1)) {
                print(origArr);
                break;
            }
        }
    }


    //求出数组中最大数字的位数
    public static int maxBits(int[] arr) {

        int max = Integer.MIN_VALUE;

        //找出数组中的最大数字
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(arr[i], max);
        }

        //求出最大数字有多少位  十进制
        int maxBits = 0;
        while (max != 0) {
            maxBits++;
            max /= 10;
        }

        return maxBits;
    }

    public static void radixSort(int[] arr, int L, int R, int maxBits) {

        //申请一个辅助数据和原数组区间相同大小
        int[] help = new int[R - L + 1];

        //循环最大数字的位数，实际处理的是数字的第几位
        for (int i = 1; i <= maxBits; i++) {

            /**
             * 因为当前基数排序是按照十进制进行统计，因此这里申请10个空间
             * count[0]位置存储第i位数字位0的数字个数
             * count[1]位置存储第i位数字位1的数字个数
             * count[2]位置存储第i位数字位2的数字个数
             * count[j]位置存储第i位数字位j的数字个数
             */
            int[] count = new int[10];
            for (int j = L; j <= R; j++) {
                int digit = getDigit(arr[j], i);
                count[digit]++;
            }

            /**
             * 将上面的统计数组变更为前缀和数组，其含义为
             * count[0]中的数字代表i位数小于等于0的数字个数
             * count[1]中的数字代表i位数小于等于1的数字个数
             * count[1]中的数字代表i位数小于等于2的数字个数
             * count[j]中的数字代表i位数小于等于i的数字个数
             */
            for (int j = 1; j < count.length; j++) {
                count[j] += count[j - 1];
            }

            /**
             * 特别注意这里遍历时是从数组的右侧开始遍历
             *  得到遍历数组的 上第i的值为 digit
             *  从count数组中取出 digit 位置的数字，实际代表了原数组中第i位数字小于等于 digit的数字的个数，
             *  其实际也就是在原数组排序的情况下应该占用多少个空间，因此当前数字应该放的位置也就是 个数-1
             */
            for (int j = R; j >= L; j--) {
                int digit = getDigit(arr[j], i);
                int index = count[digit]--;
                help[index - 1] = arr[j];
            }

            for (int j = 0, k = L; k <= R; j++, k++) {
                arr[k] = help[j];
            }

        }

    }

    public static int getDigit(int x, int d) {
        //Math.pow 实际是指数运算 Math.pow(10, 1)= 10^1 Math.pow(10, 2)=10^2
        return ((x / ((int) Math.pow(10, d - 1))) % 10);
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
