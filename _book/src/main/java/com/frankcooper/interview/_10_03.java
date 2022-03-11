package com.frankcooper.interview;

import java.util.*;

import org.junit.Assert;

public class _10_03 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[] arr = {15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14};
            int target = 5;
//            Assert.assertEquals(8, handler.search(arr, target));
            arr = new int[]{1, 1, 1, 1, 1, 2, 1, 1, 1};
            target = 2;
//            Assert.assertEquals(5, handler.search(arr, target));
            arr = new int[]{21, 21, -21, -20, -17, -8, -6, -2, -2, -1, 0, 2, 3, 4, 4, 6, 11, 13, 14, 16, 17, 18, 20};
            target = 4;
            Assert.assertEquals(13, handler.search(arr, target));


        }


        public int search(int[] arr, int target) {
            int l = 0, r = arr.length - 1;
            while (l <= r) {//退出条件时l=r+1
                //l 符合的时候，返回，找的是最小的索引
                if (arr[l] == target) return l;
                int mid = l + r >> 1;
                if (arr[mid] == target) {
                    r = mid;//等于target时，要找最小的mid，将右边界排除
                } else if (arr[0] < arr[mid]) {
                    if (target >= arr[0] && target < arr[mid]) {
                        r = mid - 1;
                    } else {
                        l = mid + 1;
                    }
                } else if (arr[0] > arr[mid]) {
                    if (target > arr[mid] && target <= arr[arr.length - 1]) {
                        l = mid + 1;
                    } else {
                        r = mid - 1;
                    }
                } else {
                    l++;//当中间数字与左边数字相等时，将左边界右移
                }
            }
            return -1;
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
