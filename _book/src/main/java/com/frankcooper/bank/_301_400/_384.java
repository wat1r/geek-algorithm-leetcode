package com.frankcooper.platform.leetcode.bank._301_400;

import java.util.*;

import org.junit.Assert;

public class _384 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        class Solution {

            int[] src;
            int[] cur;

            Random random = new Random();

            public Solution(int[] nums) {
                src = nums.clone();
                cur = nums;
            }

            /**
             * Resets the array to its original configuration and return it.
             */
            public int[] reset() {
                return src;
            }

            /**
             * Returns a random shuffling of the array.
             */
            public int[] shuffle() {
                int n = cur.length;
                for (int i = 0; i < n; i++) {
                    int idx = randOfRange(i, n);
                    swap(cur, i, idx);
                }
                return cur;
            }


            private int randOfRange(int lo, int hi) {
                return random.nextInt(hi - lo) + lo;
            }


            private void swap(int[] nums, int i, int j) {
                int t = nums[i];
                nums[i] = nums[j];
                nums[j] = t;
            }
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
