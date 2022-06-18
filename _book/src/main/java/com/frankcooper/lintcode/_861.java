package com.frankcooper.platform.lintcode;

import java.util.*;

import org.junit.Assert;

/**
 * 同 lc 683
 */

public class _861 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[] f = new int[]{1, 3, 2};
            int k = 1;
            handler.kEmptySlots(f, k);


        }

        public int kEmptySlots(int[] flowers, int k) {
            TreeSet<Integer> ts = new TreeSet<>();
            for (int i = 0; i < flowers.length; i++) {
                Integer lower = ts.lower(flowers[i]);
                Integer higher = ts.higher(flowers[i]);
                ts.add(flowers[i]);
                if (lower != null && flowers[i] - lower - 1 == k) return i + 1;
                if (higher != null && higher - flowers[i] - 1 == k) return i + 1;
            }
            return -1;
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
