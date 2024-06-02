package handwriting.recursion;

import org.apache.commons.lang3.RandomStringUtils;

//https://leetcode.com/problems/longest-common-subsequence/
//最长公共子序列
public class LongestCommonSubsequence {


    public static void main(String[] args) {
        int times = 10000;
        int length = 20;

        for (int i = 0; i < times; i++) {

            //生成两个随机字符串
            String text1 = RandomStringUtils.randomAlphanumeric((int) (Math.random() * length) + 1);
            String text2 = RandomStringUtils.randomAlphanumeric((int) (Math.random() * length) + 1);

            System.out.println("text1:" + text1);
            System.out.println("text2:" + text2);

            //暴力递归处理
            int ans1 = longestCommonSubsequence(text1, text2);
            //迭代逻辑处理
            int ans2 = longestCommonSubsequence1(text1, text2);
            if (ans1 != ans2) {
                System.out.printf("errr");
            }

        }
    }


    public static int longestCommonSubsequence1(String text1, String text2) {

        if (text1 == null || text1.length() == 0 || text2 == null || text2.length() == 0) {
            return 0;
        }

        int N = text1.length();
        int M = text2.length();

        int[][] ans = new int[N][M];

        ans[0][0] = text1.charAt(0) == text2.charAt(0) ? 1 : 0;

        for (int i = 1; i < N; i++) {
            ans[i][0] = text1.charAt(i) == text2.charAt(0) ? 1 : ans[i-1][0];
        }

        for (int i = 1; i < M; i++) {
            ans[0][i] = text1.charAt(0) == text2.charAt(i) ? 1 : ans[0][i-1];
        }

        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                int ans1 = text1.charAt(i) == text2.charAt(j) ? 1 + ans[i - 1][j - 1] : 0;
                ans[i][j] = Math.max(ans[i - 1][j], Math.max(ans[i][j - 1], ans1));
            }
        }

        return ans[N - 1][M - 1];
    }

    //递归逻辑实现
    public static int longestCommonSubsequence(String text1, String text2) {

        //临界判断
        if (text1 == null || text1.length() == 0 || text2 == null || text2.length() == 0) {
            return 0;
        }
        return process(text1.toCharArray(), text2.toCharArray(), text1.length() - 1, text2.length() - 1);
    }

    /**
     * 递归处理数据 求 字符数组 chars1 【0-index1】位置和字符数组 chars2 的【0-index2】位置上的公共子序列
     * 整个递归逻辑是从字符串的结尾往前匹配
     * @param chars1 字符数组1
     * @param chars2 字符数组2
     * @param index1 chars1 的【0-index1】位置
     * @param index2 chars2 的【0-index2】位置
     * @return
     */
    public static int process(char[] chars1, char[] chars2, int index1, int index2) {

        //当递归到两个字串的最后一个位置时，如果相等则返回1，反之0
        if (index1 == 0 && index2 == 0) {
            return chars1[0] == chars2[0] ? 1 : 0;
        }

        //当第一个字串还剩下一个时
        if (index1 == 0) {

            //判断是否和第二个字串的末尾位置是否相同，相同直接返回1，因为第一个字串只剩下一个字符
            if (chars1[0] == chars2[index2]) {
                return 1;
            } else {
                //不相同是查找第二个字串前面的位置是和第一个字串的首字符的公共部分
                return process(chars1, chars2, index1, index2 - 1);
            }
            //当第二个字串只剩下一个位置时，同上面的情况相似
        } else if (index2 == 0) {

            if (chars1[index1] == chars2[0]) {
                return 1;
            } else {
                return process(chars1, chars2, index1 - 1, index2);
            }

            //两个字串的位置均剩下多个时
        } else {
            //当两个字串的末尾字符不相同时
            int ans1 = process(chars1, chars2, index1 - 1, index2);
            int ans2 = process(chars1, chars2, index1, index2 - 1);
            //当两个字串的末尾字符相同时
            int ans3 = chars1[index1] == chars2[index2] ? 1 + process(chars1, chars2, index1 - 1, index2 - 1) : 0;
            //取得最大值
            return Math.max(ans1, Math.max(ans2, ans3));
        }

    }

}
