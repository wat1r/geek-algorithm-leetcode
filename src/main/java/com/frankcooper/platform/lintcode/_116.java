package com.frankcooper.platform.lintcode;

import java.util.*;

import org.junit.Assert;

public class _116 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[] a = new int[]{2, 1, 0, 2, 3, 2, 8, 5, 7, 10, 9, 9, 6, 6, 3, 4, 2, 9, 9, 0};
            Assert.assertFalse(handler.canJump(a));

        }

        public boolean canJump(int[] a) {
            int n = a.length;
            boolean[] f = new boolean[n];
            f[0] = true;
            for (int i = 1; i < n; i++) {
                f[i] = false;
                for (int j = 0; j < i; j++) {
                    if (f[j] && j + a[j] >= i) {
                        f[i] = true;
                        break;
                    }
                }
            }
            return f[n - 1];
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
