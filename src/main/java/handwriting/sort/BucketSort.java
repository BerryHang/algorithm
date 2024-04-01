package handwriting.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class BucketSort {

    public static void main(String[] args) {
        int minValue = 0;
        int maxValue = 20;
        int length = 50;
        int times = 10000;

        for (int i = 0; i < times; i++) {
            int[] origArr = generate(minValue, maxValue, length);
            int[] copyArr = copy(origArr);
            int[] copyArr1 = copy(origArr);

            int bucketSize = (int) (Math.random() * copyArr.length);
            if (bucketSize == 0) {
                bucketSize = 1;
            }
            bucketSort(copyArr, bucketSize);
            Arrays.sort(copyArr1);
            if (!compare(copyArr, copyArr1)) {
                System.out.printf("err");
                break;
            }
        }
    }

    public static void bucketSort(int[] arr, int bucketSize) {

        //判断排序集合是否为空或者只有一个元素
        if (arr == null || arr.length < 2) {
            return;
        }

        //获取元素的最大值和最小值
        int max = arr[0];
        int min = arr[0];

        for (int i = 1; i < arr.length; i++) {
            max = Math.max(arr[i], max);
            min = Math.min(arr[i], min);
        }

        //计算数据需要的桶个数
        int bucketCount = (max - min) / bucketSize + 1;

        //创建桶，这里为了方便每个桶中直接使用了 ArrayList 的数据结构
        ArrayList<Integer>[] bucket = new ArrayList[bucketCount];

        //遍历数组数据，把数据放到指定的桶中
        for (int i = 0; i < arr.length; i++) {
            int index = (arr[i] - min) / bucketSize;
            if (bucket[index] == null) {
                bucket[index] = new ArrayList<>();
            }
            bucket[index].add(arr[i]);
        }

        int index = 0;

        //循环所有的桶
        for (int i = 0; i < bucket.length; i++) {
            if (bucket[i] != null && bucket[i].size() > 0) {
                //对桶中的数据进行排序
                Collections.sort(bucket[i]);
                //最后输出到目标数组中
                for (int value : bucket[i]) {
                    arr[index++] = value;
                }
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
