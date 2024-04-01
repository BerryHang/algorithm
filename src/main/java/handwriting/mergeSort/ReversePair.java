package handwriting.mergeSort;

public class ReversePair {

    public static void main(String[] args) {

        int testTimes = 1000;
        int length = 10;
        int range = 10;
        for (int i = 0; i < testTimes; i++) {
            int[] arr = generate(length, range);
            int[] mergeArr = copyArr(arr);

            int loopReversePair = getReversePairByLoop(arr);
            int mergeReversePair = getReversePairByMerge(mergeArr);

            if (loopReversePair != mergeReversePair) {
                System.out.printf("出错：mergeSmallSum：" + mergeReversePair + "  loopSmallSum :" + loopReversePair + " ");
                print(arr);
            }

        }

    }

    public static int getReversePairByMerge(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return getReversePairByMerge(arr, 0, arr.length - 1);
    }

    public static int getReversePairByMerge(int[] arr, int left, int right) {

        if (left == right) {
            return 0;
        }

        int mid = left + ((right - left) >> 1);

        return getReversePairByMerge(arr, left, mid) + getReversePairByMerge(arr, mid + 1, right) + merge(arr, left, mid, right);

    }

    public static int merge(int[] arr, int left, int mid, int right) {
        int[] help = new int[right - left + 1];
        int l = mid;
        int r = right;
        int ans = 0;
        int index = right - left;
        while (l >= left && r > mid) {
            //这里必须要注意只有在左组数据大时才进行逆序对的计数，
            // 当两数相等时，一定是先移动右组数据，这样才能真正算出来逆序的数字个数
            ans += arr[l] > arr[r] ? (r - mid) : 0;
            help[index--] = arr[l] > arr[r] ? arr[l--] : arr[r--];
        }

        while (r > mid) {
            help[index--] = arr[r--];
        }

        while (l >= left) {
            help[index--] = arr[l--];
        }

        for (int i = 0; i < help.length; i++) {
            arr[left + i] = help[i];
        }

        return ans;
    }

    public static int getReversePairByLoop(int[] arr) {
        int ans = 0;

        if (arr == null || arr.length < 2) {
            return ans;
        }

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                ans += arr[j] < arr[i] ? 1 : 0;
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
