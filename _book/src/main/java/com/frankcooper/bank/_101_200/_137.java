package com.frankcooper.platform.leetcode.bank._101_200;

import java.util.*;

import org.junit.Assert;

public class _137 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[] nusms = {2, 2, 3, 2};
            Assert.assertEquals(handler.singleNumber(nusms), 3);

        }

        public int singleNumber(int[] nums) {//遍历位数，记录每个位数上1的个数，对3取MOD
            int[] arr = new int[32];
            for (int i = 0; i < 32; i++) {
                int bit = 0;
                for (int x : nums) {
                    if ((x >> i & 1) == 1) bit++;
                }
                arr[i] = bit;
            }
            int res = 0;
            for (int i = 0; i < 32; i++) {
                res |= (arr[i] % 3) << i;
            }
            return res;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        public int singleNumber(int[] nums) {
            int[] arr = new int[32];
            for (int i = 0; i < 32; i++) {
                int bit = 0;
                for (int x : nums) {
                    if ((x >> i & 1) == 1) bit++;

                }
                arr[i] = bit;
            }
            int res = 0;
            for (int i = 0; i < 32; i++) {
                res |= (arr[i] % 3) << i;

            }
            return res;
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
