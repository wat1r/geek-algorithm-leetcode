package com.frankcooper.platform.leetcode.bank._2001_3000;

import org.junit.Assert;

public class _2087 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int startPos[] = {1, 0}, homePos[] = {2, 3}, rowCosts[] = {5, 4, 3}, colCosts[] = {8, 2, 6, 7};
            Assert.assertEquals(18, handler.minCost(startPos, homePos, rowCosts, colCosts));

        }


        //TLE
        int[][] dirs = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        int minCost = Integer.MAX_VALUE;
        int m, n;
        boolean[][] vis;
        int[] rowCosts;
        int[] colCosts;

        public int minCost(int[] startPos, int[] homePos, int[] rowCosts, int[] colCosts) {
            m = rowCosts.length;
            n = colCosts.length;
            vis = new boolean[m][n];
            this.rowCosts = rowCosts;
            this.colCosts = colCosts;
            move(startPos, homePos, 0);
            return minCost;
        }


        private void move(int[] startPos, int[] homePos, int cost) {
            if (minCost != Integer.MAX_VALUE && cost >= minCost) {
                return;
            }
            if (startPos[0] == homePos[0] && startPos[1] == homePos[1]) {
                minCost = Math.min(minCost, cost);
                return;
            }
            vis[startPos[0]][startPos[1]] = true;
            for (int k = 0; k <= 3; k++) {
                int nr = startPos[0] + dirs[k][0], nc = startPos[1] + dirs[k][1];
                if (nr >= 0 && nr < m && nc >= 0 && nc < n && !vis[nr][nc]) {
                    if (k <= 1) {
                        move(new int[]{nr, nc}, homePos, cost + rowCosts[nr]);
                    } else {
                        move(new int[]{nr, nc}, homePos, cost + colCosts[nc]);
                    }
                }
            }
            vis[startPos[0]][startPos[1]] = false;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            int startPos[] = {1, 0}, homePos[] = {2, 3}, rowCosts[] = {5, 4, 3}, colCosts[] = {8, 2, 6, 7};
//            Assert.assertEquals(18, handler.minCost(startPos, homePos, rowCosts, colCosts));

            startPos = new int[]{5, 5};
            homePos = new int[]{5, 2};
            rowCosts = new int[]{7, 1, 3, 3, 5, 3, 22, 10, 23};
            colCosts = new int[]{5, 5, 6, 2, 0, 16};
            Assert.assertEquals(8, handler.minCost(startPos, homePos, rowCosts, colCosts));


        }


        public int minCost(int[] startPos, int[] homePos, int[] rowCosts, int[] colCosts) {
            int R = rowCosts.length, C = colCosts.length;
            int sr = startPos[0], sc = startPos[1];
            int er = homePos[0], ec = homePos[1];
            int cost = 0;
            int r_delta = (sr <= er ? 1 : -1);
            for (int i = sr + r_delta; r_delta == 1 ? i <= er : i >= er; i += r_delta) {
                if (i < R && i >= 0) cost += rowCosts[i];
            }
            int c_delta = (sc <= ec ? 1 : -1);
            for (int j = sc + c_delta; c_delta == 1 ? j <= ec : j >= ec; j += c_delta) {
                if (j < C && j >= 0) cost += colCosts[j];
            }
            return cost;
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
