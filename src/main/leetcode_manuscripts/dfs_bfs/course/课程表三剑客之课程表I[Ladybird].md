## 课程表三剑客之课程表I[Ladybird]

![ladybug-4795004_640](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dfs_bfs\course\课程表三剑客之课程表I[Ladybird].assets\ladybug-4795004_640.jpg)

#### 拓扑排序

**拓扑排序的定义**

> 对一个[有向无环图](https://baike.baidu.com/item/有向无环图/10972513)(Directed Acyclic Graph简称DAG)G进行拓扑排序，是将G中所有顶点排成一个线性序列，使得图中任意一对顶点u和v，若边<u,v>∈E(G)，则u在线性序列中出现在v之前。通常，这样的线性序列称为满足拓扑次序(Topological Order)的序列，简称拓扑序列。简单的说，由某个集合上的一个[偏序](https://baike.baidu.com/item/偏序/2439087)得到该集合上的一个[全序](https://baike.baidu.com/item/全序/10577699)，这个操作称之为拓扑排序。

有向无环图在$Spark$的设计中是很核心的基础概念

**拓扑排序能做什么?**

> 用来检测是否有负环

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
    {    Vertex data;			//顶点信息
         ArcNode *firstarc;		//指向第一条边
    }  VNode;//头结点数组

    typedef struct ANode
    {     int adjvex;			//该边的终点编号
          struct ANode *nextarc;//指向下一条边的指针
          InfoType info;		//该边的权值等信息
    }  ArcNode;//链表节点，存边内容

    typedef struct 
    {     VNode adjlist[MAXV] ;	//邻接表
           int n，e;			//图中顶点数n和边数e
    } AdjGraph;

    AdjGraph *G；//声明一个邻接表存储的图G

```

> AOV&AOE

$AOV$网，顶点表示活动，弧表示活动间的优先关系的有向图。 即如果a->b,那么a是b的先决条件。

$AOE$网，边表示活动，是一个带权的有向无环图， 其中顶点表示事件，弧表示活动，权表示活动持续时间。

求拓扑序列就是$AOV$，求关键路径就是$AOE$

##### **入度**

> 入度(indegree)就是有向图中指向这个点的边的数量，即有向图的某个顶点作为终点的次数和

##### **出度**

> 出度(outdegree)就是从这个点出去的边的数量，即有向图的某个顶点作为起点的次数和

---

本题中的$(u,v)$,表示$v$是$u$的前置条件，要想完成$u$，必须先完成$v$，如想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们：`[0,1]`

![image-20200804091056165](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dfs_bfs\course\课程表三剑客之课程表I[Ladybird].assets\image-20200804091056165.png)

本题的想法变成是找到这个有向图无环，如下面这样的有向环图，我们不知道该从哪里开始课程

![image-20200804091434993](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dfs_bfs\course\课程表三剑客之课程表I[Ladybird].assets\image-20200804091434993.png)

### 方法1：BFS+入度表

- 首先生成入度表$indegrees$,因为$(u,v)$的$u$表示终点，$prerequisites[0]$是$u$

- 需要找到入度为0的那个顶点，将这个$index$推入队列$queue$
- 当$queue$非空的时候，不断转这个队列
  - 弹出元素$pre$作为当前的顶点，课程$numCourses$--，开始在$prerequires$中找这个顶点，这个$pre$会作为下个顶点的前置顶点，所以找的是$prerequisites[1]$
  - 每次找到顶点$pre$时，将其入度-1
  - 将入度为0的顶点的索引加入到$queue$中

- 返回$numCourses == 0$

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

#### **复杂度分析**:

- **时间复杂度**：$O(N + M)$, $N$为节点数量，$M$为临边数量；
- **空间复杂度**：$O(N + M)$： 为建立邻接表所需额外空间。

### 方法2：DFS+邻接矩阵

> 待补充

```java
public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[][] adjacency = new int[numCourses][numCourses];
        int[] flags = new int[numCourses];
        for (int[] p : prerequisites) {
            adjacency[p[1]][p[0]] = 1;
        }
        for (int i = 0; i < numCourses; i++) {
            if (!canFinish1stDFS(adjacency, flags, i)) {
                return false;
            }
        }
        return true;
    }

    private boolean canFinish1stDFS(int[][] adjacency, int[] flags, int i) {
        if (flags[i] == 1) return false;
        if (flags[i] == -1) return true;
        flags[i] = 1;
        for (int j = 0; j < adjacency.length; j++) {
            if (adjacency[i][j] == 1 && !canFinish1stDFS(adjacency, flags, j)) {
                return false;
            }
        }
        flags[i] = -1;
        return true;
    }
```









### Reference

 [DS博客作业04--图](https://www.cnblogs.com/zml7/p/12832715.html)

[数据结构关于AOV与AOE网的区别](https://www.cnblogs.com/lpxblog/p/4994531.html)