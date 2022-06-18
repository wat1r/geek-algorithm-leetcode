package com.frankcooper.platform.leetcode.bank.bi_weekly;

import java.util.*;

public class BiWeek49 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        public boolean squareIsWhite(String s) {
            int i = s.charAt(0) - 'a', j = s.charAt(1) - '1';
            if (((i + j) & 1) == 0) return false;
            else return true;
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            String sentence1 = "My name is Haley", sentence2 = "My Haley";
            sentence1 = "of";
            sentence2 = "A lot of words";
            handler.areSentencesSimilar(sentence1, sentence2);
        }

        public boolean areSentencesSimilar(String s1, String s2) {
            Deque<String> q1 = new LinkedList<String>(Arrays.asList(s1.split("\\s+")));
            Deque<String> q2 = new LinkedList<String>(Arrays.asList(s2.split("\\s+")));
            while (!q1.isEmpty() && !q2.isEmpty() && q1.peekFirst().equals(q2.peekFirst())) {
                q1.pollFirst();
                q2.pollFirst();
            }

            while (!q1.isEmpty() && !q2.isEmpty() && q1.peekLast().equals(q2.peekLast())) {
                q1.pollLast();
                q2.pollLast();
            }
            return q1.size() * q2.size() == 0;
        }
    }


    static class _3rd {
//        public static void main(String[] args) {
//            _3rd handler = new _3rd();
////            handler.rev(42);
//            int[] nums = new int[]{13, 10, 35, 24, 76};
////            Assert.assertEquals(handler.countNicePairs(nums), 4);
////            Assert.assertEquals(handler.countNicePairs(nums), 999949972);
//
//
//        }

//        int MOD = (int) 1e9 + 7;

//        public int countNicePairs(int[] nums) {
//            int N = nums.length;
//            int[] rev = new int[N];
//            for (int i = 0; i < N; i++) rev[i] = rev(nums[i]);
//            Map<String, Integer> map = new HashMap<>();
//            int res = 0;
//            for (int i = 0; i < N; i++) {
//                for (int j = 0; j < N; j++) {
//                    if (i == j) continue;
//                    int sum1 = (int) (long) (nums[i] + rev[j]) % MOD;
//                    int sum2 = (int) (long) (nums[j] + rev[i]) % MOD;
//                    if (sum1 == sum2) res++;
//
//                }
//            }
//            return res / 2;
//        }

        int MOD = (int) 1e9 + 7;

        public int countNicePairs(int[] nums) {
            Map<Integer, Long> map = new HashMap<>();//标记成long类型
            long res = 0;
            for (int x : nums) {
                int diff = x - rev(x);
                map.put(diff, map.getOrDefault(diff, 0L) + 1);
            }
            for (int diff : map.keySet()) {
                if (map.get(diff) > 1) {
                    long t = map.get(diff);
                    res += t * (t - 1) / 2 % MOD;
                }
            }
            return (int) res % MOD;

        }


        private int rev(int x) {
            int res = 0;
            while (x > 0) {
                res = res * 10 + x % 10;
                x /= 10;
            }
            return res;
        }

//        private int rev(int x) {
//            String s = new StringBuilder(String.valueOf(x)).reverse().toString();
//            int res = 0;
//            for (int i = 0; i < s.length(); i++) {
//                if (res == 0 && s.charAt(i) == '0') continue;
//                res = res * 10 + s.charAt(i) - '0';
//            }
//            return res;
//
//        }
    }

    static class _3rd_1 {
        public static void main(String[] args) {
            _3rd_1 handler = new _3rd_1();
        }

        //待发表
        int MOD = (int) 1e9 + 7;

        public int countNicePairs(int[] nums) {
            long res = 0;
            Map<Integer, Long> map = new HashMap<>();//标记成Long类型
            for (int x : nums) {
                int diff = x - rev(x);
                if (map.containsKey(diff)) res = (res + map.get(diff)) % MOD; //取MOD 每次进来的一个数都可以与前面和其相同的数，组成一对
                map.put(diff, map.getOrDefault(diff, 0L) + 1);
            }
            return (int) res % MOD;

        }


        private int rev(int x) {
            int res = 0;
            while (x > 0) {
                res = res * 10 + x % 10;
                x /= 10;
            }
            return res;
        }

    }


    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
