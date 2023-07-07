package com.frankcooper.platform.leetcode.bank.week;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*import java.util.*;
import org.junit.Assert;*/
public class Week352 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[] nums = {2, 3, 4, 5};
            int threshold = 4;
//            handler.longestAlternatingSubarray(nums, threshold);

            nums = new int[]{3, 2, 5, 4};
            threshold = 5;
//            Assert.assertEquals(3, handler.longestAlternatingSubarray(nums, threshold));
            nums = new int[]{1, 2};
            threshold = 2;
//            Assert.assertEquals(1, handler.longestAlternatingSubarray(nums, threshold));
            nums = new int[]{10, 8};
            threshold = 4;
//            Assert.assertEquals(0, handler.longestAlternatingSubarray(nums, threshold));
            nums = new int[]{1, 10, 5};
            threshold = 9;
//            Assert.assertEquals(0, handler.longestAlternatingSubarray(nums, threshold));
            nums = new int[]{8, 4};
            threshold = 6;
            Assert.assertEquals(1, handler.longestAlternatingSubarray(nums, threshold));


        }

        public int longestAlternatingSubarray(int[] nums, int threshold) {
            int n = nums.length;
            int[] diff = new int[n];
            diff[0] = 1;
            for (int i = 1; i < n; i++) {
                diff[i] = nums[i] - nums[i - 1];
            }
            int res = 0;
            for (int l = 0; l < n; l++) {
                if (nums[l] > threshold || nums[l] % 2 == 1 || diff[l] % 2 == 0) {
                    continue;
                }
                res = Math.max(res, 1);
                for (int r = l + 1; r < n; r++) {
                    if (nums[r] > threshold || diff[r] % 2 == 0) {
                        if (nums[r] <= threshold) {
                            res = Math.max(res, r - l);
                        }
                        l = r;
                        break;
                    }
                    res = Math.max(res, r - l + 1);
                }
            }
            return res;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        //TLE
        public List<List<Integer>> findPrimePairs(int n) {
            List<List<Integer>> res = new ArrayList<>();
            for (int x = 2; x <= n; x++) {
                int y = n - x;
                if (x <= y && isPrime(x) && isPrime(y)) {
                    res.add(Arrays.asList(x, y));
                }
            }
            return res;
        }


        private boolean isPrime(int x) {
            if (x < 2) return false;
            for (int i = 2; i <= x / i; i++) {
                if (x % i == 0) return false;
            }
            return true;
        }
    }

    static class _2nd_1 {
        public static void main(String[] args) {

        }


        public List<List<Integer>> findPrimePairs(int n) {
            List<Integer> primes = new ArrayList<>();
            boolean[] isPrime = new boolean[n];
            Arrays.fill(isPrime, true);
            for (int i = 2; i < n; i++) {
                if (isPrime[i]) {
                    primes.add(i);
                    if ((long) i * i < n) {
                        for (int j = i * i; j < n; j += i) {
                            isPrime[j] = false;// 是i的倍数的均不是素数
                        }
                    }
                }
            }
            List<List<Integer>> res = new ArrayList<>();
            for (int x : primes) {
                int y = n - x;
                if (x > y) {
                    break;
                }
                if (isPrime[y]) {
                    res.add(Arrays.asList(x, y));
                }
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
