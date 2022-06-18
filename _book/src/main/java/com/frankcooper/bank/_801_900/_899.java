package com.frankcooper.platform.leetcode.bank._801_900;

import java.util.*;

import org.junit.Assert;

public class _899 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            String s = "baaca";
            int k = 3;
            handler.orderlyQueue(s, k);

        }


        public String orderlyQueue(String s, int k) {
            if (k == 1) return getMin(s);
            char[] ch = s.toCharArray();
            Arrays.sort(ch);
            return String.valueOf(ch);
        }

        public String getMin(String s) {
            int n = s.length();
            s = s + s;
            int i = 0, j = 1;
            while (i < n && j < n) {
                int p = 0;
                while (p < n && s.charAt(i + p) == s.charAt(j + p)) p++;
                if (s.charAt(i + p) > s.charAt(j + p)) {
                    i += p + 1;
                } else {
                    j += p + 1;
                }
                if (i == j) j++;
            }
            int q = Math.min(i, j);
            return s.substring(q, q + n);
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
