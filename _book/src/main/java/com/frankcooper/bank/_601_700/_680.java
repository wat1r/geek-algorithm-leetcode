package com.frankcooper.bank._601_700;

/**
 * @Date 2020/5/19
 * @Author Frank Cooper
 * @Description
 */
public class _680 {

    public boolean validPalindrome(String s) {
        if (s == null || s.length() == 0) return false;
        char[] chas = s.toCharArray();
        int l = 0, r = chas.length-1;
        while (l < r) {
            if (chas[l] != chas[r]) {
                return validate(chas, l + 1, r) || validate(chas, l, r - 1);
            }
            l++;
            r--;
        }
        return true;
    }

    private boolean validate(char[] chas, int l, int r) {
        while (l < r) {
            if (chas[l++] != chas[r--]) return false;
        }
        return true;
    }
}
