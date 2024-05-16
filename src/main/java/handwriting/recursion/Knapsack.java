package handwriting.recursion;

public class Knapsack {

    static int[] weights;
    static int[] values;

    public static void main(String[] args) {

        int times = 10000;
        int length = 10;
        int range = 10;
        int bag = 15;
        for (int i = 0; i < times; i++) {
            generator(length, range);
            bag = (int) (Math.random() * bag);
            int ans1 = knapsack(weights, values, bag);
            int ans2 = knapsack2(weights, values, bag);
            if (ans2 != ans1) {
                System.out.printf("err");
            }
        }

    }

    //递归计算逻辑
    public static int knapsack(int[] weights, int[] values, int bag) {
        return process(weights, values, 0, bag);
    }

    /**
     * 递归计算
     * @param weights 重量数组
     * @param values 价值数组
     * @param index 当前选择所在的位置
     * @param rest 背包剩余的可容纳重量
     * @return
     */
    public static int process(int[] weights, int[] values, int index, int rest) {

        //如果当前已经走出了数组的范围直接返回0
        if (index == weights.length) {
            return 0;
        }

        //当前位置不选择，直接交给下一个位置取选择
        int ans1 = process(weights, values, index + 1, rest);

        int ans2 = 0;

        //当前位置的物品重量如果大于背包剩余可容纳的中则直接为0，反之使用当前价值加上下一个位置选择的结果，这里需要注意传给下一个位置时剩余的重量需要减去当前的选择
        if (weights[index] <= rest) {
            ans2 = values[index] + process(weights, values, index + 1, rest - weights[index]);
        }

        //取选择当前物品和步选择当前物品中能去得价值最大的
        return Math.max(ans1, ans2);
    }

    //迭代计算逻辑
    public static int knapsack2(int[] weights, int[] values, int bag) {

        //定义一个等大的二维数字，这里也相当于初始化了选择位置在数组之外的值为0
        int[][] dp = new int[weights.length + 1][bag + 1];

        //外层循环逻辑是从最后一个位置开始做选择，
        for (int i = weights.length - 1; i >= 0; i--) {

            for (int j = 0; j <= bag; j++) {
                //不选择当前位置时，直接依赖下一层的值
                int ans1 = dp[i + 1][j];
                int ans2 = 0;

                //当可以选择当前位置时计算当前位置价值和下一层的结果相加
                if (weights[i] <= j) {
                    ans2 = values[i] + dp[i + 1][j - weights[i]];
                }

                //取最大保存
                dp[i][j] = Math.max(ans1, ans2);
            }

        }

        return dp[0][bag];
    }

    public static void generator(int length, int range) {
        length = (int) (Math.random() * length + 1);

        weights = new int[length];
        values = new int[length];

        for (int i = 0; i < length; i++) {
            weights[i] = (int) (Math.random() * range);
            values[i] = (int) (Math.random() * range);
        }

    }
}
