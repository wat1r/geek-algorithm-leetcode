package com.frankcooper.bank._901_1000;

import java.util.*;

import org.junit.Assert;

public class _922 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

            int[] nums = {2, 3, 1, 1, 4, 0, 0, 4, 3, 3};
            handler.sortArrayByParityII(nums);
        }


        public int[] sortArrayByParityII(int[] nums) {
            int n = nums.length;
            int even = 0, odd = 1;//偶数， 奇数指针
            for (; even < n; even += 2) {
                if (nums[even] % 2 == 1) {
                    while (nums[odd] % 2 == 1) odd += 2;
                    swap(nums,odd,even);
                }
            }
            return nums;
        }

        private void swap(int[] arr, int i, int j) {
            int t = arr[i];
            arr[i] = arr[j];
            arr[j] = t;
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
