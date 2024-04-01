package handwriting.heap;

import java.util.Arrays;

public class HeapSort {

    public static void main(String[] args) {

        int minValue = 0;
        int maxValue = 20;
        int length = 10;
        int times = 10000;

        for (int i = 0; i < times; i++) {
            int[] origArr = generate(minValue, maxValue, length);
            int[] copyArr = copy(origArr);
            int[] copyArr1 = copy(origArr);
            heapSort(copyArr);
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

    }

    public static void heapSort(int[] arr) {

        int len = arr.length;

        buildHeap(arr, len);

        for (int i = len - 1; i > 0; i--) {
            swap(arr, 0, i);
            heapify(arr, 0, i);
        }
    }

    public static void buildHeap(int[] arr, int len) {

        //注意这里的其实位置，由于在堆上面叶子节点约占所有节点的1/2，叶子节点不需要做heapify操作，因此起始位置为最后一个非叶子节点
        for (int i = (int) Math.floor(len / 2); i >= 0; i--) {
            heapify(arr, i, len);
        }

    }

    public static boolean heapInsert(int[] data, int index) {

        //判断自己的父级和自己谁大，当自己大时和父级交换位置
        while (data[(index - 1) / 2] < data[index]) {
            swap(data, index, (index - 1) / 2);
        }
        return true;
    }

    public static void heapify(int[] data, int index, int last) {

        //计算当前节点的左子节点位置
        int leftChild = 2 * index + 1;

        //当前节点存在子节点时，进入循环
        while (leftChild < last) {
            //计算当前节点的右子节点位置
            int rigthChild = leftChild + 1;

            //取出子节点中较大数字的位置
            int bigIndex = rigthChild < last && data[rigthChild] > data[leftChild] ? rigthChild : leftChild;

            //较大的子节点与当前节点比较
            if (data[index] > data[bigIndex]) {
                return;
            }

            //需要调整，交换当前节点和较大子节点的位置
            swap(data, index, bigIndex);

            //将当前节点的位置下移到较大的子节点为止
            index = bigIndex;

            //重新计算子左子节点的位置
            leftChild = 2 * index + 1;
        }
    }

    public static int[] copy(int[] arr) {
        int[] copyArr = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            copyArr[i] = arr[i];
        }
        return copyArr;
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
