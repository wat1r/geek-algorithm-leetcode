package com.frankcooper.platform.leetcode.bank._901_1000;

public class _905 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[] nums = new int[]{0, 1};
            handler.sortArrayByParity(nums);

        }


        public int[] sortArrayByParity(int[] nums) {
            int i = 0, j = 0;
            while (j < nums.length) {
                while (j < nums.length && nums[j] % 2 == 1) j++;
                if (j > nums.length - 1) break;
                swap(nums, i, j);
                i++;
                j++;
            }
            return nums;
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
            int[] nums = new int[]{3, 1, 2, 4};
            handler.sortArrayByParity(nums);
        }

        public int[] sortArrayByParity(int[] nums) {
            int n = nums.length, l = 0, r = n - 1;
            while (l < r) {
                if (nums[l] % 2 == 1 && nums[r] % 2 == 0) {
                    int t = nums[l];
                    nums[l++] = nums[r];
                    nums[r--] = t;
                } else if (nums[l] % 2 == 0) l++;
                else if (nums[r] % 2 == 1) r--;
            }
            return nums;
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
            int[] nums = new int[]{3, 1, 2, 4};
            handler.sortArrayByParity(nums);
        }

        public int[] sortArrayByParity(int[] nums) {
            for (int i = 0, j = 0; j < nums.length; j++) {
                if (nums[j] % 2 == 0) {
                    int t = nums[i];
                    nums[i++] = nums[j];
                    nums[j] = t;
                }
            }
            return nums;
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
