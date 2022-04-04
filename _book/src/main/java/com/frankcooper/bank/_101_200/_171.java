package com.frankcooper.bank._101_200;

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


    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            handler.titleToNumber("ZY");
        }

        public int titleToNumber(String s) {
            int res = 0;
            for (int i = 0; i < s.length(); i++) {
                int c = s.charAt(i) - 'A' + 1;
                res = res * 26 + c;
            }
            return res;
        }
    }

    static class _3rd {
        public int titleToNumber(String s) {
            int res = 0;
            for (int i = 0; i < s.length(); i++) {
                int c = s.charAt(i) - 'A' + 1;
                res = res * 26 + c;
            }
            return res;
        }
    }
}
