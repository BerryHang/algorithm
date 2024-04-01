package handwriting.search;

import java.util.Arrays;

//在一个有序数组中，找小于等于某个数最右侧的位置
public class RightMostSearch {

    public static void main(String[] args) {

        //生成随机数的最小值
        int minValue = 0;
        //生成随机数的最大值
        int maxValue = 20;
        //生成随机数组长度的范围
        int length = 50;
        //测试此说
        int times = 10000;

        for (int i = 0; i < times; i++) {
            //生成测试数据的数组样本
            int[] origArr = generate(minValue, maxValue, length);
            print(origArr);
            //随机生成目标数据
            int target = (int) (Math.random() * (maxValue - minValue) + minValue);
            //二分查找
            System.out.println("找到数据" + target + "最左位置：" + find(origArr, target));
        }

    }

    //二分查找逻辑
    public static int find(int[] arr, int num) {

        //数组为空或者大小为0时直接返回
        if (arr == null || arr.length == 0) {
            return -1;
        }

        //初始化二分查询起始的左端点
        int left = 0;
        //初始化二分查询起始的右端点
        int right = arr.length - 1;
        //记录大于等于目标数字的下标
        int index = -1;
        do {
            //二分取中点
            int mid = left + ((right - left) >> 1);

            //判断重中点是否大于等于目标值
            if (arr[mid] <= num) {
                //小于等于目标值记录当前下标，并且左侧端点移动到中间左侧
                index = mid;
                left = mid + 1;
            } else {
                //大于目标值，右侧端点移动到中间右侧
                right = mid - 1;
            }

            //循环条件是左侧端点的下标没有走到右侧下标的右边
        } while (left <= right);

        return index;
    }

    //随机生成数据
    public static int[] generate(int min, int max, int length) {
        int[] arr = new int[(int) (Math.random() * length) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (max - min) + min);
        }
        Arrays.sort(arr);
        return arr;
    }

    //打印数据
    public static void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

}
