package com.frankcooper.platform.leetcode.bank._1_100;

public class _27 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        public int removeElement(int[] nums, int val) {
            int i = 0, j = 0;
            for (; j < nums.length; j++) {
                if (nums[j] != val) swap(nums, i++, j);
            }

            return i;
        }

        private void swap(int[] nums, int i, int j) {
            int t = nums[i];
            nums[i] = nums[j];
            nums[j] = t;
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
