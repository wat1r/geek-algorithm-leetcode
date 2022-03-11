package com.frankcooper.classic.fleury;

import java.util.ArrayList;

public class Fleury {


    public static void main(String[] args) {
        FleuryProcess handler = new FleuryProcess();

        Graph graph = new Graph(4);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);

        handler.printEulerTour();
        graph = new Graph(3);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);
        handler.printEulerTour();
        graph = new Graph(5);
        graph.addEdge(1, 0);
        graph.addEdge(0, 2);
        graph.addEdge(2, 1);
        graph.addEdge(0, 3);
        graph.addEdge(3, 4);
        graph.addEdge(3, 2);
        graph.addEdge(3, 1);
        graph.addEdge(2, 4);
        graph = new Graph(3);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(2, 0);
        handler.printEulerTour();

    }


    /**
     *dfs detect bridge and non-bridge
     */
    public static class FleuryProcess {

        /**
         * 入口类
         */
        public static void printEulerTour() {
            //找到起点，如果没有奇数度的点，从0开始
            int u = 0;
            for (int i = 0; i < Graph.vertices; i++) {
                if (Graph.adj[i].size() % 2 == 1) {
                    u = i;
                    break;
                }
            }
            printEulerUtil(u);
        }


        /**
         * 打印欧拉路径
         * @param u 当前处理的顶点
         */
        private static void printEulerUtil(Integer u) {
            for (int i = 0; i < Graph.adj[u].size(); i++) {
                Integer v = Graph.adj[u].get(i);
                if (isValidNextEdge(u, v)) {
                    System.out.printf("%d->%d ", u, v);
                    Graph.removeEdge(u, v);
                    printEulerUtil(v);
                }
            }
            System.out.println();
        }

        /**
         * 判断u-v是否是桥 桥与非桥
         * @param u
         * @param v
         * @return
         */
        private static boolean isValidNextEdge(Integer u, Integer v) {
            if (Graph.adj[u].size() == 1) return true;//当前只有条边，返回
            boolean[] visited = new boolean[Graph.vertices];//顶点的访问数组
            int count1 = dfs(u, visited);//获得count1
            Graph.removeEdge(u, v);//移除边
            visited = new boolean[Graph.vertices];
            int count2 = dfs(u, visited);//获得count2
            Graph.addEdge(u, v);//恢复边
            return count1 <= count2;
        }

        /**
         * 计算当前点v 可达的顶点数量
         * @param v
         * @param visited
         * @return
         */
        private static int dfs(Integer v, boolean[] visited) {
            visited[v] = true;//标记
            int count = 1;
            for (int adj : Graph.adj[v]) {
                if (!visited[adj]) {
                    count += dfs(adj, visited);
                }
            }
            return count;
        }

    }


    /**
     * 图结构
     */
    public static class Graph {

        private static int vertices; //顶点的数量
        private static ArrayList<Integer>[] adj; // 邻接表

        Graph(int vertices) {
            this.vertices = vertices;//复制
            initGraph();
        }

        // 初始化邻接表
        private static void initGraph() {
            adj = new ArrayList[vertices];
            for (int i = 0; i < vertices; i++) {
                adj[i] = new ArrayList<>();
            }
        }

        // 添加边u-v
        private static void addEdge(Integer u, Integer v) {
            adj[u].add(v);
            adj[v].add(u);
        }

        // 移除边u-v
        private static void removeEdge(Integer u, Integer v) {
            adj[u].remove(v);
            adj[v].remove(u);
        }
    }


}
