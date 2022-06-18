package com.frankcooper.platform.leetcode.bank._1_100;

public class _79 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        int m, n;
        char[][] board;

        public boolean exist(char[][] board, String word) {
            this.m = board.length;
            this.n = board[0].length;
            this.board = board;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (this.board[i][j] == word.charAt(0) && dfs(word, i, j, 0)) return true;
                }
            }
            return false;
        }

        private boolean dfs(String word, int i, int j, int idx) {
            if (i < 0 || i >= m || j < 0 || j >= n || board[i][j] != word.charAt(idx) || board[i][j] == '#')
                return false;
            if (idx == word.length() - 1) return true;
            board[i][j] = '#';
            for (int d = 0; d < dx.length; d++) {
                if (dfs(word, i + dx[d], j + dy[d], idx + 1)) return true;
            }
            board[i][j] = word.charAt(idx);
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
