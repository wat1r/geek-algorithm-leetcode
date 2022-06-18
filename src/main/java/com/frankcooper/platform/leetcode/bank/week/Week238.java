package com.frankcooper.platform.leetcode.bank.week;

import java.util.*;

import org.junit.Assert;

public class Week238 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            handler.sumBase(34, 6);
        }


        public int sumBase(int n, int k) {

            int res = 0;
            while (n > 0) {
                res += n % k;
                n /= k;
            }
            return res;

        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            int[] nums = {1, 4, 8, 13};
            int k = 5;
//            Assert.assertEquals(2, handler.maxFrequency(nums, k));
            nums = new int[]{1, 2, 4};
            k = 5;
//            Assert.assertEquals(3, handler.maxFrequency(nums, k));
            nums = new int[]{3, 9, 6};
            k = 2;
//            Assert.assertEquals(1, handler.maxFrequency(nums, k));
            nums = new int[]{1, 2, 4, 2, 3, 7, 9, 10};
            k = 5;
//            Assert.assertEquals(4, handler.maxFrequency(nums, k));
            nums = new int[]{9930, 9923, 9983, 9997, 9934, 9952, 9945, 9914, 9985, 9982, 9970, 9932, 9985, 9902, 9975, 9990, 9922, 9990, 9994, 9937, 9996, 9964, 9943, 9963, 9911, 9925, 9935, 9945, 9933, 9916, 9930, 9938, 10000, 9916, 9911, 9959, 9957, 9907, 9913, 9916, 9993, 9930, 9975, 9924, 9988, 9923, 9910, 9925, 9977, 9981, 9927, 9930, 9927, 9925, 9923, 9904, 9928, 9928, 9986, 9903, 9985, 9954, 9938, 9911, 9952, 9974, 9926, 9920, 9972, 9983, 9973, 9917, 9995, 9973, 9977, 9947, 9936, 9975, 9954, 9932, 9964, 9972, 9935, 9946, 9966};
            k = 3056;
            Assert.assertEquals(73, handler.maxFrequency(nums, k));


        }

        public int maxFrequency(int[] nums, int k) {
            for (int i = 0; i < nums.length; i++) {
                nums[i] = nums[i] - 9900;
            }
            Arrays.sort(nums);
            System.out.println(Arrays.toString(nums));

            int[] delta = new int[nums.length - 1];
            for (int i = 1; i < nums.length; i++) delta[i - 1] = nums[i] - nums[i - 1];
            int[] preSum = new int[delta.length + 1];
            for (int i = 0; i < delta.length; i++) preSum[i + 1] = preSum[i] + delta[i];
            for (int i = 0; i < delta.length; i++) {
                System.out.printf("%d->%d\n", i, nums[i]);
            }
            int res = 1, sum = 0;
            int i = 0, j = 0;
            for (; j < delta.length; j++) {
                sum += delta[j] + preSum[j] - preSum[i];
                if (sum <= k) res = Math.max(res, j - i + 2);
                else {
                    i = j;
                    sum = delta[j];
                }

            }
            return res;
        }
    }


    static class _2nd_1 {
        static _2nd_1 handler = new _2nd_1();

        public static void main(String[] args) {
            int[] nums = {1, 4, 8, 13};
            int k = 5;
//            Assert.assertEquals(2, handler.maxFrequency(nums, k));
            nums = new int[]{1, 2, 4};
            k = 5;
//            Assert.assertEquals(3, handler.maxFrequency(nums, k));
            nums = new int[]{3, 9, 6};
            k = 2;
//            Assert.assertEquals(1, handler.maxFrequency(nums, k));
            nums = new int[]{1, 2, 4, 2, 3, 7, 9, 10};
            k = 5;
//            Assert.assertEquals(4, handler.maxFrequency(nums, k));
            nums = new int[]{9930, 9923, 9983, 9997, 9934, 9952, 9945, 9914, 9985, 9982, 9970, 9932, 9985, 9902, 9975, 9990, 9922, 9990, 9994, 9937, 9996, 9964, 9943, 9963, 9911, 9925, 9935, 9945, 9933, 9916, 9930, 9938, 10000, 9916, 9911, 9959, 9957, 9907, 9913, 9916, 9993, 9930, 9975, 9924, 9988, 9923, 9910, 9925, 9977, 9981, 9927, 9930, 9927, 9925, 9923, 9904, 9928, 9928, 9986, 9903, 9985, 9954, 9938, 9911, 9952, 9974, 9926, 9920, 9972, 9983, 9973, 9917, 9995, 9973, 9977, 9947, 9936, 9975, 9954, 9932, 9964, 9972, 9935, 9946, 9966};
            k = 3056;
            Assert.assertEquals(73, handler.maxFrequency(nums, k));
        }


        public int maxFrequency(int[] nums, int k) {
            Arrays.sort(nums);
            int res = 1;
            long sum = 0;
            int i = 0;
            for (int j = 1; j < nums.length; j++) {
                sum += (nums[j] - nums[j - 1]) * (j - i);
                while (sum > k) {
                    sum -= nums[j] - nums[i];
                    i++;
                }
                res = Math.max(res, j - i + 1);

            }
            return res;
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
            handler.longestBeautifulSubstring("aeiaaioaaaaeiiiiouuuooaauuaeiu");
            Assert.assertEquals(handler.longestBeautifulSubstring("aeeeiiiioooauuuaeiou"), 5);
            Assert.assertEquals(handler.longestBeautifulSubstring("a"), 0);
        }

        public int longestBeautifulSubstring(String word) {
            Map<Character, Integer> map = new HashMap<>();
            char[] chas = word.toCharArray();
            int i = 0, j = 0, res = 0;
            while (j < chas.length) {
                if (chas[j] == 'a') {
                    if (contains(map, new char[]{'e', 'i', 'o', 'u'})) {
                        i = j;
                        map = new HashMap<>();
                        map.put(chas[j], map.getOrDefault(chas[j], 0) + 1);
                        j++;
                        continue;
                    }
                } else if (chas[j] == 'e') {
                    if (contains(map, new char[]{'i', 'o', 'u'})) {
                        i = j;
                        map = new HashMap<>();
                        map.put(chas[j], map.getOrDefault(chas[j], 0) + 1);
                        j++;
                        continue;
                    }
                } else if (chas[j] == 'i') {
                    if (contains(map, new char[]{'o', 'u'})) {
                        i = j;
                        map = new HashMap<>();
                        map.put(chas[j], map.getOrDefault(chas[j], 0) + 1);
                        j++;
                        continue;
                    }
                } else if (chas[j] == 'o') {
                    if (contains(map, new char[]{'u'})) {
                        i = j;
                        map = new HashMap<>();
                        map.put(chas[j], map.getOrDefault(chas[j], 0) + 1);
                        j++;
                        continue;
                    }
                } else if (chas[j] == 'u') {
//
                }
                map.put(chas[j], map.getOrDefault(chas[j], 0) + 1);
                if (map.size() == 5) {
                    res = Math.max(res, j - i + 1);
                }
                j++;

            }
            return res;
        }

        private boolean contains(Map<Character, Integer> map, char[] ch) {
            for (char c : ch) {
                if (map.containsKey(c)) return true;
            }
            return false;
        }

    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
