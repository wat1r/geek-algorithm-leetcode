## 岛屿问题之岛屿的周长面积[Morpho Cypris Aphrodite]



![butterfly-2265010_640](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dfs_bfs\island\岛屿问题之岛屿的周长面积[Morpho Cypris Aphrodite].assets\butterfly-2265010_640.jpg)







### 方法1:DFS



```java
    int m, n;
    int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};//对应了上右下左四个方向

    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int res = 0;
        m = grid.length;
        n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int seg = dfs(grid, i, j);
                res = Math.max(res, seg);
            }
        }
        return res;

    }

    private int dfs(int[][] grid, int i, int j) {
        if (isOut(i, j) || grid[i][j] != 1) return 0;
        grid[i][j] = 2;
        int seg = 1;
        for (int[] direction : directions) {
            int nextI = i + direction[0];
            int nextJ = j + direction[1];
            seg += dfs(grid, nextI, nextJ);
        }
        return seg;

    }

    public boolean isOut(int i, int j) {
        return i < 0 || i >= m || j < 0 || j >= n;
    }
```



