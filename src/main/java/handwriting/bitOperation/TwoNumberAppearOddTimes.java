package handwriting.bitOperation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TwoNumberAppearOddTimes {

    public static void main(String[] args) {

        Integer[] arr = generate(10, 6, 1, 50);
        System.out.printf("原数组为：");
        print(arr);
        findOddTimesNumbers(arr);
        Arrays.sort(arr);
        System.out.println("排序后数组为：");
        print(arr);
    }

    //打印数组
    public static void print(Integer[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    //找到出现奇数次的两个数字
    public static void findOddTimesNumbers(Integer[] arr) {

        //先进行所有数字的异或运算，由于有两个出现奇数次的数字且两个数字不同所以异或运算后绝对不为0
        Integer allResoult = 0;
        for (int i = 0; i < arr.length; i++) {
            allResoult ^= arr[i];
        }

        //取出最右的差异位
        Integer xorBit = allResoult & (-allResoult);

        Integer oneOf = 0;

        for (int i = 0; i < arr.length; i++) {
            if ((arr[i] & xorBit) != 0) {
                oneOf ^= arr[i];
            }
        }
        System.out.printf("两个出现奇数次的数字分别是："+oneOf+","+(allResoult^oneOf));
    }

    public static Integer[] generate(int length, int times, int min, int max) {

        List<Integer> list = new ArrayList<>();

        //生成数组长度
        length = (int) (Math.random() * length);
        //偶数转奇数

        if (times <= 2) {
            times = times << 2;
        }

        //填充出现偶数次数字
        for (int i = 0; i < length; i = i + 2) {

            int randomEvenNum = (int) (Math.random() * (max - min)) + min;
            int randomEvenTimes = (int) (Math.random() * times);
            if (randomEvenTimes % 2 != 0) randomEvenTimes++;
            for (int j = 0; j < randomEvenTimes; j++) {
                list.add(randomEvenNum);
            }

        }

        //随机生成第一个出现奇数次数字和出现次数
        int randomFirstOddNum = (int) (Math.random() * (max - min)) + min;

        int randomFirstOddTimes = (int) (Math.random() * times);
        if (randomFirstOddTimes % 2 == 0) randomFirstOddTimes++;

        for (int j = 0; j < randomFirstOddTimes; j++) {
            list.add(randomFirstOddNum);
        }

        //随机生成第二个出现数字和出现次数
        int randomSecondOddNum = (int) (Math.random() * (max - min)) + min;

        while (randomFirstOddNum == randomSecondOddNum) {
            randomSecondOddNum = (int) (Math.random() * (max - min)) + min;
        }

        int randomSecondOddTimes = (int) (Math.random() * times);
        if (randomSecondOddTimes % 2 == 0) randomSecondOddTimes++;

        for (int j = 0; j < randomSecondOddTimes; j++) {
            list.add(randomSecondOddNum);
        }

        //打乱顺序
        Integer[] arr = list.toArray(new Integer[0]);

        int arrLength = arr.length;

        for (int i = 0; i < arrLength; i++) {
            //随机交换下标
            int index1 = (int) (Math.random() * arrLength);
            int index2 = (int) (Math.random() * arrLength);
            swap(arr, index1, index2);
        }

        return arr;
    }

    public static void swap(Integer[] arr, int preIndex, int sufIndex) {
        int temp = arr[preIndex];
        arr[preIndex] = arr[sufIndex];
        arr[sufIndex] = temp;
    }

}
