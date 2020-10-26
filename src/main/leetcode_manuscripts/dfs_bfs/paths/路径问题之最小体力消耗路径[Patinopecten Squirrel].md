## 路径问题之最小体力消耗路径[Patinopecten Squirrel]

![fall-animals-5386477_640](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dfs_bfs\paths\路径问题之最小体力消耗路径[Patinopecten Squirrel].assets\fall-animals-5386477_640.png)

#### 方法1：二分+BFS

##### 思路

- 题目要求的是找到一条路径，在这个路径上，消耗的体力值最小， 可以转化成，给定一个固定的体力值，是否能达到这找到一条路径到达终点

- 根据题意给出的`1-1000000`的范围，`max`值至多为这个区间，不断缩小这个区间的范围，`bfs`函数来判断是否能有这样一条路径

```java
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

```

#### 方法2：DFS

- 转成`dfs`来解，超时

```java
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
```

#### 方法3：Dijkstra

> [一文掌握Dijkstra算法](https://wat1r.github.io/2020/09/23/dijkstra-algorithm-master/)

##### 思路

- `dist[i][j]`表示起点`(0,0)`到`(i,j)`的某条路径上的相邻点的差值的最大值（即一条路径上的最小消耗值）

```java
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
```

#### 方法4:并查集

##### 思路

> [二维矩阵的常见转换技巧](https://wat1r.github.io/2020/09/29/two-direction-array-skill/)
>
> [一文掌握并查集算法](https://leetcode-cn.com/problems/redundant-connection/solution/yi-wen-zhang-wo-bing-cha-ji-suan-fa-by-a-fei-8/)

![image-20201026205943528](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dfs_bfs\paths\路径问题之最小体力消耗路径[Patinopecten Squirrel].assets\image-20201026205943528.png)

- 将相邻的边连接起来，索引的做法小技巧可参考 

```java
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
```



