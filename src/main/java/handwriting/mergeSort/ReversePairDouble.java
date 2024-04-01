package handwriting.mergeSort;

//  本题测试链接 : https://leetcode.com/problems/reverse-pairs/
public class ReversePairDouble {

    public static void main(String[] args) {

        int testTimes = 1000;
        int length = 10;
        int range = 10;
        for (int i = 0; i < testTimes; i++) {
            int[] arr = generate(length, range);
            int[] mergeArr = copyArr(arr);

            int reversePairDoubleByStep = getReversePairDoubleByStep(arr);
            int reversePairDoubleByMerge = getReversePairDoubleByMerge(mergeArr);

            if (reversePairDoubleByStep != reversePairDoubleByMerge) {
                System.out.printf("出错：reversePairDoubleByStep：" + reversePairDoubleByStep + "  reversePairDoubleByMerge :" + reversePairDoubleByMerge + " ");
                print(arr);
            }

        }

    }

    public static int getReversePairDoubleByMerge(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return getReversePairDoubleByMerge(arr, 0, arr.length - 1);
    }

    public static int getReversePairDoubleByMerge(int[] arr, int left, int right) {

        if (left == right) {
            return 0;
        }

        int mid = left + ((right - left) >> 1);

        return getReversePairDoubleByMerge(arr, left, mid) + getReversePairDoubleByMerge(arr, mid + 1, right) + merge(arr, left, mid, right);

    }

    public static int merge(int[] arr, int left, int mid, int right) {

        int l = left;
        int r = mid + 1;
        int ans = 0;

        /**
         * 与归并排序不同的地方，不在归并的时候处理翻转对逻辑的判断，
         * 由于左右两组依然是有序的，因此只有在左组满足条件时，才会进行理翻转的累计
         * 这里定义一个window变量，用来保存已处理的数据范围 包含的数据范围是 [mid+,window]
         */
        int window = mid + 1;
        for (int i = left; i <= mid; i++) {

            while (window <= right && (long) arr[i] > (long) 2 * arr[window]) {
                window++;
            }
            ans += window - mid - 1;
        }

        int index = 0;
        int[] help = new int[right - left + 1];

        while (l <= mid && r <= right) {
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

    public static int getReversePairDoubleByStep(int[] arr) {
        int ans = 0;

        if (arr == null || arr.length < 2) {
            return ans;
        }

        int step = 1;

        int length = arr.length;

        while (step < length) {

            int left = 0;
            while (left < length) {
                if (step > length - left) break;
                int mid = left + step - 1;
                int right = mid + Math.min(step, length - mid - 1);
                ans += merge(arr, left, mid, right);
                left = right + 1;
            }

            if (step > length >> 1) break;
            step <<= 1;

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
