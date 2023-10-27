package com.frankcooper.platform.leetcode.bank._1_100;


import java.util.ArrayList;
import java.util.*;
import java.util.Set;

public class _51 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            handler.solveNQueens(4);

        }

        List<List<String>> res = new ArrayList<>();
        int n;

        public List<List<String>> solveNQueens(int n) {
            this.n = n;
            int[] queens = new int[n];
            Arrays.fill(queens, -1);
            Set<Integer> cols = new HashSet<>();
            Set<Integer> dia1 = new HashSet<>();
            Set<Integer> dia2 = new HashSet<>();
            backtrack(queens, 0, cols, dia1, dia2);
            return res;
        }


        /**
         * @param queens 当前这一行row，哪个位置会被皇后占据
         * @param row    当前编辑的行
         * @param cols   列中已经被皇后占据的位置（列索引）
         * @param dia1   斜角1被皇后占据的位置 （row - i） 行列之差相等
         * @param dia2   斜角2被皇后占据的位置 （row + i） 行列之和相等
         */
        public void backtrack(int[] queens, int row, Set<Integer> cols, Set<Integer> dia1, Set<Integer> dia2) {
            if (row == n) {
                res.add(build(queens));
                return;
            }
            for (int i = 0; i < n; i++) {
                if (cols.contains(i)) continue;
                if (dia1.contains(row - i)) continue;
                if (dia2.contains(row + i)) continue;
                //当前这一行row，哪个位置会被皇后占据
                queens[row] = i;
                cols.add(i);
                dia1.add(row - i);
                dia2.add(row + i);
                backtrack(queens, row + 1, cols, dia1, dia2);
                //恢复现场
                dia2.remove(row + i);
                dia1.remove(row - i);
                cols.remove(i);
                queens[row] = -1;
            }
        }

        private List<String> build(int[] queens) {
            List<String> board = new ArrayList<String>();
            for (int i = 0; i < n; i++) {
                char[] row = new char[n];
                Arrays.fill(row, '.');
                row[queens[i]] = 'Q';
                board.add(new String(row));
            }
            return board;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            handler.solveNQueens(4);
        }

        List<List<String>> res = new ArrayList<>();

        public List<List<String>> solveNQueens(int n) {
            char[][] board = new char[n][n];
            for (int i = 0; i < n; i++) {
                Arrays.fill(board[i], '.');
            }
            backtrack(board, 0);
            return res;
        }

        private void backtrack(char[][] board, int row) {
            if (row == board.length) {
                res.add(build(board));
                return;
            }
            for (int col = 0; col < board.length; col++) {
                if (check(board, row, col)) {
                    char[][] t = copy(board);
                    t[row][col] = 'Q';
                    backtrack(t, row + 1);
                }
            }
        }

        //把二维数组chess中的数据测下copy一份
        private char[][] copy(char[][] board) {
            char[][] t = new char[board.length][board[0].length];
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    t[i][j] = board[i][j];
                }
            }
            return t;
        }


        private boolean check(char[][] board, int row, int col) {
            for (int r = 0; r < row; r++) {
                if (board[r][col] == 'Q') return false;
            }
            //右上角
            for (int r = row - 1, c = col + 1; r >= 0 && c < board.length; r--, c++) {
                if (board[r][c] == 'Q') return false;
            }
            //左上角
            for (int r = row - 1, c = col - 1; r >= 0 && c >= 0; r--, c--) {
                if (board[r][c] == 'Q') return false;
            }
            return true;
        }


        private List<String> build(char[][] board) {
            List<String> res = new ArrayList<>();
            for (int i = 0; i < board.length; i++) {
                res.add(new String(board[i]));
            }
            return res;
        }

    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
            handler.solveNQueens(4);
        }

        List<List<String>> ans = new ArrayList<>();
        List<String> path = new ArrayList<>();
        int[] chessboard;//存储第i行皇后在第几列

        public List<List<String>> solveNQueens(int n) {
            chessboard = new int[n];
            Arrays.fill(chessboard, -1);
            dfs(0, n);
            return ans;
        }

        public void dfs(int row, int n) {
            //判断目前棋盘是否满足要求
            //现在要放入第row行的皇后，所以判断第row-1行皇后是否满足要求
            if (!isValid(row - 1)) {
                return;
            }
            if (row == n) {
                ans.add(new ArrayList<>(path));
                return;
            }
            for (int i = 0; i < n; i++) {
                //做选择
                StringBuilder str = new StringBuilder();
                for (int j = 0; j < i; j++) {
                    str.append('.');
                }
                str.append('Q');
                for (int j = i + 1; j < n; j++) {
                    str.append('.');
                }
                path.add(str.toString());
                chessboard[row] = i;
                //进入下一层决策
                dfs(row + 1, n);
                //恢复现场
                path.remove(path.size() - 1);
                chessboard[row] = -1;
            }
        }

        public boolean isValid(int row) {
            if (row < 1) {
                return true;
            }
            int col = chessboard[row];
            for (int i = 0; i < row; i++) {
                //同一列有皇后
                if (chessboard[i] == col) {
                    return false;
                }
                // “/”方向有皇后
                if (chessboard[i] + i == row + col) {
                    return false;
                }
                // “\”方向有皇后
                if (chessboard[i] - i == col - row) {
                    return false;
                }
            }
            return true;
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
