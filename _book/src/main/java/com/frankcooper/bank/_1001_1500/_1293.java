package com.frankcooper.platform.leetcode.bank._1001_1500;

import java.util.*;

import org.junit.Assert;

public class _1293 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        int m, n;
        //右 下 左 上
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        //我们还可以对搜索空间进行优化。注意到题目中 k 的上限为 m * n，但考虑一条从 (0, 0) 向下走到 (m - 1, 0) 再向右走到
        // (m - 1, n - 1) 的路径，它经过了 m + n - 1 个位置，其中起点 (0, 0) 和终点 (m - 1, n - 1) 没有障碍物，
        // 那么这条路径上最多只会有 m + n - 3 个障碍物。因此我们可以将 k 的值设置为 m + n - 3 与其本身的较小值
        // min(k, m + n - 3)，将广度优先搜索的时间复杂度从 O(MNK) 降低至 (MN∗min(M+N,K))

        public int shortestPath(int[][] grid, int k) {
            m = grid.length;
            n = grid[0].length;
            //case ->
            //[[0]]
            //1
            if (m == 1 && n == 1) return 0;
//            if ( k >= m + n - 3){
//                return m + n - 2;
//            }
//            k = Math.min(k, m + n - 3);
            boolean[][][] vis = new boolean[m][n][k + 1];
            Queue<Position> q = new LinkedList<>();
            //标记访问的状态
            q.offer(new Position(0, 0, k));
            vis[0][0][k] = true;
            int steps = 0;
            while (!q.isEmpty()) {
                int size = q.size();
                steps++;
                for (int i = 0; i < size; i++) {
                    Position p = q.poll();
                    for (int[] d : dirs) {
                        int nx = p.x + d[0], ny = p.y + d[1];
                        if (nx >= m || nx < 0 || ny >= n || ny < 0) {
                            continue;
                        }
                        if (grid[nx][ny] == 0 && !vis[nx][ny][p.count]) {
                            if (nx == m - 1 && ny == n - 1) {
                                return steps;
                            }
                            q.offer(new Position(nx, ny, p.count));
                            vis[nx][ny][p.count] = true;
                        } else if (grid[nx][ny] == 1 && p.count > 0 && !vis[nx][ny][p.count - 1]) {
                            q.offer(new Position(nx, ny, p.count - 1));
                            vis[nx][ny][p.count - 1] = true;
                        }

                    }
                }
            }
            return -1;
        }

        class Position {
            int x, y;
            int count;//当前状态下还可以经过多少个障碍物，此数量为非负

            public Position(int x, int y, int count) {
                this.x = x;
                this.y = y;
                this.count = count;
            }
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
