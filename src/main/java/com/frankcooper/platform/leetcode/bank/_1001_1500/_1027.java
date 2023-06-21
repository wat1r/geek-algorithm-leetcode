package com.frankcooper.platform.leetcode.bank._1001_1500;

import java.util.*;

import org.junit.Assert;

public class _1027 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[] nums = {3, 6, 9, 12};
            Assert.assertEquals(4, handler.longestArithSeqLength(nums));

        }


        private Map<Integer, Integer>[] maxLen;
        private int res;
        private int[] nums;

        public int longestArithSeqLength(int[] nums) {
            this.nums = nums;
            int n = nums.length;
            maxLen = new HashMap[n];
            for (int i = 1; i < n; i++) {
                dfs(i);
            }
            return res;
        }

        //返回nums[i]和i之前之多i个公差不同的LAS(最长等差子序列)
        public Map<Integer, Integer> dfs(int i) {
            if (maxLen[i] != null) {
                return maxLen[i];
            }
            maxLen[i] = new HashMap<>();
            for (int j = i - 1; j >= 0; j--) {
                int d = nums[i] - nums[j];
                if (!maxLen[i].containsKey(d)) {
                    maxLen[i].put(d, dfs(j).getOrDefault(d, 1) + 1);
                    res = Math.max(res, maxLen[i].get(d));
                }
            }
            return maxLen[i];
        }
    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            int[] nums = {3, 6, 9, 12};
            Assert.assertEquals(4, handler.longestArithSeqLength(nums));
        }

        public int longestArithSeqLength(int[] nums) {
            int n = nums.length, res = 0;
            Map<Integer, Integer>[] f = new HashMap[n];
//            Arrays.fill(f, new HashMap<>());//此做法，每个数组是同一个HashMap
            Arrays.setAll(f, e -> new HashMap<>());//深拷贝
            for (int i = 1; i < n; i++) {
                for (int j = i - 1; j >= 0; j--) {
                    int d = nums[i] - nums[j];
                    if (!f[i].containsKey(d)) {
                        f[i].put(d, f[j].getOrDefault(d, 1) + 1);
                        res = Math.max(res, f[i].get(d));
                    }
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
