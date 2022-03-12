package com.frankcooper.bank._201_300;

import com.frankcooper.utils.PrintUtils;

import java.util.LinkedList;
import java.util.Queue;

public class _289 {


    public static void main(String[] args) {
        int[][] board = PrintUtils.processSymbol("[[0,1,0],[0,0,1],[1,1,1],[0,0,0]]");
//        _1st handler = new _1st();
        _2nd handler = new _2nd();
        handler.gameOfLife(board);

    }


    /**
     * bfs
     */
    static class _1st {


        int R, C;
        int[][] dirs = new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}};


        public void gameOfLife(int[][] board) {
            if (board == null || (board.length == 0 || board[0].length == 0)) return;
            R = board.length;
            C = board[0].length;
            bfs(board, 0, 0);
//            PrintUtils.printMatrix(board);
            for (int r = 0; r < R; ++r)
                for (int c = 0; c < C; ++c)
                    //被标记的发生翻转
                    if (board[r][c] == -1) board[r][c] = 0;
                    else if (board[r][c] == -2) board[r][c] = 1;
//            PrintUtils.printMatrix(board);
        }


        public void bfs(int[][] board, int sr, int sc) {
            boolean[][] visited = new boolean[R][C];
            Queue<int[]> q = new LinkedList<>();
            q.offer(new int[]{sr, sc});
            visited[sr][sc] = true;//标记原始(sr,sc)被访问过
            while (!q.isEmpty()) {
                int[] curr = q.poll();
                int cr = curr[0], cc = curr[1];//当前点
                int cnt = 0;
                for (int[] d : dirs) {
                    int nr = cr + d[0], nc = cc + d[1];
                    if (!inArea(nr, nc)) continue;
                    //当前为1 或者我们暂时标记的-1 cnt++
                    if (board[nr][nc] == 1 || board[nr][nc] == -1) cnt++;
                    if (visited[nr][nc]) continue;
                    q.offer(new int[]{nr, nc});
                    visited[nr][nc] = true;
                }
//                System.out.printf("(%d,%d)-%d\n", cr, cc, cnt);
                if (board[cr][cc] == 1) {//当前细胞为活细胞
                    if (cnt < 2 || cnt > 3) board[cr][cc] = -1;//<2 >3 两种情况下需要设置当前的活细胞为死细胞，区别0这种，我们设置为-1
                    else if (cnt == 2 || cnt == 3) board[cr][cc] = 1;//等于2 等于3 活细胞继续活着
                } else if (board[cr][cc] == 0) {//当前细胞为死细胞
                    if (cnt == 3) board[cr][cc] = -2;//死细胞复活，区别1这种活细胞，设置为-2
                }
            }
        }


        private boolean inArea(int r, int c) {
            return r >= 0 && r < R && c >= 0 && c < C;
        }

    }


    /**
     * https://leetcode.com/problems/game-of-life/discuss/73223/Easiest-JAVA-solution-with-explanation
     * bit
     */
    static class _2nd {

        int R, C;

        public void gameOfLife(int[][] board) {
            if (board == null || board.length == 0) return;
            R = board.length;
            C = board[0].length;
            PrintUtils.printMatrix(board, true);
            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    int lives = getLiveNeighbors1(board, r, c);
                    //一开始的时候，所有的2nd都是0，因此只需要确定2nd是否变成1
                    // 01 --> 11 做2nd位 3想成是十进制的11
                    if (board[r][c] == 1 && (lives >= 2 && lives <= 3)) board[r][c] = 3;
                    //00  --> 10 做2nd位 2想成是十进制的10
                    if (board[r][c] == 0 && lives == 3) board[r][c] = 2;
                }
            }
            PrintUtils.printMatrix(board, true);
            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    board[r][c] >>= 1;
                }
            }

        }


        public int getLiveNeighbors(int[][] board, int sr, int sc) {
            int lives = 0;
            //遍历的时候拿的是sr，sc一圈为1的9个点，有1的+1
            for (int r = Math.max(sr - 1, 0); r <= Math.min(sr + 1, R - 1); r++) {
                for (int c = Math.max(sc - 1, 0); c <= Math.min(sc + 1, C - 1); c++) {
                    lives += board[r][c] & 1;
                }
            }
            //去掉自己是1的情况
            lives -= board[sr][sc] & 1;
            return lives;
        }


        public int getLiveNeighbors1(int[][] board, int sr, int sc) {
            int[][] dirs = new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}};
            int lives = 0;
            for (int[] d : dirs) {
                int nr = sr + d[0], nc = sc + d[1];
                if (!inArea(nr, nc)) continue;
                lives += board[nr][nc] & 1;
            }
            return lives;
        }

        private boolean inArea(int r, int c) {
            return r >= 0 && r < R && c >= 0 && c < C;
        }


    }


}
