package com.frankcooper.platform.leetcode.bank.bi_weekly;

import java.util.Arrays;

public class BiWeek41 {

    static BiWeek41 handler = new BiWeek41();

    public static void main(String[] args) {

        String allowed = "ab";
        String[] words = {"ad", "bd", "aaab", "baa", "badab"};
//        handler.countConsistentStrings(allowed, words);

        int[] nums = {2, 3, 5};
        handler.getSumAbsoluteDifferences(nums);

    }

    /**
     * 1684
     */
    public int countConsistentStrings(String allowed, String[] words) {
        int ans = 0;
        int[] arr = new int[26];
        for (char c : allowed.toCharArray()) arr[c - 'a']++;
        for (String word : words) {
            boolean f = true;
            for (char c : word.toCharArray()) {
                if (arr[c - 'a'] == 0) {
                    f = false;
                    break;
                }
            }
            if (f) ans++;
        }
        return ans;
    }


    /**
     *1685
     */
    public int[] getSumAbsoluteDifferences(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        int[] pre = new int[n + 1];
        for (int i = 0; i < n; i++) pre[i + 1] = pre[i] + nums[i];
        for (int i = 0; i < n; i++) {
            res[i] = (nums[i] * i - pre[i]) + (pre[n] - pre[i + 1] - nums[i] * (n - i - 1));
        }
        return res;
    }


    /**
     *1686
     */
    public int stoneGameVI(int[] A, int[] B) {
        int n = A.length;
        int[][] sum = new int[n][3];
        for (int i = 0; i < n; i++) sum[i] = new int[]{A[i] + B[i], A[i], B[i]};
        Arrays.sort(sum, (o1, o2) -> o2[0] - o1[0]);
        int a = 0, b = 0;
        for (int i = 0; i < n; i++) {
            if ((i & 1) == 0) a += sum[i][1];
            else b += sum[i][2];
        }
        return Integer.compare(a, b);
    }





}
