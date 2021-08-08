package com.frankcooper.graph;

import java.util.LinkedList;
import java.util.List;

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

}
