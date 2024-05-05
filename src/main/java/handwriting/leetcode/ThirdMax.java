package handwriting.leetcode;

import java.util.TreeSet;

public class ThirdMax {

    public static void main(String[] args) {
        thirdMax(new int[]{1, 2});
    }

    public static int thirdMax(int[] nums) {

        Integer max = null;
        Integer second = null;
        Integer third = null;

        for (int i = 0; i < nums.length; i++) {

            if ((max != null && nums[i] == max) || (second != null && nums[i] == second) || (third != null && nums[i] == third)) {
                continue;
            }

            if (max == null) {
                max = nums[i];
                continue;
            }

            if (nums[i] > max) {
                third = second;
                second = max;
                max = nums[i];
                continue;
            }

            if (second == null) {
                second = nums[i];
                continue;
            }

            if (nums[i] > second) {
                third = second;
                second = nums[i];
                continue;
            }

            if (third == null) {
                third = nums[i];
                continue;
            }

            if (nums[i] > third) {
                third = nums[i];
            }

        }

        return third == null ? max : third;
    }

    public int thirdMax2(int[] nums) {
        TreeSet<Integer> s = new TreeSet<Integer>();
        for (int num : nums) {
            s.add(num);
            if (s.size() > 3) {
                s.remove(s.first());
            }
        }
        return s.size() == 3 ? s.first() : s.last();
    }


}
