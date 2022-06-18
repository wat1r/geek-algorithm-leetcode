package com.frankcooper.platform.leetcode.bank._601_700;

public class _665 {


    class _1st {

        public boolean checkPossibility(int[] nums) {
            int cnt = 0;
            for (int i = 1; i < nums.length; i++) {
                if (nums[i - 1] > nums[i]) {
                    cnt++;
                    if (i == 1 || nums[i] >= nums[i - 2]) {
                        nums[i - 1] = nums[i];
                    } else {
                        nums[i] = nums[i - 1];
                    }
                }
            }
            return cnt<=1;
        }

    }
}
