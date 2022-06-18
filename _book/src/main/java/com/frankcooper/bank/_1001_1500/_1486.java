package com.frankcooper.platform.leetcode.bank._1001_1500;

import java.util.*;

import org.junit.Assert;

public class _1486 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }

        public int xorOperation(int n, int start) {
            int[] arr = new int[n];
            int ans = 0;
            for (int i = 0; i < n; i++) {
                arr[i] = start + 2 * i;
                ans ^= arr[i];
            }
            return ans;
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
