package com.frankcooper.bank;

public class _171 {
    static class _1st {
        public int titleToNumber(String s) {
            int ans = 0;
            for (int i = 0; i < s.length(); i++) {
                int c = s.charAt(i) - 'A' + 1;
                ans = ans * 26 + c;
            }
            return ans;
        }
    }
}
