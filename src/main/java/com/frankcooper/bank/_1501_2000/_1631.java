package com.frankcooper.bank._1501_2000;

import java.util.*;

import com.frankcooper.utils.PrintUtils;

public class _1631 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[][] h = PrintUtils.processSymbol("[[1,2,2],[3,8,2],[5,3,5]]");
            handler.minimumEffortPath(h);
        }


        /**
         * https://leetcode-cn.com/problems/path-with-minimum-effort/solution/duo-tu-xiang-xi-fen-xi-jie-ti-si-lu-fen-7z89x/
         *
         * @param heights
         * @return
         */
        public int minimumEffortPath(int[][] heights) {
            int R = heights.length, C = heights[0].length;
            PriorityQueue<int[]> pq = new PriorityQueue<>(((o1, o2) -> o1[2] - o2[2]));
            UnionFind uf = new UnionFind(10_005);
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    int u = i * C + j;
                    int v;
                    if (j < C - 1) {
                        v = u + 1;
                        int w = Math.abs(heights[u / C][u % C] - heights[v / C][v % C]);
                        pq.offer(new int[]{u, v, w});
                    }
                    if (i < R - 1) {
                        v = u + C;
                        int w = Math.abs(heights[u / C][u % C] - heights[v / C][v % C]);
                        pq.offer(new int[]{u, v, w});
                    }
                }
            }
            int s = 0, e = R * C - 1;
            while (!pq.isEmpty()) {
                int[] cur = pq.poll();
                uf.unoin(cur[0], cur[1]);
                if (uf.connect(s, e)) return cur[2];
            }
            return 0;
        }


        class UnionFind {
            int[] parents;
            int[] ranks;

            public UnionFind(int n) {
                parents = new int[n];
                ranks = new int[n];
                for (int i = 0; i < n; i++) parents[i] = i;
            }

            public int find(int x) {
                if (x != parents[x]) {
                    parents[x] = find(parents[x]);
                }
                return parents[x];
            }

            public boolean unoin(int x, int y) {
                int rootX = find(x), rootY = find(y);
                if (rootX == rootY) return false;
                if (ranks[rootX] > ranks[rootY]) parents[rootY] = rootX;
                if (ranks[rootX] < ranks[rootY]) parents[rootX] = rootY;
                if (ranks[rootX] == ranks[rootY]) {
                    parents[rootY] = rootX;
                    ranks[rootY]++;
                }
                return true;
            }

            public boolean connect(int x, int y) {
                int rootX = find(x);
                int rootY = find(y);
                return rootX == rootY;
            }

        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            int[][] h = new int[][]{{1, 10, 6, 7, 9, 10, 4, 9}};
            h = PrintUtils.processSymbol("[[4,3,4,10,5,5,9,2],[10,8,2,10,9,7,5,6],[5,8,10,10,10,7,4,2],[5,1,3,1,1,3,1,9],[6,4,10,6,10,9,4,6]]");
            handler.minimumEffortPath(h);
        }


        public int minimumEffortPath(int[][] heights) {

            int R = heights.length, C = heights[0].length;
            int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
            PriorityQueue<int[]> pq = new PriorityQueue<>(((o1, o2) -> o1[2] - o2[2]));
            pq.offer(new int[]{0, 0, 0});
            int[] dist = new int[R * C];
            Arrays.fill(dist, Integer.MAX_VALUE);
            dist[0] = 0;
            boolean[] vis = new boolean[R * C];//存的是每个点转换成一维下标
            while (!pq.isEmpty()) {
                int[] cur = pq.poll();
                int x = cur[0], y = cur[1], d = cur[2];
                int pos = x * C + y;//当前的position
                if (x == R - 1 && y == C - 1) break;
                if (vis[pos]) continue;
                vis[pos] = true;
                for (int[] dir : dirs) {
                    int nx = x + dir[0], ny = y + dir[1];
                    if (nx >= 0 && nx < R && ny >= 0 && ny < C) {
                        int npos = nx * C + ny;//下一个position
                        int p = Math.max(d, Math.abs(heights[nx][ny] - heights[x][y]));
                        if (p < dist[npos]) {
                            dist[npos] = p;
                            pq.offer(new int[]{nx, ny, dist[npos]});
                        }
                    }
                }
            }
            return dist[R * C - 1];
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
