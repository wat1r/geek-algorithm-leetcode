package com.frankcooper.bank;

import java.util.Arrays;

public class _673 {


    static _673 handler = new _673();

    public static void main(String[] args) {
        handler.findNumberOfLIS(new int[]{1, 3, 5, 4, 7});
    }


    public int findNumberOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int[] dp = new int[n];
        int[] counter = new int[n];
        Arrays.fill(dp, 1);
        Arrays.fill(counter, 1);
        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    if (dp[j] + 1 > dp[i]) {
                        dp[i] = Math.max(dp[i], dp[j] + 1);
                        counter[i] = counter[j];
                    } else if (dp[j] + 1 == dp[i]) {
                        counter[i] += counter[j];
                    }
                }
            }
            max = Math.max(max, dp[i]);
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (dp[i] == max) res += counter[i];
        }
        return res;
    }


}
