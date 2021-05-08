package com.frankcooper.bank._1501_2000;

import java.util.*;

import com.frankcooper.utils.PrintUtils;
import org.junit.Assert;

public class _1723 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


            int[] jobs = {1, 2, 4, 7, 8};
            int k = 2;
            handler.minimumTimeRequired(jobs, k);
        }


        /**
         * Integer.numberOfTrailingZeros()方法返回指定int值的二进制补码二进制表示形式中最低顺序（“最右”）一位之后的零位数目。
         * ----
         * Binary = 11000111
         * Count of one bits = 5
         * Number of trailing zeros : 0
         * ----
         *
         * @param jobs
         * @param k
         * @return
         */
        public int minimumTimeRequired(int[] jobs, int k) {
            int N = jobs.length;
            int[] sum = new int[1 << N];
            //sum[j]表示j这个状态下的总的工作量
            for (int i = 1; i < (1 << N); i++) {
                int x = Integer.numberOfTrailingZeros(i);
                int y = i - (1 << x);
                sum[i] = sum[y] + jobs[x];
//                System.out.printf("i:%d, bin: %s , x:%d , y:%d\n", i, PrintUtils.toBinaryString(i, 5), x, y);
            }

            /**
             * 下面的这段写法，等同于上面的
             */
//            for (int i = 1; i < (1 << N); i++) {
//                for (int j = 0; j < N; j++) {
//                    if ((i & (1 << j)) == 0) continue;
//                    sum[i] = sum[i - (1 << j)] + jobs[j];
//                    break;
//                }
//            }

            /**
             *         // 预处理每一个集合的完成时间
             *         for(int i = 0; i < 1 << n; i++){
             *             for(int j = 0; j < n; j++){
             *                 if((i >> j & 1) == 1) time[i] += jobs[j];
             *             }
             *         }
             */


            //dp[i][j]表示在给前i个工人分配工作，工作分配的状态是j的情况下，完成所有工作的最短时间
            int[][] dp = new int[k][1 << N];
            for (int j = 0; j < (1 << N); j++) {
                dp[0][j] = sum[j];
            }
            for (int i = 1; i < k; i++) {
                for (int j = 0; j < (1 << N); j++) {
                    int minn = Integer.MAX_VALUE / 2;
                    /**
                     *
                     // 遍历 u 的非空子集
                     for (int s = u; s; s = (s - 1) & u) {
                     // s 是 u 的一个非空子集
                     }
                     */
//                    for (int p = j; p > 0; p = (p - 1) ^ j) {
//                        minn = Math.min(minn, Math.max(dp[i - 1][j - p], sum[p]));
//                    }

//                    for (int p = j; p != 0; p = (p - 1) & j) {
//                        minn = Math.min(minn, Math.max(dp[i - 1][j - p], sum[p]));
//                    }
                    dp[i][j] = minn;
                }
            }
            return dp[k - 1][(1 << N) - 1];
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        /**
         * https://leetcode-cn.com/problems/find-minimum-time-to-finish-all-jobs/solution/zhuang-ya-dp-jing-dian-tao-lu-xin-shou-j-3w7r/
         * @param jobs
         * @param k
         * @return
         */

        public int minimumTimeRequired(int[] jobs, int k) {
            int n = jobs.length;
            int[] time = new int[1 << n];
            // 预处理每一个集合的完成时间
            for (int i = 0; i < 1 << n; i++) {
                for (int j = 0; j < n; j++) {
                    if ((i >> j & 1) == 1) time[i] += jobs[j];
                }
            }

            // f[state][i]表示当前集合为state, 且用到前i个工人的最小的最大工作时间
            int[][] f = new int[1 << n][k + 1];
            // 由于求最小值，初始化成一个大数
            for (int i = 0; i < 1 << n; i++) Arrays.fill(f[i], (int) 1e9);
            // 当前集合为0, 且用0个工人为合理状态，且完成时间为0
            f[0][0] = 0;
            // 枚举用了多少个工人
            for (int u = 1; u <= k; u++) {
                // 枚举集合
                for (int state = 1; state < 1 << n; state++) {
                    // 枚举子集
                    for (int sub = state; sub > 0; sub = (sub - 1) & state) {
                        // 先求出在state这个状态下所有划分出的所有子集与其差集的最大值
                        int t = Math.max(f[state - sub][u - 1], time[sub]);
                        // 求所有划分方案中的最小值
                        f[state][u] = Math.min(f[state][u], t);
                    }
                }
            }
            return f[(1 << n) - 1][k];
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
