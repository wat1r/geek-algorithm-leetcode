package com.frankcooper.graph.struct;

import java.util.ArrayList;
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


    static class _2nd {


        static class Graph {


        }
    }

}
