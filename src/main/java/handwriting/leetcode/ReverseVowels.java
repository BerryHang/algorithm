package handwriting.leetcode;

public class ReverseVowels {

    public static void main(String[] args) {
        reverseVowels(".,");
    }

    final static String TARGET = "aeiouAEIOU";

    public static String reverseVowels(String s) {

        int left = 0;
        int right = s.length() - 1;

        char[] charArray = s.toCharArray();

        while (left < right) {

            while (left < s.length() && TARGET.indexOf(charArray[left])<0) {
                left++;
            }

            while (right >= 0 && TARGET.indexOf(charArray[right])<0) {
                right--;
            }

            if (left >= right) {
                break;
            }

            char temp = charArray[left];
            charArray[left] = charArray[right];
            charArray[right] = temp;
            left++;
            right--;
        }

        return String.valueOf(charArray);
    }

}
