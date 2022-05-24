

## [LeetCode]675. 为高尔夫比赛砍树







## 题目

[675. 为高尔夫比赛砍树](https://leetcode.cn/problems/cut-off-trees-for-golf-event/)

```java
675. 为高尔夫比赛砍树
你被请来给一个要举办高尔夫比赛的树林砍树。树林由一个 m x n 的矩阵表示， 在这个矩阵中：

0 表示障碍，无法触碰
1 表示地面，可以行走
比 1 大的数 表示有树的单元格，可以行走，数值表示树的高度
每一步，你都可以向上、下、左、右四个方向之一移动一个单位，如果你站的地方有一棵树，那么你可以决定是否要砍倒它。

你需要按照树的高度从低向高砍掉所有的树，每砍过一颗树，该单元格的值变为 1（即变为地面）。

你将从 (0, 0) 点开始工作，返回你砍完所有树需要走的最小步数。 如果你无法砍完所有的树，返回 -1 。

可以保证的是，没有两棵树的高度是相同的，并且你至少需要砍倒一棵树。

 

示例 1：


输入：forest = [[1,2,3],[0,0,4],[7,6,5]]
输出：6
解释：沿着上面的路径，你可以用 6 步，按从最矮到最高的顺序砍掉这些树。
示例 2：


输入：forest = [[1,2,3],[0,0,0],[7,6,5]]
输出：-1
解释：由于中间一行被障碍阻塞，无法访问最下面一行中的树。
示例 3：

输入：forest = [[2,3,4],[0,0,5],[8,7,6]]
输出：6
解释：可以按与示例 1 相同的路径来砍掉所有的树。
(0,0) 位置的树，可以直接砍去，不用算步数。
 

提示：

m == forest.length
n == forest[i].length
1 <= m, n <= 50
0 <= forest[i][j] <= 109

```





## 解法



### 方法1：排序+BFS

```java
//格子的边界 row, col
int R, C;
int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
List<List<Integer>> forest;

public int cutOffTree(List<List<Integer>> forest) {
    R = forest.size();
    C = forest.get(0).size();
    this.forest = forest;
    List<int[]> trees = new ArrayList<>();
    for (int r = 0; r < R; r++) {
        for (int c = 0; c < C; c++) {
            if (forest.get(r).get(c) > 1) {
                trees.add(new int[]{r, c});
            }
        }
    }
    //排序按树高从小到大排序
    Collections.sort(trees, (a, b) -> forest.get(a[0]).get(a[1]) - forest.get(b[0]).get(b[1]));
    //source_x source_y 起始的点
    int sx = 0, sy = 0;
    int tot = 0;//总的步数
    for (int i = 0; i < trees.size(); i++) {
        //这一轮目标坐标 target_x target_y
        int tx = trees.get(i)[0], ty = trees.get(i)[1];
        int steps = bfs(sx, sy, tx, ty);
        if (steps == -1) return -1;
        tot += steps;
        sx = tx;
        sy = ty;
    }
    return tot;
}


/**
 * @param sx source_x
 * @param sy source_y
 * @param tx target_x
 * @param ty target_y
 * @return
 */
private int bfs(int sx, int sy, int tx, int ty) {
    //如果源点和目标点是同一个点，不需要行走
    if (sx == tx && sy == ty) return 0;
    Queue<int[]> q = new LinkedList<>();
    int step = 0;
    //访问数组
    boolean[][] vis = new boolean[R][C];
    q.add(new int[]{sx, sy});
    vis[sx][sy] = true;
    while (!q.isEmpty()) {
        step++;
        int size = q.size();
        for (int i = 0; i < size; i++) {
            int[] p = q.poll();
            //current_x current_y
            int cx = p[0], cy = p[1];
            for (int[] d : dirs) {
                //neighbor_x neighbor_y
                int nx = cx + d[0], ny = cy + d[1];
                //在边界范围内且没有被访问过 并且是树
                if (nx >= 0 && nx < R && ny >= 0 && ny < C
                        && !vis[nx][ny] && forest.get(nx).get(ny) > 0) {
                    if (nx == tx && ny == ty) {
                        return step;
                    }
                    q.offer(new int[]{nx, ny});
                    vis[nx][ny] = true;
                }
            }
        }
    }
    return -1;
}
```



### 方法2：Dijkstra 算法

```java
//格子的边界 row, col
int R, C;
int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
List<List<Integer>> forest;


public int cutOffTree(List<List<Integer>> forest) {
    R = forest.size();
    C = forest.get(0).size();
    this.forest = forest;
    List<int[]> trees = new ArrayList<int[]>();
    for (int r = 0; r < R; ++r) {
        for (int c = 0; c < C; ++c) {
            if (forest.get(r).get(c) > 1) {
                trees.add(new int[]{r, c});
            }
        }
    }
    Collections.sort(trees, (a, b) -> forest.get(a[0]).get(a[1]) - forest.get(b[0]).get(b[1]));

    int sx = 0, sy = 0;
    int tot = 0;
    for (int i = 0; i < trees.size(); ++i) {
        //这一轮目标坐标 target_x target_y
        int tx = trees.get(i)[0], ty = trees.get(i)[1];
        int steps = bfs(sx, sy, tx, ty);
        if (steps == -1) return -1;
        tot += steps;
        sx = tx;
        sy = ty;
    }
    return tot;
}

public int bfs(int sx, int sy, int tx, int ty) {
    if (sx == tx && sy == ty) {
        return 0;
    }

    PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b) -> a[0] - b[0]);
    boolean[][] vis = new boolean[R][C];
    pq.offer(new int[]{0, sx * C + sy});
    vis[sx][sy] = true;
    while (!pq.isEmpty()) {
        int[] t = pq.poll();
        int dist = t[0], loc = t[1];
        for (int j = 0; j < 4; ++j) {
            int nx = loc / C + dirs[j][0];
            int ny = loc % C + dirs[j][1];
            if (nx >= 0 && nx < R && ny >= 0 && ny < C
                    && !vis[nx][ny] && forest.get(nx).get(ny) > 0) {
                if (nx == tx && ny == ty) {
                    return dist + 1;
                }
                pq.offer(new int[]{dist + 1, nx * C + ny});
                vis[nx][ny] = true;
            }
        }
    }
    return -1;
}
```



### 附

\- [图算法专栏](https://blog.csdn.net/wat1r/category_10804842.html)



