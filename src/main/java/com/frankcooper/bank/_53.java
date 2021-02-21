package com.frankcooper.bank;

public class _53 {


    public static void main(String[] args) {
        _53 handler = new _53();
        handler.maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4});
    }

    public int maxSubArray(int[] nums) {
        int currSum = nums[0], maxSum = currSum;
        for (int i = 1; i < nums.length; i++) {
            currSum = Math.max(nums[i], currSum + nums[i]);
            maxSum = Math.max(maxSum, currSum);
        }
        return maxSum;
    }

}
