package com.frankcooper.graph;

import java.util.*;

public class GraphAlgorithm {


    /**
     * 判断一个图是否是树
     */
    static class _1st {
        static class Graph {
            int V;
            List<Integer>[] adj;

            public Graph(int V) {
                this.V = V;
                adj = new List[V];
                for (int i = 0; i < V; i++) {
                    adj[i] = new LinkedList<>();
                }
            }

            void addEdge(int u, int v) {
                adj[u].add(v);
                adj[v].add(u);
            }


            boolean isCyclicUtil(int u, boolean[] vis, int parent) {
                vis[u] = true;
                for (int v : adj[u]) {
                    if (!vis[v]) {
                        if (isCyclicUtil(v, vis, u)) return true;
                    } else if (v != parent) return true;
                }
                return false;
            }


            boolean isTree() {
                boolean[] vis = new boolean[V];
                if (isCyclicUtil(0, vis, -1)) return false;//有环则不是一棵树
                for (int u = 0; u < V; u++) {
                    if (!vis[u]) return false;//存在没有访问到的节点，说明有部分节点不可达，不是一棵树
                }
                return true;
            }

            public static void main(String args[]) {
                // Create a graph given in the above diagram
                Graph g1 = new Graph(5);
                g1.addEdge(1, 0);
                g1.addEdge(0, 2);
                g1.addEdge(0, 3);
                g1.addEdge(3, 4);
                if (g1.isTree())
                    System.out.println("Graph is Tree");
                else
                    System.out.println("Graph is not Tree");

                Graph g2 = new Graph(5);
                g2.addEdge(1, 0);
                g2.addEdge(0, 2);
                g2.addEdge(2, 1);
                g2.addEdge(0, 3);
                g2.addEdge(3, 4);

                if (g2.isTree())
                    System.out.println("Graph is Tree");
                else
                    System.out.println("Graph is not Tree");
                /**
                 * Graph is Tree
                 * Graph is not Tree
                 */
            }
        }
    }

    /**
     * 查找有向图中两个顶点之间是否存在路径
     */
    static class _2nd {
        static class Graph {
            int V;
            List<Integer>[] adj;

            public Graph(int V) {
                this.V = V;
                adj = new List[V];
                for (int i = 0; i < V; i++) {
                    adj[i] = new LinkedList<>();
                }
            }

            void addEdge(int u, int v) {
                adj[u].add(v);
            }

            /**
             * @param s 起始顶点 source
             * @param d 结束顶点 destination
             * @return
             */
            boolean isReachable(int s, int d) {
                boolean[] visited = new boolean[V];
                Queue<Integer> queue = new LinkedList<>();
                queue.offer(s);
                visited[s] = true;
                while (!queue.isEmpty()) {
                    int u = queue.poll();
                    for (int v : adj[u]) {
                        if (v == d) return true;
                        if (!visited[v]) {
                            visited[v] = true;
                            queue.offer(v);
                        }
                    }
                }
                return false;
            }

            public static void main(String args[]) {
                // Create a graph given in the above diagram
                Graph g = new Graph(4);
                g.addEdge(0, 1);
                g.addEdge(0, 2);
                g.addEdge(1, 2);
                g.addEdge(2, 0);
                g.addEdge(2, 3);
                g.addEdge(3, 3);

                int u = 1;
                int v = 3;
                if (g.isReachable(u, v))
                    System.out.println("There is a path from " + u + " to " + v);
                else
                    System.out.println("There is no path from " + u + " to " + v);
                u = 3;
                v = 1;
                if (g.isReachable(u, v))
                    System.out.println("There is a path from " + u + " to " + v);
                else
                    System.out.println("There is no path from " + u + " to " + v);
            }
            /**
             * There is a path from 1 to 3
             * There is no path from 3 to 1
             */
        }
    }

    //图的传递闭包
    static class _3rd {
        static class GraphClosure {
            final static int V = 4;

            void transitiveClosure(int graph[][]) {
                int[][] reach = new int[V][V];
                int i, j, k;
                for (i = 0; i < V; i++) {
                    for (j = 0; j < V; j++) {
                        reach[i][j] = graph[i][j];
                    }
                }
                for (k = 0; k < V; k++) {
                    for (i = 0; i < V; i++) {
                        for (j = 0; j < V; j++) {
                            reach[i][j] = (reach[i][j] != 0 || (reach[i][k] != 0 && reach[k][j] != 0)) ? 1 : 0;
                        }
                    }
                }
                printSolution(reach);
            }

            void printSolution(int reach[][]) {
                System.out.println("Following matrix is transitive closure of the given graph");
                for (int i = 0; i < V; i++) {
                    for (int j = 0; j < V; j++) {
                        if (i == j)
                            System.out.print("1 ");
                        else
                            System.out.print(reach[i][j] + " ");
                    }
                    System.out.println();
                }
            }

            public static void main(String[] args) {
                /* Let us create the following weighted graph
                   10
                (0)------->(3)
                |         /|\
              5 |          |
                |          | 1
                \|/        |
                (1)------->(2)
                   3           */

                /* Let us create the following weighted graph

                      10
                 (0)------->(3)
                  |         /|\
                5 |          |
                  |          | 1
                 \|/         |
                 (1)------->(2)
                    3           */
                int graph[][] = new int[][]{{1, 1, 0, 1},
                        {0, 1, 1, 0},
                        {0, 0, 1, 1},
                        {0, 0, 0, 1}
                };

                // Print the solution
                GraphClosure g = new GraphClosure();
                g.transitiveClosure(graph);
            }
        }
    }


    /**
     * 有向无环图中的最长路径-PART1
     */
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


    /**
     * 有向无环图的所有拓扑排序
     */
    static class _4th_2 {
        static class Graph {
            int V;
            List<Integer>[] adj;

            public Graph(int V) {
                this.V = V;
                adj = new LinkedList[V];
                for (int i = 0; i < V; i++) {
                    adj[i] = new LinkedList<>();
                }
            }

            void addEdge(int u, int v) {
                adj[u].add(v);
            }


            void allTopologicalSortsUtil(boolean[] visited, int[] indegree, List<Integer> stack) {
                boolean flag = false;//标志位，表示是否所有的拓扑排序被找到与否
                for (int i = 0; i < V; i++) {
                    if (!visited[i] && indegree[i] == 0) {//选择未访问过并且入度为0的顶点
                        visited[i] = true;
                        stack.add(i);
                        for (int next : adj[i]) {
                            indegree[next]--;
                        }
                        allTopologicalSortsUtil(visited, indegree, stack);
                        //回溯
                        visited[i] = false;
                        stack.remove(stack.size() - 1);
                        for (int next : adj[i]) {
                            indegree[next]++;
                        }
                        flag = true;
                    }
                }
                //如果找到，则打印
                if (!flag) {
                    stack.forEach(i -> System.out.print(i + " "));
                    System.out.println();
                }

            }

            void allTopologicalSorts() {
                boolean[] visited = new boolean[V];
                int[] indegree = new int[V];
                for (int i = 0; i < V; i++) {
                    for (int x : adj[i]) {
                        indegree[x]++;
                    }
                }
                List<Integer> stack = new ArrayList<>();
                allTopologicalSortsUtil(visited, indegree, stack);
            }

            public static void main(String[] args) {

                // Create a graph given in the above diagram
                Graph graph = new Graph(6);
                graph.addEdge(5, 2);
                graph.addEdge(5, 0);
                graph.addEdge(4, 0);
                graph.addEdge(4, 1);
                graph.addEdge(2, 3);
                graph.addEdge(3, 1);

                System.out.println("All Topological sorts");
                graph.allTopologicalSorts();
                /**
                 * All Topological sorts
                 * 4 5 0 2 3 1
                 * 4 5 2 0 3 1
                 * 4 5 2 3 0 1
                 * 4 5 2 3 1 0
                 * 5 2 3 4 0 1
                 * 5 2 3 4 1 0
                 * 5 2 4 0 3 1
                 * 5 2 4 3 0 1
                 * 5 2 4 3 1 0
                 * 5 4 0 2 3 1
                 * 5 4 2 0 3 1
                 * 5 4 2 3 0 1
                 * 5 4 2 3 1 0
                 */
            }
        }
    }

}
