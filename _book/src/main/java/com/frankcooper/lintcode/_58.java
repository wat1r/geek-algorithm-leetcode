package com.frankcooper.lintcode;

import java.util.*;

import org.junit.Assert;

public class _58 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        public List<List<Integer>> fourSum(int[] nums, int target) {
            List<List<Integer>> res = new ArrayList<>();
            Arrays.sort(nums);
            int n = nums.length;
            for (int i = 0; i < n - 3; i++) {
                if (i > 0 && nums[i] == nums[i - 1]) continue;//三个continue去重
                for (int j = i + 1; j < n - 2; j++) {
                    if (j > i + 1 && nums[j] == nums[j - 1]) continue;
                    for (int l = j + 1, r = n - 1; l < r; l++) {
                        if (l > j + 1 && nums[l] == nums[l - 1]) continue;
                        while (l < r && nums[i] + nums[j] + nums[l] + nums[r] > target) {
                            r--;
                        }
                        if (l >= r) break;//当l比r大的时候，提桥break掉
                        if (nums[i] + nums[j] + nums[l] + nums[r] == target) {
                            List<Integer> tList = new ArrayList<Integer>();
                            tList.add(nums[i]);
                            tList.add(nums[j]);
                            tList.add(nums[l]);
                            tList.add(nums[r]);
                            res.add(tList);
                        }
                    }
                }
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
