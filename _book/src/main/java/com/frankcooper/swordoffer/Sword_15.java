package com.frankcooper.platform.leetcode.swordoffer;

import java.util.*;

import org.junit.Assert;

public class Sword_15 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }

        public int hammingWeight(int n) {
            int ans = 0;
            while (n != 0) {
                if ((n & 1) == 1) ans++;
                n >>>= 1;
            }
            return ans;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }


        public int hammingWeight(int n) {
            int res = 0;
            while (n != 0) {
                res++;
                n -= n & -n;
            }
            return res;

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
