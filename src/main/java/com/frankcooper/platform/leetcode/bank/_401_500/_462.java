package com.frankcooper.platform.leetcode.bank._401_500;

import java.util.*;

public class _462 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        //排序后求中位数
        public int minMoves2(int[] nums) {
            Arrays.sort(nums);
            int res = 0, t = nums[nums.length / 2];
            for (int i = 0; i < nums.length; i++) {
                res += Math.abs(nums[i] - t);
            }
            return res;
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        public int minMoves2(int[] nums) {
            Arrays.sort(nums);
            int res = 0, t = nums[nums.length / 2];
            for (int i = 0; i < nums.length; i++) {
                res += Math.abs(nums[i] - t);
            }
            return res;
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }


        private void quickSort(int[] nums, int left, int right) {
            if (left < right) {
                int[] p = partition(nums, left, right);
                quickSort(nums, left, p[0] - 1);
                quickSort(nums, p[1] + 1, right);
            }
        }

        private int[] partition(int[] nums, int left, int right) {
            int less = left - 1;
            int more = right;
            while (left < more) {
                if (nums[left] < nums[right]) {
                    swap(nums, ++less, left++);
                } else if (nums[left] > nums[right]) {
                    swap(nums, --more, left);
                } else {
                    left++;
                }
            }
            swap(nums, more, right);
            return new int[]{less + 1, more};
        }

        protected void swap(int[] a, int i, int j) {
            int t = a[i];
            a[i] = a[j];
            a[j] = t;
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }

        Random random = new Random();

        public int minMoves2(int[] nums) {
            int n = nums.length;
            int t = quickSelect(nums, 0, n - 1, n / 2);
            int res = 0;
            for (int i = 0; i < n; ++i) {
                res += Math.abs(nums[i] - t);
            }
            return res;
        }

        //在[left,right]区间上找index下标的数
        public int quickSelect(int[] nums, int l, int r, int index) {
            int p = randomPartition(nums, l, r);
            if (p == index) {
                return nums[p];
            } else {
                //p是分割点的下标，比index小，说明在右半部分找[p+1,right]
                //否则在左半部分找[left, p-1]
                return p < index ?
                        quickSelect(nums, p + 1, r, index)
                        : quickSelect(nums, l, p - 1, index);
            }
        }

        public int randomPartition(int[] nums, int l, int r) {
            int pivotIndex = random.nextInt(r - l + 1) + l;
            swap(nums, pivotIndex, r);
            int pivot = nums[r], i = l - 1;
            for (int j = l; j < r; ++j) {
                if (nums[j] <= pivot) {
                    ++i;
                    swap(nums, i, j);
                }
            }
            swap(nums, i + 1, r);
            return i + 1;
        }

        public void swap(int[] nums, int i, int j) {
            int t = nums[i];
            nums[i] = nums[j];
            nums[j] = t;
        }


    }
}
