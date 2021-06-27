package com.frankcooper.bank.bi_weekly;

import java.util.*;

import org.junit.Assert;

public class BiWeek55 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        public boolean canBeIncreasing(int[] nums) {
            for (int i = -1; i < nums.length; i++) {
                boolean f = false;
                int prev = 0;
                for (int j = 0; j < nums.length; j++) {
                    if (i == j) continue;
                    if (prev >= nums[j]) f = true;
                    prev = nums[j];
                }
                if (!f) return true;
            }
            return false;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        public String removeOccurrences(String s, String part) {

            while (true) {
                int idx = s.indexOf(part);
                if (idx == -1) break;
                s = s.substring(0, idx) + s.substring(idx + part.length());
            }
            return s;
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }

        //状态机DP
        public long maxAlternatingSum(int[] nums) {
            // f[i][0] 表示[]0...i-1]个数中选偶数个数的最大交替和
            //f[i][1]  表示[]0...i-1]个数中选奇数个数的最大交替和
            int n = nums.length;
            long[][] f = new long[n + 1][2];
            for (int i = 0; i <= n; i++) Arrays.fill(f[i], Integer.MIN_VALUE / 2);
            f[0][0] = 0;
            for (int i = 1; i <= n; i++) {
                f[i][0] = Math.max(f[i - 1][0], f[i - 1][1] - nums[i - 1]);
                f[i][1] = Math.max(f[i - 1][1], Math.max(nums[i - 1], f[i - 1][0] + nums[i - 1]));
            }
            return Math.max(f[n][0], f[n][1]);
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
