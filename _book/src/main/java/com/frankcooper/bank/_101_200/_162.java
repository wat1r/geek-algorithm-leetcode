package com.frankcooper.platform.leetcode.bank._101_200;

import java.util.*;

import org.junit.Assert;

public class _162 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        public int findPeakElement(int[] nums) {
            int l = 0, r = nums.length - 1;
            while (l < r) {
                int mid = l + (r - l) / 2;
                if (nums[mid] > nums[mid + 1]) {
                    r = mid;
                } else {
                    l = mid + 1;//mid这个数肯定不是峰值，可以排除掉
                }
            }
            return l;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        public int findPeakElement(int[] nums) {
            int l = 0, r = nums.length - 1;
            while (l < r) {
                int mid = l + (r - l) / 2;
                if (nums[mid] > nums[mid + 1]) {//与mid右侧挨着的点进行比较筛选
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }
            return l;
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }

        public int findPeakElement(int[] nums) {
            int n = nums.length, l = 0, r = n - 1;
            while (l < r) {
                int mid = l + (r - l) / 2;
                if (nums[mid] < nums[mid + 1]) {
                    l = mid + 1;
                } else {
                    r = mid;
                }
            }
            return l;
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
