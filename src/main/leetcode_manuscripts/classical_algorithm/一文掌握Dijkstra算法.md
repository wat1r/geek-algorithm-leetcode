## 一文掌握Dijkstra算法

> 迪杰斯特拉(**Dijkstra**)算法是典型最短路径算法，用于计算一个节点到其他节点的最短路径。
> 它的主要特点是以起始点为中心向外层层扩展(广度优先搜索思想)，直到扩展到终点为止。

![dijkstra](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\classical_algorithm\一文掌握Dijkstra算法.assets\dijkstra.png)

#### 基本思想

1. 通过`Dijkstra`计算图`G`中的最短路径时，需要指定起点`s`(即从顶点`s`开始计算)。
2. 此外，引进两个集合`S`和`U`。`S`的作用是记录已求出最短路径的顶点(以及相应的最短路径长度)，而`U`则是记录还未求出最短路径的顶点(以及该顶点到起点`s`的距离)。
3. 初始时，`S`中只有起点`s`；`U`中是除`s`之外的顶点，并且`U`中顶点的路径是”起点s到该顶点的路径”。然后，从`U`中找出路径最短的顶点，并将其加入到`S`中；接着，更新`U`中的顶点和顶点对应的路径。 然后，再从`U`中找出路径最短的顶点，并将其加入到`S`中；接着，更新`U`中的顶点和顶点对应的路径。 … 重复该操作，直到遍历完所有顶点。

#### 操作步骤

1. 初始时，`S`只包含起点`s`；`U`包含除`s`外的其他顶点，且`U`中顶点的距离为”起点`s`到该顶点的距离”，例如，`U`中顶点v的距离为`(s,v)`的长度，然后`s`和`v`不相邻，则`v`的距离为`∞`。
2. 从`U`中选出”距离最短的顶点`k`”，并将顶点k加入到`S`中；同时，从`U`中移除顶点`k`。
3. 更新`U`中各个顶点到起点`s`的距离。之所以更新`U`中顶点的距离，是由于上一步中确定了`k`是求出最短路径的顶点，从而可以利用`k`来更新其它顶点的距离；例如，`(s,v)`的距离可能大于`(s,k)+(k,v)`的距离。
4. 重复步骤`(2)`和`(3)`，直到遍历完所有顶点。

单纯的看上面的理论可能比较难以理解，下面通过实例来对该算法进行说明。

#### 核心代码

```java
  /**
     * @param edges 传入的边
     * @param s     起始顶点
     * @param n
     * @return
     */
    public int[] dijkstra(int[][] edges, int s, int n) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] edge : edges)
            graph.computeIfAbsent(edge[0], e -> new ArrayList<>()).add(new int[]{edge[1], edge[2]});
        int[] dis = new int[n];
        Arrays.fill(dis, Integer.MAX_VALUE);
        boolean[] vis = new boolean[n];
        dis[s] = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(((o1, o2) -> dis[o1] - dis[o2]));
        pq.offer(s);
        while (!pq.isEmpty()) {
            int curr = pq.poll();
            if (vis[curr]) continue;
            vis[curr] = true;
            List<int[]> nexts = graph.getOrDefault(curr, new ArrayList<>());
            for (int[] next : nexts) {
                int to = next[0];
                int weigh = next[1];
                if (vis[to]) continue;
                if (dis[to] > dis[curr] + weigh) {
                    dis[to] = dis[curr] + weigh;
                }
                pq.offer(to);
            }
        }
        return dis;
    }
```

#### 测试

```java
    private void testOne() {
        int n = 6;//顶点数量
        int s = 0;//起点的下标索引
        int e = 8;//边的数量
        int[][] edges = new int[e][3];
        edges[0] = new int[]{0, 2, 10};
        edges[1] = new int[]{0, 4, 30};
        edges[2] = new int[]{0, 5, 100};
        edges[3] = new int[]{1, 2, 5};
        edges[4] = new int[]{2, 3, 50};
        edges[5] = new int[]{3, 5, 10};
        edges[6] = new int[]{4, 3, 20};
        edges[7] = new int[]{4, 5, 60};
//        System.out.println(JSON.toJSONString(edges));
        dijkstra(edges, s, n);
    }
```

### Reference

- https://blog.csdn.net/heroacool/article/details/51014824

