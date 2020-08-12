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


    static class _2nd extends _130 {

        public static void main(String[] args) {
            char[][] board = {{'O', 'X', 'X', 'O', 'X'}, {'X', 'O', 'O', 'X', 'O'}, {'X', 'O', 'X', 'O', 'X'}, {'O', 'X', 'O', 'O', 'O'}, {'X', 'X', 'O', 'X', 'O'}};
            _2nd handler = new _2nd();
            board = new char[][]{{'X', 'O', 'X', 'X'}, {'O', 'X', 'O', 'X'}, {'X', 'O', 'X', 'O'}, {'O', 'X', 'O', 'X'}};
            handler.solve(board);
        }


        static class UnionFind {
            int[] parent;

            public UnionFind(int initValue) {
                parent = new int[initValue];
                for (int i = 0; i < initValue; i++) {
                    parent[i] = -1;
                }
            }

            public void union(int x, int y) {
                int rootX = find(x);
                int rootY = find(y);
                if (rootX != rootY) {
                    parent[rootX] = rootY;
                }
            }

            public int find(int x) {
                int rootX = x;
                while (parent[rootX] != -1) {
                    rootX = parent[rootX];
                }
                return rootX;
            }
        }

        int m, n;
        int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        public void solve(char[][] board) {
            if (board == null || board.length == 0) return;
            System.out.println(JSON.toJSONString(board));
            m = board.length;
            n = board[0].length;
            int initValue = m * n + 1;
            UnionFind unionFind = new UnionFind(initValue);
            int dummy = m * n;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (board[i][j] == 'O') {
                        if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                            unionFind.union(node(i, j), dummy);
                        } else {
                            for (int k = 0; k < directions.length; k++) {
                                int nextI = i + directions[k][0];
                                int nextJ = j + directions[k][1];
//                                System.out.println(String.format("%d:%d", nextI, nextJ));
                                if ((nextI > 0 || nextI < m  || nextJ > 0  || nextJ < n) && board[nextI][nextJ] == 'O') {
                                    unionFind.union(node(i, j), node(nextI, nextJ));
                                }
//                                if (i + directions[i][0] > 0&&board[i])
                            }
//                            if (i > 0 && board[i - 1][j] == 'O') {
//                                unionFind.union(node(i, j), node(i - 1, j));
//                            }
//                            if (i < m - 1 && board[i + 1][j] == 'O') {
//                                unionFind.union(node(i, j), node(i + 1, j));
//                            }
//                            if (j > 0 && board[i][j - 1] == 'O') {
//                                unionFind.union(node(i, j), node(i, j - 1));
//                            }
//                            if (j < n - 1 && board[i][j + 1] == 'O') {
//                                unionFind.union(node(i, j), node(i, j + 1));
//                            }
                        }
                    }
                }
            }
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (unionFind.find(node(i, j)) == unionFind.find(dummy)) {
                        board[i][j] = 'O';
                    } else {
                        board[i][j] = 'X';
                    }
                }
            }
            System.out.println(JSON.toJSONString(board));
        }

        public int node(int i, int j) {
            return i * n + j;
        }


    }

}
