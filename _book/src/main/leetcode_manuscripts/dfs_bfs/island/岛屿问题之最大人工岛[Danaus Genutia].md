## 岛屿问题之最大人工岛[Danaus Genutia]



![butterflies-2713343_640](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dfs_bfs\island\岛屿问题之最大人工岛[Danaus Genutia].assets\butterflies-2713343_640.png)



### 基础

设一维数组下标为index，二维数组长度为m * n，则：

#### 一维数组转换为二维数组

```java
row = index / n 
col = index % n
```

#### 二维数组转换为一维数组

```java
index = col + row * n
```



### 方法1:并查集

**[**一文掌握并查集算法**](***https://leetcode-cn.com/problems/redundant-connection/solution/yi-wen-zhang-wo-bing-cha-ji-suan-fa-by-a-fei-8/***)**

```java
    class _1st {

        int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        int m, n;

        public int largestIsland(int[][] grid) {

            m = grid.length;
            n = grid[0].length;
            //1.初始化UnionFind 并且做联通，当前点的down 和 right 方向
            UnionFind uf = new UnionFind(m * n);
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 1) {
                        if ((i + 1) < m && grid[i + 1][j] == 1) {
                            uf.unoin((i + 1) * n + j, i * n + j);
                        }
                        if ((j + 1) < n && grid[i][j + 1] == 1) {
                            uf.unoin(i * n + (j + 1), i * n + j);
                        }
                    }
                }
            }
            //2.计算当前点的所在区域的面积，并记录最大值，满格子为1(陆地的情况)
            int[] areas = new int[m * n];
            int res = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    int rootIdx = uf.find(i * n + j);
                    areas[rootIdx]++;
                    res = Math.max(res, areas[rootIdx]);
                }
            }

            //3.开始考虑0（海洋）的情况，我那个四个方向扩散
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 0) {
                        Set<Integer> rootSet = new HashSet<>();
                        int tmp = 1;//此点位0，改变其为1，陆地面积+1
                        for (int[] dir : dirs) {
                            int nextI = i + dir[0], nextJ = j + dir[1];//当前点的下一个点，四个方向
                            if (!inArea(nextI, nextJ)) continue;
                            int rootIdx = uf.find(nextI * n + nextJ);//找到当前点的共同父节点
                            if (grid[nextI][nextJ] == 1 && !rootSet.contains(rootIdx)) {//此父节点未被访问过
                                tmp += areas[rootIdx];
                                rootSet.add(rootIdx);
                            }
                        }
                        res = Math.max(res, tmp);//取最大值
                    }
                }
            }
            return res;
        }

        //判断坐标点是否在网格区域范围内
        private boolean inArea(int x, int y) {
            return x >= 0 && x < m && y >= 0 && y < n;
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

            public void unoin(int x, int y) {
                int rootX = find(x), rootY = find(y);
                if (rootX == rootY) return;
                if (ranks[rootX] > ranks[rootY]) parents[rootY] = rootX;
                if (ranks[rootX] < ranks[rootY]) parents[rootX] = rootY;
                if (ranks[rootX] == ranks[rootY]) {
                    parents[rootY] = rootX;
                    ranks[rootY]++;
                }
            }
        }
    }
```







### 方法2:DFS 连通块大小

> 参考官方题解

- 一个索引从2开始的$area$来记录，每个为1的格子对应的联通的1的数量
- 开始考虑0，将其变为1的时候，需要将当前位置的0的相邻位置的放进来

```java
 int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    int m, n;
    int[][] grid;

    public int largestIsland(int[][] grid) {
        this.grid = grid;
        m = grid.length;
        n = grid[0].length;
        int idx = 2;
        //准备一个从2开始的area数组，用来装当前坐标（编好号），装的是当前的这个格子联通了多少1
        int[] area = new int[m * n + 2];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    area[idx] = dfs(i, j, idx++);
                }
            }
        }
        //先找到最大值
        int res = 0;
        for (int a : area) res = Math.max(res, a);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {//是0 考虑，变成1
                    Set<Integer> visited = new HashSet<>();//记录被访问的
                    for (int p : getNeighbors(i, j)) {
                        if (grid[p / n][p % n] > 1) visited.add(grid[p / n][p % n]);
                    }
                    int tmp = 1;
                    for (int v : visited) tmp += area[v];
                    res = Math.max(res, tmp);
                }
            }
        }
        return res;
    }

    /**
     * 计算当前坐标 x，y四个方向的邻居 已经他们联通了多少个1
     * @param x
     * @param y
     * @param idx area的索引，从2开始
     * @return 1的数量
     */
    private int dfs(int x, int y, int idx) {
        int cnt = 1;
        grid[x][y] = idx;
        for (int p : getNeighbors(x, y)) {
            if (grid[p / n][p % n] == 1) {
                grid[p / n][p % n] = idx;
                cnt += dfs(p / n, p % n, idx);
            }
        }
        return cnt;
    }

    //获取当前坐标的四个方向的坐标，拍扁成一维的索引
    private List<Integer> getNeighbors(int x, int y) {
        List<Integer> res = new ArrayList<>();
        for (int[] dir : dirs) {
            int nextX = x + dir[0], nextY = y + dir[1];
            if (inArea(nextX, nextY)) res.add(nextX * n + nextY);
        }
        return res;
    }


    //判断坐标点是否在网格区域范围内
    private boolean inArea(int x, int y) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }
```

