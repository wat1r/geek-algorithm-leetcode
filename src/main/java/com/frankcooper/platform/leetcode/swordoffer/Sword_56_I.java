package com.frankcooper.platform.leetcode.swordoffer;

public class Sword_56_I {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[] nums = new int[]{4, 1, 4, 6};
            handler.singleNumbers(nums);

        }

        public int[] singleNumbers(int[] nums) {
            int t = 0;
            for (int x : nums) t ^= x;
            int diff = t & (-t);
            int one = 0;
            for (int x : nums) {
                if ((diff & x) != 0) one ^= x;
            }
            return new int[]{one, one ^ t};
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
