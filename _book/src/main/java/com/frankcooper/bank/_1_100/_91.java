package com.frankcooper.bank._1_100;

import java.util.*;

import org.junit.Assert;

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
