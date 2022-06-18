package com.frankcooper.platform.leetcode.bank.bi_weekly;

public class BiWeek29 {


    public static void main(String[] args) {
        _3rd handler = new _3rd();
        handler.longestSubarray(new int[]{0, 1, 1, 1, 0, 1, 1, 0, 1});
    }


    /**
     * 1491
     */
    static class _1st {
        public double average(int[] salary) {
            int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
            int sum = 0;
            for (int s : salary) {
                max = Math.max(max, s);
                min = Math.min(min, s);
                sum += s;
            }
            return (double) (sum - max - min) / (salary.length - 2);
        }
    }


    /**
     * 1492
     */
    static class _2nd {
        public int kthFactor(int n, int k) {
            for (int i = 1; i <= n; i++) {
                if (n % i == 0) k--;
                if (k == 0) return i;
            }
            return -1;
        }
    }


    /**
     * 1493
     */
    static class _3rd {
        public int longestSubarray(int[] nums) {
            int ans = 0;
            for (int r = 0, l = 0, zero = 0; r < nums.length; r++) {
                if (nums[r] == 0) zero++;
                while (zero > 1) {
                    if (nums[l++] == 0) zero--;
                }
                ans = Math.max(ans, r - l);
            }
            return ans;
        }
    }

}
