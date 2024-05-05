package handwriting.recursion;

public class CardsInLine {

    public static void main(String[] args) {

        int times = 10000;

        int length = 5;
        int range = 50;

        for (int i = 0; i < times; i++) {

            int[] arr = generate(length, range);

            int wins = wins(arr);
            int wins2 = wins2(arr);

            if (wins != wins2) {
                System.out.printf("err");
            }

        }
    }

    public static int wins(int[] arr) {
        int before = before(arr, 0, arr.length - 1);
        int after = after(arr, 0, arr.length - 1);
        return Math.max(before, after);
    }

    public static int before(int[] arr, int left, int right) {

        if (left == right) {
            return arr[left];
        }

        int ans1 = arr[left] + after(arr, left + 1, right);
        int ans2 = arr[right] + after(arr, left, right - 1);

        return Math.max(ans1, ans2);
    }

    public static int after(int[] arr, int left, int right) {

        if (left == right) {
            return 0;
        }

        int ans1 = before(arr, left + 1, right);
        int ans2 = before(arr, left, right - 1);

        return Math.min(ans1, ans2);
    }


    public static int wins2(int[] arr) {

        int[][] beforeArr = new int[arr.length][arr.length];
        int[][] afterArr = new int[arr.length][arr.length];

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                beforeArr[i][j] = -1;
                afterArr[i][j] = -1;
            }
        }

        int before = before2(arr, 0, arr.length - 1, beforeArr, afterArr);
        int after = after2(arr, 0, arr.length - 1, beforeArr, afterArr);
        return Math.max(before, after);
    }

    public static int before2(int[] arr, int left, int right, int[][] beforeArr, int[][] afterArr) {

        if (beforeArr[left][right] != -1) {
            return beforeArr[left][right];
        }

        int ans = 0;
        if (left == right) {
            ans = arr[left];
        } else {
            int ans1 = arr[left] + after2(arr, left + 1, right, beforeArr, afterArr);
            int ans2 = arr[right] + after2(arr, left, right - 1, beforeArr, afterArr);
            ans = Math.max(ans1, ans2);
        }

        beforeArr[left][right] = ans;

        return ans;
    }

    public static int after2(int[] arr, int left, int right, int[][] beforeArr, int[][] afterArr) {

        if (afterArr[left][right] != -1) {
            return afterArr[left][right];
        }

        int ans = 0;
        if (left == right) {
            ans = 0;
        } else {
            int ans1 = before2(arr, left + 1, right, beforeArr, afterArr);
            int ans2 = before2(arr, left, right - 1, beforeArr, afterArr);
            ans = Math.min(ans1, ans2);
        }

        afterArr[left][right] = ans;

        return ans;
    }

    //初始化数据样本
    private static int[] generate(int length, int range) {

        length = (int) (Math.random() * length + 1);

        int[] arr = new int[length];

        for (int i = 0; i < length; i++) {
            arr[i] = (int) (Math.random() * range);
        }

        return arr;
    }

}
