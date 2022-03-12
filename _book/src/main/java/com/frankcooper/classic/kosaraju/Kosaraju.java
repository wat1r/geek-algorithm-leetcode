package com.frankcooper.classic.kosaraju;

import java.util.LinkedList;

/**
 * @author: Frank Cooper
 * @date: 2021/4/2 10:17
 * @description:
 */
public class Kosaraju {


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


        /**
         * check graph if contains strong connect component
         *
         * @return
         */
        public boolean isStronglyConnect() {
            // Step 1: Mark all the vertices as not visited
            // (For first DFS)
            boolean[] vis = new boolean[vertices];
            // Step 2: Do DFS traversal starting from first vertex.
            dfs(0, vis);
            // If DFS traversal doesn't visit all vertices, then
            // return false.
            for (int v = 0; v < vertices; v++) {
                if (!vis[v]) return false;
            }
            // Step 3: Create a reversed graph
            Graph graph = getTranspose();
            // Step 4: Mark all the vertices as not visited (For
            // second DFS)
            vis = new boolean[vertices];
            // Step 5: Do DFS for reversed graph starting from
            // first vertex. Staring Vertex must be same starting
            // point of first DFS
            graph.dfs(0, vis);
            // If all vertices are not visited in second DFS, then
            // return false
            for (int v = 0; v < vertices; v++) {
                if (!vis[v]) return false;
            }
            return true;
        }


        public static void main(String args[]) {
            // Create graphs given in the above diagrams
            Graph g1 = new Graph(5);
            g1.addEdge(0, 1);
            g1.addEdge(1, 2);
            g1.addEdge(2, 3);
            g1.addEdge(3, 0);
            g1.addEdge(2, 4);
            g1.addEdge(4, 2);
            if (g1.isStronglyConnect())
                System.out.println("Yes");
            else
                System.out.println("No");

            Graph g2 = new Graph(4);
            g2.addEdge(0, 1);
            g2.addEdge(1, 2);
            g2.addEdge(2, 3);
            if (g2.isStronglyConnect())
                System.out.println("Yes");
            else
                System.out.println("No");
        }

    }


    /**
     * https://www.geeksforgeeks.org/connectivity-in-a-directed-graph/
     */


}
