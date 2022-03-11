package com.frankcooper.bank._1001_1500;

import java.util.TreeMap;

public class _1438 {


    static class _1st {
        public int longestSubarray(int[] nums, int limit) {
            int n = nums.length, l = 0, ans = 0, r = 0;
            TreeMap<Integer, Integer> map = new TreeMap<>();
            while (r < n) {
                map.put(nums[r], map.getOrDefault(nums[r], 0) + 1);
                while (map.lastKey() - map.firstKey() > limit) {
                    map.put(nums[l], map.get(nums[l]) - 1);
                    if (map.get(nums[l]) == 0) {
                        map.remove(nums[l]);
                    }
                    l++;
                }
                ans = Math.max(ans, r - l + 1);
                r++;
            }
            return ans;
        }
    }

}
