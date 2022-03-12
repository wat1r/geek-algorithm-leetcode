package com.frankcooper.graph.struct;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wangzhou(Frank Cooper)
 * @date: 2021/8/5 19:43
 * @description:
 */
public class DirectedGraph {

    static class Graph {
        public int V; //顶点的数量
        public List<List<Integer>> adj; // 邻接表

        public Graph(int V) {
            this.V = V;
            adj = new ArrayList<>();
            for (int i = 0; i < V; i++) {
                adj.add(new ArrayList<>());
            }
        }


        private void addEdge(int source, int dest) {
            adj.get(source).add(dest);
        }


        private boolean isCyclicUtil(int i, boolean[] visited, boolean[] recStack) {
            if (recStack[i]) return true;
            if (visited[i]) return false;
            visited[i] = true;
            recStack[i] = true;
            for (Integer next : adj.get(i)) {
                if (isCyclicUtil(next, visited, recStack)) return true;
            }
            recStack[i] = false;
            return false;
        }


        private boolean isCyclic() {
            boolean[] visited = new boolean[V];
            boolean[] recStack = new boolean[V];
            for (int i = 0; i < V; i++) {
                if(isCyclicUtil(i,visited,recStack)) return true;
            }
            return false;
        }


        public static void main(String[] args) {
            Graph graph = new Graph(4);
            graph.addEdge(0, 1);
            graph.addEdge(0, 2);
            graph.addEdge(1, 2);
            graph.addEdge(2, 0);
            graph.addEdge(2, 3);
            graph.addEdge(3, 3);

            if (graph.isCyclic())
                System.out.println("Graph contains cycle");
            else
                System.out.println("Graph doesn't contain cycle");
        }
    }
}
