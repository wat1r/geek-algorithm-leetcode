package com.frankcooper.platform.leetcode.bank._1001_1500;

import com.frankcooper.utils.PrintUtils;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class _1091 {

    static _1091 handler = new _1091();


    public static void main(String[] args) {
        int[][] grid = {{1, 0, 0}, {1, 1, 0}, {1, 1, 0}};
//        handler.shortestPathBinaryMatrix(grid);


    }


    static class _2nd {

        public static void main(String[] args) {
            _2nd handler = new _2nd();
            int[][] grid = {{0, 1}, {1, 0}};
            handler.shortestPathBinaryMatrix(grid);
        }


        int[][] dirs = new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}};
        int R, C;

        public int shortestPathBinaryMatrix(int[][] grid) {
            R = grid.length;
            C = grid[0].length;
            if (grid[0][0] == 1 || grid[R - 1][C - 1] == 1) {
                return -1;
            }
            if (C == 1) {
                return 1;
            }
            Queue<int[]> q = new LinkedList<>();
            q.offer(new int[]{0, 0});
            int level = 1;
            while (!q.isEmpty()) {
                level++;
                int size = q.size();
                for (int k = 0; k < size; k++) {
                    int[] curr = q.poll();
                    for (int[] d : dirs) {
                        int nr = curr[0] + d[0], nc = curr[1] + d[1];
                        if (!inArea(nr, nc)) {
                            continue;
                        }
                        if (nr == R - 1 && nc == C - 1) {
                            return level;
                        }
                        if (grid[nr][nc] == 1) {
                            continue;
                        }
                        if (grid[nr][nc] == -1) {
                            continue;
                        }
                        grid[nr][nc] = -1;
                        q.offer(new int[]{nr, nc});
                    }
                }
            }
            return -1;
        }


        private boolean inArea(int r, int c) {
            return r >= 0 && r < R && c >= 0 && c < C;
        }


    }


    static class _1st {

        public static void main(String[] args) {
            _1st handler = new _1st();
            int[][] grid = {{0, 1}, {1, 0}};
            handler.shortestPathBinaryMatrix(grid);
        }


        int[][] directions = new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}};
        int R, C;


        public int shortestPathBinaryMatrix(int[][] grid) {
            R = grid.length;
            C = grid[0].length;
            if (grid[0][0] == 1 || grid[R - 1][C - 1] == 1) return -1;
            if (C == 1) return 1;
            Queue<int[]> queue = new LinkedList<>();
            queue.offer(new int[]{0, 0});
            int level = 1;
            while (!queue.isEmpty()) {
                level++;
                int size = queue.size();
                for (int k = 0; k < size; k++) {
                    int[] curr = queue.poll();
                    for (int[] d : directions) {
                        int nextR = curr[0] + d[0], nextC = curr[1] + d[1];
                        if (!inArea(nextR, nextC)) continue;
                        if (nextR == R - 1 && nextC == C - 1) {
                            return level;
                        }
                        if (grid[nextR][nextC] == 1) continue;
                        if (grid[nextR][nextC] == -1) continue;
                        queue.offer(new int[]{nextR, nextC});
                        grid[nextR][nextC] = -1;
                    }
                    PrintUtils.printMatrix(grid);
                }
            }
            return -1;
        }

        private boolean inArea(int r, int c) {
            return r >= 0 && r < R && c >= 0 && c < C;
        }
    }

    static class _3rd {

        public static void main(String[] args) {
            _3rd handler = new _3rd();
            int[][] grid = {{0, 1}, {1, 0}};
            handler.shortestPathBinaryMatrix(grid);
        }

        public int shortestPathBinaryMatrix(int[][] grid) {
            if (grid[0][0] == 1) {
                return -1;
            }
            int n = grid.length;
            int[][] dist = new int[n][n];
            for (int i = 0; i < n; i++) {
                Arrays.fill(dist[i], Integer.MAX_VALUE);
            }
            Queue<int[]> queue = new ArrayDeque<int[]>();
            queue.offer(new int[]{0, 0});
            dist[0][0] = 1;
            while (!queue.isEmpty()) {
                int[] arr = queue.poll();
                int x = arr[0], y = arr[1];
                if (x == n - 1 && y == n - 1) {
                    return dist[x][y];
                }
                for (int dx = -1; dx <= 1; dx++) {
                    for (int dy = -1; dy <= 1; dy++) {
                        if (x + dx < 0 || x + dx >= n || y + dy < 0 || y + dy >= n) { // 越界
                            continue;
                        }
                        if (grid[x + dx][y + dy] == 1 || dist[x + dx][y + dy] <= dist[x][y] + 1) { // 单元格值不为 0 或已被访问
                            continue;
                        }
                        dist[x + dx][y + dy] = dist[x][y] + 1;
                        queue.offer(new int[]{x + dx, y + dy});
                    }
                }
            }
            return -1;
        }

    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
            int[][] grid = {{0, 0, 0}, {1, 1, 0}, {1, 1, 0}};
            handler.shortestPathBinaryMatrix(grid);
        }

        int[][] dirs = new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}};
        int R, C;

        public int shortestPathBinaryMatrix(int[][] grid) {
            if (grid[0][0] == 1) {
                return -1;
            }
            R = grid.length;
            C = grid[0].length;
            int[][] dist = new int[R][C];//存储当前点到起点坐标的距离
            for (int r = 0; r < R; r++) {
                Arrays.fill(dist[r], Integer.MAX_VALUE);
            }
            Queue<int[]> q = new ArrayDeque<>();
            q.offer(new int[]{0, 0});//初始化
            dist[0][0] = 1;
            while (!q.isEmpty()) {
                int[] curr = q.poll();
                int r = curr[0], c = curr[1];
                if (r == R - 1 && c == C - 1) {
                    return dist[r][c];
                }
                for (int dx = -1; dx <= 1; dx++) {
                    for (int dy = -1; dy <= 1; dy++) {
                        int nr = r + dx, nc = c + dy;
                        if (!inArea(nr, nc)) {
                            continue;
                        }
                        //如果下一个点是1，说明该点不能被访问
                        //如果下个点的距离不是MAX_VALUE，说明被访问过(周围8个点有重合)
                        if (grid[nr][nc] == 1 || dist[nr][nc] <= dist[r][c] + 1) {
                            continue;
                        }
                        dist[nr][nc] = dist[r][c] + 1;
                        q.offer(new int[]{nr, nc});
                    }
                }

//                for (int[] d : dirs) {
//                    int nr = r + d[0], nc = c + d[1];
//                    if (!inArea(nr, nc)) {
//                        continue;
//                    }
//                    if (grid[nr][nc] == 1 || dist[nr][nc] <= dist[r][c] + 1) {
//                        continue;
//                    }
//                    dist[nr][nc] = dist[r][c] + 1;
//                    q.offer(new int[]{nr, nc});
//                }
            }
            return -1;

        }


        private boolean inArea(int r, int c) {
            return r >= 0 && r < R && c >= 0 && c < C;
        }
    }

}
