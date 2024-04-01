package handwriting.mergeSort;

public class SmallSum {

    public static void main(String[] args) {

        int testTimes = 1000;
        int length = 10;
        int range = 10;
        for (int i = 0; i < testTimes; i++) {
            int[] arr = generate(length, range);
            int[] mergeArr = copyArr(arr);

            int loopSmallSum = getSmallSumByLoop(arr);
            int mergeSmallSum = getSmallSumByMerge(mergeArr);

            if (loopSmallSum != mergeSmallSum) {
                System.out.printf("出错：mergeSmallSum：" + mergeSmallSum + "  loopSmallSum :" + loopSmallSum + " ");
                print(arr);
            }

        }

    }

    public static int getSmallSumByMerge(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return getSmallSumByMerge(arr, 0, arr.length - 1);
    }

    public static int getSmallSumByMerge(int[] arr, int left, int right) {

        if (left == right) {
            return 0;
        }

        int mid = left + ((right - left) >> 1);

        return getSmallSumByMerge(arr, left, mid) + getSmallSumByMerge(arr, mid + 1, right) + merge(arr, left, mid, right);

    }

    public static int merge(int[] arr, int left, int mid, int right) {
        int[] help = new int[right - left + 1];
        int l = left;
        int r = mid + 1;
        int ans = 0;
        int index = 0;
        while (l <= mid && r <= right) {
            //这里必须要注意只有在左组数据小时才进行小和累加，
            // 当两数相等时，一定是先移动右组数据，这样才能真正算出来需要累加的次数
            ans += arr[l] < arr[r] ? arr[l] * (right - r + 1) : 0;
            help[index++] = arr[l] < arr[r] ? arr[l++] : arr[r++];
        }

        while (r <= right) {
            help[index++] = arr[r++];
        }

        while (l <= mid) {
            help[index++] = arr[l++];
        }

        for (int i = 0; i < help.length; i++) {
            arr[left + i] = help[i];
        }

        return ans;
    }

    public static int getSmallSumByLoop(int[] arr) {
        int ans = 0;

        if (arr == null || arr.length < 2) {
            return ans;
        }

        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                ans += arr[j] < arr[i] ? arr[j] : 0;
            }
        }
        return ans;
    }

    public static int[] generate(int length, int range) {
        length = (int) (Math.random() * length) + 1;
        int[] arr = new int[length];

        for (int i = 0; i < length; i++) {
            arr[i] = (int) (Math.random() * range);
        }

        return arr;
    }

    public static int[] copyArr(int[] arr) {
        int[] copyArr = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            copyArr[i] = arr[i];
        }

        return copyArr;
    }


    public static void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

}
