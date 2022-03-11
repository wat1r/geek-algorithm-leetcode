package com.frankcooper.bank._301_400;

import java.util.*;

import org.junit.Assert;

public class _324 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[] nums = new int[]{1, 3, 2, 2, 3, 1};
            handler.wiggleSort(nums);
        }

        public void wiggleSort(int[] nums) {
            int[] arr = Arrays.copyOf(nums, nums.length);
            Arrays.sort(arr);
            int n = arr.length;
            int l = (n + 1) / 2 - 1;//中间位置的索引
            int r = n - 1;//最后的索引
            for (int i = 0; i < n; i++) {
                if (i % 2 == 1) {//奇数下标放置大的数
                    nums[i] = arr[r--];
                } else {//偶数下标放置小的数
                    nums[i] = arr[l--];
                }
            }
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            int[] nums = new int[]{1, 5, 1, 1, 6, 4};
            handler.wiggleSort(nums);
        }

        public void wiggleSort(int[] nums) {
            int[] arr = Arrays.copyOf(nums, nums.length);
            Arrays.sort(arr);
            int n = nums.length, l = (n + 1) / 2 - 1, r = n - 1;
            for (int i = 0; i < n; i++) {
                if (i % 2 == 0) nums[i] = arr[l--];
                else nums[i] = arr[r--];
            }
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
