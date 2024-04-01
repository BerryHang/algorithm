package handwriting.search;

//局部最小值
public class BinarySearch_LocalMinimumSearch {

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
            //二分查找
            System.out.println("找到局部最小数组的下标为：" + find(origArr));
        }

    }

    //二分查找逻辑
    public static int find(int[] arr) {

        //数组为空或者大小为0时直接返回
        if (arr == null || arr.length == 0) {
            return -1;
        }

        //当前数组长度为1或者数组的第二个数字大于第一个直接确定第一个数字就是局部最小
        if (arr.length == 1 || arr[0] < arr[1]) return 0;

        //当数组的倒数第二个数字大于最后一个数字也可以直接确定最后一个数字是局部最小
        if (arr[arr.length - 2] > arr[arr.length - 1]) return arr.length - 1;
        //以上两个判断主要是排除局部最小在两端的情况，两端数字比较特殊，他只有一侧有数字可以比较
        // 也可以通过上面的判断可以得出两侧的数字较大，往内侧时均为下降趋势

        //初始化二分查询左侧起始端点为左侧第二个数字
        int left = 1;
        //初始化二分查询右侧起始端点为右侧第二个
        int right = arr.length - 2;
        //以上因为两侧端点都已经判断过所以直接减少了二分范围

        //循环条件是左侧端点没有碰到右侧
        while (left < right) {
            //二分取中点
            int mid = left + ((right - left) >> 1);

            //判断中点与其左侧数字，如果左侧数据小，则左侧是下降趋势，将右端点移动到中点左侧
            if (arr[mid] >= arr[mid - 1]) {
                right = mid - 1;
                //判断中点与其右侧数字，如果右侧数据小，则右侧是下降趋势，将左端点移动到中点右侧
            } else if (arr[mid] >= arr[mid + 1]) {
                left = mid + 1;
            } else {
                return mid;
            }

            //循环条件是左侧端点的下标没有走到右侧下标的右边
        }

        return left;
    }

    //随机生成数据
    public static int[] generate(int min, int max, int length) {
        int[] arr = new int[(int) (Math.random() * length) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (max - min) + min);
        }
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
