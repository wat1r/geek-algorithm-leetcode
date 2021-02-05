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

![background-1695579_640](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\graph\搜索与图论之FloodFill.assets\background-1695579_640.jpg)

#### 方法1：DFS+计数

- dfs返回的值是0或者1
  - 当如果不是和`oldColor`相同的颜色，返回1
  - 当不在区域范围或者当前的`(r,c)`点被访问过，返回0
- 当cnt<4时，说明有一个方向是可以突围的，是和外面是联通的，不是被当前点的同类颜色所包围

```java
        int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        int R, C;
        boolean[][] visited;
        int oldColor;


        public int[][] colorBorder(int[][] grid, int r0, int c0, int newColor) {
            R = grid.length;
            C = grid[0].length;
            visited = new boolean[R][C];
            oldColor = grid[r0][c0];
            dfs(grid, r0, c0, newColor);
            return grid;
        }


        public int dfs(int[][] grid, int r, int c, int newColor) {
            if (!inArea(r, c)) return 0;
            if (visited[r][c]) return 1;
            if (grid[r][c] != oldColor) return 0;
            visited[r][c] = true;
            int cnt = 0;
            for (int[] d : dirs) {
                int nr = r + d[0], nc = c + d[1];
                cnt += dfs(grid, nr, nc, newColor);
            }
            if (cnt < 4) grid[r][c] = newColor;
            return 1;
        }


        public boolean inArea(int r, int c) {
            return r >= 0 && r < R && c >= 0 && c < C;
        }
```

#### 方法2：DFS+翻转成负数

> 思路图片来自国际站

- 在dfs的过程中，将其翻转成负数，在结束的时候，翻转负数成正数
- 下面的思路与下图大体相当，需要开一个额外的`res`二维数组
- `res`需要重新开地址赋值

![image-20210203191122047](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\graph\搜索与图论之FloodFill.assets\image-20210203191122047.png)

```java
   int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        int R, C;
        int[][] res;

        public int[][] colorBorder(int[][] grid, int r0, int c0, int color) {
            R = grid.length;
            C = grid[0].length;
            res = new int[R][C];
            for (int r = 0; r < R; r++)
                for (int c = 0; c < C; c++)
                    res[r][c] = grid[r][c];
            dfs(grid, r0, c0, color);
            return res;
        }


        public void dfs(int[][] grid, int r, int c, int color) {
            res[r][c] *= -1;
            boolean isEdge = false;
            boolean f = false;
            for (int[] d : dirs) {
                int nr = r + d[0], nc = c + d[1];
                if (d[0] == -1) System.out.printf("上\n");
                if (d[0] == 1) System.out.printf("下\n");
                if (d[1] == -1) System.out.printf("左\n");
                if (d[1] == 1) System.out.printf("右\n");
                //当不在区域范围内或者当前颜色与旁边的颜色不同时 如下面的例子1
                if (!inArea(nr, nc) || grid[r][c] != grid[nr][nc]) {
                    isEdge = true;
                }
                //res[nr][nc]首次翻转的时候（变成负数）一定会进下面的dfs，当再次被翻转后，其变成与grid的原坐标相同的时候，不会再进
                if (inArea(nr, nc) && res[nr][nc] == grid[r][c]) {
                    dfs(grid, nr, nc, color);
                }

            }
            res[r][c] *= -1;
            if (isEdge) res[r][c] = color;
        }

        public boolean inArea(int r, int c) {
            return r >= 0 && r < R && c >= 0 && c < C;
        }
```

> 例1
>
> 当在(0,3)位置，扫其右侧的(0,4)位置时，满足`grid[r][c] != grid[nr][nc]`

```java
[[1,2,1,2,1,2],[2,2,2,2,1,2],[1,2,2,2,1,2]]
1
3
1
```

#### 方法3：BFS

```java
int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
int R, C;

public int[][] colorBorder(int[][] grid, int r0, int c0, int color) {
    R = grid.length;
    C = grid[0].length;
    int oldColor = grid[r0][c0];
    boolean[][] visited = new boolean[R][C];
    Queue<int[]> queue = new LinkedList<>();
    queue.offer(new int[]{r0, c0});
    visited[r0][c0] = true;
    while (!queue.isEmpty()) {
        int[] p = queue.poll();
        int r = p[0], c = p[1];
        if (isBorder(r, c)) grid[r][c] = color;
        for (int[] d : dirs) {
            if (d[0] == -1) System.out.printf("上\n");
            if (d[0] == 1) System.out.printf("下\n");
            if (d[1] == -1) System.out.printf("左\n");
            if (d[1] == 1) System.out.printf("右\n");
            int nr = r + d[0], nc = c + d[1];
            System.out.printf("(%d,%d)<--->(%d,%d)\n", r, c, nr, nc);
            if (!inArea(nr, nc) || visited[nr][nc]) continue;
            if (grid[nr][nc] == oldColor) {//挨着的点不是原来的oldColor，当前点便是异类
                queue.offer(new int[]{nr, nc});
                visited[nr][nc] = true;
            } else {
                grid[r][c] = color;
            }
        }
    }
    return grid;
}

//注意是或
public boolean isBorder(int r, int c) {
    return r == 0 || r == R - 1 || c == 0 || c == C - 1;
}

public boolean inArea(int r, int c) {
    return r >= 0 && r < R && c >= 0 && c < C;
}
```

> 配的例子

```java
int[][] grid = new int[][]{
        {1, 1, 1, 1, 2, 2},
        {3, 3, 3, 3, 2, 2},
        {3, 3, 3, 2, 3, 3},
        {3, 3, 2, 3, 3, 3},
        {3, 2, 2, 2, 3, 3},
        {3, 2, 2, 2, 3, 3},
        {3, 3, 2, 2, 3, 3}
};
int color = 4;
int r0 = 5, c0 = 1;
```



### [785. 判断二分图](https://leetcode-cn.com/problems/is-graph-bipartite/)

![beehive-3434143_640](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\graph\搜索与图论之FloodFill.assets\beehive-3434143_640.jpg)



> 这个问题可以抽象为：给定一个无向图，判断是否能找到一个使用两种颜色的着色方案，使每条边连接的两点颜色均不同

#### 方法1：DFS染色

```java
public boolean isBipartite(int[][] graph) {
    int[] visited = new int[graph.length];
    for (int i = 0; i < graph.length; i++) {
        //当前点没有被访问过且染色失败，返回false
        if (visited[i] == 0 && !dfs(graph, i, 1, visited)) return false;
    }
    return true;
}


/**
 * @param graph   图
 * @param curr    当前处理的顶点
 * @param color   当前顶点即将被染的颜色
 * @param visited 记录顶点是否被访问过
 * @return 成功染色，返回true，失败染色返回false
 */
public boolean dfs(int[][] graph, int curr, int color, int[] visited) {
    if (visited[curr] != 0) {
        return visited[curr] == color;
    }
    visited[curr] = color;
    for (int next : graph[curr]) {
        if (!dfs(graph, next, -color, visited)) return false;
    }
    return true;
}
```

#### 方法2：BFS染色

```java
public boolean isBipartite(int[][] graph) {
    int[] v = new int[graph.length];
    for (int i = 0; i < graph.length; i++) {
        //对没有染色并且有邻居的进行下面的循环
        if (v[i] == 0 && graph[i].length > 0) {
            Queue<Integer> q = new LinkedList<>();
            q.offer(i);
            v[i] = 1;
            while (!q.isEmpty()) {
                int size = q.size();
                for (int j = 0; j < size; j++) {
                    int curr = q.poll();
                    for (int next : graph[curr]) {
                        //如果curr的邻居处于没有被染色的状态,染上一与curr相反的颜色，curr为1,next为-1，curr为-1，next为1
                        if (v[next] == 0) {
                            q.offer(next);
                            v[next] = -1 * v[curr];
                        } 
                        //这时候next已经染上色了，开始对其染色进行判断，如果next与curr同色，不符合题意
                        else if (v[curr] == v[next]) {
                            return false;
                        }
                    }
                }
            }
        }
    }
    return true;
}
```

### [886. 可能的二分法](https://leetcode-cn.com/problems/possible-bipartition/)

![orange-1173991_640](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\graph\搜索与图论之FloodFill.assets\orange-1173991_640.jpg)

- 需要构建图，其他的模板类似于785二分图这题

#### 方法1：DFS染色

- 构造图，其余的和785一毛一样

```java
public boolean possibleBipartition(int N, int[][] dislikes) {
    int[] visited = new int[N + 1];
    List<Integer>[] graph = new List[N + 1];
    for (int i = 1; i <= N; ++i) graph[i] = new ArrayList<>();
    for (int[] d : dislikes) {
        graph[d[0]].add(d[1]);
        graph[d[1]].add(d[0]);
    }
    for (int i = 1; i <= N; ++i) {
        //当前点没有被访问过且染色失败，返回false
        if (visited[i] == 0 && !dfs(graph, i, 1, visited)) return false;
    }
    return true;
}

/**
 * @param graph   图
 * @param curr    当前处理的顶点
 * @param color   当前顶点即将被染的颜色
 * @param visited 记录顶点是否被访问过
 * @return 成功染色，返回true，失败染色返回false
 */
public boolean dfs(List<Integer>[] graph, int curr, int color, int[] visited) {
    if (visited[curr] != 0) {
        return visited[curr] == color;
    }
    visited[curr] = color;
    for (int next : graph[curr]) {
        if (!dfs(graph, next, -color, visited)) return false;
    }
    return true;
}
```

#### 方法2：BFS染色

```java
public boolean possibleBipartition(int N, int[][] dislikes) {
    //v 表示从1 到 N 的节点，其各自的颜色，初始化为0， 两种颜色 1 和-1
    int[] v = new int[N + 1];
    //构造图
    List<Integer>[] graph = new List[N + 1];
    for (int i = 1; i <= N; ++i) graph[i] = new ArrayList<>();
    //无向图
    for (int[] d : dislikes) {
        graph[d[0]].add(d[1]);
        graph[d[1]].add(d[0]);
    }
    for (int i = 1; i <= N; ++i) {
        if (v[i] != 0) continue;//已经染色的不需要再染色了
        Queue<Integer> q = new LinkedList<>();
        q.offer(i);
        v[i] = 1;//染色1
        while (!q.isEmpty()) {
            int curr = q.poll();
            for (int next : graph[curr]) {
                if (v[next] != 0) {//next如果是和curr一样的颜色，说明是同类，但是他们各自不喜欢，返回false
                    if (v[curr] == v[next]) return false;
                } else {
                    q.offer(next);
                    v[next] = -v[curr];//翻转next的颜色为curr的相反数
                }
            }
        }
    }
    return true;
}
```

#### 番外

> 构建图的另外的方式

```java
Map<Integer, Set<Integer>> graph = new HashMap<>();
for (int[] d : dislikes) {
    int s = d[0], t = d[1];
    graph.putIfAbsent(s, new HashSet<>());
    graph.putIfAbsent(t, new HashSet<>());
    graph.get(s).add(t);
    graph.get(t).add(s);
}
```



### [529. 扫雷游戏](https://leetcode-cn.com/problems/minesweeper/)

![bee-2984342_640](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\graph\搜索与图论之FloodFill.assets\bee-2984342_640.jpg)

#### 方法1：DFS

- 八方向，需要额外多做一个探测地雷的操作`detect`

```java
int R, C;
int[][] dirs = new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}};

public char[][] updateBoard(char[][] board, int[] click) {
    R = board.length;
    C = board[0].length;
    dfs(board, click[0], click[1]);
    return board;
}

public void dfs(char[][] board, int r, int c) {
    if (!inArea(r, c)) return;
    if (board[r][c] == 'M') {
        board[r][c] = 'X';
        return;
    }
    if (board[r][c] == 'E') {
        detect(board, r, c);
        if (board[r][c] == 'B') {
            for (int[] d : dirs) {
                int nr = r + d[0], nc = c + d[1];
                dfs(board, nr, nc);
            }
        }
    }
}

public void detect(char[][] board, int r, int c) {
    int bomb = 0;
    for (int[] d : dirs) {
        int nr = r + d[0], nc = c + d[1];
        if (inArea(nr, nc) && board[nr][nc] == 'M') bomb++;
    }
    board[r][c] = bomb > 0 ? (char) (bomb + '0') : 'B';
}


private boolean inArea(int r, int c) {
    return r >= 0 && r < R && c >= 0 && c < C;
}
```

#### 方法2：BFS

- 用`visited`控制重复访问

```java
int R, C;
int[][] dirs = new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}};

public char[][] updateBoard(char[][] board, int[] click) {
    R = board.length;
    C = board[0].length;
    int sr = click[0], sc = click[1];
    if (board[sr][sc] == 'M') {
        board[sr][sc] = 'X';
        return board;
    }
    bfs(board,sr,sc);
    return board;
}


public void bfs(char[][] board, int r, int c) {
    boolean[][] visited = new boolean[R][C];
    Queue<int[]> q = new LinkedList<>();
    q.offer(new int[]{r, c});
    visited[r][c] = true;
    while (!q.isEmpty()) {
        int[] curr = q.poll();
        int currR = curr[0], currC = curr[1];
        detect(board, currR, currC);
        if (board[currR][currC] == 'B') {
            for (int[] d : dirs) {
                int nextR = currR + d[0], nextC = currC + d[1];
                if (inArea(nextR, nextC) && !visited[nextR][nextC]) {
                    q.offer(new int[]{nextR, nextC});
                    visited[nextR][nextC] = true;
                }
            }
        }
    }
}


private boolean inArea(int r, int c) {
    return r >= 0 && r < R && c >= 0 && c < C;
}


public void detect(char[][] board, int r, int c) {
    int bomb = 0;
    for (int[] d : dirs) {
        int nr = r + d[0], nc = c + d[1];
        if (inArea(nr, nc) && board[nr][nc] == 'M') bomb++;
    }
    board[r][c] = bomb > 0 ? (char) (bomb + '0') : 'B';
}
```

### [200. 岛屿数量](https://leetcode-cn.com/problems/number-of-islands/)

[岛屿问题之岛屿的数量Eighty-eight Butterfly](https://leetcode-cn.com/problems/number-of-islands/solution/dao-yu-wen-ti-zhi-dao-yu-de-shu-liang-eighty-eight/)

### [827. 最大人工岛](https://leetcode-cn.com/problems/making-a-large-island/)

[ 岛屿问题之最大人工岛Danaus Genutia](https://leetcode-cn.com/problems/making-a-large-island/solution/dao-yu-wen-ti-zhi-zui-da-ren-gong-dao-danaus-genut/)

### [695. 岛屿的最大面积](https://leetcode-cn.com/problems/max-area-of-island/)

[岛屿问题之岛屿的周长面积Morpho Cypris Aphrodite](https://leetcode-cn.com/problems/island-perimeter/solution/dao-yu-wen-ti-zhi-dao-yu-de-zhou-chang-mian-ji-mor/)

### [463. 岛屿的周长](https://leetcode-cn.com/problems/island-perimeter/)

[岛屿问题之岛屿的周长面积Morpho Cypris Aphrodite](https://leetcode-cn.com/problems/island-perimeter/solution/dao-yu-wen-ti-zhi-dao-yu-de-zhou-chang-mian-ji-mor/)

### [1254. 统计封闭岛屿的数目](https://leetcode-cn.com/problems/number-of-closed-islands/)

[岛屿问题之不同岛屿的数量Monarch Butterfly](https://leetcode-cn.com/problems/number-of-islands/solution/dao-yu-wen-ti-zhi-bu-tong-dao-yu-de-shu-liang-mona/)

### [130. 被围绕的区域](https://leetcode-cn.com/problems/surrounded-regions/)

[岛屿问题之被围绕的区域[Cicada]](https://leetcode-cn.com/problems/surrounded-regions/solution/dao-yu-wen-ti-zhi-bei-wei-rao-de-qu-yu-cicada-by-2/)



### [289. 生命游戏](https://leetcode-cn.com/problems/game-of-life/)



![background-1760294_640](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\graph\搜索与图论之FloodFill.assets\background-1760294_640.jpg)

### 方法1：未操作

> 来自国际站@yavinci大神，地址在[这里](https://leetcode.com/problems/game-of-life/discuss/73223/Easiest-JAVA-solution-with-explanation)

- 可以使用2 bits来存储这几种状态

```java
[2nd bit, 1st bit] = [next state, current state]
//从左到右是第2位，第1位， 
- 00  dead (next) <- dead (current)  0(10) //十进制的0
- 01  dead (next) <- live (current)  1(10) //十进制的1
- 10  live (next) <- dead (current)  2(10) //十进制的2
- 11  live (next) <- live (current)  3(10) //十进制的3
```

- 一些解释
  - 一开始的时候，只有0和1 也就是只有`00` 和 `01`这两种二进制状态
  - 注意到第1位的状态`1st`与第2位`2nd`的状态是独立的
  - 想象下，所有的细胞从`1st`状态切换到`2nd`状态
  - 计算当前点从`1st`状态的存活着的邻居，依据规则设置`2nd`状态
  - 因为`2nd`默认是0，也就是死亡的，没有必要考虑01->00
  - 在结束前，移除`1st`状态，保留`2nd`的状态，返回`board`  执行>> 操作即可

- 检查每个细胞的 `1st`状态，然后根据存活状态的邻居数量来设置`2nd`

```java
01->11 //一开始的时候是存活的，继续保持存活需要满足：
    board[r][c]== 1 && lives in (2,3)
00->10 //一开始的时候是死亡的，转成存活需要满足：
    board[r][c]==0 && lives in (3)
```

> 位操作小技巧

```java
//获取当前低位 1st
board[[r][c]&1 
//获取下个状态位 即高位 2nd
board[r][c]>>1
```

- 与（ & ）每一位进行比较，两位都为1，结果为1，否则为0（-4 & 1 = 0）

```java
  1 0 0 1 1 -->(19)[10]  表示10进制中的19
& 1 1 0 0 1 -->(25)[10]
------------------------------
  1 0 0 0 1 -->(17)[10]
```



- 右移（ >> ） 整体右移，左边空出位补零或补1（负数补1，整数补0），右边位舍弃 （-4 >> 1 = -2）

```java
unsigned int a = 8;
a >> 3;
移位前：0000 0000 0000 0000 0000 0000 0000 1000 -->(8)[10]
移位后：0000 0000 0000 0000 0000 0000 0000 0001 -->(1)[10] 相当于 / 2^3

int a = -8;
a >> 3;
移位前：1111 1111 1111 1111 1111 1111 1111 1000 -->(-8)[10]
移位前：1111 1111 1111 1111 1111 1111 1111 1111 -->(-1)[10] 
```











#### 方法2：BFS染色

```java
  int R, C;
        int[][] dirs = new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}};


        public void gameOfLife(int[][] board) {
            if (board == null || (board.length == 0 || board[0].length == 0)) return;
            R = board.length;
            C = board[0].length;
            bfs(board, 0, 0);
//            PrintUtils.printMatrix(board);
            for (int r = 0; r < R; ++r)
                for (int c = 0; c < C; ++c)
                    //被标记的发生翻转
                    if (board[r][c] == -1) board[r][c] = 0;
                    else if (board[r][c] == -2) board[r][c] = 1;
//            PrintUtils.printMatrix(board);
        }


        public void bfs(int[][] board, int sr, int sc) {
            boolean[][] visited = new boolean[R][C];
            Queue<int[]> q = new LinkedList<>();
            q.offer(new int[]{sr, sc});
            visited[sr][sc] = true;//标记原始(sr,sc)被访问过
            while (!q.isEmpty()) {
                int[] curr = q.poll();
                int cr = curr[0], cc = curr[1];//当前点
                int cnt = 0;
                for (int[] d : dirs) {
                    int nr = cr + d[0], nc = cc + d[1];
                    if (!inArea(nr, nc)) continue;
                   	//当前为1 或者我们暂时标记的-1 cnt++
                    if (board[nr][nc] == 1 || board[nr][nc] == -1) cnt++;
                    if (visited[nr][nc]) continue;
                    q.offer(new int[]{nr, nc});
                    visited[nr][nc] = true;
                }
//                System.out.printf("(%d,%d)-%d\n", cr, cc, cnt);
                if (board[cr][cc] == 1) {//当前细胞为活细胞
                    if (cnt < 2 || cnt > 3) board[cr][cc] = -1;//<2 >3 两种情况下需要设置当前的活细胞为死细胞，区别0这种，我们设置为-1
                    else if (cnt == 2 || cnt == 3) board[cr][cc] = 1;//等于2 等于3 活细胞继续活着
                } else if (board[cr][cc] == 0) {//当前细胞为死细胞
                    if (cnt == 3) board[cr][cc] = -2;//死细胞复活，区别1这种活细胞，设置为-2
                }
            }
        }


        private boolean inArea(int r, int c) {
            return r >= 0 && r < R && c >= 0 && c < C;
        }
```





























































[1020. 飞地的数量](https://leetcode-cn.com/problems/number-of-enclaves/)







[547. 朋友圈](https://leetcode-cn.com/problems/friend-circles/)





















