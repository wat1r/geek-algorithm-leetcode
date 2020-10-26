package com.frankcooper.bank.week;

import org.omg.CORBA.INTERNAL;

import java.util.*;

/**
 * Created by FrankCooper
 * Date 2020/10/25 10:27
 * Description
 */
public class Week212 {

    static Week212 handler = new Week212();

    public static void main(String[] args) {
//        int[] releaseTimes = {12, 23, 36, 46, 62};
//        String keysPressed = "spuda";
//
//        handler.slowestKey(releaseTimes, keysPressed);

//        int[] nums = {4, 6, 5, 9, 3, 7};
//        int[] l = {0, 0, 2};
//        int[] r = {2, 3, 5};
//        handler.checkArithmeticSubarrays(nums, l, r);

        int[][] h = {{1, 2, 2}, {3, 8, 2}, {5, 3, 5}};
        handler.minimumEffortPath(h);

    }


    public char slowestKey(int[] releaseTimes, String keysPressed) {
        char[] chas = keysPressed.toCharArray();
        char ans = chas[0];
        int max = releaseTimes[0];
        int n = keysPressed.length();
        for (int i = 1; i < n; i++) {
            int tmp = releaseTimes[i] - releaseTimes[i - 1];
            if (tmp >= max) {
                if (tmp == max && chas[i] > ans) {
                    ans = chas[i];
                } else {
                    ans = chas[i];
                }
                max = tmp;
            }
        }
        return ans;
    }


    public List<Boolean> checkArithmeticSubarrays(int[] nums, int[] l, int[] r) {
        List<Boolean> ans = new ArrayList<>();
        for (int i = 0; i < l.length; i++) {
            int[] arr = Arrays.copyOfRange(nums, l[i], r[i] + 1);
            ans.add(validate(arr));
        }
        return ans;
    }

    private boolean validate(int[] arr) {
        Arrays.sort(arr);
        for (int i = 1; i < arr.length; i++) {
            if ((arr[i] - arr[i - 1]) != (arr[1] - arr[0])) return false;
        }
        return true;
    }


    /**
     * Dijkstra 最短路径
     */
    static class _3rd_1 {

        static _3rd_1 handler = new _3rd_1();

        public static void main(String[] args) {
            int[][] heights = {{1, 2, 2}, {3, 8, 2}, {5, 3, 5}};
            handler.minimumEffortPath(heights);
        }

        int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        int m, n;
        int[][] dist; //距离
        boolean[][] vis; //访问数组
        int INF = Integer.MAX_VALUE; //INF


        class Node {
            int i;
            int j;
            int val;

            public Node(int i, int j, int val) {
                this.i = i;
                this.j = j;
                this.val = val; //距离
            }
        }


        public int minimumEffortPath(int[][] heights) {
            m = heights.length;
            n = heights[0].length;
            dist = new int[m][n];
            vis = new boolean[m][n];
            for (int i = 0; i < m; i++) Arrays.fill(dist[i], INF);
            PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.val - o2.val);
            pq.offer(new Node(0, 0, 0));
            dist[0][0] = 0;
            while (!pq.isEmpty()) {
                Node curr = pq.poll();
                int i = curr.i, j = curr.j, val = curr.val;
                if (vis[i][j]) continue;
                vis[i][j] = true;
                for (int[] d : directions) {
                    int ni = i + d[0], nj = j + d[1];
                    if (!inArea(ni, nj)) continue;
                    int nval = Math.max(val, Math.abs(heights[i][j] - heights[ni][nj]));
                    //松弛
                    if (!vis[ni][nj] && dist[ni][nj] > nval) {
                        dist[ni][nj] = nval;
                        pq.offer(new Node(ni, nj, nval));
                    }
                }
            }
            return dist[m - 1][n - 1];
        }


        private boolean inArea(int i, int j) {
            return i >= 0 && i < m && j >= 0 && j < n;
        }

    }


    static class _3rd_2 {

        static _3rd_2 handler = new _3rd_2();

        public static void main(String[] args) {
            int[][] heights = {{1, 2, 2}, {3, 8, 2}, {5, 3, 5}};
            handler.minimumEffortPath(heights);
        }


        public int minimumEffortPath(int[][] heights) {
            int L = 0, R = 1_000_000; //
            while (L <= R) {
                int M = (L + R) >> 1; // 取mid
                if (bfs(M, heights)) {
                    R = M - 1;
                } else {
                    L = M + 1;
                }
            }
            return L;
        }


        /**
         * 能否小于max的情况下找到一条从起点到终点的路径
         *
         * @param max
         * @param heights
         * @return
         */
        int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        int m, n;

        private boolean bfs(int max, int[][] heights) {
            m = heights.length;
            n = heights[0].length;
            Queue<int[]> queue = new LinkedList<>();
            queue.add(new int[]{0, 0});
            boolean[][] vis = new boolean[m][n];
            vis[0][0] = true;
            while (!queue.isEmpty()) {
                int[] curr = queue.poll();
                int i = curr[0], j = curr[1];
                if (i == m - 1 && j == n - 1) return true;
                for (int[] d : directions) {
                    int ni = i + d[0], nj = j + d[1];
                    //判断(i,j) --> (ni,nj)的距离是否比当前的小距离小，如果是这点是有效的
                    if (inArea(ni, nj) && !vis[ni][nj] && Math.abs(heights[i][j] - heights[ni][nj]) <= max) {
                        vis[ni][nj] = true;
                        queue.add(new int[]{ni, nj});
                    }
                }
            }
            return false;
        }

        private boolean inArea(int i, int j) {
            return i >= 0 && i < m && j >= 0 && j < n;
        }
    }


    static class _3rd_3 {
        static _3rd_3 handler = new _3rd_3();

        public static void main(String[] args) {
            int[][] heights = {{1, 2, 2}, {3, 8, 2}, {5, 3, 5}};
            handler.minimumEffortPath(heights);
        }

        int INF = Integer.MAX_VALUE >> 1;
        int ans = INF;
        int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        int m, n;
        boolean[][] vis;

        Map<Integer, Integer> memo = new HashMap<>();


        public int minimumEffortPath(int[][] heights) {
            m = heights.length;
            n = heights[0].length;
            vis = new boolean[m][n];
            vis[0][0] = true;
            dfs(heights, 0, 0, 0);
            return ans;
        }


        private void dfs(int[][] heights, int i, int j, int val) {
            int idx = i * n + j;
            if (memo.containsKey(idx) && memo.get(idx) < val) {
                return;
            }
            if (!memo.containsKey(idx)) memo.put(idx, val);
            if (i == m - 1 && j == n - 1) {
                if (ans >= val) ans = val;
                return;
            }
            for (int[] d : directions) {
                int ni = i + d[0], nj = j + d[1];
                if (inArea(ni, nj) && !vis[ni][nj]) {
                    vis[ni][nj] = true;
                    dfs(heights, ni, nj, Math.max(Math.abs(heights[ni][nj] - heights[i][j]), val));
                    vis[ni][nj] = false;
                }
            }
        }


        private boolean inArea(int i, int j) {
            return i >= 0 && i < m && j >= 0 && j < n;
        }


    }

    static class _3rd_4 {

        static _3rd_4 handler = new _3rd_4();

        public static void main(String[] args) {
            int[][] h = {{4, 3, 4, 10, 5, 5, 9, 2}, {10, 8, 2, 10, 9, 7, 5, 6}, {5, 8, 10, 10, 10, 7, 4, 2}, {5, 1, 3, 1, 1, 3, 1, 9}, {6, 4, 10, 6, 10, 9, 4, 6}};
            handler.minimumEffortPath(h);
        }

        class Edge {
            int x;//from的idx
            int y; //to的idx
            int val; //距离

            public Edge(int x, int y, int val) {
                this.x = x;
                this.y = y;
                this.val = val;
            }
        }


        public int minimumEffortPath(int[][] heights) {
            List<Edge> edges = new ArrayList<>();
            int m = heights.length;
            int n = heights[0].length;
            //处理这些边
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    int idx = i * n + j;
                    if (i > 0) edges.add(new Edge(idx - n, idx, Math.abs(heights[i][j] - heights[i - 1][j])));
                    if (j > 0) edges.add(new Edge(idx - 1, idx, Math.abs(heights[i][j] - heights[i][j - 1])));
                }
            }
            //排序，按边的值从小到大排序
            edges.sort((o1, o2) -> o1.val - o2.val);
            UnionFind uf = new UnionFind(m * n);
            for (Edge edge : edges) {
                uf.unoin(edge.x, edge.y);//合并
                if (uf.connect(0, m * n - 1)) return edge.val; //判断
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


    int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    int m, n;

    public int minimumEffortPath(int[][] heights) {
        m = heights.length;
        n = heights[0].length;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        boolean[][] vis = new boolean[m][n];
        vis[0][0] = true;
        int min = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int k = 0; k < size; k++) {
                int[] curr = queue.poll();
                for (int[] d : directions) {
                    int nextI = curr[0] + d[0], nextJ = curr[1] + d[1];
                    System.out.printf("%d--%d\n", nextI, nextJ);
                    if (inArea(nextI, nextJ) && !vis[nextI][nextJ]) {
                        queue.add(new int[]{nextI, nextJ});
                        vis[nextI][nextJ] = true;
                        min = Math.min(min, Math.abs(heights[nextI][nextJ] - heights[curr[0]][curr[1]]));
                    }
                    if (nextI == m - 1 && nextJ == n - 1 && vis[nextI][nextJ]) {
                        return min;
                    }
                }
            }
        }
        return min;
    }


    private boolean inArea(int i, int j) {
        return i >= 0 && i < m && j >= 0 && j < n;
    }


    static class _2nd {

        static _2nd handler = new _2nd();


        public static void main(String[] args) {

            int[][] h = {{1, 2, 2}, {3, 8, 2}, {5, 3, 5}};
            h = new int[][]{{1, 10, 6, 7, 9, 10, 4, 9}};
            handler.minimumEffortPath(h);

        }

        int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        int m, n;

        public int minimumEffortPath(int[][] heights) {
            m = heights.length;
            n = heights[0].length;
            if (m == 1 && n == 1) return 0;
            int[][] dp = new int[m][n];
//            Arrays.fill(dp, Integer.MAX_VALUE);
            dp[0][0] = heights[0][0];
            for (int i = 1; i < m; i++) dp[i][0] = Math.max(dp[i][0], Math.abs(heights[i][0] - heights[i - 1][0]));
            for (int j = 1; j < n; j++) dp[0][j] = Math.max(dp[0][j], Math.abs(heights[0][j] - heights[0][j - 1]));
            for (int i = 1; i < m; i++) {
                for (int j = 1; j < n; j++) {
                    dp[i][j] = Math.min(Math.max(dp[i - 1][j], Math.abs(heights[i][j] - heights[i - 1][j]))
                            , Math.max(dp[i][j - 1], Math.abs(heights[i][j] - heights[i][j - 1]))
                    );

                }
            }
            return dp[m - 1][n - 1];
        }


    }


}
