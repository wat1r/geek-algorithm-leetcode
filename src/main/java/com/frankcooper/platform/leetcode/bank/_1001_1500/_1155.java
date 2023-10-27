package com.frankcooper.platform.leetcode.bank._1001_1500;

import java.util.*;

public class _1155 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }


        public int numRollsToTarget(int d, int f, int target) {
            int mod = 1000000007;
            int[][] dp = new int[d + 1][target + 1];
            dp[0][0] = 1;

            for (int i = 1; i <= d; i++) {
                for (int j = 1; j <= f; j++) {
                    for (int k = j; k <= target; k++) {
                        dp[i][k] = (dp[i][k] + dp[i - 1][k - j]) % mod;
                    }
                }
            }
            return dp[d][target];
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }
        //            dp[i][j]表示i个骰子，组成j点的组合数量
//           看dp[i][j]的前一个dp[i-1][*] 在第i次能掷出k的点数，k在[1,f]之间，就是前i-1次的组合数量是dp[i-1][j-k]
//         dp[i][j] =dp[i-1][j-1]+dp[i-1][j-2]+...dp[i-1][j-k]+...+dp[i-1][j-f]
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }

        int MOD = (int) 1e9 + 7;
        Map<String, Integer> cache = new HashMap<>();

        /**
         * @param d      d个一样的骰子
         * @param f      每个骰子上有f个面
         * @param target 目标的总点数
         * @return
         */
        public int numRollsToTarget(int d, int f, int target) {
            if (d == 0 && target == 0) return 1;
            if (d == 0 || target == 0) return 0;
            //骰子数量+形成的点数
            String key = d + "#" + target;
            if (cache.containsKey(key)) return cache.get(key);
            int res = 0;//记录结果
            //当前能掷出的点数
            for (int i = 1; i <= f; i++) {
                //只有target的数量大于i才有意义
                if (target >= i) {
                    //用掉一个骰子，当前的骰子掷出来的点数是i，相应地，target数量也要减少
                    res = (res + numRollsToTarget(d - 1, f, target - i)) % MOD;
                } else {
                    break;
                }
            }
            //记忆化
            cache.put(key, res);
            return res;
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }

        int MOD = (int) 1e9 + 7;

        /**
         * @param d      d个一样的骰子
         * @param f      每个骰子上有f个面
         * @param target 目标的总点数
         * @return
         */
        public int numRollsToTarget(int d, int f, int target) {
            int[][] dp = new int[d + 1][target + 1];
            dp[0][0] = 1;
            for (int i = 1; i <= d; i++) {
                for (int j = 0; j <= target; j++) {
                    for (int k = 1; k <= f && k <= j; k++) {
                        dp[i][j] = (dp[i][j] + dp[i - 1][j - k]) % MOD;
                    }
                }
            }
            return dp[d][target];
        }
    }


    static class _5th {
        int MOD = (int) 1e9 + 7;

        /**
         * @param d d个一样的骰子
         * @param f 每个骰子上有f个面
         * @param t 目标的总点数
         * @return
         */
        public int numRollsToTarget(int d, int f, int t) {
            int[][] dp = new int[d + 1][t + 1];
            //初始化，0个骰子形成0的点数，只有一种组合数
            dp[0][0] = 1;
            //枚举i个骰子，上限是d个骰子
            for (int i = 1; i <= d; i++) {
                //枚举j的点数，上限是t的点数
                for (int j = 1; j <= t; j++) {
                    //开始枚举第i个骰子能掷出的点数k，范围是[1,f]，j的点数小于k没有意义
                    for (int k = 1; k <= f && j >= k; k++) {
                        dp[i][j] = (dp[i][j] + dp[i - 1][j - k]) % MOD;
                    }
                }
            }
            //返回d个骰子掷出t的点数的方案数量
            return dp[d][t];
        }

    }

    static class _6th {
        int MOD = (int) 1e9 + 7;

        /**
         * @param d d个一样的骰子
         * @param f 每个骰子上有f个面
         * @param t 目标的总点数
         * @return
         */
        public int numRollsToTarget(int d, int f, int t) {
            int[] dp = new int[t + 1];
            //初始化，0个骰子形成0的点数，只有一种组合数
            dp[0] = 1;
            //枚举i个骰子，上限是d个骰子
            for (int i = 1; i <= d; i++) {
                //枚举j的点数，上限是t的点数 倒序遍历
                for (int j = t; j >= 0; j--) {
                    dp[j] = 0;//注意，每一轮遍历到j的时候，需要置为0
                    //开始枚举第i个骰子能掷出的点数k，范围是[1,f]，j的点数小于k没有意义
                    for (int k = 1; k <= f && j >= k; k++) {
                        dp[j] = (dp[j] + dp[j - k]) % MOD;
                    }
                }
            }
            //返回d个骰子掷出t的点数的方案数量
            return dp[t];
        }
    }

    static class _7th {

        int MOD = (int) 1e9 + 7;
        Map<String, Integer> cache = new HashMap<>();

        /**
         * @param d      d个一样的骰子
         * @param f      每个骰子上有f个面
         * @param target 目标的总点数
         * @return
         */
        public int numRollsToTarget(int d, int f, int target) {
            if (d == 0 && target == 0) return 1;
            //优化点：
            // 1.当前的骰子的数量为d，大于target，也就是d个骰子，每个骰子掷出1，都凑不出target
            // 2.当前的骰子的数量为d，每个骰子都能掷出f的点数，d*f<target 都凑不出target
            if (d > target || d * f < target) return 0;
            //骰子数量+形成的点数
            String key = d + "#" + target;
            if (cache.containsKey(key)) return cache.get(key);
            int res = 0;//记录结果
            //当前能掷出的点数
            for (int i = 1; i <= f; i++) {
                //只有target的数量大于i才有意义
                if (target >= i) {
                    //用掉一个骰子，当前的骰子掷出来的点数是i，相应地，target数量也要减少
                    res = (res + numRollsToTarget(d - 1, f, target - i)) % MOD;
                } else {
                    break;
                }
            }
            //记忆化
            cache.put(key, res);
            return res;
        }
    }


    static class _8th {
        public static void main(String[] args) {

        }

        int MOD = (int) 1e9 + 7;
        Map<String, Integer> cache = new HashMap<>();

        /**
         * @param n      个骰子
         * @param k      个面
         * @param target 目标和
         * @return
         */
        public int numRollsToTarget(int n, int k, int target) {
            if (n == 0 && target == 0) {
                return 1;
            }
            if (n > target || n * k < target) {
                return 0;
            }
            String key = n + "_" + target;
            if (cache.containsKey(key)) {
                return cache.get(key);
            }
            int res = 0;
            for (int i = 1; i <= k; i++) {
                if (target >= i) {
                    res = (res + numRollsToTarget(n - 1, k, target - i)) % MOD;
                } else {
                    break;
                }
            }
            cache.put(key, res);
            return res;
        }
    }
}
