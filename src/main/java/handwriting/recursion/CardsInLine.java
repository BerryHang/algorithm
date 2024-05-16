package handwriting.recursion;

public class CardsInLine {

    public static void main(String[] args) {

        int times = 10000;

        int length = 5;
        int range = 50;

        for (int i = 0; i < times; i++) {

            int[] arr =new  int[]{6,3,8,5,7}; //generate(length, range);

            int wins = wins(arr);
            int wins2 = wins2(arr);
            int wins3 = wins3(arr);

            if (wins != wins2 || wins2 != wins3) {
                System.out.printf("err");
            }

        }
    }

    //获取胜者分数
    public static int wins(int[] arr) {
        //取得先手分数和后手分数，并返回最大的
        int before = before(arr, 0, arr.length - 1);
        int after = after(arr, 0, arr.length - 1);
        return Math.max(before, after);
    }

    //作为先手的逻辑处理
    public static int before(int[] arr, int left, int right) {

        //如果只剩下最后一个位置了，自己作为先手直接获得并返回
        if (left == right) {
            return arr[left];
        }

        //剩余多个位置未拿时
        //取走左侧位置，剩余的交给后手选择
        int ans1 = arr[left] + after(arr, left + 1, right);
        //取走右侧位置，剩余的交给后手选择
        int ans2 = arr[right] + after(arr, left, right - 1);

        //返回两者中较大的
        return Math.max(ans1, ans2);
    }

    //作为后手的逻辑处理
    public static int after(int[] arr, int left, int right) {

        //如果只剩下一个位置了，对于先手来说没有可获得的数字了，因此返回0
        if (left == right) {
            return 0;
        }

        //分别取走左侧和右侧的数据交给先手选择，这个不会进行加当前的选择值
        int ans1 = before(arr, left + 1, right);
        int ans2 = before(arr, left, right - 1);

        //这个返回较小的值，有与两者都极力使自己的分数最高，作为后手肯定选择让先手的得分更低
        return Math.min(ans1, ans2);
    }


    public static int wins2(int[] arr) {

        //创建和数组等长的二维数组保存计算过的历史数据
        int[][] beforeArr = new int[arr.length][arr.length];
        int[][] afterArr = new int[arr.length][arr.length];

        //都初始化为-1代表没有处理过
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

        //如果不等于-1证明计算过直接返回
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

        //对计算结果进行保存，并返回
        beforeArr[left][right] = ans;
        return ans;
    }

    public static int after2(int[] arr, int left, int right, int[][] beforeArr, int[][] afterArr) {

        //如果不等于-1证明计算过直接返回
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

        //对计算结果进行保存，并返回
        afterArr[left][right] = ans;
        return ans;
    }


    public static int wins3(int[] arr) {

        //同样创建缓存空间
        int[][] beforeArr = new int[arr.length][arr.length];
        int[][] afterArr = new int[arr.length][arr.length];

        //先手方法中只剩一个位置时等于当前位置的值，因此对对角线进行设置对应值
        for (int i = 0; i < arr.length; i++) {
            beforeArr[i][i] = arr[i];
        }

        //根据先手和后手函数中的处理关系进行依次填充
        for (int i = 1; i < arr.length; i++) {
            int left = 0;
            for (int right = i; right < arr.length; right++) {
                beforeArr[left][right] = Math.max(arr[left] + afterArr[left + 1][right], arr[right] + afterArr[left][right - 1]);
                afterArr[left][right] = Math.min(beforeArr[left + 1][right], beforeArr[left][right - 1]);
                left++;
            }
        }

        //最终取得是是 0 - N-1 上的最大值
        return Math.max(beforeArr[0][arr.length - 1], afterArr[0][arr.length - 1]);
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
