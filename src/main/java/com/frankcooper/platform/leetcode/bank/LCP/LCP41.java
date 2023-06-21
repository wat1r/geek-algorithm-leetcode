package com.frankcooper.platform.leetcode.bank.LCP;

import java.util.*;

import com.frankcooper.utils.PrintUtils;
import org.junit.Assert;

public class LCP41 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            String[] chessboard = new String[]{"....X.", "....X.", "XOOO..", "......", "......"};
            Assert.assertEquals(3, handler.flipChess(chessboard));

        }

        int m, n;
        int[][] directions = new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}};
        char BLACK = 'X';
        char EMPTY = '.';

        public int flipChess(String[] chessboard) {
            m = chessboard.length;
            n = chessboard[0].length();
            int res = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (chessboard[i].charAt(j) == EMPTY) {
                        if (i == 2 && j == 4) {
                            System.out.println();
                        }
                        res = Math.max(res, bfs(chessboard, i, j));
                    }
                }
            }
            return res;
        }


        public int bfs(String[] chessboard, int px, int py) {
            char[][] board = new char[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    board[i][j] = chessboard[i].charAt(j);
                }
            }
            Queue<int[]> q = new ArrayDeque<>();
            q.offer(new int[]{px, py});
            board[px][py] = BLACK;
            int cnt = 0;
            while (!q.isEmpty()) {
                int[] curr = q.poll();
                for (int[] d : directions) {
                    if (judge(board, curr[0], curr[1], d)) {
                        int dx = d[0], dy = d[1];
                        int x = curr[0] + dx, y = curr[1] + dy;
                        while (board[x][y] != BLACK) {//等价于 'O'
                            q.offer(new int[]{x, y});
                            board[x][y] = BLACK;
                            x += dx;
                            y += dy;
                            cnt++;
                        }
                    }
                }
            }
            return cnt;
        }


        public boolean judge(char[][] board, int x, int y, int[] d) {
            int dx = d[0], dy = d[1];
            x += dx;
            y += dy;
            while (x >= 0 && x < m && y >= 0 && y < n) {
                if (board[x][y] == BLACK) {
                    return true;
                } else if (board[x][y] == EMPTY) {
                    return false;
                }
                x += dx;
                y += dy;
            }
            return false;
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
