package handwriting.bitOperation;

import java.util.*;

public class AppearKMTimes {

    public static void main(String[] args) {

        Integer numberLengh = 20;
        Integer kTimesRange = 5;
        Integer mTimesRange = 5;
        Integer range = 20;
        Integer testTimes = 20;

        for (int i = 0; i < testTimes; i++) {

            //生成数组组数字的个数，保证至少两个
            numberLengh = (int) (Math.random() * numberLengh + 2);
            //生成两种不同的出现次数
            Integer mTimes = (int) (Math.random() * mTimesRange + 1);
            Integer kTimes = (int) (Math.random() * kTimesRange + 1);

            if (mTimes == kTimes) {
                mTimes++;
            }
            range = (int) (Math.random() * range + numberLengh);
            int[] arr = generate(numberLengh, Math.min(kTimes, mTimes), Math.max(kTimes, mTimes), range);
            Integer ans = findKTimesNumbers(arr, Math.min(kTimes, mTimes), Math.max(kTimes, mTimes));
            Integer ans1 = test(arr, Math.min(kTimes, mTimes), Math.max(kTimes, mTimes));

            if (ans != ans1) {
                System.out.println("出错了：K :" + kTimes + " m:" + mTimes + " ans:" + ans + " ans1 :" + ans1);
                Arrays.sort(arr);
                print(arr);
            }

        }
    }

    public static Integer test(int[] arr, int kTimes, int mTimes) {

        HashMap<Integer, Integer> countMap = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {

            if (countMap.containsKey(arr[i])) {
                countMap.put(arr[i], countMap.get(arr[i]) + 1);
            } else {
                countMap.put(arr[i], 1);
            }

        }
        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {

            if (entry.getValue() == kTimes) {
                return entry.getKey();
            }

        }
        return null;
    }

    //打印数组
    public static void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    //找出出现K次的数字
    public static Integer findKTimesNumbers(int[] arr, int kTimes, int mTimes) {

        int[] help = new int[32];

        //遍历所有的数字
        for (Integer number : arr) {
            //遍历数字所以的二进制位，将二进制位的数字进行叠加
            for (int j = 0; j < 32; j++) {
                help[j] += (number >> j) & 1;
            }

        }
        int ans = 0;
        for (int i = 0; i < help.length; i++) {
            if (help[i] % mTimes != 0) {
                ans |= 1 << i;
            }
        }

        return ans;
    }

    public static int[] generate(int length, int kTimes, int mTimes, int range) {

        int ktimeNum = randomNumber(range);
        // 目标数字出现的次数
        int times = kTimes;
        // 2
        int numKinds = (int) (Math.random() * length) + 2;
        // k * 1 + (numKinds - 1) * m
        int[] arr = new int[times + (numKinds - 1) * mTimes];
        int index = 0;
        for (; index < times; index++) {
            arr[index] = ktimeNum;
        }
        numKinds--;
        HashSet<Integer> set = new HashSet<>();
        set.add(ktimeNum);
        while (numKinds != 0) {
            int curNum = 0;
            do {
                curNum = randomNumber(range);
            } while (set.contains(curNum));
            set.add(curNum);
            numKinds--;
            for (int i = 0; i < mTimes; i++) {
                arr[index++] = curNum;
            }
        }
        // arr 填好了
        for (int i = 0; i < arr.length; i++) {
            // i 位置的数，我想随机和j位置的数做交换
            int j = (int) (Math.random() * arr.length);// 0 ~ N-1
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
        return arr;
    }

    public static int randomNumber(int range) {
        return (int) (Math.random() * (range + 1)) - (int) (Math.random() * (range + 1));
    }

    public static void swap(Integer[] arr, int preIndex, int sufIndex) {
        int temp = arr[preIndex];
        arr[preIndex] = arr[sufIndex];
        arr[sufIndex] = temp;
    }

}
