# 图论





## [搜索与图论之拓扑排序](https://blog.csdn.net/wat1r/article/details/113871449)







## [210. 课程表 II](https://leetcode-cn.com/problems/course-schedule-ii/)

### 方法1:BFS+入度

> 这题完全就是207题的姐妹题，207要求是否能学完课程，返回布尔，这里是返回一个学完课程的路径

下面是207的代码


```java
    public boolean canFinish(int numCourses, int[][] prerequisites) {
    //1.计算入度表，[u,v] v->u
    //入度(indegree)就是有向图中指向这个点的边的数量，即有向图的某个顶点作为终点的次数和
    int[] indegrees = new int[numCourses];
    for (int[] p : prerequisites) {
        indegrees[p[0]]++;
    }
    //2.将入度为0的点放入queue中,queue中装的是点的下标索引，即是入度表中的索引
    //题意：你这个学期必须选修 numCourse 门课程，记为 0 到 numCourse-1 。课程名称与索引是对应的
    Queue<Integer> queue = new LinkedList<>();
    for (int i = 0; i < indegrees.length; i++) {
        if (indegrees[i] == 0) queue.offer(i);
    }
    //3.轮转这个queue，这个queue中弹出的节点其实是v->u中的v
    //弹出一个节点，将课程数-1，在prerequisites中找到这个节点的指向节点，即通过v->u
    //如果u找到了，将其入度-1，如果入度为0了，将其加到queue中
    while (!queue.isEmpty()) {
        int pre = queue.poll();//这个是v->u的v
        numCourses--;
        for (int[] p : prerequisites) {
            if (p[1] != pre) continue;
            indegrees[p[0]]--;
            if (indegrees[p[0]] == 0) queue.offer(p[0]);
        }
    }
    //判断有没有剩余的课程数
    return numCourses == 0;
}
```
只需要添加记录路径的逻辑

需要一个array来记录路径的大小，大小等于numCourse，因为要是能学完的话，是需要numCourse=0的
index记录加入路径的游标
完整代码

```java
public int[] findOrder(int numCourses, int[][] prerequisites) {
    int[] indegrees = new int[numCourses];
    int[] res = new int[numCourses];//添加的code
    for (int[] p : prerequisites) indegrees[p[0]]++;
    Queue<Integer> queue = new LinkedList<>();
    for (int i = 0; i < numCourses; i++) {
        if (indegrees[i] == 0) queue.offer(i);
    }
    int index = 0;//添加的code
    while (!queue.isEmpty()) {
        int pre = queue.poll();
        res[index++] = pre;//添加的code
        numCourses--;
        for (int[] p : prerequisites) {
            if (p[1] != pre) continue;
            indegrees[p[0]]--;
            if (indegrees[p[0]] == 0) queue.offer(p[0]);
        }
    }
    return numCourses == 0 ? res : new int[0];//添加的code
}
```





## [310. 最小高度树](https://leetcode-cn.com/problems/minimum-height-trees/)


### 概念



#### 拓扑排序

**拓扑排序的定义**

> 对一个[有向无环图](https://baike.baidu.com/item/有向无环图/10972513)(Directed Acyclic Graph简称DAG)G进行拓扑排序，是将G中所有顶点排成一个线性序列，使得图中任意一对顶点u和v，若边<u,v>∈E(G)，则u在线性序列中出现在v之前。通常，这样的线性序列称为满足拓扑次序(Topological Order)的序列，简称拓扑序列。简单的说，由某个集合上的一个[偏序](https://baike.baidu.com/item/偏序/2439087)得到该集合上的一个[全序](https://baike.baidu.com/item/全序/10577699)，这个操作称之为拓扑排序。

有向无环图在`Spark`的设计中是很核心的基础概念

**拓扑排序能做什么?**

> 用来检测是否有环

大体分三步：

1.在有向图中找出（没有前驱）入度为零的点，并且输出。

2.从图中删除以它为弧尾的边（删除从它出发的边）

3.重复1、2两步直至所有顶点全部输出，或者图中不存在入度为零的顶点（剩下的就是环），说明有向图有环。

#### 一些概念

##### 邻接表

- 邻接表是一种顺序分配和链式分配相结合的存储结构。
  （即利用数组和链表相结合）

- 用数组存储每条链头结点（头结点即每个顶点），链结点存储的是边的内容（存储该顶点的邻接表或**出边**）。

  头结点结构定义，需存储顶点信息即其**后继边**，保存后继关系。

  链表结点保存边内容，包含该边所连终点编号，以及链的后继关系（**但要注意，并非是边与边的后继关系。实际上是头结点顶点与边终点的后继关系**）

  利用一维数组保存头结点，称为邻接表

```c++
typedef struct Vnode
    {    Vertex data;           //顶点信息
         ArcNode *firstarc;     //指向第一条边
    }  VNode;//头结点数组

    typedef struct ANode
    {     int adjvex;           //该边的终点编号
          struct ANode *nextarc;//指向下一条边的指针
          InfoType info;        //该边的权值等信息
    }  ArcNode;//链表节点，存边内容

    typedef struct 
    {     VNode adjlist[MAXV] ; //邻接表
           int n，e;         //图中顶点数n和边数e
    } AdjGraph;

    AdjGraph *G；//声明一个邻接表存储的图G

```

> AOV&AOE

`AOV`网，顶点表示活动，弧表示活动间的优先关系的有向图。 即如果a->b,那么a是b的先决条件。

`AOE`网，边表示活动，是一个带权的有向无环图， 其中顶点表示事件，弧表示活动，权表示活动持续时间。

求拓扑序列就是`AOV`，求关键路径就是`AOE`


##### **入度**

> 入度(indegree)就是有向图中指向这个点的边的数量，即有向图的某个顶点作为终点的次数和

##### **出度**

> 出度(outdegree)就是从这个点出去的边的数量，即有向图的某个顶点作为起点的次数和



![](/imgs/leetcode/classify/image-20220406142505835.png)







### 方法1:BFS+邻接矩阵

- 从叶子节点开始bfs，逐步深入到中心节点，最后一层bfs留下的点，是到叶子节点距离最短的点，也就是最小高度树的根节点

```java
public List<Integer> findMinHeightTrees(int n, int[][] edges) {
    List<Integer> res = new ArrayList<>();
    if (n == 1) {
        res.add(0);
        return res;
    }
    List<List<Integer>> graph = new ArrayList<>();
    for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
    int[] outdegrees = new int[n];
    for (int[] e : edges) {
        int u = e[0], v = e[1];
        outdegrees[u]++;
        outdegrees[v]++;
        graph.get(u).add(v);
        graph.get(v).add(u);
    }
    Queue<Integer> q = new LinkedList<>();
    for (int i = 0; i < n; i++) {
        if (outdegrees[i] == 1) q.offer(i);
    }
    while (!q.isEmpty()) {
        res = new ArrayList<>();
        int size = q.size();
        for (int i = 0; i < size; i++) {
            int u = q.poll();
            res.add(u);
            List<Integer> vs = graph.get(u);
            for (int v : vs) {
                outdegrees[v]--;
                if (outdegrees[v] == 1) q.offer(v);

            }
        }
    }
    return res;
}
```

另外一种写法：

- 不使用queue存储叶子节点，不存储出度，size为1的邻接矩阵为出度为1的点，即叶子节点

```java
public List<Integer> findMinHeightTrees(int n, int[][] edges) {
    List<Integer> res = new ArrayList<>();
    if (n == 1) {
        res.add(0);
        return res;
    }
    List<Set<Integer>> adj = new ArrayList<>();
    for (int i = 0; i < n; i++) adj.add(new HashSet<>());
    for (int[] e : edges) {
        int u = e[0], v = e[1];
        adj.get(u).add(v);
        adj.get(v).add(u);
    }
    //叶子节点
    List<Integer> leaves = new ArrayList<>();
    for (int i = 0; i < n; i++) if (adj.get(i).size() == 1) leaves.add(i);
    while (n > 2) {
        n -= leaves.size();
        List<Integer> tmp = new ArrayList<>();
        for (int u : leaves) {
            Iterator<Integer> it = adj.get(u).iterator();
            while (it.hasNext()) {
                int v = it.next();
                adj.get(v).remove(u);
                if (adj.get(v).size() == 1) tmp.add(v);

            }
        }
        leaves = tmp;
    }
    return leaves;
}
```

### 方法2:BFS+邻接表







## [675. 为高尔夫比赛砍树](https://leetcode.cn/problems/cut-off-trees-for-golf-event/)

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





## [1293. 网格中的最短路径](https://leetcode.cn/problems/shortest-path-in-a-grid-with-obstacles-elimination/)

### 方法1：BFS

```java
     int m, n;
        //右 下 左 上
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        //我们还可以对搜索空间进行优化。注意到题目中 k 的上限为 m * n，但考虑一条从 (0, 0) 向下走到 (m - 1, 0) 再向右走到
        // (m - 1, n - 1) 的路径，它经过了 m + n - 1 个位置，其中起点 (0, 0) 和终点 (m - 1, n - 1) 没有障碍物，
        // 那么这条路径上最多只会有 m + n - 3 个障碍物。因此我们可以将 k 的值设置为 m + n - 3 与其本身的较小值
        // min(k, m + n - 3)，将广度优先搜索的时间复杂度从 O(MNK) 降低至 (MN∗min(M+N,K))

        public int shortestPath(int[][] grid, int k) {
            m = grid.length;
            n = grid[0].length;
            //case ->
            //[[0]]
            //1
            if (m == 1 && n == 1) return 0;
//            if ( k >= m + n - 3){
//                return m + n - 2;
//            }
//            k = Math.min(k, m + n - 3);
            boolean[][][] vis = new boolean[m][n][k + 1];
            Queue<Position> q = new LinkedList<>();
            //标记访问的状态
            q.offer(new Position(0, 0, k));
            vis[0][0][k] = true;
            int steps = 0;
            while (!q.isEmpty()) {
                int size = q.size();
                steps++;
                for (int i = 0; i < size; i++) {
                    Position p = q.poll();
                    for (int[] d : dirs) {
                        int nx = p.x + d[0], ny = p.y + d[1];
                        if (nx >= m || nx < 0 || ny >= n || ny < 0) {
                            continue;
                        }
                        if (grid[nx][ny] == 0 && !vis[nx][ny][p.count]) {
                            if (nx == m - 1 && ny == n - 1) {
                                return steps;
                            }
                            q.offer(new Position(nx, ny, p.count));
                            vis[nx][ny][p.count] = true;
                        } else if (grid[nx][ny] == 1 && p.count > 0 && !vis[nx][ny][p.count - 1]) {
                            q.offer(new Position(nx, ny, p.count - 1));
                            vis[nx][ny][p.count - 1] = true;
                        }

                    }
                }
            }
            return -1;
        }

        class Position {
            int x, y;
            int count;//当前状态下还可以经过多少个障碍物，此数量为非负

            public Position(int x, int y, int count) {
                this.x = x;
                this.y = y;
                this.count = count;
            }
        }
```

## [1368. 使网格图至少有一条有效路径的最小代价](https://leetcode.cn/problems/minimum-cost-to-make-at-least-one-valid-path-in-a-grid/)

### 方法1：01广度优先搜索

```java
int m, n;
int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

public int minCost(int[][] grid) {
    m = grid.length;
    n = grid[0].length;
    int[] dist = new int[m * n];
    Arrays.fill(dist, Integer.MAX_VALUE);
    dist[0] = 0;
    boolean[] vis = new boolean[m * n];
    Deque<Integer> deque = new ArrayDeque<>();
    deque.offerFirst(0);
    while (!deque.isEmpty()) {
        int cur_pos = deque.pollFirst();
        if (vis[cur_pos]) {
            continue;
        }
        vis[cur_pos] = true;
        int x = cur_pos / n, y = cur_pos % n;
        for (int i = 0; i < 4; i++) {
            int nx = x + dirs[i][0], ny = y + dirs[i][1];
            int new_pos = nx * n + ny;
            //1:右 2:左 3:下 4:上
            //当前的[x,y]的值如果是符合1 2 3 4 的值，则不需要修改，也就是 0
            int new_dist = dist[cur_pos] + (grid[x][y] == i + 1 ? 0 : 1);
            if (nx < 0 || nx >= m || ny < 0 || ny >= n) {
                continue;
            }
            if (new_dist < dist[new_pos]) {
                dist[new_pos] = new_dist;
                //01广度优先搜索，将0的那个点加入到队首，1的那个点加入到对尾
                if (grid[x][y] == i + 1) {
                    deque.offerFirst(new_pos);
                } else {
                    deque.offerLast(new_pos);
                }
            }
        }
    }
    return dist[m * n - 1];
}
```

- 另，不适用vis标记数组

```java
    int m, n;
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        public int minCost(int[][] grid) {
            m = grid.length;
            n = grid[0].length;
            int[] dist = new int[m * n];
            Arrays.fill(dist, Integer.MAX_VALUE);
            dist[0] = 0;
            Deque<Integer> deque = new ArrayDeque<>();
            deque.offerFirst(0);
            while (!deque.isEmpty()) {
                int cur_pos = deque.pollFirst();
                int x = cur_pos / n, y = cur_pos % n;
                for (int i = 0; i < 4; i++) {
                    int nx = x + dirs[i][0], ny = y + dirs[i][1];
                    int new_pos = nx * n + ny;
                    //1:右 2:左 3:下 4:上
                    //当前的[x,y]的值如果是符合1 2 3 4 的值，则不需要修改，也就是 0
                    int new_dist = dist[cur_pos] + (grid[x][y] == i + 1 ? 0 : 1);
                    if (nx < 0 || nx >= m || ny < 0 || ny >= n) {
                        continue;
                    }
                    if (new_dist < dist[new_pos]) {
                        dist[new_pos] = new_dist;
                        //01广度优先搜索，将0的那个点加入到队首，1的那个点加入到对尾
                        if (grid[x][y] == i + 1) {
                            deque.offerFirst(new_pos);
                        } else {
                            deque.offerLast(new_pos);
                        }
                    }
                }
            }
            return dist[m * n - 1];
        }
```



### 方法2:Dijkstra

```java
        class Point {
            int dist;
            int r;
            int c;

            Point() {
            }

            Point(int dist, int r, int c) {
                this.dist = dist;
                this.r = r;
                this.c = c;
            }
        }

        int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        public int minCost(int[][] grid) {
            int m = grid.length;
            int n = grid[0].length;
            boolean[][] vis = new boolean[m][n];
            int[][] dist = new int[m][n];
            for (int r = 0; r < m; r++) {
                Arrays.fill(dist[r], Integer.MAX_VALUE);
            }
            //按距离排序
            PriorityQueue<Point> pq = new PriorityQueue<>((o1, o2) -> o1.dist - o2.dist);
            pq.offer(new Point(0, 0, 0));
            dist[0][0] = 0;
            while (!pq.isEmpty()) {
                Point cur = pq.poll();
                int d = cur.dist, r = cur.r, c = cur.c;
                if (vis[r][c]) {
                    continue;
                }
                vis[r][c] = true;
                for (int k = 0; k < 4; k++) {
                    int nr = r + dirs[k][0], nc = c + dirs[k][1];
                    int cost = (k + 1 == grid[r][c] ? 0 : 1);
                    if (0 <= nr && nr < m && 0 <= nc && nc < n && dist[r][c] + cost < dist[nr][nc]) {
                        dist[nr][nc] = dist[r][c] + cost;
                        pq.offer(new Point(dist[nr][nc], nr, nc));
                    }
                }
            }
            return dist[m - 1][n - 1];
        }
```



### 方法3：Dijkstra

```java
   int m, n;
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};


        public int minCost(int[][] grid) {
            m = grid.length;
            n = grid[0].length;
            int[] dist = new int[m * n];
            Arrays.fill(dist, Integer.MAX_VALUE);
            boolean[] vis = new boolean[m * n];
            dist[0] = 0;
            PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> dist[a] - dist[b]);
            pq.offer(0);
            while (!pq.isEmpty()) {
                //当前点位置
                int cur_pos = pq.poll();
                if (vis[cur_pos]) {
                    continue;
                }
                vis[cur_pos] = true;
                //当前点的位置转化成坐标
                int x = cur_pos / n, y = cur_pos % n;
                for (int i = 0; i < 4; i++) {
                    int nx = x + dirs[i][0], ny = y + dirs[i][1];
                    if (nx < 0 || nx >= m || ny < 0 || ny >= n) {
                        continue;
                    }
                    int new_pos = nx * n + ny;
                    int new_dist = dist[cur_pos] + (grid[x][y] == i + 1 ? 0 : 1);
                    if (new_dist < dist[new_pos]) {
                        dist[new_pos] = new_dist;
                        pq.offer(new_pos);
                    }
                }
            }
            return dist[m * n - 1];
        }

```









## [2039. 网络空闲的时刻](https://leetcode-cn.com/problems/the-time-when-the-network-becomes-idle/)

![](/imgs/leetcode/classify/image-20220320105254556.png)

```java
static int V = 100_005;//顶点数量
static int E = V * 2;//边的数量
static int[] head = new int[V];//头节点数组
static int[] edge = new int[E];//按输入顺序存储每条边指向的节点
static int[] next = new int[E];//记录邻接表中当前节点的下一个节点
int idx = 0;//记录边的序号,边的序号从0开始 不能使用static
static int INF = Integer.MAX_VALUE / 2;
static int[] dist = new int[V];

public void addEdge(int u, int v) {
    edge[idx] = v;// 第idx边指向v
    next[idx] = head[u];//采用头插法 第idx边的下一个节点是上一个时刻的头节点
    head[u] = idx; // 当前链表头节点更新，指向第idx边
    idx++;// idx++ 更新边序号
}

public int networkBecomesIdle(int[][] edges, int[] patience) {
    Arrays.fill(head, -1);
    Arrays.fill(dist, INF);
    for (int[] e : edges) {
        addEdge(e[0], e[1]);
        addEdge(e[1], e[0]);
    }
    Deque<Integer> deque = new ArrayDeque<>();
    deque.addLast(0);
    dist[0] = 0;
    while (!deque.isEmpty()) {
        int u = deque.pollFirst();
        for (int i = head[u]; i != -1; i = next[i]) {
            int v = edge[i];
            if (dist[v] != INF) continue;
            dist[v] = dist[u] + 1;
            deque.addLast(v);
        }
    }
    int res = 0;
    for (int i = 1; i < patience.length; i++) {
        int d = dist[i] * 2;
        int t = patience[i];
        int cur = d <= t ? d : (d - 1) / t * t + d;
        if (cur > res) res = cur;
    }
    return res + 1;
}
```

### 附

\- [图算法专栏](https://blog.csdn.net/wat1r/category_10804842.html)





## [2049. 统计最高分的节点数目](https://leetcode-cn.com/problems/count-nodes-with-the-highest-score/)

### 方法1:邻接表+DFS

```java
int N = 100010, M = N * 2;
int[] head = new int[N];//存储某个节点所对应的边的集合(链表)的头节点
int[] to = new int[M];//某一条边的指向节点
int[] next = new int[M];//指针数组，存储当前边的下一条边
int idx = 0;
int[] f = new int[N];

private void add(int u, int v) {
    to[idx] = v;
    next[idx] = head[u];
    head[u] = idx;
    idx++;
}


public int countHighestScoreNodes(int[] parents) {
    Arrays.fill(head, -1);
    int n = parents.length;
    for (int i = 1; i < n; i++) {
        add(parents[i], i);
    }
    dfs(0);
    long maxScore = 0;
    int cnt = 0;
    for (int u = 0; u < n; u++) {
        long score = 1;
        for (int i = head[u]; i != -1; i = next[i]) {
            score *= f[to[i]];
        }
        if (u != 0) {
            score *= n - f[u];
        }
        if (score > maxScore) {
            maxScore = score;
            cnt = 1;
        } else if (score == maxScore) {
            cnt++;
        }
    }
    return cnt;
}


private int dfs(int u) {
    int res = 1;
    for (int i = head[u]; i != -1; i = next[i]) {
        res += dfs(to[i]);
    }
    return f[u] = res;
}
```

### 方法2:DFS

```java
int n;
long maxScore = 0;
int cnt = 0;
List<Integer>[] children;

public int countHighestScoreNodes(int[] parents) {
    n = parents.length;
    children = new List[n];
    for (int i = 0; i < n; i++) children[i] = new ArrayList<>();
    for (int i = 0; i < n; i++) {
        if (parents[i] != -1) children[parents[i]].add(i);
    }
    dfs(0);
    return cnt;
}


private int dfs(int node) {
    long score = 1;
    int size = n - 1;
    for (int child : children[node]) {
        int t = dfs(child);
        score *= t;
        size -= t;
    }
    if (node != 0) score *= size;
    if (score == maxScore) {
        cnt++;
    } else if (score > maxScore) {
        maxScore = score;
        cnt = 1;
    }
    return n - size;
}
```



## [2087. 网格图中机器人回家的最小代价](https://leetcode.cn/problems/minimum-cost-homecoming-of-a-robot-in-a-grid/)

### 方法1：模拟

- 因为代价值都是正整数，所以往回走或者重复走都会增加代价，最佳方式是直角线走

```java
        public int minCost(int[] startPos, int[] homePos, int[] rowCosts, int[] colCosts) {
            int R = rowCosts.length, C = colCosts.length;
            int sr = startPos[0], sc = startPos[1];
            int er = homePos[0], ec = homePos[1];
            int cost = 0;
            int r_delta = (sr <= er ? 1 : -1);
            for (int i = sr + r_delta; r_delta == 1 ? i <= er : i >= er; i += r_delta) {
                if (i < R && i >= 0) cost += rowCosts[i];
            }
            int c_delta = (sc <= ec ? 1 : -1);
            for (int j = sc + c_delta; c_delta == 1 ? j <= ec : j >= ec; j += c_delta) {
                if (j < C && j >= 0) cost += colCosts[j];
            }
            return cost;
        }
```











### 方法3:拓扑排序

```java
public int countHighestScoreNodes(int[] parents) {
    int n = parents.length;
    int[] outdegrees = new int[n];//记录每个节点的出度
    for (int i = 1; i < n; i++) outdegrees[parents[i]]++;
    Deque<Integer> q = new ArrayDeque<>();
    for (int i = 0; i < n; i++) {
        if (outdegrees[i] == 0) q.offer(i);
    }
    //分别表示[i]节点的左右子树上节点的个数
    int[] left = new int[n], right = new int[n];
    while (!q.isEmpty()) {
        int t = q.poll();
        int fa = parents[t];
        outdegrees[fa]--;
        if (left[fa] == 0) left[fa] = left[t] + right[t] + 1;
        else right[fa] = left[t] + right[t] + 1;
        if (outdegrees[fa] == 0 && fa != 0) q.offer(fa);
    }
    long maxScore = 0;
    int cnt = 0;
    for (int i = 0; i < n; i++) {
        long score = (long) Math.max(1, left[i]) * Math.max(1, right[i]);
        if (i != 0) {
            score *= n - (left[i] + right[i] + 1);
        }
        if (score > maxScore) {
            maxScore = score;
            cnt = 1;
        } else if (score == maxScore) {
            cnt++;
        }
    }
    return cnt;
}
```





## [6081. 到达角落需要移除障碍物的最小数目](https://leetcode.cn/problems/minimum-obstacle-removal-to-reach-corner/)

### 方法1：01-BFS

```java
        int m, n;
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        public int minimumObstacles(int[][] grid) {
            m = grid.length;
            n = grid[0].length;
            int[] dist = new int[m * n];
            Arrays.fill(dist, Integer.MAX_VALUE);
            dist[0] = 0;
            boolean[] vis = new boolean[m * n];
            Deque<Integer> deque = new ArrayDeque<>();
            deque.offerFirst(0);
            while (!deque.isEmpty()) {
                int cur_pos = deque.pollFirst();
                if (vis[cur_pos]) {
                    continue;
                }
                vis[cur_pos] = true;
                int x = cur_pos / n, y = cur_pos % n;
                for (int i = 0; i < 4; i++) {
                    int nx = x + dirs[i][0], ny = y + dirs[i][1];
                    int new_pos = nx * n + ny;
                    if (nx < 0 || nx >= m || ny < 0 || ny >= n) {
                        continue;
                    }
                    int g = grid[x][y];
                    if (dist[cur_pos] + g < dist[new_pos]) {
                        dist[new_pos] = dist[cur_pos] + g;
                        //01广度优先搜索，将0的那个点加入到队首，1的那个点加入到对尾
                        if (g == 0) {
                            deque.offerFirst(new_pos);
                        } else {
                            deque.offerLast(new_pos);
                        }
                    }
                }
            }
            return dist[m * n - 1];
        }
```

- 另 ，数组写法，不转换坐标

```java
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        public int minimumObstacles(int[][] grid) {
            int m = grid.length;
            int n = grid[0].length;
            int[][] dis = new int[m][n];
            for (int i = 0; i < m; i++) {
                Arrays.fill(dis[i], Integer.MAX_VALUE);
            }
            dis[0][0] = 0;
            Deque<int[]> q = new ArrayDeque<>();
            q.addFirst(new int[]{0, 0});
            while (!q.isEmpty()) {
                int[] p = q.pollFirst();
                int x = p[0], y = p[1];
                for (int[] d : dirs) {
                    int nx = x + d[0], ny = y + d[1];
                    if (0 <= nx && nx < m && 0 <= ny && ny < n) {
                        int g = grid[nx][ny];
                        if (dis[x][y] + g < dis[nx][ny]) {
                            dis[nx][ny] = dis[x][y] + g;
                            if (g == 0) q.addFirst(new int[]{nx, ny});
                            else q.addLast(new int[]{nx, ny});
                        }
                    }
                }
            }
            return dis[m - 1][n - 1];
        }
```

### 方法2:01BFS

```java
      class Point {
            int value;
            int pos;

            Point() {
            }

            public Point(int value, int pos) {
                this.value = value;
                this.pos = pos;
            }
        }

        int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        public int minimumObstacles(int[][] grid) {
            int m = grid.length;
            int n = grid[0].length;
            int[][] dist = new int[m][n];
            for (int r = 0; r < m; r++) {
                Arrays.fill(dist[r], Integer.MAX_VALUE);
            }
            //按value 从小到大排序
            PriorityQueue<Point> pq = new PriorityQueue<>((o1, o2) -> o1.value - o2.value);
            pq.offer(new Point(grid[0][0], 0));
            dist[0][0] = 0;
            while (!pq.isEmpty()) {
                Point cur = pq.poll();
                int r = cur.pos / n, c = cur.pos % n;
                int value = cur.value;
                if (dist[r][c] < value) {
                    continue;
                }
                if (r == m - 1 && c == n - 1) {
                    return value;
                }
                for (int k = 0; k < 4; k++) {
                    int nr = r + dirs[k][0], nc = c + dirs[k][1];
                    int cost = (k + 1 == grid[r][c] ? 0 : 1);
                    if (0 <= nr && nr < m && 0 <= nc && nc < n) {
                        int next_value = grid[nr][nc] + value;
                        if (dist[nr][nc] > next_value) {
                            dist[nr][nc] = next_value;
                            pq.offer(new Point(next_value, nr * n + nc));
                        }
                    }
                }
            }
            return dist[m - 1][n - 1];
        }
```



