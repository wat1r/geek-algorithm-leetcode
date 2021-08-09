## 有向无环图中的最长路径-PART1

> **给定一个加权有向无环图DAG和一个源点s在它，找到从s中最长的距离在给定图中的所有其他顶点**

一般图的**最长路径**问题不像**最短路径**问题那么容易，因为最长路径问题不具有最优子结构属性。事实上，**最长路径**问题对于一般的图来说是**NP-Hard**问题。然而，对于有向无环图的最长路径问题，其具有线性时间解。这个想法类似于有向无环图中最短路径的线性时间解决方案，我们使用拓扑排序。 

我们将到所有顶点的距离初始化为负无穷大，到源点的距离初始化为 0，然后我们找到图的拓扑排序。图的拓扑排序表示图的线性排序（图b是图a的线性表示）。一旦我们有了拓扑顺序（或线性表示），我们就按照拓扑顺序一一处理所有顶点。对于正在处理的每个顶点，我们使用当前顶点的距离更新其相邻的距离。
下图显示了寻找最长路径的详细过程。

![image-20210809095700517](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\graph\有向无环图中的最长路径-PART1.assets\image-20210809095700517.png)

### Alogrithm:

以下是寻找最长距离的完整算法。 

1. 初始化 dist[] = {NINF, NINF, ....} 和 dist[s] = 0 其中 s 是源顶点。这里 NINF 表示负无穷大。 
2. 创建所有顶点的拓扑顺序。 
3. 按照拓扑顺序对每个顶点 u 执行以下操作。 
   ……….. 对 u 的每个相邻顶点 v 进行跟踪 
   ………………if (dist[v] < dist[u] + weight(u, v)) 
   ………………………dist[ v] = dist[u] + weight(u, v) 

### Code:

```java
static class _4th_1 {
    static class AdjListNode {
        int v;
        int w;

        public AdjListNode(int v, int w) {
            this.v = v;
            this.w = w;
        }

        public int getV() {
            return v;
        }

        public int getW() {
            return w;
        }
    }

    static class Graph {
        int V;
        List<List<AdjListNode>> adj;

        public Graph(int V) {
            this.V = V;
            adj = new ArrayList<>(V);
            for (int i = 0; i < V; i++) {
                adj.add(new ArrayList<>());
            }
        }

        void addEdge(int u, int v, int w) {//添加边，有向图
            AdjListNode node = new AdjListNode(v, w);
            adj.get(u).add(node);
        }

        void topologicalSortUtil(int v, boolean[] visited, Stack<Integer> stack) {
            visited[v] = true;//当前节点被标记为访问过
            for (AdjListNode node : adj.get(v)) {//遍历当前节点的相邻节点
                if (!visited[node.getV()]) {
                    topologicalSortUtil(node.getV(), visited, stack);
                }
            }
            stack.push(v);
        }

        final static Integer NINF = Integer.MIN_VALUE;

        void longestPath(int s) {
            Stack<Integer> stack = new Stack<>();
            int[] dist = new int[V];
            boolean[] visited = new boolean[V];
            //先做拓扑排序
            for (int i = 0; i < V; i++) {
                if (!visited[i]) topologicalSortUtil(i, visited, stack);
            }
            Arrays.fill(dist, NINF);
            dist[s] = 0;//初始化源点s
            //遍历节点的相邻节点
            while (!stack.isEmpty()) {
                int u = stack.pop();
                if (dist[u] != NINF) {
                    for (AdjListNode vNode : adj.get(u)) {
                        int v = vNode.getV(), w = vNode.getW();
                        if (dist[v] < dist[u] + w) {
                            dist[v] = dist[u] + w;
                        }
                    }
                }
            }
            //打印最长路径
            for (int i = 0; i < V; i++)
                if (dist[i] == NINF)
                    System.out.print("INF ");
                else
                    System.out.print(dist[i] + " ");
        }


        public static void main(String args[]) {
            // Create a graph given in the above diagram.
            // Here vertex numbers are 0, 1, 2, 3, 4, 5 with
            // following mappings:
            // 0=r, 1=s, 2=t, 3=x, 4=y, 5=z
            Graph g = new Graph(6);
            g.addEdge(0, 1, 5);
            g.addEdge(0, 2, 3);
            g.addEdge(1, 3, 6);
            g.addEdge(1, 2, 2);
            g.addEdge(2, 4, 4);
            g.addEdge(2, 5, 2);
            g.addEdge(2, 3, 7);
            g.addEdge(3, 5, 1);
            g.addEdge(3, 4, -1);
            g.addEdge(4, 5, -2);

            int s = 1;
            System.out.print("Following are longest distances from source vertex " + s + " \n");
            g.longestPath(s);
            /**
             * Following are longest distances from source vertex 1
             * INF 0 2 9 8
             */
        }
    }
}
```

#### **时间复杂度**

- 拓扑排序的时间复杂度为 $O(V+E)$。找到拓扑顺序后，算法处理所有顶点，对于每个顶点，它为所有相邻顶点运行一个循环。图中的总相邻顶点为 $O(E)$。所以内循环运行$ O(V+E) $次。因此，该算法的整体时间复杂度为 $O(V+E)$。

### Reference

- [Longest Path in a Directed Acyclic Graph](https://www.geeksforgeeks.org/find-longest-path-directed-acyclic-graph/)

   

