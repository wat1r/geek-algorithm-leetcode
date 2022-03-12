package com.frankcooper.graph.struct;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author: wangzhou(Frank Cooper)
 * @date: 2021/8/5 19:43
 * @description:
 */
public class UndirectedGraph {


    static class _1st {
        static class Graph {
            private int V;
            private LinkedList<Integer>[] adj;

            public Graph(int V) {
                this.V = V;
                adj = new LinkedList[V];
                for (int i = 0; i < V; i++) {
                    adj[i] = new LinkedList<>();
                }
            }

            private void addEdge(int u, int v) {
                adj[u].add(v);
                adj[v].add(u);
            }

            /**
             * 从u出发，visited[] 标记节点是否访问过，parent表示父亲节点
             * 是否存在一个环
             */
            public boolean isCyclicUtil(int u, boolean[] visited, int parent) {
                visited[u] = true;//标记当前节点被访问过
                for (int v : adj[u]) {//遍历u的相邻节点
                    if (!visited[v]) {//第一次访问v
                        if (isCyclicUtil(v, visited, u)) return true;
                    } else if (v != parent) return true;//相邻节点不是父节点且被访问过
                }
                return false;
            }

            public boolean isCyclic() {
                boolean[] visited = new boolean[V];
                for (int u = 0; u < V; u++) {
                    if (!visited[u]) {
                        if (isCyclicUtil(u, visited, -1)) return true;
                    }
                }
                return false;
            }

            public static void main(String args[]) {
                Graph g1 = new Graph(5);
                g1.addEdge(1, 0);
                g1.addEdge(0, 2);
                g1.addEdge(2, 1);
                g1.addEdge(0, 3);
                g1.addEdge(3, 4);
                if (g1.isCyclic())
                    System.out.println("Graph  contains cycle");
                else
                    System.out.println("Graph  doesn 't contains cycle");
                Graph g2 = new Graph(3);
                g2.addEdge(0, 1);
                g2.addEdge(1, 2);
                if (g2.isCyclic())
                    System.out.println("Graph  contains cycle");
                else
                    System.out.println("Graph doesn 't contains cycle");
            }
        }
    }


    /**
     * 并查集查找无向图中的环
     */
    static class _2nd {


        static class Graph {


            int V, E;//顶点和边的数量
            Edge[] edges;

            class Edge {
                int src;
                int dest;


            }

            //初始化
            public Graph(int v, int e) {
                V = v;
                E = e;
                edges = new Edge[E];
                for (int i = 0; i < E; i++) {
                    edges[i] = new Edge();
                }
            }

            //返回i这个节点的子集
            private int find(int[] parents, int i) {
                if (parents[i] == -1) return i;
                return find(parents, parents[i]);
            }

            //合并两个子集，没有带路径压缩
            private void union(int[] parents, int x, int y) {
                parents[x] = y;
            }

            public int isCycle(Graph graph) {
                int[] parents = new int[graph.V];//分配V个节点的parents数组
                Arrays.fill(parents, -1);//初始化
                for (int i = 0; i < graph.E; i++) {
                    Edge edge = graph.edges[i];
                    int x = graph.find(parents, edge.src);
                    int y = graph.find(parents, edge.dest);
                    if (x == y) return 1;
                    graph.union(parents, x, y);
                }
                return 0;
            }

            // Driver Method
            public static void main(String[] args) {
        /* Let us create the following graph
        0
        | \
        |  \
        1---2 */
                int V = 3, E = 3;
                Graph graph = new Graph(V, E);
                // add edge 0-1
                graph.edges[0].src = 0;
                graph.edges[0].dest = 1;
                // add edge 1-2
                graph.edges[1].src = 1;
                graph.edges[1].dest = 2;
                // add edge 0-2
                graph.edges[2].src = 0;
                graph.edges[2].dest = 2;
                if (graph.isCycle(graph) == 1)
                    System.out.println("graph contains cycle");
                else
                    System.out.println("graph doesn't contain cycle");
            }

        }
    }

}
