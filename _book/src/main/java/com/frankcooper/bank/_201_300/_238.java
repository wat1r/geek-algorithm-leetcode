package com.frankcooper.bank._201_300;

import java.util.*;

import org.junit.Assert;

public class _238 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[] nums = {1, 2, 3, 4};
            handler.productExceptSelf(nums);
        }


        public int[] productExceptSelf(int[] nums) {
            int n = nums.length;
            int[] ans = new int[n];
            ans[n - 1] = 1;
            for (int i = n - 2; i >= 0; --i) {
                ans[i] = ans[i + 1] * nums[i + 1];
            }
            int x = 1;
            for (int i = 0; i < n; i++) {
                ans[i] = x * ans[i];
                x *= nums[i];
            }
            return ans;

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
