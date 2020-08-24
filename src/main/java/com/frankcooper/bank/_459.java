package com.frankcooper.bank;

/**
 * @Date 2020/8/24
 * @Author Frank Cooper
 * @Description
 */
public class _459 {
    static _459 handler = new _459();

    public static void main(String[] args) {
        String s = "abcabcabcabc";
        s = "aba";
        s = "abab";
        s = "abaababaab";
//        handler.repeatedSubstringPattern(s);
    }


//    public boolean repeatedSubstringPattern(String s) {
//        if (s == null || s.length() <= 1) return false;
//        StringBuilder seed = new StringBuilder();
//        int n = s.length();
//        int i;
//        for (i = 0; i < n; i++) {
//            if (seed.toString().contains(String.valueOf(s.charAt(i)))) break;
//            seed.append(s.charAt(i));
//        }
//        for (int j = i; j < n; j += i) {
//            if ((j + i) >= n || !s.substring(j, j + i).equals(seed.toString())) return false;
//        }
//        return true;
//    }
}
