package com.frankcooper.bank._301_400;

/**
 * @Date 2020/9/8
 * @Author Frank Cooper
 * @Description
 */
public class _392 {

    public boolean isSubsequence(String s, String t) {
        int j = 0;
        for (int i = 0; i < t.length() && j < s.length(); i++) {
            if (s.charAt(j) == t.charAt(i)) {
                j++;
            }
        }
        return j == s.length();
    }


}
