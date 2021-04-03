package com.frankcooper.bank._201_300;


import org.junit.Assert;

public class _260 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[] nums = new int[]{6, 1, 6, 5};
            Assert.assertEquals(handler.singleNumber(nums), new int[]{1, 5});
        }

        public int[] singleNumber(int[] nums) {
            int bit = 0;
            for (int x : nums) bit ^= x;//获取到异或的结果
            int t = 1;
            while ((t & bit) == 0) {
                t <<= 1;
            }
            int a = 0, b = 0;
            for (int x : nums) {
                if ((t & x) == 0) a ^= x;
                else b ^= x;
            }
            return new int[]{a, b};

        }
    }
}
