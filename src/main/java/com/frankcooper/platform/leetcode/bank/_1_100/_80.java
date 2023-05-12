package com.frankcooper.platform.leetcode.bank._1_100;

public class _80 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }

        public int removeDuplicates(int[] nums) {
            int n = nums.length;
            if (n <= 2) return n;
            int i = 2, j = 2;
            while (j < nums.length) {
                if (nums[i - 2] != nums[j]) {
                    nums[i] = nums[j];
                    i++;
                }
                j++;
            }
            return i;

        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            int[] nums = {0, 1, 2, 2, 3, 3, 4};
            handler.removeDuplicates(nums);
        }

        public int removeDuplicates(int[] nums) {
            if (nums.length == 0) {
                return 0;
            }
            int i = 0;
            for (int j = 1; j < nums.length; j++) {
                if (nums[j] != nums[i]) {
                    i++;
                    nums[i] = nums[j];
                }
            }
            return i + 1;
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
