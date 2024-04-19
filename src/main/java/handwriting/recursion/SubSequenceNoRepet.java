package handwriting.recursion;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.HashSet;
import java.util.Set;

public class SubSequenceNoRepet {

    public static void main(String[] args) {
        String s = RandomStringUtils.randomAlphabetic(3);
        System.out.println("s=" + s);
        Set<String> ans = subSequence(s);
        for (String str : ans) {
            System.out.println(str);
        }
    }

    public static Set<String> subSequence(String s) {

        Set<String> ans = new HashSet<>();

        if (s == null || s.length() == 1) {
            return ans;
        }

        process(s.toCharArray(), 0, "", ans);

        return ans;
    }

    public static void process(char[] arr, int index, String path, Set<String> ans) {

        if (index == arr.length) {
            ans.add(path);
            return;
        }
        process(arr, index + 1, path, ans);
        process(arr, index + 1, path + arr[index], ans);
    }

}
