package com.frankcooper.platform.leetcode.bank._401_500;

public class _481 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

            handler.magicalString(6);
        }


        public int magicalString(int n) {
            StringBuilder sb = new StringBuilder();
            sb.append(1);
            int idx = 1;
            while (sb.length() < n) {
                if (idx == sb.length()) {
                    sb.append(sb.charAt(sb.length() - 1) == '1' ? 22 : 1);
                    idx++;
                } else {
                    if (sb.charAt(sb.length() - 1) == '1') {
                        sb.append(sb.charAt(idx++) == '1' ? 2 : 22);
                    } else {
                        sb.append(sb.charAt(idx++) == '1' ? 1 : 11);
                    }
                }
            }
            int res = 0;
            for (int i = 0; i < n; i++) {
                if (sb.charAt(i) == '1') res++;
            }
            return res;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
