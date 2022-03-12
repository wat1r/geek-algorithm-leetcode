package com.frankcooper.bank.week;

import com.alibaba.fastjson.JSON;
import com.frankcooper.utils.PrintUtils;

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
//            handler.minimumEffortPath(heights);
            handler.minimumEffortPath(PrintUtils.processSymbol("[[307,9,885,566,354,185,680,539,197,960,785,950,306,751,482,207,971,689,211,500,415,663,889],[398,963,360,162,63,167,360,653,44,914,647,90,711,430,49,701,338,973,282,414,52,113,711],[529,803,62,883,247,873,810,832,479,910,876,382,238,128,670,61,966,272,768,228,22,950,604],[377,33,111,657,514,984,233,109,822,344,624,830,159,746,352,342,703,407,955,730,892,523,184],[960,244,334,432,574,40,309,695,704,794,997,186,124,584,648,873,738,979,270,478,324,675,350],[348,92,355,235,490,357,128,836,852,570,18,86,781,728,255,115,581,973,37,677,170,774,664],[521,840,902,693,92,797,748,829,924,311,649,990,464,302,786,845,344,397,422,339,147,824,94],[240,219,162,284,217,198,694,235,882,756,169,267,656,994,175,3,478,683,996,298,301,756,305],[536,34,245,731,772,560,18,986,501,762,926,173,934,231,158,1,933,544,700,423,893,718,214],[227,638,448,350,375,388,867,480,788,35,50,884,270,14,363,737,658,658,743,918,237,381,622],[544,348,67,203,534,374,634,507,299,97,379,412,671,589,604,236,694,923,481,784,897,65,143],[440,343,73,508,310,953,748,900,24,711,801,87,698,339,990,668,238,701,460,753,885,105,299],[937,56,812,255,514,83,996,695,58,695,660,450,604,808,680,360,554,195,16,779,56,390,274],[185,474,174,882,578,470,908,413,345,858,558,725,17,384,973,402,37,40,289,461,761,981,406],[223,690,287,126,981,609,499,783,181,609,286,64,752,933,65,828,481,981,181,723,280,313,777],[844,239,950,238,742,872,851,417,44,741,371,201,414,95,369,270,504,339,319,618,415,660,332],[419,990,890,24,656,292,158,73,910,673,125,122,76,247,438,274,223,128,967,445,77,343,433]]"));
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
            PrintUtils.printMatrix(dist, 6);
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


        public int minimumEffortPath(int[][] heights) {
            m = heights.length;
            n = heights[0].length;
            vis = new boolean[m][n];
            int L = 0, R = 1_000_000; //
            while (L <= R) {
                int M = (L + R) >> 1; // 取mid
                if (dfs(heights, 0, 0, M)) {
                    R = M - 1;
                } else {
                    L = M + 1;
                }
            }
            return L;
        }


        private boolean dfs(int[][] heights, int i, int j, int val) {
            if (i == m - 1 && j == n - 1) {
                if (ans >= val) {
                    ans = val;
                    return true;
                }
            }
            for (int[] d : directions) {
                int ni = i + d[0], nj = j + d[1];
                if (inArea(ni, nj) && !vis[ni][nj]) {
                    vis[ni][nj] = true;
                    dfs(heights, ni, nj, Math.max(Math.abs(heights[ni][nj] - heights[i][j]), val));
                    vis[ni][nj] = false;
                }
            }
            return false;
        }


        private boolean inArea(int i, int j) {
            return i >= 0 && i < m && j >= 0 && j < n;
        }


    }

    static class _3rd_4 {

        static _3rd_4 handler = new _3rd_4();

        public static void main(String[] args) {
            int[][] h = {{4, 3, 4, 10, 5, 5, 9, 2}, {10, 8, 2, 10, 9, 7, 5, 6}, {5, 8, 10, 10, 10, 7, 4, 2}, {5, 1, 3, 1, 1, 3, 1, 9}, {6, 4, 10, 6, 10, 9, 4, 6}};


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


    static class _3rd_5 {

        static _3rd_5 handler = new _3rd_5();

        public static void main(String[] args) {
//            handler.minimumEffortPath(PrintUtils.processSymbol("[[307,9,885,566,354,185,680,539,197,960,785,950,306,751,482,207,971,689,211,500,415,663,889],[398,963,360,162,63,167,360,653,44,914,647,90,711,430,49,701,338,973,282,414,52,113,711],[529,803,62,883,247,873,810,832,479,910,876,382,238,128,670,61,966,272,768,228,22,950,604],[377,33,111,657,514,984,233,109,822,344,624,830,159,746,352,342,703,407,955,730,892,523,184],[960,244,334,432,574,40,309,695,704,794,997,186,124,584,648,873,738,979,270,478,324,675,350],[348,92,355,235,490,357,128,836,852,570,18,86,781,728,255,115,581,973,37,677,170,774,664],[521,840,902,693,92,797,748,829,924,311,649,990,464,302,786,845,344,397,422,339,147,824,94],[240,219,162,284,217,198,694,235,882,756,169,267,656,994,175,3,478,683,996,298,301,756,305],[536,34,245,731,772,560,18,986,501,762,926,173,934,231,158,1,933,544,700,423,893,718,214],[227,638,448,350,375,388,867,480,788,35,50,884,270,14,363,737,658,658,743,918,237,381,622],[544,348,67,203,534,374,634,507,299,97,379,412,671,589,604,236,694,923,481,784,897,65,143],[440,343,73,508,310,953,748,900,24,711,801,87,698,339,990,668,238,701,460,753,885,105,299],[937,56,812,255,514,83,996,695,58,695,660,450,604,808,680,360,554,195,16,779,56,390,274],[185,474,174,882,578,470,908,413,345,858,558,725,17,384,973,402,37,40,289,461,761,981,406],[223,690,287,126,981,609,499,783,181,609,286,64,752,933,65,828,481,981,181,723,280,313,777],[844,239,950,238,742,872,851,417,44,741,371,201,414,95,369,270,504,339,319,618,415,660,332],[419,990,890,24,656,292,158,73,910,673,125,122,76,247,438,274,223,128,967,445,77,343,433]]"));


            System.out.println();
        }


        public int minimumEffortPath(int[][] heights) {
            int len1 = heights.length, len2 = heights[0].length;
            int[][] dist = new int[len1][len2];
            boolean[][] vis = new boolean[len1][len2];
            for (int i = 0; i < len1; i++) {
                Arrays.fill(dist[i], Integer.MAX_VALUE >> 1);
            }
            dist[0][0] = 0;
            Queue<int[]> queue = new PriorityQueue<>((a, b) -> {
                return dist[a[0]][a[1]] - dist[b[0]][b[1]];
            });
            queue.offer(new int[]{0, 0});
            int[][] dir = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
            while (!queue.isEmpty()) {
                int[] point = queue.poll();
                int i = point[0];
                int j = point[1];
                vis[i][j] = true;
                for (int[] d : dir) {
                    int ni = i + d[0];
                    int nj = j + d[1];
                    if (ni < 0 || ni >= len1 || nj < 0 || nj >= len2 || vis[ni][nj]) {
                        continue;
                    }
                    int tmp = Math.max(dist[i][j], Math.abs(heights[ni][nj] - heights[i][j]));
                    dist[ni][nj] = Math.min(dist[ni][nj], tmp);
                    queue.offer(new int[]{ni, nj});
                }


            }

//            for (int i = 0; i < dist.length; i++) {
//                for (int j = 0; j < dist[0].length; j++) {
//                    System.out.printf("%d ",dist[i][j]);
//                }
//                System.out.println();
//            }

            PrintUtils.printMatrix(dist, 6);
            return dist[len1 - 1][len2 - 1];
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


    /**
     * 1632.矩阵转换后的秩
     */
    static class _4th_1 {
        static _4th_1 handler = new _4th_1();

        public static void main(String[] args) {

        }

        int R, C; //行列
        int LIM = 512;

        public int[][] matrixRankTransform(int[][] matrix) {
            R = matrix.length;
            C = matrix[0].length;
            int[] countR = new int[R];
            int[] countC = new int[C];
            Map<Integer, List<Integer>> map = new HashMap<>();
            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    List<Integer> list = map.getOrDefault(matrix[r][c], new ArrayList<>());
                    list.add(r * LIM + c);
                    map.put(matrix[r][c], list);
                }
            }
            UnionFind uf = new UnionFind(LIM * 2);
            int[] values = new int[map.size()];
            int i = 0;
            for (List<Integer> list : map.values()) {
                values[i++] = list.get(0);
            }
            Arrays.sort(values);
            for (int val : values) {
                for (int vv : map.get(val)) {
//                    uf.find(val/LIM)
                }
            }


            return null;
        }


    }

    static class UnionFind {
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


    static class _4th_2 {


        static _4th_2 handler = new _4th_2();


        public static void main(String[] args) {
//            handler.matrixRankTransform(PrintUtils.processSymbol("[[7,3,6],[1,4,5],[9,8,2]]"));
            handler.matrixRankTransform(PrintUtils.processSymbol("[[20,-21,14],[-19,4,19],[22,-47,24],[-19,4,19]]"));
        }


        public int[][] matrixRankTransform(int[][] matrix) {
            int R = matrix.length, C = matrix[0].length;
            UnionFind uf = new UnionFind(R * C);
            Integer[] idxs = new Integer[R * C];
            int[] vals = new int[R * C];
            for (int i = 0; i < R * C; i++) idxs[i] = i;
            Arrays.sort(idxs, (o1, o2) -> matrix[o1 / C][o1 % C] - matrix[o2 / C][o2 % C]);
            int[] rowMaxRank = new int[R];
            int[] colMaxRank = new int[C];
            Arrays.fill(rowMaxRank, -1);
            Arrays.fill(colMaxRank, -1);
            System.out.printf("idxs:%s\n", JSON.toJSONString(idxs));
            int pos = 0;
            while (pos < R * C) {
                int val = 1;
                int idx = idxs[pos];
                int r = idx / C, c = idx % C;
                if (rowMaxRank[r] != -1) {
                    int k = rowMaxRank[r];
                    int tmpIdx = r * C + k;
                    int tmpRoot = uf.find(tmpIdx);
                    int tmpVal = vals[tmpRoot];
                    if (matrix[r][c] == matrix[r][k]) {
                        uf.unoin(idx, tmpIdx);
                        val = tmpVal;
                    } else {
                        val = tmpVal + 1;
                    }
                }
                if (colMaxRank[c] != -1) {
                    int k = colMaxRank[c];
                    int tmpIdx = k * C + c;
                    int tmpRoot = uf.find(tmpIdx);
                    int tmpVal = vals[tmpRoot];
                    if (matrix[r][c] == matrix[k][c]) {
                        uf.unoin(idx, tmpIdx);
                        val = Math.max(val, tmpVal);
                    } else {
                        val = Math.max(val, tmpVal + 1);
                    }
                }
                rowMaxRank[r] = c;
                colMaxRank[c] = r;
                int idxRoot = uf.find(idx);
                vals[idxRoot] = val;
                System.out.printf("idx:%d,r:%d,c:%d,pos:%d\n", idx, r, c, pos);
                System.out.printf("rowMaxRank:%s\n", JSON.toJSONString(rowMaxRank));
                System.out.printf("colMaxRank:%s\n", JSON.toJSONString(colMaxRank));
                System.out.printf("vals:%s\n", JSON.toJSONString(vals));
                System.out.println("---------------------------------");
                pos++;
            }
            int[][] res = new int[R][C];
            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    int idx = r * C + c;
                    res[r][c] = vals[uf.find(idx)];
                }
            }
            return res;
        }


    }


}
