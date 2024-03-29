package com.frankcooper.platform.leetcode.bank._301_400;

public class _303 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        class NumArray {

            int[] pre;

            public NumArray(int[] nums) {
                pre = new int[nums.length + 1];
                for (int i = 0; i < nums.length; i++) pre[i + 1] = pre[i] + nums[i];
            }

            public int sumRange(int left, int right) {
                return pre[right + 1] - pre[left];
            }
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
