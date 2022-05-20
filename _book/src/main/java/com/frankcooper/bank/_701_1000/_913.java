package com.frankcooper.bank._701_1000;

import java.util.*;

import org.junit.Assert;

public class _913 {


    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[][] graph = {{2, 5}, {3}, {0, 4, 5}, {1, 4, 5}, {2, 3}, {0, 2, 3}};
            Assert.assertEquals(0, handler.catMouseGame(graph));

        }

        //static 关键词不加 会超时 //TLE 54 / 57
        static int N = 55;
        //        f[k][i][j]表示当前进行了k步，老鼠在位置i处，猫在位置j处，当前的博弈结果，0为平局。1为老鼠获胜，2为猫获胜
        //
        static int[][][] f = new int[2 * N * N][N][N];
        int[][] graph;
        int n;


        public int catMouseGame(int[][] graph) {
            this.graph = graph;
            n = graph.length;
            for (int k = 0; k < 2 * n * n; k++) {
                for (int i = 0; i < n; i++)
                    Arrays.fill(f[k][i], -1);
            }
            return dfs(0, 1, 2);
        }

        //进行了k步，老鼠在位置i处，猫在位置j处，当前的博弈结果，0为平局。1为老鼠获胜，2为猫获胜
        public int dfs(int k, int i, int j) {
            int res = f[k][i][j];
            if (i == 0) res = 1;
            else if (i == j) res = 2;
            else if (k >= 2 * n * n) res = 0;
            else if (res == -1) {
                //老鼠走的是0 2 4 偶数步
                if (k % 2 == 0) {
                    boolean win = false, draw = false;
                    for (int ne : graph[i]) {
//                        System.out.printf("[mouse] k:%d , i:%d -> ne:%d\n", k, i, ne);
                        int t = dfs(k + 1, ne, j);
                        if (t == 1) win = true;
                        else if (t == 0) draw = true;
                        if (win) {
                            break;
                        }
                    }
                    if (win) res = 1;
                    else if (draw) res = 0;
                    else res = 2;
                } else {//猫走的是1 3 5 奇数步
                    boolean win = false, draw = false;
                    for (int ne : graph[j]) {
                        if (ne == 0) continue;
//                        System.out.printf("[cat  ] k:%d , j:%d -> ne:%d\n", k, j, ne);
                        int t = dfs(k + 1, i, ne);
                        if (t == 2) win = true;
                        else if (t == 0) draw = true;
                        if (win) {
                            break;
                        }
                    }
                    if (win) res = 2;
                    else if (draw) res = 0;
                    else res = 1;
                }
            }
            return f[k][i][j] = res;
        }


    }


    //[[5,7,9],[3,4,5,6],[3,4,5,8],[1,2,6,7],[1,2,5,7,9],[0,1,2,4,8],[1,3,7,8],[0,3,4,6,8],[2,5,6,7,9],[0,4,8]]
    //过不了
    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            int[][] graph = {{2, 5}, {3}, {0, 4, 5}, {1, 4, 5}, {2, 3}, {0, 2, 3}};
//            Assert.assertEquals(0, handler.catMouseGame(graph));
        }

        /**
         * 平局
         */
        private static final int DRAW = 0;
        /**
         * 老鼠赢
         */
        private static final int MOUSE_WIN = 1;
        /**
         * 猫赢
         */
        private static final int CAT_WIN = 2;
        int[][][] memo;
        int n;

        public int catMouseGame(int[][] graph) {
            n = graph.length;
            memo = new int[n][n][2 * n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    Arrays.fill(memo[i][j], -1);
                }
            }
            return dfs(graph, 2, 1, 0);
        }

        private int dfs(int[][] graph, int i, int j, int turns) {
            // 超过节点数两倍，可以确定为平局 这里的处理不严谨
            if (turns >= 2 * n) return DRAW;
            // 缓存中有，直接返回
            if (memo[i][j][turns] != -1) return memo[i][j][turns];
            // 老鼠赢
            if (j == 0) return memo[i][j][turns] = MOUSE_WIN;
            // 猫赢
            if (i == j) return memo[i][j][turns] = CAT_WIN;

            // turns 为偶数是轮到老鼠走，为奇数是轮到猫走
            if (turns % 2 == 0) {
                // 老鼠最坏情况是猫赢
                int ans = CAT_WIN;
                // 尝试走到下一个节点
                for (int ne : graph[j]) {
                    int t = dfs(graph, i, ne, turns + 1);
                    // 如果老鼠可以赢，直接返回
                    if (t == MOUSE_WIN) return memo[i][j][turns] = MOUSE_WIN;
                    // 有平局，先记录为平局，后面如果有老鼠可以赢的场景，通过上述语句可以返回
                    if (t == DRAW) ans = DRAW;
                }
                // 返回老鼠走的结果
                return memo[i][j][turns] = ans;
            } else {
                // 猫最坏情况是老鼠赢
                int ans = MOUSE_WIN;
                for (int ne : graph[i]) {
                    // 注意猫不能走到0号节点
                    if (ne != 0) {
                        // 尝试进入下一个节点
                        int nextAns = dfs(graph, ne, j, turns + 1);
                        // 如果猫可以赢，直接返回
                        if (nextAns == CAT_WIN) return memo[i][j][turns] = CAT_WIN;
                        // 有平局，先记录为平局，后面如果有猫可以赢的场景，通过上述语句可以返回
                        if (nextAns == DRAW) ans = DRAW;
                    }
                }
                // 返回猫走的结果
                return memo[i][j][turns] = ans;
            }
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
