package handwriting.greedy;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

public class MinLexicographicOrder {

    public static void main(String[] args) {
        int times = 10000;
        int arrayLength = 5;
        int stringLength = 10;

        for (int i = 0; i < times; i++) {

            String[] arr = generator(arrayLength, stringLength);

            String s = orderByGreedy(arr);
            String s1 = bruteForceAttack(arr);

            if (!s.equals(s1)) {
                System.out.printf("err");
            }

        }
    }

    //暴力破解，枚举所有可能性，取出最小
    public static String bruteForceAttack(String[] strs) {

        if (strs == null || strs.length == 0) {
            return "";
        }

        TreeSet<String> treeSet = process(strs);

        return treeSet.size() == 0 ? "" : treeSet.first();
    }

    //这里使用TreeSet 默认是小顶堆  String是有默认的排序规则
    public static TreeSet<String> process(String[] strs) {

        TreeSet<String> treeSet = new TreeSet<>();

        //如果数组为空的话返回一个空串
        if (strs == null || strs.length == 0) {
            treeSet.add("");
            return treeSet;
        }

        //循环当前数组，以数组中的每一次元素作为最终字串的前缀
        for (int i = 0; i < strs.length; i++) {

            String prefix = strs[i];

            //复制原数组并加已经选择的元素剔除
            String[] remainStrs = removeByIndex(strs, i);

            //用剩余的数组递归进行拼接字串
            TreeSet<String> sufTreeSet = process(remainStrs);

            //遍历剩余数组拼接的字串集合和当前前缀进行组合，添加到当前层的结果集中
            for (String suffix : sufTreeSet) {
                treeSet.add(prefix + suffix);
            }
        }

        return treeSet;
    }

    //根据下标移除数组中的元素
    private static String[] removeByIndex(String[] strs, int i) {

        String[] newStrs = new String[strs.length - 1];

        int index = 0;

        for (int j = 0; j < strs.length; j++) {
            if (j != i) {
                newStrs[index++] = strs[j];
            }
        }

        return newStrs;
    }


    //使用贪心算法处理
    public static String orderByGreedy(String[] strs) {

        if (strs == null || strs.length == 0) {
            return "";
        }

        //对数组进行排序  重点是比较器
        Arrays.sort(strs, new Compare());

        String res = "";

        //遍历拼接即可
        for (int i = 0; i < strs.length; i++) {
            res += strs[i];
        }

        return res;
    }

    //贪心比较器
    public static class Compare implements Comparator<String> {

        //拼接后结果比较小的序列信息排前面
        @Override
        public int compare(String o1, String o2) {
            return (o1 + o2).compareTo(o2 + o1);
        }
    }

    //生产随机字串数组
    private static String[] generator(int arrayLength, int stringLength) {

        arrayLength = (int) (Math.random() * arrayLength + 1);

        String[] arr = new String[arrayLength];

        for (int i = 0; i < arrayLength; i++) {
            arr[i] = StringUtils.lowerCase(RandomStringUtils.randomAlphabetic((int) (Math.random() * stringLength + 1)));
        }

        return arr;
    }

}