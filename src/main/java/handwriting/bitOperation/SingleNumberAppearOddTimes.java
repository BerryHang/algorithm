package handwriting.bitOperation;

import java.util.ArrayList;
import java.util.List;

public class SingleNumberAppearOddTimes {

    public static void main(String[] args) {

        Integer[] arr = generate(10,6,1,50);
        print(arr);
        System.out.printf("出现奇数次的数为："+findOddTimesNum(arr));

    }

    //打印数组
    public static void print(Integer[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    //找到出现奇数次的数字
    public static Integer findOddTimesNum( Integer[] arr){

        Integer num = 0;
        for (int i = 0; i < arr.length; i++) {
            num^=arr[i];
        }

        return num;

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

        //随机生成一个奇数次数字和出现次数
        int randomOddNum = (int) (Math.random() * (max - min)) + min;

        int randomOddTimes = (int) (Math.random() * times);
        if (randomOddTimes % 2 == 0) randomOddTimes++;

        for (int j = 0; j < randomOddTimes; j++) {
            list.add(randomOddNum);
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
