package handwriting;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    public static void main(String[] args) {
        wordPattern("abba", "dog cat cat dog");
    }

    public static boolean wordPattern(String pattern, String s) {

        Map<Character, String> map1 = new HashMap<>();
        Map<String, Character> map2 = new HashMap<>();

        int n = pattern.length();
        int l = s.length();
        int left = 0;
        int i = 0;
        for (char c : pattern.toCharArray()) {

            if (left >= l) {
                return false;
            }

            int right = left;
            while (right < l && s.charAt(right) != ' ') {
                right++;
            }
            String sub = s.substring(left, right);

            if (map1.containsKey(c) && !map1.get(c).equals(sub)) {
                return false;
            }

            if (map2.containsKey(sub) && map2.get(sub) != c) {
                return false;
            }

            map1.put(c, sub);
            map2.put(sub, c);

            left = right + 1;
            i++;
        }

        return left >= l;
    }

}

