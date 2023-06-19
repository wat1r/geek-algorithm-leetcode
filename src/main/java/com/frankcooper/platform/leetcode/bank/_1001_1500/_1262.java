package com.frankcooper.platform.leetcode.bank._1001_1500;

import java.util.Arrays;

/**
 * @Date 2020/9/12
 * @Author Frank Cooper
 * @Description
 */
public class _1262 {


    static class _1st {
        public static void main(String[] args) {

        }


        public int maxSumDivThree(int[] nums) {
            int[] dp = {0, Integer.MIN_VALUE, Integer.MIN_VALUE};
            int[] arr = new int[3];
            for (int num : nums) {
                int mod = num % 3;
                for (int j = 0; j < 3; j++) {
                    arr[(j + mod) % 3] = Math.max(dp[(j + mod) % 3], dp[j] + num);
                }
                dp = Arrays.copyOf(arr, 3);
            }
            return dp[0];
        }
    }


    static class _2nd {
        public static void main(String[] args) {

        }

        Integer[][] cache = null;


        public int maxSumDivThree(int[] nums) {
            int n = nums.length;
            cache = new Integer[n][3];
            return dfs(nums, n - 1, 0);
        }


        /**
         * @param nums
         * @param i
         * @param d    已经选的数之和mod 3
         * @return
         */
        public int dfs(int[] nums, int i, int d) {
            if (i < 0) {
                return d == 0 ? 0 : Integer.MIN_VALUE;
            }
            if (cache[i][d] != null) {
                return cache[i][d];
            }
            return cache[i][d] = Math.max(dfs(nums, i - 1, d),
                    dfs(nums, i - 1, (d + nums[i]) % 3) + nums[i]);
        }

    }

    static class _3rd {
        public int maxSumDivThree(int[] nums) {
            int n = nums.length;
            int[][] f = new int[n + 1][3];
            f[0][1] = f[0][2] = Integer.MIN_VALUE;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j <= 2; j++) {
                    f[i + 1][j] = Math.max(f[i][j], f[i][(j + nums[i]) % 3] + nums[i]);
                }
            }
            return f[n][0];
        }
    }
}
