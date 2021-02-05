package com.frankcooper.bank;

public class _1208 {


    static _1208 handler = new _1208();

    public static void main(String[] args) {

        String s = "abcd", t = "bcdf";
        int cost = 3;
        s = "abcd";
        t = "cdef";
        cost = 3;
        handler.equalSubstring(s, t, cost);

    }


    public int equalSubstring(String s, String t, int maxCost) {
        int i = 0, j = 0, ans = 0, currCost = 0;
        while (j < s.length()) {
            currCost += Math.abs(s.charAt(j) - t.charAt(j));
            while (currCost > maxCost) currCost -= Math.abs(s.charAt(i) - t.charAt(i++));
            if (currCost <= maxCost) ans = Math.max(ans, j++ - i + 1);
        }
        return ans;
    }


}
