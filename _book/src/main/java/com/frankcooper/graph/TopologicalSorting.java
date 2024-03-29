package com.frankcooper.classify.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author: wangzhou(Frank Cooper)
 * @date: 2021/8/6 16:11
 * @description:
 */
public class TopologicalSorting {

    static class Graph {
        private int V;
        private List<List<Integer>> adj;

        //初始化构建图
        public Graph(int V) {
            this.V = V;
            adj = new ArrayList<>();
            for (int i = 0; i < this.V; i++) {
                adj.add(new ArrayList<>());
            }
        }

        //有向图
        public void addEdge(int u, int v) {
            adj.get(u).add(v);
        }


        public void topologicalSortUtil(int u, boolean[] vis, Stack<Integer> stk) {
            vis[u] = true;//标记当前节点被访问过
            for (int v : adj.get(u)) {
                if (!vis[v]) topologicalSortUtil(v, vis, stk);
            }
            stk.push(u);//加入当前节点
        }


        public void topologicalSort() {
            Stack<Integer> stk = new Stack<>();
            boolean[] vis = new boolean[V];
            for (int u = 0; u < V; u++) {//依次遍历
                if (!vis[u]) topologicalSortUtil(u, vis, stk);
            }
            while (!stk.isEmpty()) {
                System.out.print(stk.pop() + " ");
            }
        }

        public static void main(String args[]) {
            // Create a graph given in the above diagram
            Graph g = new Graph(6);
            g.addEdge(5, 2);
            g.addEdge(5, 0);
            g.addEdge(4, 0);
            g.addEdge(4, 1);
            g.addEdge(2, 3);
            g.addEdge(3, 1);
            System.out.println("Following is a Topological sort of the given graph");
            g.topologicalSort();
            //5 4 2 3 1 0
        }

    }

}
