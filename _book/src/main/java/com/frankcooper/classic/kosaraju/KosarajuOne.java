package com.frankcooper.classic.kosaraju;

import java.util.LinkedList;
import java.util.Stack;

/**
 * @author: Frank Cooper
 * @date: 2021/4/2 10:17
 * @description: 获取SCC
 */
public class KosarajuOne {


    /**
     * 图结构: 有向图
     */
    public static class Graph {

        public int vertices; //顶点的数量
        public LinkedList<Integer>[] adj; // 邻接表

        Graph(int vertices) {
            this.vertices = vertices;//复制
            initGraph();
        }

        // 初始化邻接表
        private void initGraph() {
            adj = new LinkedList[vertices];
            for (int i = 0; i < vertices; i++) {
                adj[i] = new LinkedList<>();
            }
        }

        // 添加边u-v
        private void addEdge(Integer u, Integer v) {
            adj[u].add(v);
        }


        void dfs(int u, boolean[] vis) {
            vis[u] = true;
            System.out.printf("%d ", u);
            for (int v : adj[u]) {
                if (!vis[v]) {
                    dfs(v, vis);
                }
            }
        }

        public Graph getTranspose() {
            Graph graph = new Graph(vertices);
            for (int v = 0; v < vertices; v++) {
                for (int u : adj[v]) {
                    graph.adj[u].add(v);
                }
            }
            return graph;
        }

        public void fillOrder(int v, boolean[] vis, Stack<Integer> stack) {
            vis[v] = true;
            for (int u : adj[v]) {
                if (!vis[u]) fillOrder(u, vis, stack);
            }
            stack.push(v);
        }


        public void printSCCs() {
            Stack<Integer> stack = new Stack<>();
            // Mark all the vertices as not visited (For first DFS)
            boolean[] vis = new boolean[vertices];
            // Fill vertices in stack according to their finishing
            // times
            for (int v = 0; v < vertices; v++) {
                if (!vis[v]) fillOrder(v, vis, stack);
            }
            // Create a reversed graph
            Graph graph = getTranspose();
            vis = new boolean[vertices];
            // Mark all the vertices as not visited (For second DFS)
            while (!stack.isEmpty()) {
                int v = stack.pop();
                if (!vis[v]) {
                    graph.dfs(v, vis);
                    System.out.println();
                }
            }

        }


        public static void main(String args[]) {
            // Create a graph given in the above diagram
            Graph g = new Graph(5);
            g.addEdge(1, 0);
            g.addEdge(0, 2);
            g.addEdge(2, 1);
            g.addEdge(0, 3);
            g.addEdge(3, 4);

            System.out.println("Following are strongly connected components " +
                    "in given graph ");
            g.printSCCs();
        }

    }


    /**
     * https://www.geeksforgeeks.org/connectivity-in-a-directed-graph/
     */


}
