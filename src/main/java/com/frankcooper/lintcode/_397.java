package com.frankcooper.lintcode;

import java.util.Arrays;

public class _397 {

    public int longestIncreasingContinuousSubsequence(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        //从左到右,start
        int[] start = new int[2];
        Arrays.fill(start, 1);
        int maxStart = 1;
        for (int i = 1; i < n; i++) {
            start[i % 2] = 1;
            if (nums[i] > nums[i - 1]) {
                start[i % 2] += start[(i - 1) % 2];
            }
            maxStart = Math.max(maxStart, start[i % 2]);
        }
        //从右到做,end
        int[] end = new int[2];
        Arrays.fill(end, 1);
        int maxEnd = 1;
        for (int i = n - 2; i >= 0; i--) {
            end[i % 2] = 1;
            if (nums[i] > nums[i + 1]) {
                end[i % 2] += end[(i + 1) % 2];
            }
            maxEnd = Math.max(maxEnd, end[i % 2]);
        }

        return Math.max(maxStart, maxEnd);
    }

}
