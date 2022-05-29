package com.frankcooper.bank._1001_1500;

import java.util.*;

public class _1368 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        int m, n;
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};


        public int minCost(int[][] grid) {
            m = grid.length;
            n = grid[0].length;
            int[] dist = new int[m * n];
            Arrays.fill(dist, Integer.MAX_VALUE);
            boolean[] vis = new boolean[m * n];
            dist[0] = 0;
            PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> dist[a] - dist[b]);
            pq.offer(0);
            while (!pq.isEmpty()) {
                //当前点位置
                int cur_pos = pq.poll();
                //注释掉下面的部分可以通过
//                if (vis[cur_pos]) {
//                    continue;
//                }
                vis[cur_pos] = true;
                //当前点的位置转化成坐标
                int x = cur_pos / n, y = cur_pos % n;
                for (int i = 0; i < 4; i++) {
                    int nx = x + dirs[i][0], ny = y + dirs[i][1];
                    if (nx < 0 || nx >= m || ny < 0 || ny >= n) {
                        continue;
                    }
                    int new_pos = nx * n + ny;
                    int new_dist = dist[cur_pos] + (grid[x][y] == i + 1 ? 0 : 1);
                    if (new_dist < dist[new_pos]) {
                        dist[new_pos] = new_dist;
                        pq.offer(new_pos);
                    }
                }
            }
            return dist[m * n - 1];
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        /* Dijkstra */
        int row = 0, col = 1;
        int m = 0, n = 0;
        int[][] dirs = new int[][]{{0, 0}, {0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        int[] dist;
        boolean[] vis;

        public int minCost(int[][] grid) {
            m = grid.length;
            n = m > 0 ? grid[0].length : 0;
            vis = new boolean[m * n];//已確認其標定的距離為最短的點
            dist = new int[m * n];//記錄每個點離原點的距離
            Arrays.fill(dist, m * n);//先將距離填上最大值
            dist[0 * m + 0] = 0;//設定原點距離為0
            List<Integer> q = new ArrayList<>();//鄰接點集合
            q.add(0 * m + 0);//在鄰接點加入原點
            Collections.sort(q, (v1, v2) -> dist[v1] - dist[v2]);//排序，距離小者在前
            while (!q.isEmpty()) {//依序處理，直到所有鄰接點的最小距離皆已確認
                int cur_pos = q.remove(0);//取出相鄰點中，最近的點
                if (vis[cur_pos]) continue;//該點距離已為最小距離，不須再處理
                vis[cur_pos] = true;//該點標定的距離已確認為最小距離
                for (int k = 1; k <= 4; k++) {//由剛確認的最小距離點再向外跨一步，取得和其鄰接點的資訊
                    int[] dir = dirs[k];
                    int x = cur_pos / n, y = cur_pos % n;
                    int nx = x + dir[row], ny = y + dir[col];
                    int new_pos = nx * n + ny;
//                    int gDir = ;//該點原行走方向
                    if (0 <= nx && nx < m && 0 <= ny && ny < n) {//確認新點合法
                        if (vis[new_pos]) continue;//該點距離已為最小距離，不須再處理
                        int new_dist = k == grid[x][y] ? dist[cur_pos] : dist[cur_pos] + 1;//若行走方向和原設不同，其權值加一
                        dist[new_pos] = Math.min(new_dist, dist[new_pos]);//重設該鄰點距離
                        q.add(new_pos);
                    }
                }
                Collections.sort(q, (v1, v2) -> dist[v1] - dist[v2]);//  排序，距離小者在前
            }
            return dist[m * n - 1];
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
            int[][] grid = {{1, 2}, {4, 3}};
            handler.minCost(grid);
        }


        int m, n;
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        public int minCost(int[][] grid) {
            m = grid.length;
            n = grid[0].length;
            int[] dist = new int[m * n];
            Arrays.fill(dist, Integer.MAX_VALUE);
            dist[0] = 0;
            boolean[] vis = new boolean[m * n];
            Deque<Integer> deque = new ArrayDeque<>();
            deque.offerFirst(0);
            while (!deque.isEmpty()) {
                int cur_pos = deque.pollFirst();
                if (vis[cur_pos]) {
                    continue;
                }
                vis[cur_pos] = true;
                int x = cur_pos / n, y = cur_pos % n;
                for (int i = 0; i < 4; i++) {
                    int nx = x + dirs[i][0], ny = y + dirs[i][1];
                    int new_pos = nx * n + ny;
                    //1:右 2:左 3:下 4:上
                    //当前的[x,y]的值如果是符合1 2 3 4 的值，则不需要修改，也就是 0
                    int new_dist = dist[cur_pos] + (grid[x][y] == i + 1 ? 0 : 1);
                    if (nx < 0 || nx >= m || ny < 0 || ny >= n) {
                        continue;
                    }
                    if (new_dist < dist[new_pos]) {
                        dist[new_pos] = new_dist;
                        //01广度优先搜索，将0的那个点加入到队首，1的那个点加入到对尾
                        if (grid[x][y] == i + 1) {
                            deque.offerFirst(new_pos);
                        } else {
                            deque.offerLast(new_pos);
                        }
                    }
                }
            }
            return dist[m * n - 1];
        }

    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }

        class Point {
            int dist;
            int r;
            int c;

            Point() {
            }

            Point(int dist, int r, int c) {
                this.dist = dist;
                this.r = r;
                this.c = c;
            }
        }

        int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        public int minCost(int[][] grid) {
            int m = grid.length;
            int n = grid[0].length;
            boolean[][] vis = new boolean[m][n];
            int[][] dist = new int[m][n];
            for (int r = 0; r < m; r++) {
                Arrays.fill(dist[r], Integer.MAX_VALUE);
            }
            //按距离排序
            PriorityQueue<Point> pq = new PriorityQueue<>((o1, o2) -> o1.dist - o2.dist);
            pq.offer(new Point(0, 0, 0));
            dist[0][0] = 0;
            while (!pq.isEmpty()) {
                Point cur = pq.poll();
                int d = cur.dist, r = cur.r, c = cur.c;
                if (vis[r][c]) {
                    continue;
                }
                vis[r][c] = true;
                for (int k = 0; k < 4; k++) {
                    int nr = r + dirs[k][0], nc = c + dirs[k][1];
                    int cost = (k + 1 == grid[r][c] ? 0 : 1);
                    if (0 <= nr && nr < m && 0 <= nc && nc < n && dist[r][c] + cost < dist[nr][nc]) {
                        dist[nr][nc] = dist[r][c] + cost;
                        pq.offer(new Point(dist[nr][nc], nr, nc));
                    }
                }
            }
            return dist[m - 1][n - 1];
        }

    }


}
