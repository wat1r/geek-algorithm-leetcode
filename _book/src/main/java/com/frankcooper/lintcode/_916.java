package com.frankcooper.platform.lintcode;

/**
 * @Date 2020/6/16
 * @Author Frank Cooper
 * @Description 同leetcode 266
 */
public class _916 {


    public boolean canPermutePalindrome(String s) {
        if (s == null || s.length() == 0) return true;
        char[] chas = s.toCharArray();
        int n = chas.length;
        int[] helper = new int[26];
        for (char c : chas) helper[c - 'a']++;
        int count = 0;
        for (int i : helper) {
            if ((i & 1) == 1 && ++count > 1) return false;
        }
        return true;
    }


}
