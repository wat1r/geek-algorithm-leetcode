



### 树与图的存储

1. 树是一种特殊的图，与图的存储方式相同。
2. 对于无向图中的边ab，存储两条有向边a->b, b->a。
3. 因此以有向图为例，利用数组存储图
   1. 邻接矩阵：g[a][b] 存储边 a -> b
   2. 邻接表：

```java
class Main{
    int[] w = new int[N]; // 存储快乐指数，每条边的收益
    // 建立邻接表存储树
    int[] head = new int[N];    // 存储邻接表的表头
    int[] edge = new int[N];    // 按输入顺序存储每条边指向的节点
    int[] next = new int[N];    // 记录邻接表中当前节点的下一个节点
    int idx = 1;                // 记录边的序号,边的序号从1开始吧

    public void add(int a, int b){
        // 第idx边指向b 
        edge[idx] = b;
        // 采用头插法
        // 第idx边的下一个节点是上一个时刻的头节点
        next[idx] = head[a];
        // 当前链表头节点更新，指向第idx边
        head[a] = idx;
        // idx++ 更新边序号
        idx++;
    }
}
```

### 树与图的遍历

1. 深度优先遍历DFS

```java
int dfs(int u){
    // st[u] 表示 u 已经被遍历过
    st[u] = true;
    for(int i = h[u]; i != -1; i = ne[i]){
        int j = e[i];
        if(!st[j]){
            dfs(j);
        }
    }
}
```

1. 广度优先遍历BFS

```java
queue<int> q;
st[1] = true; // 表示1号点已经被遍历过
q.push(1);

while (q.size())
{
    int t = q.front();
    q.pop();

    for (int i = h[t]; i != -1; i = ne[i])
    {
        int j = e[i];
        if (!st[j])
        {
            st[j] = true; // 表示点j已经被遍历过
            q.push(j);
        }
    }
}
```

### 回溯模板

**DFS != 递归**

八皇后，数独可以总结为精确覆盖问题，dancing links

矩阵或者棋盘，一般数据范围比较小用搜索，数据范围比较大，用动态规划。

回溯，改变了状态之后需要恢复状态

回溯的**DFS**模板

```java
List<> res = new LinkedList<>();
void dfs(路径, 选择列表){
    if 满足结束条件{
        res.add(路径);
        return;
    }

    for 选择 in 选择列表{
        // 做选择;
        // 标记一下已经选了，有些题目不需要标记
        nums[i] = true;
        // 把选择的放进路径
        path.push(i)
        dfs(路径, 选择列表);
        
        // 恢复现场;
        path.pop(i);
        nums[i] = false;
    }
}
```

搜索题目，主要是确定顺序

1. 枚举起点
2. 从起点开始，依次搜索下一个点的位置
3. 在枚举过程中，要保证和目标匹配