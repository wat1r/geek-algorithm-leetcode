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
