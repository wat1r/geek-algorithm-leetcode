package com.frankcooper.bank;

import java.util.HashMap;
import java.util.Map;

public class _416 {

    static class _1st {
        /**
         * 回溯  记忆化 递归 可以通过，不记忆化 会报LTE
         */


        Map<String, Boolean> cache = new HashMap<>();

        public boolean canPartition(int[] nums) {
            int sum = 0;
            for (int num : nums) sum += num;
            if (sum % 2 != 0) return false;
            sum /= 2;
            return dfs(nums, 0, sum);
        }

        private boolean dfs(int[] nums, int idx, int target) {
            // System.out.printf("%d\n",idx);
            if (target < 0 || idx >= nums.length) return false;
            if (target == 0) return true;
            String key = idx + "#" + target;
            if (cache.containsKey(key)) return cache.get(key);
            boolean res = dfs(nums, idx + 1, target - nums[idx]) || dfs(nums, idx + 1, target);
            cache.put(key, res);
            return res;

        }
    }


    static class _2nd {
        /*一维dp*/
        public boolean canPartition(int[] nums) {
            int s = 0;
            for (int num : nums) s += num;
            if (s % 2 == 1) return false;
            s /= 2;
            boolean[] f = new boolean[s + 1];
            f[0] = true;
            for (int num : nums) {
                for (int j = s; j >= 0; j--) {
                    if (j >= num) {
                        f[j] = f[j] || f[j - num];
                    }
                }
            }
            return f[s];
        }
    }

    static class _3rd {

    }
}
