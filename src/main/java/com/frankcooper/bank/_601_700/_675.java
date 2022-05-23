package com.frankcooper.bank._601_700;

import java.util.*;

import org.junit.Assert;

public class _675 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        //格子的边界 row, col
        int R, C;
        int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        List<List<Integer>> forest;

        public int cutOffTree(List<List<Integer>> forest) {
            R = forest.size();
            C = forest.get(0).size();
            this.forest = forest;
            List<int[]> trees = new ArrayList<>();
            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    if (forest.get(r).get(c) > 1) {
                        trees.add(new int[]{r, c});
                    }
                }
            }
            //排序按树高从小到大排序
            Collections.sort(trees, (a, b) -> forest.get(a[0]).get(a[1]) - forest.get(b[0]).get(b[1]));
            //source_x source_y 起始的点
            int sx = 0, sy = 0;
            int tot = 0;//总的步数
            for (int i = 0; i < trees.size(); i++) {
                //这一轮目标坐标 target_x target_y
                int tx = trees.get(i)[0], ty = trees.get(i)[1];
                int steps = bfs(sx, sy, tx, ty);
                if (steps == -1) return -1;
                tot += steps;
                sx = tx;
                sy = ty;
            }
            return tot;
        }


        /**
         * @param sx source_x
         * @param sy source_y
         * @param tx target_x
         * @param ty target_y
         * @return
         */
        private int bfs(int sx, int sy, int tx, int ty) {
            //如果源点和目标点是同一个点，不需要行走
            if (sx == tx && sy == ty) return 0;
            Queue<int[]> q = new LinkedList<>();
            int step = 0;
            //访问数组
            boolean[][] vis = new boolean[R][C];
            q.add(new int[]{sx, sy});
            vis[sx][sy] = true;
            while (!q.isEmpty()) {
                step++;
                int size = q.size();
                for (int i = 0; i < size; i++) {
                    int[] p = q.poll();
                    //current_x current_y
                    int cx = p[0], cy = p[1];
                    for (int[] d : dirs) {
                        //neighbor_x neighbor_y
                        int nx = cx + d[0], ny = cy + d[1];
                        //在边界范围内且没有被访问过 并且是树
                        if (nx >= 0 && nx < R && ny >= 0 && ny < C
                                && !vis[nx][ny] && forest.get(nx).get(ny) > 0) {
                            if (nx == tx && ny == ty) {
                                return step;
                            }
                            q.offer(new int[]{nx, ny});
                            vis[nx][ny] = true;
                        }
                    }
                }
            }
            return -1;
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        //格子的边界 row, col
        int R, C;
        int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        List<List<Integer>> forest;


        public int cutOffTree(List<List<Integer>> forest) {
            R = forest.size();
            C = forest.get(0).size();
            this.forest = forest;
            List<int[]> trees = new ArrayList<int[]>();
            for (int r = 0; r < R; ++r) {
                for (int c = 0; c < C; ++c) {
                    if (forest.get(r).get(c) > 1) {
                        trees.add(new int[]{r, c});
                    }
                }
            }
            Collections.sort(trees, (a, b) -> forest.get(a[0]).get(a[1]) - forest.get(b[0]).get(b[1]));

            int sx = 0, sy = 0;
            int tot = 0;
            for (int i = 0; i < trees.size(); ++i) {
                //这一轮目标坐标 target_x target_y
                int tx = trees.get(i)[0], ty = trees.get(i)[1];
                int steps = bfs(sx, sy, tx, ty);
                if (steps == -1) return -1;
                tot += steps;
                sx = tx;
                sy = ty;
            }
            return tot;
        }

        public int bfs(int sx, int sy, int tx, int ty) {
            if (sx == tx && sy == ty) {
                return 0;
            }

            PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b) -> a[0] - b[0]);
            boolean[][] vis = new boolean[R][C];
            pq.offer(new int[]{0, sx * C + sy});
            vis[sx][sy] = true;
            while (!pq.isEmpty()) {
                int[] t = pq.poll();
                int dist = t[0], loc = t[1];
                for (int j = 0; j < 4; ++j) {
                    int nx = loc / C + dirs[j][0];
                    int ny = loc % C + dirs[j][1];
                    if (nx >= 0 && nx < R && ny >= 0 && ny < C
                            && !vis[nx][ny] && forest.get(nx).get(ny) > 0) {
                        if (nx == tx && ny == ty) {
                            return dist + 1;
                        }
                        pq.offer(new int[]{dist + 1, nx * C + ny});
                        vis[nx][ny] = true;
                    }
                }
            }
            return -1;
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
