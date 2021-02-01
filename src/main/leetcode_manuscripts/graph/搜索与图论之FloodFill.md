## FloodFill

> 泛洪填充算法(**Flood Fill Algorithm**)泛洪填充算法又称洪水填充算法是在很多图形绘制软件中常用的填充算法，最熟悉不过就是 **windows paint**的油漆桶功能。算法的原理很简单，就是从一个点开始附近像素点，填充成新的颜色，直到封闭区域内的所有像素点都被填充新颜色为止。泛红填充实现最常见有四邻域像素填充法，八邻域像素填充法，基于扫描线的像素填充方法。根据实现又可以分为递归与非递归（基于栈）。

### [733. 图像渲染](https://leetcode-cn.com/problems/flood-fill/)

#### 方法1：DFS

- 当当前的位置的颜色和开始位置`(sr,sc)`的颜色不同时，不能继续`dfs`
- 如果一开始的时候，要修改的目标颜色和开始位置的颜色的值相同，不需要修改`imgage`

```java
        int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        int R, C;

        public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
            int oldColor = image[sr][sc];
            if (oldColor == newColor) return image;
            R = image.length;
            C = image[0].length;
            dfs(image, sr, sc, newColor, oldColor);
            return image;
        }
        public void dfs(int[][] image, int r, int c, int newColor, int oldColor) {
            if (!inArea(r, c)) return;
            if (image[r][c] != oldColor) return;
            image[r][c] = newColor;
            for (int[] d : dirs) {
                int nr = r + d[0], nc = c + d[1];
                dfs(image, nr, nc, newColor, oldColor);
            }
        }

        public boolean inArea(int r, int c) {
            return r >= 0 && r < R && c >= 0 && c < C;
        }
```

#### 方法2：BFS

```java
int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
int R, C;

public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
    int oldColor = image[sr][sc];
    if (oldColor == newColor) return image;
    R = image.length;
    C = image[0].length;
    Queue<int[]> queue = new LinkedList<>();
    queue.offer(new int[]{sr, sc});
    image[sr][sc] = newColor;
    while (!queue.isEmpty()) {
        int[] p = queue.poll();
        for (int[] d : dirs) {
            int nr = p[0] + d[0], nc = p[1] + d[1];
            if (!inArea(nr, nc)) continue;
            if (image[nr][nc] == oldColor) {
                image[nr][nc] = newColor;
                queue.offer(new int[]{nr, nc});
            }
        }
    }
    return image;
}

public boolean inArea(int r, int c) {
    return r >= 0 && r < R && c >= 0 && c < C;
}
```



### [1034. 边框着色](https://leetcode-cn.com/problems/coloring-a-border/) 



















785













695









886



200

[1020. 飞地的数量](https://leetcode-cn.com/problems/number-of-enclaves/)



130





1034





529扫雷游戏





827最大人工岛 Hard





Flood Fill 这类题还有很多：

- 
- [1254. 统计封闭岛屿的数目](https://leetcode-cn.com/problems/number-of-closed-islands/)
- [547. 朋友圈](https://leetcode-cn.com/problems/friend-circles/)

733. 图像渲染
463. 岛屿的周长
130. 被围绕的区域 1020.飞地的数量 （这两题基本一样的）
200. 岛屿数量
1254. 统计封闭岛屿的数目 130题与200题的组合

作者：mrmm
链接：https://leetcode-cn.com/problems/coloring-a-border/solution/jian-dan-yi-dong-dfsjie-jue-duo-dao-yan-se-tian-ch/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。



