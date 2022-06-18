package com.frankcooper.platform.leetcode.bank._401_500;

import java.util.Arrays;

/**
 * @Date 2020/9/1
 * @Author Frank Cooper
 * @Description
 */
public class _486 {
    static _486 handler = new _486();

    public static void main(String[] args) {
        int[] nums = {1, 5, 233, 7};
//        handler.PredictTheWinner1st(nums);
//        handler.PredictTheWinner4th(nums);
        handler.PredictTheWinner(nums);


    }


    int[][] memo;

    public boolean PredictTheWinner(int[] nums) {
        if (nums == null || nums.length == 0) return false;
        int n = nums.length;
        memo = new int[n][n];
        for (int i = 0; i < n; i++) Arrays.fill(memo[i], Integer.MIN_VALUE);
        return dfs(nums, 0, n - 1) >= 0;
    }

    private int dfs(int[] nums, int left, int right) {
        if (left == right) return nums[left];
        if (memo[left][right] != Integer.MIN_VALUE) return memo[left][right];
        int pickLeft = nums[left] + (-dfs(nums, left + 1, right));
        int pickRight = nums[right] + (-dfs(nums, left, right - 1));
        memo[left][right] = Math.max(pickLeft, pickRight);
        return memo[left][right];
    }


    public boolean PredictTheWinner4th(int[] nums) {
        if (nums == null || nums.length == 0) return false;
        int n = nums.length;
        int[][] dp = new int[n][n];
        int turn = n & 1;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            if (turn == 1) dp[i][i] = nums[i];
            sum += nums[i];
        }
        for (int step = 2; step <= n; step++) {
            turn ^= 1;
            for (int i = 0; i < n - step + 1; i++) {
                int j = i + step - 1;
                if (turn == 1) dp[i][j] = Math.max(nums[i] + dp[i + 1][j], nums[j] + dp[i][j - 1]);
                else dp[i][j] = Math.min(dp[i + 1][j], dp[i][j - 1]);
            }
        }
        return 2 * dp[0][n - 1] >= sum;
    }


    public boolean PredictTheWinner1st(int[] nums) {
        if (nums == null || nums.length == 0) return false;
        int n = nums.length;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) dp[i][i] = nums[i];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                dp[i][j] = Math.max(nums[i] + (-dp[i + 1][j]), nums[j] + (-dp[i][j - 1]));
            }
        }
        return dp[0][n - 1] >= 0;
    }


    public boolean PredictTheWinner2nd(int[] nums) {
        if (nums == null || nums.length == 0) return false;
        int n = nums.length;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) dp[i][i] = nums[i];
        for (int step = 2; step <= n; step++) {
            for (int i = 0; i < n - step + 1; i++) {
                int j = i + step - 1;
                System.out.println(String.format("step:%d,i:%d,j:%d", step, i, j));
                dp[i][j] = Math.max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1]);
            }
        }
        return dp[0][n - 1] >= 0;
    }


    public boolean PredictTheWinner3rd(int[] nums) {
        if (nums == null || nums.length == 0) return false;
        int n = nums.length;
        int[][] dp = new int[n][n];
//        for (int i = 0; i < n; i++) dp[i][i] = nums[i];
        for (int step = 1; step <= n; step++) {
            for (int i = 0; i < n - step + 1; i++) {
                int j = i + step - 1;
                if (i == j) {
                    dp[i][i] = nums[i];
                    continue;
                }
                System.out.println(String.format("step:%d,i:%d,j:%d", step, i, j));
                dp[i][j] = Math.max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1]);
            }
        }
        return dp[0][n - 1] >= 0;
    }

}
