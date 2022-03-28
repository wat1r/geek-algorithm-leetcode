package com.frankcooper.bank._1_100;

import java.util.*;

import org.junit.Assert;

public class _4 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }


        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            int m = nums1.length, n = nums2.length;
            if (m > n) {
                int[] temp = nums1;
                nums1 = nums2;
                nums2 = temp;
            }
            m = nums1.length;
            n = nums2.length;
            boolean isOdd = ((m + n) & 1) == 1;
            int left = 0, right = m;
            while (left <= right) {
                int i = (left + right) / 2;
                int j = (m + n + 1) / 2 - i;
                int nums1LeftMax = i == 0 ? Integer.MIN_VALUE : nums1[i - 1];
                int nums1RightMin = i == m ? Integer.MAX_VALUE : nums1[i];
                int nums2LeftMax = j == 0 ? Integer.MIN_VALUE : nums2[j - 1];
                int nums2RightMin = j == n ? Integer.MAX_VALUE : nums2[j];
                if (nums1LeftMax <= nums2RightMin && nums2LeftMax <= nums1RightMin) {
                    if (isOdd) {
                        return (double) Math.max(nums1LeftMax, nums2LeftMax);
                    } else {
                        return ((double) (Math.max(nums1LeftMax, nums2LeftMax) + Math.min(nums1RightMin, nums2RightMin))) / 2;
                    }
                } else if (nums1RightMin < nums2LeftMax) {
                    left = i + 1;
                } else {
                    right = i - 1;
                }

            }
            return 0.0;
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
