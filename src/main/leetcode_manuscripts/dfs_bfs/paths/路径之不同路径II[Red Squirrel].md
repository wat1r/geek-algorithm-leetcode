## 路径之不同路径II[Red Squirrel]



![image-20200709193239845](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dp\paths\动态规划解路径之不同路径II[Red Squirrel].assets\image-20200709193239845.png)

> **欢迎阅读、点赞、转发、订阅，你的举手之间，我的动力源泉。**

---

> **每一个无障碍方格都要通过一次**。

- 题意要求每个无障碍的方格要通过一次，一条路径中的最后一个是$2$,这条路径才可以结束，但并非只的是在搜索的过程中，$2$这个位置是最后出现的，当碰到$2$时，可以回溯，下图给出了矩阵的两种路径：![980_1](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dfs_bfs\paths\路径之不同路径II[Red Squirrel].assets\980_1.jpg)

- 本题的特点是开始和结束的位置并不知道，路障的种类很多，求不止一条路径的种类，后续可以$follow$ $up$到记录路径的路线
- 准备两个主要方法：
  - $helper$: 做两件事情：一是找到开始的坐标$[X,Y]$，找打从其实位置到结束位置(也就是$2$的点)的路径长度$paths$
  - $dfs$: 搜索方法，具体的细节见代码注释，注意出口条件，边界，标记与反标记

```java
	int[][] directions = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};//四个方向
    int X = 0, Y = 0, paths = 1;//开始时的坐标轴（X,Y）及路径长度（初始化时为1，因为1本身也可以走）
    int m = 0, n = 0;//行*列

    public int uniquePathsIII(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        helper(X, Y, grid);
        return dfs(X, Y, paths, grid);
    }

    /**
     * @param x     坐标的row
     * @param y     坐标的col
     * @param paths 当前路径所剩的长度
     * @param grid  矩阵
     * @return
     */
    private int dfs(int x, int y, int paths, int[][] grid) {
        if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] == -1) return 0;//其中的-1表示是原矩阵的-1
        System.out.println(String.format("%d,%d", x, y));
        if (grid[x][y] == 2) {
            //这一句很关键，paths表示当前剩下的路径的长度，其为0表示已经走完了，开始收集，return 1 在原来的res基础上加上这个方案
            //当然有场景是未走完全部的0后，到达2这个终点的，这时候并不开始计算路径，因为paths不是0，表示没有全部走完0这种点，return 0 加上后对结果影响
            return paths == 0 ? 1 : 0;
        }
        grid[x][y] = -1;//如果当前的位置未被走过，标记为-1表示已经走过
        int res = 0;
        for (int i = 0; i < directions.length; i++) {//四个方向的结果都走，res+注意
            res += dfs(x + directions[i][0], y + directions[i][1], paths - 1, grid);
        }
        grid[x][y] = 0;//恢复当前的位置表示可走
        return res;
    }

    /**
     * 1.拿到开始时的坐标轴，即grid[i][j] =1时
     * 2.从1走到2要走过多少步，即矩阵中0的个数+1
     *
     * @param x    坐标的row
     * @param y    坐标的col
     * @param grid 矩阵
     */
    private void helper(int x, int y, int[][] grid) {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    X = i;
                    Y = j;
                    continue;
                }
                if (grid[i][j] == 0) paths++;
            }
        }
    }
```



