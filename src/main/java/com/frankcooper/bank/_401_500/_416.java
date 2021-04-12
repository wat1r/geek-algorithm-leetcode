package com.frankcooper.bank._401_500;

import org.junit.Assert;

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
        public static void main(String[] args) {
            _3rd handler = new _3rd();
            int[] nums = new int[]{1, 5, 11, 5};
            Assert.assertTrue(handler.canPartition(nums));
            nums = new int[]{1, 2, 3, 5};
            Assert.assertFalse(handler.canPartition(nums));
            nums = new int[]{1, 2, 3, 6};
            Assert.assertTrue(handler.canPartition(nums));
        }

        public boolean canPartition(int[] nums) {
            int N = nums.length;
            int sum = 0;
            for (int i : nums) sum += i;
            if (sum % 2 == 1) return false;
            int T = sum / 2;
            boolean[][] dp = new boolean[N][T + 1];
            for (int i = 0; i < N; i++) dp[i][0] = true;
            for (int j = 0; j <= T; j++) if (j == nums[0]) dp[0][j] = true;
            for (int i = 1; i < N; i++) {
                for (int j = 1; j <= T; j++) {
                    dp[i][j] = dp[i - 1][j];
                    if (j >= nums[i]) dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i]];
                }
            }
//            PrintUtils.printMatrix(dp);
            return dp[N - 1][T];
        }

    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
            int[] nums = new int[]{1, 5, 11, 5};
            Assert.assertTrue(handler.canPartition(nums));
            nums = new int[]{1, 2, 3, 5};
            Assert.assertFalse(handler.canPartition(nums));
            nums = new int[]{1, 2, 3, 6};
            Assert.assertTrue(handler.canPartition(nums));
        }


        public boolean canPartition(int[] nums) {
            int N = nums.length;
            int sum = 0;
            for (int i : nums) sum += i;
            if (sum % 2 == 1) return false;
            int T = sum / 2;
            boolean[] dp = new boolean[T + 1];
            dp[0] = true;
            for (int num : nums) {
/*                for (int j = T; j >= 0; j--) {
                    if (j >= num) dp[j] = dp[j] || dp[j - num];
                }*/
                for (int j = T; j >= num; j--) {
                    dp[j] = dp[j] || dp[j - num];
                }

            }
//            PrintUtils.printMatrix(dp);
            return dp[T];
        }

    }
}
