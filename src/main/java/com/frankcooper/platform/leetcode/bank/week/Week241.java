package com.frankcooper.platform.leetcode.bank.week;

import java.util.*;

public class Week241 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[] nums = new int[]{1, 3};
            handler.subsetXORSum(nums);

        }

        List<List<Integer>> res = new ArrayList<>();

        public int subsetXORSum(int[] nums) {
            dfs(nums, 0, new ArrayList<>());
            int sum = 0;
            for (List<Integer> sub : res) {
                sum += getXORSum(sub);
            }
            return sum;
        }

        public void dfs(int[] nums, int idx, List<Integer> sub) {
            for (int i = idx; i < nums.length; i++) {
                sub.add(nums[i]);
                res.add(new ArrayList<>(sub));
//                sub.forEach(System.out::print);
//                System.out.println();
                dfs(nums, i + 1, sub);
                sub.remove(sub.size() - 1);
            }
        }

        public int getXORSum(List<Integer> sub) {
            int res = 0;
            for (int x : sub) res ^= x;
            return res;
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            handler.minSwaps("111000");
        }


        public int minSwaps(String s) {
            char[] chas = s.toCharArray();
            int s0_n0 = 0, s0_n1 = 0;//形如010101...这样的字符串s0, n0表示不是0的个数，n1表示不是1的个数
            int s1_n0 = 0, s1_n1 = 0;//形如101010...这样的字符串s1
            for (int i = 0; i < chas.length; i++) {
                if (i % 2 == 0)//偶数下标
                {
                    if (chas[i] != '0') s0_n0++;//不是'0'的时候，也就是为'1'的时候
                    else s1_n1++;
                } else {
                    if (chas[i] != '0') s1_n0++;
                    else s0_n1++;
                }
            }
            if (s0_n0 != s0_n1 && s1_n0 != s1_n1) return -1;//s0 s1 都不能换
            if (s0_n0 == s0_n1 && s1_n0 != s1_n1) return s0_n0;// s0 能换， s1 不能换
            if (s0_n0 != s0_n1 && s1_n0 == s1_n1) return s1_n0; //s0 不能换 s1能换
            return Math.min(s0_n0, s1_n0);//s0 s1 都能换
        }

    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }


        class FindSumPairs {

            HashMap<Integer, Integer> map = new HashMap<>();//k : v -> tot， 次数
            int[] nums2;

            public FindSumPairs(int[] nums1, int[] nums2) {
                this.nums2 = nums2;
                for (int i = 0; i < nums1.length; i++) {
                    for (int j = 0; j < nums2.length; j++) {
                        int k = nums1[i] + nums2[j];
                        map.put(k, map.getOrDefault(k, 0) + 1);
                    }
                }
            }

            public void add(int index, int val) {
                int t = nums2[index];
                nums2[index] = val;

            }

            public int count(int tot) {

                return 0;
            }
        }

    }


    static class _3rd_1 {
        class FindSumPairs {

            HashMap<Integer, Integer> map1 = new HashMap<>();
            HashMap<Integer, Integer> map2 = new HashMap<>();
            int[] nums2;

            public FindSumPairs(int[] nums1, int[] nums2) {
                this.nums2 = nums2;
                for (int x : nums1) map1.put(x, map1.getOrDefault(x, 0) + 1);
                for (int x : nums2) map2.put(x, map2.getOrDefault(x, 0) + 1);
            }

            public void add(int index, int val) {
                int t = this.nums2[index];
                nums2[index] = t + val;
                map2.put(t, map2.get(t) - 1);
                int nt = t + val;
                map2.put(nt, map2.getOrDefault(nt, 0) + 1);
            }

            public int count(int tot) {
                int res = 0;
                for (int k1 : map1.keySet()) {
                    int k2 = tot - k1;
                    if (map2.containsKey(k2)) {
                        res += (map1.get(k1) * map2.get(k2));
                    }

                }
                return res;
            }
        }
    }


    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }

        public int rearrangeSticks(int n, int k) {
            long[][] dp = new long[1005][1005];
            int MOD = (int) 1e9 + 7;
            dp[0][0] = 1;
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= k; j++) {
                    dp[i][j] = (dp[i - 1][j - 1] + (i - 1) * dp[i - 1][j]) % MOD;
                }
            }
            return (int) dp[n][k];
        }

    }
}
