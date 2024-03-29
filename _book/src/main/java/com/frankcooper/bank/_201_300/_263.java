package com.frankcooper.platform.leetcode.bank._201_300;

import java.util.*;
import org.junit.Assert;
public class _263 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }

        public boolean isUgly(int n) {
            if (n <= 0) return false;
            int[] factors = {2, 3, 5};
            for (int f : factors) {
                while (n % f == 0) {
                    n /= f;
                }
            }
            return n == 1;
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
