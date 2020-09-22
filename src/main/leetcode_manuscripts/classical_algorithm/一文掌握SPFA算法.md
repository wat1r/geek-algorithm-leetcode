## 一文掌握SPFA算法

>  何为SPFA（Shortest Path Faster Algorithm）算法:
>
> **给定一个加权连通图，选取一个顶点，称为起点，求取起点到其它所有顶点之间的最短距离，其显著特点是可以求含负权图的单源最短路径，且效率较高。（**spfa是求单源[最短路径](http://baike.baidu.com/item/最短路径)的一种算法，它还有一个重要的功能是判负环（在差分约束系统中会得以体现），在[Bellman-ford](http://baike.baidu.com/item/Bellman-ford)算法的基础上加上一个队列优化，减少了冗余的[松弛操作](http://baike.baidu.com/item/松弛操作)，是一种高效的最短路算法。）
>
> **spfa算法思想：**spfa就是BellmanFord的一种实现方式，其具体不同在于，对于处理松弛操作时，采用了队列（先进先出方式）操作，从而大大降低了时间复杂度



![image-20200921211548307](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\classical_algorithm\一文掌握SPFA算法.assets\image-20200921211548307.png)

### 思路

- 算法大致流程是用一个队列来进行维护。 初始时将源加入队列。 每次从队列中取出一个元素，并对所有与他相邻的点进行[松弛](http://www.nocow.cn/index.php/松弛)，若某个相邻的点松弛成功，如果该点没有在队列中，则将其入队。 直到队列为空时算法结束。

- 判断有无负环：如果某个点进入队列的次数超过V次则存在负环（SPFA无法处理带负环的图）

### 核心代码

```java
    public long[] dis;         //用于得到第s个顶点到其它顶点之间的最短距离

    /*
     * 参数n:给定图的顶点个数
     * 参数s:求取第s个顶点到其它所有顶点之间的最短距离
     * 参数edge:给定图的具体边
     * 函数功能：如果给定图不含负权回路，则可以得到最终结果，如果含有负权回路，则不能得到最终结果
     */
    public boolean getShortestPaths(int n, int s, Edge[] edges) {
        List<Integer> queue = new ArrayList<>();
        dis = new long[n];
        boolean[] used = new boolean[n];
        int[] num = new int[n];
        Arrays.fill(dis,Integer.MAX_VALUE);
        dis[s] = 0;     //第s个顶点到自身距离为0
        used[s] = true;    //表示第s个顶点进入数组队
        num[s] = 1;       //表示第s个顶点已被遍历一次
        queue.add(s);      //第s个顶点入队
        while (queue.size() != 0) {
            int a = queue.get(0);   //获取数组队中第一个元素
            queue.remove(0);         //删除数组队中第一个元素
            System.out.println(a);
            for (int i = 0; i < edges.length; i++) {
                //当queue的第一个元素等于边edges[i]的起点时
                if (a == edges[i].u && dis[edges[i].v] > dis[edges[i].u] + edges[i].w) {
                    dis[edges[i].v] = dis[edges[i].u] + edges[i].w;
                    if (!used[edges[i].v]) {
                        queue.add(edges[i].v);
                        num[edges[i].v]++;
                        if (num[edges[i].v] > n) return false;
                        used[edges[i].v] = true;   //表示边edges[i]的终点b已进入数组队
                    }
                }
            }
            used[a] = false;        //顶点a出数组对
        }
        return true;
    }
```

### 数据结构

```java
    //内部类，用于存放图的具体边数据
    class Edge {
        public int u;  //边的起点
        public int v;  //边的终点
        public int w;   //边的权值

        Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }
```

### 测试

```java
    private void testOne() {
        System.out.println("请输入一个图的顶点总数n起点下标s和边总数p：");
        int n = 6;
        int s = 0;
        int p = 8;
        Edge[] edges = new Edge[p];
        edges[0] = new Edge(0, 2, 10);
        edges[1] = new Edge(0, 4, 30);
        edges[2] = new Edge(0, 5, 100);
        edges[3] = new Edge(1, 2, 5);
        edges[4] = new Edge(2, 3, 50);
        edges[5] = new Edge(3, 5, 10);
        edges[6] = new Edge(4, 3, 20);
        edges[7] = new Edge(4, 5, 60);
        if (getShortestPaths(n, s, edges)) {
            for (int i = 0; i < dis.length; i++) {
                System.out.print(dis[i] + " ");
            }
        } else
            System.out.println("给定图存在负环，没有最短距离");

    }

```



### Reference

- https://www.cnblogs.com/liuzhen1995/p/6535025.html
- https://blog.csdn.net/qq_35644234/article/details/61614581
- https://www.jianshu.com/p/544d7d801355



