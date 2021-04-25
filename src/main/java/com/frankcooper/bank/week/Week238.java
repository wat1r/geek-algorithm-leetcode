package com.frankcooper.bank.week;

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
        }

        public int maxFrequency(int[] nums, int k) {
            Arrays.sort(nums);
            int[] delta = new int[nums.length - 1];
            for (int i = 1; i < nums.length; i++) delta[i - 1] = nums[i] - nums[i - 1];
            int[] preSum = new int[delta.length + 1];
            for (int i = 0; i < nums.length; i++) preSum[i + 1] = preSum[i] + delta[i];
            int res = 1;
            for (int i = 0; i < delta.length; i++) {

            }
            return 0;
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
