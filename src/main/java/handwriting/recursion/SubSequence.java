package handwriting.recursion;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.List;

public class SubSequence {

    public static void main(String[] args) {
        String s = RandomStringUtils.randomAlphabetic(3);
        System.out.println("s=" + s);
        List<String> ans = subSequence(s);
        for (String str : ans) {
            System.out.println(str);
        }
    }

    public static List<String> subSequence(String s) {

        List<String> ans = new ArrayList<>();

        //字符串是空的，直接返回
        if (s == null || s.length() == 1) {
            return ans;
        }

        //请求处理数据，从第0个位置开始，之前没有做过任何选择
        process(s.toCharArray(), 0, "", ans);
        return ans;
    }

    /**
     * @param arr 将字符串转化为字符数组
     * @param index 当前处理的字符位置
     * @param path 之前已经做过的选择
     * @param ans 最终选择的结果集
     */
    public static void process(char[] arr, int index, String path, List<String> ans) {

        //如果节点走完了，直接收集结果
        if (index == arr.length) {
            ans.add(path);
            return;
        }
        //当前节点不选的情况下，处理下一个位置
        process(arr, index + 1, path, ans);

        //选择当前节点的情况，把当前节点的路径拼接上，处理下一个位置
        process(arr, index + 1, path + arr[index], ans);
    }

}
