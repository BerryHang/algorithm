package handwriting.recursion;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.List;

public class Permutation {

    public static void main(String[] args) {
        String s = RandomStringUtils.randomAlphabetic(4);
        System.out.println("s=" + s);
        List<String> ans = permutation("aab");
        for (String str : ans) {
            System.out.println(str);
        }
    }

    public static List<String> permutation(String s) {
        List<String> ans = new ArrayList<>();

        //字符串是空的，直接返回
        if (s == null || s.length() == 1) {
            return ans;
        }

        List<Character> characters = new ArrayList<>();

        for (char c : s.toCharArray()) {
            characters.add(c);
        }
        //请求处理数据,之前没有做过任何选择
        process(characters, "", ans);
        return ans;
    }

    /**
     * @param characters 需要处理的目标字符串的字符集合
     * @param path 前面路径中已经选择的元素
     * @param ans 最终的结果
     */
    public static void process(List<Character> characters,String path,List<String> ans){

        //如果集合已经为空，证明没有可以做的选择，直接收集结果
        if (characters.isEmpty()){
            ans.add(path);
            return;
        }

        boolean[] flag = new boolean[128];

        //循环字符集合，分别用集合中的没一个位置的字符当做当前处理的位置的选择
        for (int i = 0; i < characters.size(); i++) {

            if (!flag[characters.get(i)]){
                //移除选择过的字符
                Character remove = characters.remove(i);
                flag[remove]=true;
                //将移除过当前选择字符发集合传给下一次，并把自己的选择和之前的选择合并
                process(characters,path+remove,ans);
                //每次处理完之后要还原，不然下一次循环的时候就会缺少一个元素
                characters.add(i,remove);
            }

        }

    }

}
