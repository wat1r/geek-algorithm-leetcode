package com.frankcooper.classic.fleury;

import java.util.ArrayList;

public class Fleury {


    public static void main(String[] args) {
        Graph graph = new Graph(4);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.printEulerTour();
        graph = new Graph(3);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);
        graph.printEulerTour();
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
        graph.printEulerTour();

    }


    public static class Graph {

        private int vertices; // No. of vertices
        private ArrayList<Integer>[] adj; // adjacency list


        public Graph() {
        }

        // Constructor
        Graph(int numOfVertices) {
            // initialise vertex count
            this.vertices = numOfVertices;

            // initialise adjacency list
            initGraph();
        }

        // utility method to initialise adjacency list
        @SuppressWarnings("unchecked")
        private void initGraph() {
            adj = new ArrayList[vertices];
            for (int i = 0; i < vertices; i++) {
                adj[i] = new ArrayList<>();
            }
        }

        // add edge u-v
        private void addEdge(Integer u, Integer v) {
            adj[u].add(v);
            adj[v].add(u);
        }

        // This function removes edge u-v from graph.
        private void removeEdge(Integer u, Integer v) {
            adj[u].remove(v);
            adj[v].remove(u);
        }


        public void printEulerTour() {
            Integer u = 0;
            for (int i = 0; i < vertices; i++) {
                if (adj[i].size() % 2 == 1) {
                    u = i;
                    break;
                }
            }
            printEulerUtil(u);
            System.out.println();


        }

        private void printEulerUtil(Integer u) {
            for (int i = 0; i < adj[u].size(); i++) {
                Integer v = adj[u].get(i);
                if (isValidNextEdge(u, v)) {
                    System.out.printf("%d->%d ", u, v);
                    removeEdge(u, v);
                    printEulerUtil(v);
                }
            }
        }

        private boolean isValidNextEdge(Integer u, Integer v) {
            if (adj[u].size() == 1) return true;
            boolean[] visited = new boolean[vertices];
            int count1 = dfs(u, visited);
            removeEdge(u, v);
            visited = new boolean[vertices];
            int count2 = dfs(u, visited);
            addEdge(u, v);
            return count1 <= count2;
        }

        private int dfs(Integer v, boolean[] visited) {
            visited[v] = true;
            int count = 1;
            for (int adj : adj[v]) {
                if (!visited[adj]) {
                    count += dfs(adj, visited);
                }
            }
            return count;
        }


    }


}
