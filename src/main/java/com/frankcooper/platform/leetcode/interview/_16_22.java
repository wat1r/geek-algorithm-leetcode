package com.frankcooper.platform.leetcode.interview;

import java.util.*;

public class _16_22 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            handler.printKMoves(5);

        }

        //方向：右 下 左 上
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int[][] hash = new int[3000][3000];//地板 默认值都为0 为白色
        int p = 0; //当前的指向
        //因为题目没有给边界范围，只是跑过题目的case，这些边界需要试一下
        public List<String> printKMoves(int K) {
            int x = 2000, y = 2000, minx = x, maxx = x, miny = y, maxy = y;
            char[] pos = {'R', 'D', 'L', 'U'};
            char[] board = {'_', 'X'};
            for (int i = 0; i < K; i++) {
                int d = 1;
                hash[x][y] ^= 1;
                p = (p + d) % 4;
                x += dirs[p][0];
                y += dirs[p][1];
                minx = Math.min(minx, x);
                maxx = Math.max(maxx, x);
                miny = Math.min(miny, y);
                maxy = Math.max(maxy, y);
            }
            List<String> res = new ArrayList<>();
            for (int i = minx; i <= maxx; i++) {
                String t = "";
                for (int j = miny; j <= maxy; j++) {
                    if (i == x && j == y) {
                        t = t + pos[p];
                    } else {
                        t = t + board[hash[i][j]];
                    }
                }
                res.add(t);
            }
            return res;

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
