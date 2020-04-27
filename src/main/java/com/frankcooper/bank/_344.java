package com.frankcooper.bank;

public class _344 {

    static _344 handler = new _344();

    public static void main(String[] args) {
        char[] s = {'h', 'e', 'l', 'l', 'o'};
        handler.reverseString(s);
    }

    public void reverseString(char[] s) {
        int l = 0, r = s.length - 1;
        while (l < r) {
            char c = s[l];
            s[l++] = s[r];
            s[r--] = c;
        }
    }
}
