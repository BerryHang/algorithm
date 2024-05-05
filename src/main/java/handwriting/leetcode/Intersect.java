package handwriting.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Intersect {

    public static void main(String[] args) {
        int[] ans = intersect(new int[]{1, 2, 2, 1}, new int[]{2, 2});

        System.out.printf("" + ans);
    }

    public static int[] intersect(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return intersect(nums2, nums1);
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums1) {
            int count = map.getOrDefault(num, 0) + 1;
            map.put(num, count);
        }

        int index = 0;
        for (int num : nums2) {
            int count = map.getOrDefault(num, 0);
            if (count > 0) {
                nums2[index++] = num;
                count--;
                if (count > 0) {
                    map.put(num, count);
                } else {
                    map.remove(num);
                }
            }
        }
        return Arrays.copyOfRange(nums2, 0, index);
    }

}
