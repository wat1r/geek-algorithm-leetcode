package com.frankcooper.bank._401_500;

/**
 * LintCode 883
 */
public class _487 {
    public int findMaxConsecutiveOnes(int[] nums) {

        int ans = 0;
        for (int r = 0, l = 0, zero = 0; r < nums.length; r++) {
            if (nums[r] == 0) zero++;
            while (zero > 1) {
                if (nums[l++] == 0) zero--;
                ans = Math.max(ans, r - l + 1);
            }
        }
        return ans;
    }


}
