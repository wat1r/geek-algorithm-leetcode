package com.frankcooper.bank._801_900;

public class _883 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
        }


        public int projectionArea(int[][] grid) {
            int xy = 0, yz = 0, zx = 0;
            int R = grid.length, C = grid[0].length;
            for (int r = 0; r < R; r++) {
                int yzHeight = 0, zxHeight = 0;
                for (int c = 0; c < C; c++) {
                    xy += (grid[r][c] != 0 ? 1 : 0);//有多少不是0的格子就有多大的面积
                    yzHeight = Math.max(yzHeight, grid[r][c]);
                    zxHeight = Math.max(zxHeight, grid[c][r]);//调转行列坐标
                }
                yz += yzHeight;
                zx += zxHeight;
            }
            return xy + yz + zx;
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
