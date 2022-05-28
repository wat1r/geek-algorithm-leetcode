package com.frankcooper.bank._901_1000;

import java.util.Arrays;

public class _977 {


    public int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        for (int i = 0; i < n; i++) res[i] = nums[i] * nums[i];
        Arrays.sort(res);
        return res;
    }

    static class _1st {
        public int[] sortedSquares(int[] nums) {
            int n = nums.length;
            for (int i = 0; i < n; i++) {
                if (nums[i] < 0) nums[i] = -nums[i];
            }
            Arrays.sort(nums);
            for (int i = 0; i < n; i++) nums[i] *= nums[i];
            return nums;
        }
    }
}
