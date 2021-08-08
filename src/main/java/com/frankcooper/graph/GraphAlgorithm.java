package com.frankcooper.graph;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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
}
