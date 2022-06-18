package com.frankcooper.platform.leetcode.bank._401_500;

public class _424 {


    static _424 handler = new _424();

    public static void main(String[] args) {
        String s = "ABAB";
        int k = 2;
        s = "AABABBA";
        k = 1;
        handler.characterReplacement(s, k);
    }


    public int characterReplacement(String s, int k) {
        int[] arr = new int[26];
        char[] chas = s.toCharArray();
        int l = 0, r = 0, ans = 0, maxCnt = 0;
        while (r < chas.length) {
            arr[chas[r] - 'A']++;
            maxCnt = Math.max(maxCnt, arr[chas[r] - 'A']);
            r++;
            if ((r - l) > (maxCnt + k)) {
                arr[chas[l] - 'A']--;
                l++;
            }
            ans = r - l;
        }
        return ans;
    }


}
