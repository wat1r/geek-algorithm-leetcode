# 图论





## [搜索与图论之拓扑排序](https://blog.csdn.net/wat1r/article/details/113871449)









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
