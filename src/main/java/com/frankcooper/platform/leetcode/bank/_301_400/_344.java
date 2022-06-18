package com.frankcooper.platform.leetcode.bank._301_400;

public class _344 {

    static _344 handler = new _344();

    public static void main(String[] args) {
        char[] s = {'h', 'e', 'l', 'l', 'o'};
        handler.reverseString(s);
        System.out.println("");
        System.out.println("");
    }

    public void reverseString(char[] s) {
        int l = 0, r = s.length - 1;
        while (l < r) {
            char c = s[l];
            s[l++] = s[r];
            s[r--] = c;
        }
    }


    static class _1st {
        public void reverseString(char[] s) {
            int l = 0, r = s.length - 1;
            while (l < r) {
                char t = s[l];
                s[l++] = s[r];
                s[r--] = t;
            }
        }
    }

    static class _2nd {
        public void reverseString(char[] s) {
            int n = s.length, l = 0, r = n - 1;
            while (l < r) {
                char t = s[l];
                s[l++] = s[r];
                s[r--] = t;
            }
        }
    }
}
