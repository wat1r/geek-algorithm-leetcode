package com.frankcooper.platform.leetcode.bank._1001_1500;

public class _1473 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }

        /**
         * https://leetcode-cn.com/problems/paint-house-iii/solution/gong-shui-san-xie-san-wei-dong-tai-gui-h-ud7m/
         * 定义 f[i][j][k] 为考虑前 i 间房子，且第 i 间房子的颜色编号为 j，前 i 间房子形成的分区数量为 k 的所有方案中的「最小上色成本」
         */

        public int minCost(int[] houses, int[][] cost, int m, int n, int t) {
            int INF = Integer.MAX_VALUE / 2;
            int[][][] f = new int[m + 1][n + 1][t + 1];
            for (int i = 0; i <= m; i++) {//分区数量为0的情况
                for (int j = 0; j <= n; j++) {
                    f[i][j][0] = INF;
                }
            }
            for (int i = 1; i <= m; i++) {
                int color = houses[i - 1];
                for (int j = 1; j <= n; j++) {
                    for (int k = 1; k <= t; k++) {
                        if (k > i) {//分区数量大于房子数量的时候，状态无效
                            f[i][j][k] = INF;
                            continue;
                        }
                        if (color != 0) //已经上色了
                        {
                            if (j == color) {// 只有与「本来的颜色」相同的状态才允许被转移
                                int tmp = INF;
                                // 先从所有「第 i 间房形成新分区」方案中选最优（即与上一房间颜色不同）
                                for (int p = 1; p <= n; p++) {
                                    if (p != j) {
                                        tmp = Math.min(tmp, f[i - 1][p][k - 1]);
                                    }
                                }
                                // 再结合「第 i 间房不形成新分区」方案中选最优（即与上一房间颜色相同）
                                f[i][j][k] = Math.min(f[i - 1][j][k], tmp);
                            } else {// 其余状态无效
                                f[i][j][k] = INF;
                            }

                        } else {//没有上色
                            int c = cost[i - 1][j - 1];
                            int tmp = INF;
                            for (int p = 1; p <= n; p++) {
                                if (p != j) tmp = Math.min(tmp, f[i - 1][p][k - 1]);
                            }
                            f[i][j][k] = Math.min(tmp, f[i - 1][j][k]) + c;
                        }

                    }

                }
            }
            int res = INF;
            for (int j = 1; j <= n; j++) {
                res = Math.min(res, f[m][j][t]);
            }
            return res == INF ? -1 : res;
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
