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
