package com.frankcooper.platform.leetcode.interview;

import java.util.*;

import org.junit.Assert;

public class _17_19 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[] nums = new int[]{2, 3};
            handler.missingTwo(nums);

        }

        public int[] missingTwo(int[] nums) {
            int size = nums.length;
            int[] arr = new int[size + 3];
            arr[0] = arr[1] = arr[2] = -1;
            for (int i = 3; i < arr.length; i++) arr[i] = nums[i - 3];
            for (int i = 0; i < arr.length; i++) {
                while (i != arr[i] && arr[i] != -1) {
                    swap(arr, i, arr[i]);
                }
            }
            int[] res = new int[2];
            for (int i = 1; i < arr.length; i++) {
                if (arr[i] == -1) {
                    if (res[0] == 0) res[0] = i;
                    else res[1] = i;
                }
            }
            return res;
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
            int[] nums = new int[]{2, 3};
            nums = new int[]{2};
            handler.missingTwo(nums);
        }


        public int[] missingTwo(int[] nums) {
            int ans = 0, n = nums.length;
            for (int i = 1; i <= n + 2; i++) ans ^= i;
            for (int x : nums) ans ^= x;

            int one = 0;
            int diff = ans & -ans;
            for (int i = 1; i <= n + 2; i++)
                if ((diff & i) != 0) one ^= i; // ?
            for (int x : nums)
                if ((diff & x) != 0) one ^= x;
            return new int[]{one, one ^ ans};
        }


        /**
         *
         *
         *
         *
         *
         *
         *
         *
         *
         *
         *
         *
         * 作者：joswxe
         * 链接：https://leetcode-cn.com/problems/missing-two-lcci/solution/zhuan-zhi-xiao-shi-de-shu-de-san-chong-jie-fa-by-w/
         * 来源：力扣（LeetCode）
         * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
         */
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
            int[] nums = new int[]{2};
            handler.missingTwo(nums);
        }

        public int[] missingTwo(int[] nums) {
            int x = 0, n = nums.length;
            for (int i = 1; i <= n + 2; i++) x ^= i;
            for (int y : nums) x ^= y;
            int diff = x & -x;
            int one = 0;
            for (int i = 1; i <= n + 2; i++) {
                if ((diff & i) != 0) one ^= i;
            }
            for (int y : nums) {
                if ((diff & y) != 0) one ^= y;
            }
            return new int[]{one, one ^ x};
        }


    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
