package com.frankcooper.platform.leetcode.bank._201_300;

import java.util.*;

import org.junit.Assert;

public class _275 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        public int hIndex(int[] cs) {
            if (cs == null || cs.length == 0) return 0;
            int len = cs.length, l = 0, r = len - 1;
            while (l < r) {
                int mid = l + (r - l) / 2;
                if (cs[mid] < (len - mid)) {
                    l = mid + 1;
                } else {
                    r = mid;
                }
            }
            return cs[l] >= (len - l) ? (len - l) : 0;
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
