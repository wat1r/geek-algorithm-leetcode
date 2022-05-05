package com.frankcooper.swordoffer;

import java.util.*;

import org.junit.Assert;

public class Sword_11 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }

        public int minArray(int[] nums) {
            int n = nums.length;
            int l = 0, r = n - 1;
            while (l < r) {
                int mid = l + (r - l) / 2;
                if (nums[mid] < nums[r]) r = mid;
                else if (nums[mid] > nums[r]) l = mid + 1;
                else r--;
            }
            return nums[l];
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
