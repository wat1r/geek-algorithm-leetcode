package com.frankcooper.lintcode;

import java.util.*;

import org.junit.Assert;

public class _568 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[] nums = {0, 3, 1, 0, 6, 3};
            nums = new int[]{1, 2};
            nums = new int[]{5, 9, -1, 7, 1, 9, -9, -9, -4, -2};
            nums = new int[]{3, -4, -1, -5, 4, 6, 6, 7, -10, -5};
            handler.threeChances(nums);

        }


        public int threeChances(int[] nums) {

            int k = 3, j = 1, res = 0;
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                if (map.containsKey(nums[i])) {
                    k++;
                }
                while (k != 0 && j < nums.length) {
                    if (nums[j] == nums[i]) j++;
                    else {
                        map.put(nums[j], map.getOrDefault(nums[j], 0) + 1);
                        j++;
                        k--;
                    }
                }
                while (j < nums.length && nums[j] == nums[i]) j++;
                if (k >= 0) res = Math.max(res, j - i);
            }
            return res;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
