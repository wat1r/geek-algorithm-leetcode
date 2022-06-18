package com.frankcooper.platform.leetcode.bank._1_100;

public class _91 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        public int numDecodings(String s) {
            if (s == null || s.charAt(0) == '0') return 0;
            int n = s.length();
            char[] chas = s.toCharArray();
            int[] f = new int[n + 1];
            f[0] = f[1] = 1;
            for (int i = 2; i <= n; i++) {
                if (chas[i - 1] != '0') f[i] += f[i - 1];
                if (chas[i - 2] == '1' || chas[i - 2] == '2' && chas[i - 1] <= '6') f[i] += f[i - 2];
            }
            return f[n];
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        public int numDecodings(String s) {
            int n = s.length();
            s = " " + s;
            char[] cs = s.toCharArray();
            int[] f = new int[n + 1];
            f[0] = 1;
            for (int i = 1; i <= n; i++) {
                // a : 代表「当前位置」单独形成 item
                // b : 代表「当前位置」与「前一位置」共同形成 item
                int a = cs[i] - '0', b = (cs[i - 1] - '0') * 10 + (cs[i] - '0');
                // 如果 a 属于有效值，那么 f[i] 可以由 f[i - 1] 转移过来
                if (1 <= a && a <= 9) f[i] = f[i - 1];
                // 如果 b 属于有效值，那么 f[i] 可以由 f[i - 2] 或者 f[i - 1] & f[i - 2] 转移过来
                if (10 <= b && b <= 26) f[i] += f[i - 2];
            }
            return f[n];
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
