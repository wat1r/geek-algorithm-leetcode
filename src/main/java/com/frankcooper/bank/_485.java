package com.frankcooper.bank;

public class _485 {


    public int findMaxConsecutiveOnes(int[] nums) {
        int ans = 0, cnt = 0;
        for (int l = 0; l < nums.length; l++) {
            if (nums[l] == 1) cnt++;
            else {
                ans = Math.max(ans, cnt);
                cnt = 0;
            }
        }
        return Math.max(ans, cnt);


    }

}
