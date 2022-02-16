package com.frankcooper.bank._1_100;

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


    static class _1st {
        public int maxSubArray(int[] nums) {
            int cur = nums[0], res = cur;
            for (int i = 1; i < nums.length; i++) {
                cur = Math.max(nums[i], nums[i] + cur);
                res = Math.max(res, cur);
            }
            return res;
        }
    }
}
