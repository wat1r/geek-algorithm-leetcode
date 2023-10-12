package com.frankcooper.platform.leetcode.bank._2001_3000;

import java.util.*;

import org.junit.Assert;

public class _2578 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
//            handler.splitNum(4325);
//            handler.splitNum(687);
//            handler.splitNum(1020);
            handler.splitNum(19);

        }

        public int splitNum(int num) {
            List<Integer> l = new ArrayList<>();
            while (num != 0) {
                int t = num % 10;
                l.add(t);
                num /= 10;
            }
            l.sort((a, b) -> a - b);
            int res = 0;
            int carry = 0;
            int b = 0;
            int lower = l.size() % 2 == 0 ? 0 : 1;
            for (int i = l.size() - 1; i >= lower; i -= 2) {
                int t = l.get(i) + l.get(i - 1) + carry;
                res += (t % 10) * Math.pow(10, b++);
                t /= 10;
                carry = t;
            }
            if (l.size() % 2 == 1) {
                res += (l.get(0) + carry) * Math.pow(10, b);
            } else {
                res += (carry) * Math.pow(10, b);
            }
            return res;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            handler.splitNum(4325);
        }

        public int splitNum(int num) {
            char[] s = Integer.toString(num).toCharArray();
            Arrays.sort(s);
            int[] a = new int[2];
            for (int i = 0; i < s.length; i++) {
                a[i % 2] = a[i % 2] * 10 + s[i] - '0';
            }
            return a[0] + a[1];
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
