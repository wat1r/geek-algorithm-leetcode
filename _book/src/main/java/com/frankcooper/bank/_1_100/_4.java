package com.frankcooper.platform.leetcode.bank._1_100;

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
            int[] nums1 = {1, 3, 4, 9};
            int[] nums2 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
            int k = 7;
//            handler.getKthNum(nums1, 0, nums1.length - 1, nums2, 0, nums2.length - 1, k);

            nums1 = new int[]{1, 2};
            nums2 = new int[]{3, 4};
//            k = 2;
//            handler.getKthNum(nums1, 0, nums1.length - 1, nums2, 0, nums2.length - 1, k);
//            handler.findMedianSortedArrays(nums1, nums2);

//            nums1 = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 17};
//            nums2 = new int[]{3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 29};
//            for (k = 1; k <= nums1.length + nums2.length; k++) {
//                System.out.printf("%dth->%d\n", k, handler.getKth(nums1, nums1.length, nums2, nums2.length, k));
//            }

        }

        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            int m = nums1.length, n = nums2.length;
            //各自上下取整，错开一个位置
            int left = (m + n + 1) / 2;
            int right = (m + n + 2) / 2;
            //计算上下取整的位置的Kth，免去判断奇偶数
            int leftKthNum = getKthNum(nums1, 0, m - 1, nums2, 0, n - 1, left);
            int rightKthNum = getKthNum(nums1, 0, m - 1, nums2, 0, n - 1, right);
            return (leftKthNum + rightKthNum) / 2.0;
        }

        /**
         * @param nums1  数组1
         * @param start1 数组1的开始位置
         * @param end1   数组1的结束位置
         * @param nums2  数组2
         * @param start2 数组2的开始位置
         * @param end2   数组2的结束位置
         * @param k      第k个
         * @return
         */
        public int getKthNum(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2, int k) {
            //当前[start1..end1] 和 [start2...end2]的长度
            int len1 = end1 - start1 + 1, len2 = end2 - start2 + 1;
            //始终保持nums1的长度短
            if (len1 > len2) return getKthNum(nums2, start2, end2, nums1, start1, end1, k);
            //拿`nums1=[1,2]`和`nums2=[3,4]`举例，当`nums1`用完的时候，`start1`已经跳到`len(nums1)`的位置，开始发生越界，这时候说明`nums1`已经用完了，按`nums2`的数组下标计算目标索引，也就是`nums2[start2+k-1]`,因为一开始保证了`nums1`比`nums2`长度小
            if (len1 == 0) return nums2[start2 + k - 1];
            //k经剩下1个时，说明找到了
            if (k == 1) return Math.min(nums1[start1], nums2[start2]);
            //每次取k的一半 计算新的i和j开始二分
            int i = start1 + Math.min(len1, k / 2) - 1;
            int j = start2 + Math.min(len2, k / 2) - 1;
            //如果nums1[i] > nums2[j]去掉[start2...j]的部分，反之，去掉[start1...i]的部分
            if (nums1[i] > nums2[j]) {
                return getKthNum(nums1, start1, end1, nums2, j + 1, end2, k - (j - start2 + 1));
            } else {
                return getKthNum(nums1, i + 1, end1, nums2, start2, end2, k - (i - start1 + 1));
            }
        }

//        public int getKth(int[] nums1, int len1, int[] nums2, int len2, int k) {
//            if (len1 > len2) return getKth(nums2, len2, nums1, len1, k);
//            if (len1 == 0) return nums2[k - 1];
//            if (k == 1) return Math.min(nums1[0], nums2[0]);
//            int i = Math.min(k / 2, len1), j = k - i;
//            if (nums1[i - 1] == nums2[j - 1]) return nums1[i];
//            else if (nums1[i - 1] > nums2[j - 1]) return getKth(nums1, len1, nums2, len2 - j, k - j);
//            else if (nums1[i - 1] < nums2[j - 1]) return getKth(nums1, len1 - i, nums2, len2, k - i);
//            return -1;
//        }


    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();

            int[] nums1 = {3, 4};
            int[] nums2 = {1, 2};
//            handler.findMedianSortedArrays(nums1, nums2);

            nums1 = new int[]{2, 4, 6, 7, 10};
            nums2 = new int[]{1, 3, 5, 8, 9, 11, 12, 13, 14};
            System.out.println(handler.findMedianSortedArrays(nums1, nums2));
        }


        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            int m = nums1.length, n = nums2.length;
            if (m > n) return findMedianSortedArrays(nums2, nums1);
            //true：奇数 false：偶数
            boolean isOdd = ((m + n) & 1) == 1;
            int start = 0, end = m;
            int cut1, cut2;
            while (start <= end) {
                cut1 = (start + end) / 2;
                cut2 = (m + n + 1) / 2 - cut1;
                //nums1左半部分和右半部分的最小值和最大值，结合边界考虑
                int nums1LeftMax = cut1 == 0 ? Integer.MIN_VALUE : nums1[cut1 - 1];
                int nums1RightMin = cut1 == m ? Integer.MAX_VALUE : nums1[cut1];
                //nums2左半部分和右半部分的最小值和最大值，结合边界考虑
                int nums2LeftMax = cut2 == 0 ? Integer.MIN_VALUE : nums2[cut2 - 1];
                int nums2RightMin = cut2 == n ? Integer.MAX_VALUE : nums2[cut2];
                // nums1左半部分最大值比nums2右半部分的最小值大，说明需要缩减[start...cut1]这个范围
                if (nums1LeftMax > nums2RightMin) end = cut1 - 1;
                    // nums1右半部分最小值比nums2左半部分的最大值小，说明需要扩大[cut1...end]这个范围
                else if (nums2LeftMax > nums1RightMin) start = cut1 + 1;
                    //开始返回结果，结合奇偶性
                else {
                    if (isOdd) return Math.max(nums1LeftMax, nums2LeftMax);
                    else
                        return ((double) (Math.max(nums1LeftMax, nums2LeftMax) + Math.min(nums1RightMin, nums2RightMin))) / 2;
                }

            }
            return 0.0;
        }

    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
