package handwriting.recursion;

import org.apache.commons.lang3.RandomStringUtils;

public class ConvertToLetterString {

    public static void main(String[] args) {
        int times = 10000;
        int length = 10;

        for (int i = 0; i < times; i++) {

            //随机生成纯数字字符串
            String s = RandomStringUtils.randomNumeric((int) (Math.random() * length + 1));
            int ans1 = convertToLetterString(s);
            int ans2 = convertToLetterString1(s);

            if (ans1 != ans2) {
                System.out.printf("err");
            }
        }
    }

    //逻辑处理入口
    public static int convertToLetterString(String s) {
        return process(s.toCharArray(), 0);
    }

    /**
     * 递归计算
     * @param chars 字符串转成字符数据
     * @param index 已经处理到的位置
     * @return
     */
    public static int process(char[] chars, int index) {

        //如果已经把整个字符串处理完了，没有异常证明是一次成功的选择，返回1
        if (index == chars.length) {
            return 1;
        }

        //如果当前位置是个0直接返回0，证明当前的选择是错误的
        if (chars[index] == '0') {
            return 0;
        }

        //当前位置作为单独的字符进行处理，并交给下一个位置
        int ans = process(chars, index + 1);

        //如果后面还有位置，并且和当前位置组合的数据小于26，证明可以和后一个位置进行组合选择
        if (index + 1 < chars.length && ((chars[index] - '0') * 10 + (chars[index + 1] - '0')) < 27) {
            //将下一个位置的结果和前面单位置处理的结果进行累加
            ans += process(chars, index + 2);
        }
        return ans;
    }

    //迭代逻辑处理
    public static int convertToLetterString1(String s) {

        //定义缓存数组
        int[] dp = new int[s.length() + 1];
        //最后一个位置设置为1
        dp[s.length()] = 1;
        char[] chars = s.toCharArray();

        //倒序处理
        for (int i = s.length() - 1; i >= 0; i--) {

            //通递归逻辑相同
            if (chars[i] == '0') {
                dp[i] = 0;
                continue;
            }

            int ans = dp[i + 1];
            if (i + 1 < chars.length && ((chars[i] - '0') * 10 + (chars[i + 1] - '0')) < 27) {
                ans += dp[i + 2];
            }
            dp[i] = ans;
        }

        //最终返回dp[0]，他和递归函数在入口时，可变参数的值相关
        return dp[0];
    }
}
