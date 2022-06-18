package com.frankcooper.platform.leetcode.bank._1501_2000;

import java.util.*;

import org.junit.Assert;

public class _1823 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }


        public int findTheWinner(int n, int k) {
            List<Integer> list = new ArrayList<>();
            for (int i = 1; i <= n; i++) list.add(i);
            int index = 0;
            while (list.size() > 1) {
                index = (index + k - 1) % list.size();
                list.remove(index);
            }
            return list.get(0);
        }
    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        public int findTheWinner(int n, int k) {
            int x = 0;
            for (int i = 2; i <= n; i++) {
                x = (x + k) % i;
            }
            return x;
        }


    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
            Assert.assertEquals(3, handler.findTheWinner(5, 2));
        }

        public int findTheWinner(int n, int k) {
            int[] f = new int[n + 1];
            f[0] = 0;
            for (int i = 1; i <= n; i++) {
                f[i] = (f[i - 1] + k) % i;
            }
            return f[n] + 1;
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }


        public int findTheWinner(int n, int k) {
            int x = 0;
            for (int i = 2; i <= n; i++) {
                x = (x + k) % i;
            }
            return x + 1;
        }
    }
}
