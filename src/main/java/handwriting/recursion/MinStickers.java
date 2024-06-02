package handwriting.recursion;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class MinStickers {

    public static void main(String[] args) {

        int times = 10000;
        int length = 5;
        int targetLength = 8;
        int size = 10;

        for (int i = 0; i < times; i++) {

            //生成目标的小写字符串
            String target = StringUtils.lowerCase(RandomStringUtils.randomAlphabetic((int) (Math.random() * targetLength) + 1));

            //生成源贴纸数组
            String[] stickers = generatorStickers(length, size);

            System.out.println("target:" + target);
            System.out.println("stickers:[" + StringUtils.join(stickers, ",") + "]");

            //暴力递归处理
            int ans1 = minStickers1(stickers, target);
            //相比较第一种增加了缓存操作
            int ans2 = minStickers2(stickers, target);
            int ans3 = minStickers3(stickers, target);

            if (ans3 != ans2 || ans1 != ans2) {
                System.out.printf("err");
            }

        }

    }

    public static int minStickers3(String[] stickers, String target) {

        int[][] source = new int[stickers.length][26];
        for (int i = 0; i < stickers.length; i++) {
            for (char c : stickers[i].toCharArray()) {
                source[i][c - 'a']++;
            }
        }

        //这里使用map结构作为缓存，由于字符串存在的可能性不确定，因此使用定长的数组结构不合适
        Map<String, Integer> dp = new HashMap<>();

        int ans = process3(source, target, dp);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public static int process3(int[][] stickers, String target, Map<String, Integer> dp) {

        //当目标字串已经缓存过，直接返回
        if (dp.containsKey(target)) {
            return dp.get(target);
        }

        if (target.length() == 0) {
            return 0;
        }

        //这里先将字符串转换为数字，因为全是小写英文字符，所以26个位置即可
        int[] ints = new int[26];
        for (char c : target.toCharArray()) {
            ints[c - 'a']++;
        }

        int min = Integer.MAX_VALUE;
        for (int[] sticker : stickers) {

            //这里仅当贴纸的
            if (sticker[target.charAt(0) - 'a'] > 0) {

                StringBuilder sb = new StringBuilder();

                for (int i = 0; i < 26; i++) {
                    if (ints[i] > 0) {
                        int count = ints[i] - sticker[i];
                        for (int j = 0; j < count; j++) {
                            sb.append((char) ('a' + i));
                        }
                    }
                }

                String rest = sb.toString();
                min = Math.min(min, process3(stickers, rest, dp));
            }
        }

        int ans = min + (min == Integer.MAX_VALUE ? 0 : 1);
        dp.put(target, ans);
        return ans;
    }

    public static int minStickers2(String[] stickers, String target) {

        //将贴纸信息数组话，加快处理速度
        int[][] source = new int[stickers.length][26];
        for (int i = 0; i < stickers.length; i++) {
            for (char c : stickers[i].toCharArray()) {
                source[i][c - 'a']++;
            }
        }

        int ans = process2(source, target);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public static int process2(int[][] stickers, String target) {
        if (target.length() == 0) {
            return 0;
        }

        //这里先将字符串转换为数字，因为全是小写英文字符，所以26个位置即可
        int[] ints = new int[26];
        for (char c : target.toCharArray()) {
            ints[c - 'a']++;
        }

        int min = Integer.MAX_VALUE;
        for (int[] sticker : stickers) {

            //仅当目标字符串中的首字母在贴纸中存在的才去处理
            if (sticker[target.charAt(0) - 'a'] > 0) {

                StringBuilder sb = new StringBuilder();

                for (int i = 0; i < 26; i++) {
                    if (ints[i] > 0) {
                        int count = ints[i] - sticker[i];
                        for (int j = 0; j < count; j++) {
                            //当前字符贴纸中未能满足的重新拼接
                            sb.append((char) ('a' + i));
                        }
                    }
                }

                //剩余需要的字符串信息进行递归处理
                String rest = sb.toString();
                min = Math.min(min, process2(stickers, rest));
            }
        }

        return min + (min == Integer.MAX_VALUE ? 0 : 1);
    }

    public static int minStickers1(String[] stickers, String target) {

        //递归逻辑处理
        int ans = process1(stickers, target);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    //整体逻辑是每次递归减去可以使用的字符
    public static int process1(String[] stickers, String target) {

        //当目前字符串全部拼接完毕后，直接返回0
        if (target.length() == 0) {
            return 0;
        }

        //默认取字符串最大值
        int min = Integer.MAX_VALUE;
        //循环所有的贴纸
        for (String sticker : stickers) {
            //在目标字串中移除当前贴纸中的字符
            String remain = minus(sticker, target);
            //如果能够移除字符再进行递归处理
            if (remain.length() != target.length()) {
                min = Math.min(min, process1(stickers, remain));
            }
        }

        //判断当前的贴纸序列中是否存在可以拼接目标字串的贴纸，不存在返回最大值，存在取最小值
        return min + (min == Integer.MAX_VALUE ? 0 : 1);
    }

    private static String minus(String sticker, String target) {

        char[] chars1 = sticker.toCharArray();
        char[] chars2 = target.toCharArray();

        int[] ints = new int[26];

        for (char c : chars2) {
            ints[c - 'a']++;
        }

        for (char c : chars1) {
            ints[c - 'a']--;
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < ints.length; i++) {

            if (ints[i] > 0) {
                for (int j = 0; j < ints[i]; j++) {
                    sb.append((char) (i + 'a'));
                }
            }

        }

        return sb.toString();
    }

    //初始化贴纸数据
    public static String[] generatorStickers(int length, int size) {

        size = (int) (Math.random() * size + 1);

        String[] stickers = new String[size];

        for (int i = 0; i < size; i++) {
            stickers[i] = StringUtils.lowerCase(RandomStringUtils.randomAlphabetic((int) (Math.random() * length) + 1));
        }

        return stickers;
    }

}