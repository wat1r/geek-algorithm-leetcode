package com.frankcooper.bank;

import com.alibaba.fastjson.JSON;

/**
 * @Date 2020/8/11
 * @Author Frank Cooper
 * @Description
 */
public class _130 {

    static _130 handler = new _130();

    public static void main(String[] args) {
        char[][] board = {{'O', 'O', 'O'}, {'O', 'O', 'O'}, {'O', 'O', 'O'}};
        _1st handler = new _1st();
        handler.solve(board);

    }


    static class _1st extends _130 {


        int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        int m, n;

        public void solve(char[][] board) {
            if (board == null || board.length == 0) return;
            m = board.length;//行
            n = board[0].length;//列
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if ((i == 0 || i == m - 1 || j == 0 || j == n - 1) && board[i][j] == 'O') {
                        dfs(board, i, j);
                    }
                }
            }
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (board[i][j] == 'O') {
                        board[i][j] = 'X';
                    }
                    if (board[i][j] == '#') {
                        board[i][j] = 'O';
                    }
                }
            }
        }
        private void dfs(char[][] board, int i, int j) {
            if (i < 0 || i > m - 1 || j < 0 || j > n - 1 || board[i][j] != 'O') {
                return;
            }
            board[i][j] = '#';
            for (int[] direction : directions) {
                dfs(board, i + direction[0], j + direction[1]);
            }
        }


    }

}
