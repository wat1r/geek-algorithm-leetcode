package com.frankcooper.bank;

public class _1423 {

    static _1423 handler = new _1423();

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 1};
        int k = 3;
        handler.maxScore(nums, k);
    }

    public int maxScore(int[] nums, int k) {
        int n = nums.length;
        int l = 0, r = n - k - 1;
        int totalSum = 0;
        int currSum = 0;
        for (int i = 0; i < n; i++) {
            if (i <= r) currSum += nums[i];
            totalSum += nums[i];
        }
        int minSum = currSum;
        while (r < n - 1) {
            currSum = currSum - nums[l++] + nums[++r];
            minSum = Math.min(minSum, currSum);
        }
        return totalSum - minSum;
    }

}
