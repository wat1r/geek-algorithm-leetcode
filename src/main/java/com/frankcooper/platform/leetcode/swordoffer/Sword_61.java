package com.frankcooper.platform.leetcode.swordoffer;

import java.util.*;

public class Sword_61 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        public boolean isStraight(int[] nums) {
            Set<Integer> set = new HashSet<>();
            int maxx = 0, minx = 14;
            for (int x : nums) {
                if (x == 0) continue;
                maxx = Math.max(maxx, x);
                minx = Math.min(minx, x);
                if (set.contains(x)) return false;
                set.add(x);
            }
            return maxx - minx < 5;
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
