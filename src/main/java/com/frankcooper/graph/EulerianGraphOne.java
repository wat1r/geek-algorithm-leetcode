package com.frankcooper.graph;

import java.io.*;
import java.util.*;
import java.util.LinkedList;

public class EulerianGraphOne {
    // A Java program to check if a given graph is Eulerian or not


    // This class represents an undirected graph using adjacency list
// representation
    static class Graph {
        private int V; // No. of vertices

        // Array of lists for Adjacency List Representation
        private LinkedList<Integer> adj[];

        // Constructor
        Graph(int v) {
            V = v;
            adj = new LinkedList[v];
            for (int i = 0; i < v; ++i) adj[i] = new LinkedList();
        }

        //Function to add an edge into the graph
        void addEdge(int v, int w) {
            adj[v].add(w);// Add w to v's list.
            adj[w].add(v); //The graph is undirected
        }

        // A function used by DFS
        void DFSUtil(int v, boolean visited[]) {
            // Mark the current node as visited
            visited[v] = true;
            // Recur for all the vertices adjacent to this vertex
            Iterator<Integer> i = adj[v].listIterator();
            while (i.hasNext()) {
                int n = i.next();
                if (!visited[n])
                    DFSUtil(n, visited);
            }
        }

        // Method to check if all non-zero degree vertices are
        // connected. It mainly does DFS traversal starting from
        boolean isConnected() {
            // Mark all the vertices as not visited
            boolean visited[] = new boolean[V];
            int i;
            // Find a vertex with non-zero degree
            for (i = 0; i < V; i++)
                if (adj[i].size() != 0) break;
            // If there are no edges in the graph, return true
            if (i == V) return true;
            // Start DFS traversal from a vertex with non-zero degree
            DFSUtil(i, visited);
            // Check if all non-zero degree vertices are visited
            for (i = 0; i < V; i++)
                if (!visited[i] && adj[i].size() > 0)
                    return false;
            return true;
        }

        /* The function returns one of the following values
        0 --> If grpah is not Eulerian
        1 --> If graph has an Euler path (Semi-Eulerian)
        2 --> If graph has an Euler Circuit (Eulerian) */
        int isEulerian() {
            // Check if all non-zero degree vertices are connected
            if (!isConnected())
                return 0;
            // Count vertices with odd degree
            int odd = 0;
            for (int i = 0; i < V; i++)
                if (adj[i].size() % 2 != 0)
                    odd++;
            // If count is more than 2, then graph is not Eulerian
            if (odd > 2)
                return 0;

            // If odd count is 2, then semi-eulerian.
            // If odd count is 0, then eulerian
            // Note that odd count can never be 1 for undirected graph
            return (odd == 2) ? 1 : 2;
        }

        // Function to run test cases
        void test() {
            int res = isEulerian();
            if (res == 0)
                System.out.println("graph is not Eulerian");
            else if (res == 1)
                System.out.println("graph has a Euler path");
            else
                System.out.println("graph has a Euler cycle");
        }

        // Driver method
        public static void main(String args[]) {
            // Let us create and test graphs shown in above figures
            Graph g1 = new Graph(5);
            g1.addEdge(1, 0);
            g1.addEdge(0, 2);
            g1.addEdge(2, 1);
            g1.addEdge(0, 3);
            g1.addEdge(3, 4);
            g1.test();

            Graph g2 = new Graph(5);
            g2.addEdge(1, 0);
            g2.addEdge(0, 2);
            g2.addEdge(2, 1);
            g2.addEdge(0, 3);
            g2.addEdge(3, 4);
            g2.addEdge(4, 0);
            g2.test();

            Graph g3 = new Graph(5);
            g3.addEdge(1, 0);
            g3.addEdge(0, 2);
            g3.addEdge(2, 1);
            g3.addEdge(0, 3);
            g3.addEdge(3, 4);
            g3.addEdge(1, 3);
            g3.test();

            // Let us create a graph with 3 vertices
            // connected in the form of cycle
            Graph g4 = new Graph(3);
            g4.addEdge(0, 1);
            g4.addEdge(1, 2);
            g4.addEdge(2, 0);
            g4.test();

            // Let us create a graph with all veritces
            // with zero degree
            Graph g5 = new Graph(3);
            g5.test();
        }
    }
// This code is contributed by Aakash Hasija

}
