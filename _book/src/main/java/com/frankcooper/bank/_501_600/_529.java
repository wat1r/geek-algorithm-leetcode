package com.frankcooper.platform.leetcode.bank._501_600;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Date 2020/8/26
 * @Author Frank Cooper
 * @Description
 */
public class _529 {


    class _3rd {

        int R, C;
        int[][] dirs = new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}};

        public char[][] updateBoard(char[][] board, int[] click) {
            R = board.length;
            C = board[0].length;
            dfs(board, click[0], click[1]);
            return board;
        }

        public void dfs(char[][] board, int r, int c) {
            if (!inArea(r, c)) return;
            if (board[r][c] == 'M') {
                board[r][c] = 'X';
                return;
            }
            if (board[r][c] == 'E') {
                detect(board, r, c);
                if (board[r][c] == 'B') {
                    for (int[] d : dirs) {
                        int nr = r + d[0], nc = c + d[1];
                        dfs(board, nr, nc);
                    }
                }
            }
        }

        public void detect(char[][] board, int r, int c) {
            int bomb = 0;
            for (int[] d : dirs) {
                int nr = r + d[0], nc = c + d[1];
                if (inArea(nr, nc) && board[nr][nc] == 'M') bomb++;
            }
            board[r][c] = bomb > 0 ? (char) (bomb + '0') : 'B';
        }


        private boolean inArea(int r, int c) {
            return r >= 0 && r < R && c >= 0 && c < C;
        }

    }


    /**
     * dfs
     */
    class _2nd {
        int m, n;
        int[][] directions = new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}};

        public char[][] updateBoard(char[][] board, int[] click) {

            m = board.length;
            n = board[0].length;
            dfs(board, click[0], click[1]);


            return board;
        }

        private void dfs(char[][] board, int x, int y) {
            if (!inArea(x, y)) return;
            if (board[x][y] == 'M') {
                board[x][y] = 'X';
                return;
            }
            if (board[x][y] == 'E') {
                detectBomb(board, x, y);
                if (board[x][y] == 'B') {
                    for (int[] d : directions) {
                        int nextX = x + d[0], nextY = y + d[1];
                        dfs(board, nextX, nextY);
                    }
                }
            }
        }

        private void detectBomb(char[][] board, int x, int y) {
            int bomb = 0;
            for (int[] d : directions) {
                int nextX = x + d[0], nextY = y + d[1];
                if (inArea(nextX, nextY) && board[nextX][nextY] == 'M') {
                    bomb++;
                }
            }
            if (bomb > 0) board[x][y] = (char) (bomb + '0');
            else board[x][y] = 'B';
        }


        private boolean inArea(int i, int j) {
            return i >= 0 && i < m && j >= 0 && j < n;
        }


    }


    /**
     * bfs
     */
    class _1st {
        int m, n;

        public char[][] updateBoard(char[][] board, int[] click) {
            if (board[click[0]][click[1]] == 'M') {
                board[click[0]][click[1]] = 'X';
                return board;
            }
            m = board.length;
            n = board[0].length;
            boolean[][] visited = new boolean[m][n];

            Queue<int[]> queue = new LinkedList<>();
            queue.offer(click);
            visited[click[0]][click[1]] = true;
            int[][] directions = new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}};
            while (!queue.isEmpty()) {
                int[] curr = queue.poll();
                int currX = curr[0], currY = curr[1];
                int bomb = 0;
                for (int[] d : directions) {
                    int nextX = currX + d[0], nextY = currY + d[1];
                    if (inArea(nextX, nextY) && board[nextX][nextY] == 'M') bomb++;
                }
                if (bomb > 0) board[currX][currY] = (char) (bomb + '0');
                else {
                    board[currX][currY] = 'B';
                    for (int[] d : directions) {
                        int nextX = currX + d[0], nextY = currY + d[1];
                        if (inArea(nextX, nextY) && !visited[nextX][nextY] && board[nextX][nextY] == 'E') {
                            visited[nextX][nextY] = true;
                            queue.offer(new int[]{nextX, nextY});
                        }
                    }
                }
            }
            return board;
        }

        private boolean inArea(int i, int j) {
            return i >= 0 && i < m && j >= 0 && j < n;
        }
    }


    /**
     * bfs
     */
    class _4th {

        int R, C;
        int[][] dirs = new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}};

        public char[][] updateBoard(char[][] board, int[] click) {
            R = board.length;
            C = board[0].length;
            int sr = click[0], sc = click[1];
            if (board[sr][sc] == 'M') {
                board[sr][sc] = 'X';
                return board;
            }
            bfs(board,sr,sc);
            return board;
        }


        public void bfs(char[][] board, int r, int c) {
            boolean[][] visited = new boolean[R][C];
            Queue<int[]> q = new LinkedList<>();
            q.offer(new int[]{r, c});
            visited[r][c] = true;
            while (!q.isEmpty()) {
                int[] curr = q.poll();
                int currR = curr[0], currC = curr[1];
                detect(board, currR, currC);
                if (board[currR][currC] == 'B') {
                    for (int[] d : dirs) {
                        int nextR = currR + d[0], nextC = currC + d[1];
                        if (inArea(nextR, nextC) && !visited[nextR][nextC]) {
                            q.offer(new int[]{nextR, nextC});
                            visited[nextR][nextC] = true;
                        }
                    }
                }
            }
        }


        private boolean inArea(int r, int c) {
            return r >= 0 && r < R && c >= 0 && c < C;
        }


        public void detect(char[][] board, int r, int c) {
            int bomb = 0;
            for (int[] d : dirs) {
                int nr = r + d[0], nc = c + d[1];
                if (inArea(nr, nc) && board[nr][nc] == 'M') bomb++;
            }
            board[r][c] = bomb > 0 ? (char) (bomb + '0') : 'B';
        }

    }


}
