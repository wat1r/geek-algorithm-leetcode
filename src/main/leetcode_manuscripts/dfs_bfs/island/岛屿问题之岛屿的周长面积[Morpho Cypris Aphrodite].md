## 岛屿问题之岛屿的周长面积[Morpho Cypris Aphrodite]



![butterfly-2265010_640](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dfs_bfs\island\岛屿问题之岛屿的周长面积[Morpho Cypris Aphrodite].assets\butterfly-2265010_640.jpg)



### 1.岛屿的最大面积

![image-20200813201306940](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dfs_bfs\island\岛屿问题之岛屿的周长面积[Morpho Cypris Aphrodite].assets\image-20200813201306940.png)



#### 方法1:DFS

##### 思路

- $dfs$函数返回值是碰到陆地边+1，遇到非陆地，（0和2）的状态，则返回0，因为2这种状态是1转换来的，已经被计算过，不需要重复计算
- 遇到边界时，也是返回0
- 采用$directions$的方式进行上下左右探测

![image-20200813202358424](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dfs_bfs\island\岛屿问题之岛屿的周长面积[Morpho Cypris Aphrodite].assets\image-20200813202358424.png)

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





### 2.岛屿的周长

![image-20200813204508076](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dfs_bfs\island\岛屿问题之岛屿的周长面积[Morpho Cypris Aphrodite].assets\image-20200813204508076.png)





#### 方法1：计算

![image-20200813204449400](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dfs_bfs\island\岛屿问题之岛屿的周长面积[Morpho Cypris Aphrodite].assets\image-20200813204449400.png)

```java
    public int islandPerimeter(int[][] grid) {
        int res = 0;
        int m = grid.length, n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    res += 4;
                    if (i > 0 && grid[i - 1][j] == 1) res -= 2;
                    if (j > 0 && grid[i][j - 1] == 1) res -= 2;
                }
            }
        }
        return res;
    }
```

>  也可以倒着遍历

```java
    public int islandPerimeter(int[][] grid) {
        int res = 0;
        int m = grid.length, n = grid[0].length;
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (grid[i][j] == 1) {
                    res += 4;
                    if (i < m - 1 && grid[i + 1][j] == 1) res -= 2;
                    if (j < n - 1 && grid[i][j + 1] == 1) res -= 2;
                }
            }
        }
        return res;
    }
```

#### 方法2：DFS



