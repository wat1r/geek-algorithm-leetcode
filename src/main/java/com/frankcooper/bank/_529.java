package com.frankcooper.bank;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Date 2020/8/26
 * @Author Frank Cooper
 * @Description
 */
public class _529 {


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


}
