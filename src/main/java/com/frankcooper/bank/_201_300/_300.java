package com.frankcooper.bank._201_300;

//300. 最长上升子序列 300. Longest Increasing Subsequence Medium
public class _300 {
    static _300 handler = new _300();

    public static void main(String[] args) {
        handler.lengthOfLISII(new int[]{10, 9, 2, 5, 3, 7, 101, 18});
        handler.lengthOfLISII(new int[]{-2, -1});
    }

    /*
    方法1：DP
    见题解
    https://leetcode-cn.com/problems/longest-consecutive-sequence/solution/tao-lu-jie-jue-zui-chang-zi-xu-lie-deng-yi-lei-wen/
     */
    public int lengthOfLIS(int[] nums) {
        //dp[i]: 到i为止 (对于所有 j in [0, i], 记录max length of increasing subsequence
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        int[] dp = new int[len];
        int res = 0;
        for (int i = 0; i < len; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                //i 位置的数与[0,i]位置之间的数比较，如果大于进逻辑
                if (nums[i] > nums[j]) {
                    //等于dp[i]或者dp[j] + 1（j对应的值比i小）的最大值
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    public int lengthOfLISII(int[] nums) {
        int n = nums.length;
        int[] tails = new int[n];
        int end = 0;
        for (int i = 0; i < n; i++) {
            int l = 0, r = end;
            while (l < r) {
                int m = (l + r) / 2;
                if (tails[m] < nums[i]) l = m + 1;
                else r = m;
            }
            tails[l] = nums[i];
            if (end == r) end++;
        }
        return end;
    }


    static class _3rd {
        public static void main(String[] args) {

        }


        public int lengthOfLIS(int[] nums) {
            int n = nums.length;
            int[] f = new int[n];
            int res = 0;
            for (int i = 0; i < n; i++) {
                f[i] = 1;
                for (int j = 0; j < i; j++) {
                    if (nums[i] > nums[j]) {
                        f[i] = Math.max(f[i], f[j] + 1);
                    }
                }
                res = Math.max(res, f[i]);
            }
            return res;
        }
    }


    static class _4th {

        public static void main(String[] args) {

        }

        public int lengthOfLIS(int[] nums) {
            int[] tails = new int[nums.length];
            int res = 0;
            for (int x : nums) {
                int l = 0, r = res;
                while (l < r) {
                    int mid = l + r >> 1;
                    if (tails[mid] < x) l = mid + 1;
                    else r = mid;
                }
                tails[l] = x;
                if (res == r) res++;
            }
            return res;
        }
    }


}
