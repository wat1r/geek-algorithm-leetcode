package com.frankcooper.platform.leetcode.bank._201_300;

public class _268 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        public int missingNumber(int[] nums) {
            int ans = nums.length;
            for (int i = 0; i < nums.length; i++) {
                ans ^= i ^ nums[i];
            }
            return ans;
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
