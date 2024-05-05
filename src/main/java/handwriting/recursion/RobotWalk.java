package handwriting.recursion;

public class RobotWalk {

    public static void main(String[] args) {

        int times = 10000;
        int nRange = 5;
        int k = 6;

        for (int i = 0; i < times; i++) {

            int N = (int) (Math.random() * nRange + 2);
            int start = (int) (Math.random() * N + +1);
            int aim = (int) (Math.random() * N + +1);
            int K = (int) (Math.random() * k + 1);

            int ans1 = ways1(N, start, aim, K);

            int ans2 = ways2(N, start, aim, K);

            int ans3 = ways3(N, start, aim, K);

            if (ans1 != ans2|| ans2!=ans3) {
                System.out.println("err");
            }

        }

    }

    /**
     * 获取不同走法的数量
     * @param N 一共有多少位置
     * @param start 起始位置
     * @param aim 目标位置
     * @param K 需要走的步数
     * @return 从 start 开始走 K 步，能走到 aim 位置的不同路径有多少
     */
    public static int ways1(int N, int start, int aim, int K) {
        return process1(N, start, aim, K);
    }

    /**
     * 递归处理逻辑
     * @param N 一共有多少位置
     * @param cur 当前所在位置
     * @param aim 目标位置
     * @param k 剩余的步数
     * @return 从 cur 开始走 k 步，能走到 aim 位置的不同路径有多少
     */
    public static int process1(int N, int cur, int aim, int k) {

        //如果当前的步数为0，只需要判断当前位置在不在目标位置，在返回1反之返回0
        if (k == 0) {
            return cur == aim ? 1 : 0;
        }

        //如果当前在1位置，只能往2位置走，并把需要的步数减一
        if (cur == 1) {
            return process1(N, 2, aim, k - 1);
        }

        //如果当前在N位置，只能往N-1位置走，并把需要的步数减一
        if (cur == N) {
            return process1(N, N - 1, aim, k - 1);
        }

        //其他情况是，分别取当前位置的加一位置和减一位置的步数总和
        return process1(N, cur - 1, aim, k - 1) + process1(N, cur + 1, aim, k - 1);
    }

    public static int ways2(int N, int start, int aim, int K) {

        //定义二维数组保存已经计算过的位置
        int[][] dp = new int[N + 1][K + 1];

        //先将所有的位置设置为-1
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= K; j++) {
                dp[i][j] = -1;
            }
        }

        return process2(N, start, aim, K, dp);
    }

    public static int process2(int N, int cur, int aim, int k, int[][] dp) {

        //当当前位置不为-1时，证明已经计算过，直接返回结果
        if (dp[cur][k] != -1) {
            return dp[cur][k];
        }

        int ans = 0;

        //计算当前位置的结果
        if (k == 0) {
            ans = cur == aim ? 1 : 0;
        } else if (cur == 1) {
            ans = process2(N, 2, aim, k - 1, dp);
        } else if (cur == N) {
            ans = process2(N, N - 1, aim, k - 1, dp);
        } else {
            ans = process2(N, cur - 1, aim, k - 1, dp) + process2(N, cur + 1, aim, k - 1, dp);
        }

        //更新当前位置的结果并返回
        dp[cur][k] = ans;
        return ans;
    }

    public static int ways3(int N, int start, int aim, int K) {

        //定义二维数组保存已经计算过的位置
        int[][] dp = new int[N + 1][K + 1];
        //起始位置一定是走0步即可到达
        dp[start][0] = 1;

        for (int i = 1; i <= K; i++) {

            dp[1][i] = dp[2][i - 1];

            for (int j = 2; j < N; j++) {
                dp[j][i] = dp[j + 1][i - 1] + dp[j - 1][i - 1];
            }

            dp[N][i] = dp[N - 1][i - 1];
        }

        return dp[aim][K];
    }

}
