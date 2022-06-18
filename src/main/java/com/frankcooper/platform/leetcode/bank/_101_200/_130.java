package com.frankcooper.platform.leetcode.bank._101_200;

import com.alibaba.fastjson.JSON;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

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
                                if ((nextI > 0 || nextI < m || nextJ > 0 || nextJ < n) && board[nextI][nextJ] == 'O') {
                                    unionFind.union(node(i, j), node(nextI, nextJ));
                                }
//                                if (i + directions[i][0] > 0&&board[i])
                            }
//                            if (i > 0 && board[i - 1][j] == 'O') {
//                                unionFind.union(Node(i, j), Node(i - 1, j));
//                            }
//                            if (i < m - 1 && board[i + 1][j] == 'O') {
//                                unionFind.union(Node(i, j), Node(i + 1, j));
//                            }
//                            if (j > 0 && board[i][j - 1] == 'O') {
//                                unionFind.union(Node(i, j), Node(i, j - 1));
//                            }
//                            if (j < n - 1 && board[i][j + 1] == 'O') {
//                                unionFind.union(Node(i, j), Node(i, j + 1));
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


    static class _3rd extends _130 {


        public static void main(String[] args) {
            char[][] board = {{'O', 'X', 'X', 'O', 'X'}, {'X', 'O', 'O', 'X', 'O'}, {'X', 'O', 'X', 'O', 'X'}, {'O', 'X', 'O', 'O', 'O'}, {'X', 'X', 'O', 'X', 'O'}};
            _3rd handler = new _3rd();
            board = new char[][]{{'X', 'O', 'X', 'X'}, {'O', 'X', 'O', 'X'}, {'X', 'O', 'X', 'O'}, {'O', 'X', 'O', 'X'}};
            handler.solve(board);
        }


        static class UnionFindSet {
            int[] parents;
            int[] ranks;

            public UnionFindSet(int n) {
                parents = new int[n];
                ranks = new int[n];
                for (int i = 0; i < n; i++) {
                    parents[i] = i;
                }
            }


            public int find(int x) {
                if (x != parents[x]) {
                    parents[x] = find(parents[x]);
                }
                System.out.println(x + ":" + parents[x]);
                return parents[x];
            }

            public void union(int x, int y) {
                int rootX = find(x);
                int rootY = find(y);
                if (rootX == rootY) return;
                if (ranks[rootX] > ranks[rootY]) parents[rootY] = rootX;
                if (ranks[rootX] < ranks[rootY]) parents[rootX] = rootY;
                if (ranks[rootX] == ranks[rootY]) {
                    parents[rootY] = rootX;
                    ranks[rootY]++;
                }
            }
        }


        int m, n;
        int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        public void solve(char[][] board) {
            if (board == null || board.length == 0) return;
            m = board.length;
            n = board[0].length;
            int initValue = m * n + 1;
            UnionFindSet unionFindSet = new UnionFindSet(initValue);
            int dummy = m * n;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (board[i][j] == 'O') {
                        if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                            unionFindSet.union(node(i, j), dummy);
                        } else {
                            for (int k = 0; k < directions.length; k++) {
                                int nextI = i + directions[k][0];
                                int nextJ = j + directions[k][1];
                                if ((nextI > 0 || nextI < m || nextJ > 0 || nextJ < n) && board[nextI][nextJ] == 'O') {
                                    unionFindSet.union(node(i, j), node(nextI, nextJ));
                                }
                            }
                        }
                    }
                }
            }
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (unionFindSet.find(node(i, j)) == unionFindSet.find(dummy)) {
                        board[i][j] = 'O';
                    } else {
                        board[i][j] = 'X';
                    }
                }
            }
        }

        public int node(int i, int j) {
            return i * n + j;
        }

    }


    static class _4th extends _130 {

        public static void main(String[] args) {
            char[][] board = {{'O', 'X', 'X', 'O', 'X'}, {'X', 'O', 'O', 'X', 'O'}, {'X', 'O', 'X', 'O', 'X'}, {'O', 'X', 'O', 'O', 'O'}, {'X', 'X', 'O', 'X', 'O'}};
            _4th handler = new _4th();
            board = new char[][]{{'X', 'O', 'X', 'X'}, {'O', 'X', 'O', 'X'}, {'X', 'O', 'X', 'O'}, {'O', 'X', 'O', 'X'}};
            board = new char[][]{{'O', 'X', 'X', 'O', 'X'}, {'X', 'O', 'O', 'X', 'O'}, {'X', 'O', 'X', 'O', 'X'}, {'O', 'X', 'O', 'O', 'O'}, {'X', 'X', 'O', 'X', 'O'}};
            board = new char[][]{{'O', 'O', 'O'}, {'O', 'O', 'O'}, {'O', 'O', 'O'}};
            board = new char[][]{{'O', 'X', 'X', 'O', 'X'}, {'X', 'X', 'X', 'X', 'O'}, {'X', 'X', 'X', 'X', 'X'}, {'O', 'X', 'O', 'O', 'O'}, {'X', 'X', 'O', 'X', 'O'}};
            handler.solve(board);
        }


        class Position {
            int i, j;

            public Position(int i, int j) {
                this.i = i;
                this.j = j;
            }
        }

        int m, n;

        public void solve(char[][] board) {
            System.out.println(JSON.toJSONString(board));
            if (board == null || board.length == 0) return;
            m = board.length;
            n = board[0].length;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (i == 0 && j == 1) {
                        System.out.println("ee");
                    }
                    if ((i == 0 || i == m - 1 || j == 0 || j == n - 1) && board[i][j] == 'O') {
                        dfs(board, i, j);
                    }
                }
            }
            System.out.println(JSON.toJSONString(board));

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
            System.out.println(JSON.toJSONString(board));

        }

        int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        private void dfs(char[][] board, int i, int j) {
            Stack<Position> stack = new Stack<>();
            stack.push(new Position(i, j));
            board[i][j] = '#';
            while (!stack.isEmpty()) {
                for (int k = 0; k < directions.length; k++) {
                    Position curr = stack.peek();
                    int nextI = curr.i + directions[k][0];
                    int nextJ = curr.j + directions[k][1];
                    System.out.println(nextI + ":" + nextJ);
                    if (nextI < 0 || nextI >= m || nextJ < 0 || nextJ >= n) continue;
                    if (board[nextI][nextJ] == 'O') {
                        stack.push(new Position(nextI, nextJ));
                        board[nextI][nextJ] = '#';
                        break;
                    }
                }
                stack.pop();
            }
        }


    }


    static class _5th extends _130 {

        public static void main(String[] args) {
            char[][] board = {{'O', 'X', 'X', 'O', 'X'}, {'X', 'O', 'O', 'X', 'O'}, {'X', 'O', 'X', 'O', 'X'}, {'O', 'X', 'O', 'O', 'O'}, {'X', 'X', 'O', 'X', 'O'}};
            _5th handler = new _5th();
            board = new char[][]{{'X', 'O', 'X', 'X'}, {'O', 'X', 'O', 'X'}, {'X', 'O', 'X', 'O'}, {'O', 'X', 'O', 'X'}};
            board = new char[][]{{'O', 'X', 'X', 'O', 'X'}, {'X', 'O', 'O', 'X', 'O'}, {'X', 'O', 'X', 'O', 'X'}, {'O', 'X', 'O', 'O', 'O'}, {'X', 'X', 'O', 'X', 'O'}};
            board = new char[][]{{'O', 'O', 'O'}, {'O', 'O', 'O'}, {'O', 'O', 'O'}};
            board = new char[][]{{'O', 'X', 'X', 'O', 'X'}, {'X', 'X', 'X', 'X', 'O'}, {'X', 'X', 'X', 'X', 'X'}, {'O', 'X', 'O', 'O', 'O'}, {'X', 'X', 'O', 'X', 'O'}};
            handler.solve(board);
        }


        class Position {
            int i, j;

            public Position(int i, int j) {
                this.i = i;
                this.j = j;
            }
        }

        int m, n;

        public void solve(char[][] board) {
            System.out.println(JSON.toJSONString(board));
            if (board == null || board.length == 0) return;
            m = board.length;
            n = board[0].length;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (i == 0 && j == 1) {
                        System.out.println("ee");
                    }
                    if ((i == 0 || i == m - 1 || j == 0 || j == n - 1) && board[i][j] == 'O') {
                        bfs(board, i, j);
                    }
                }
            }
            System.out.println(JSON.toJSONString(board));

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
            System.out.println(JSON.toJSONString(board));

        }

        int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        private void bfs(char[][] board, int i, int j) {
            Queue<Position> queue = new LinkedList<>();
            queue.offer(new Position(i, j));
            board[i][j] = '#';
            while (!queue.isEmpty()) {
                Position curr = queue.poll();
                for (int k = 0; k < directions.length; k++) {
                    int nextI = curr.i + directions[k][0];
                    int nextJ = curr.j + directions[k][1];
                    System.out.println(nextI + ":" + nextJ);
                    if (nextI < 0 || nextI >= m || nextJ < 0 || nextJ >= n) continue;
                    if (board[nextI][nextJ] == 'O') {
                        queue.offer(new Position(nextI, nextJ));
                        board[nextI][nextJ] = '#';
                    }
                }
            }
        }


    }
}
