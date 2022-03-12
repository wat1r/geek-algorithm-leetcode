package com.frankcooper.graph;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: wangzhou(Frank Cooper)
 * @date: 2021/9/26 8:23
 * @description:
 */
public class CycleInDirectedGraph {


    public boolean hasCycle(Graph<Integer> graph) {
        Set<Vertex<Integer>> whiteSet = new HashSet<>();
        Set<Vertex<Integer>> graySet = new HashSet<>();
        Set<Vertex<Integer>> blackSet = new HashSet<>();
        whiteSet.addAll(graph.getAllVertex());
        while (whiteSet.size() != 0) {
            Vertex<Integer> cur = whiteSet.iterator().next();
            if (dfs(cur, whiteSet, graySet, blackSet)) {
                return true;
            }
        }
        return false;
    }

    private boolean dfs(Vertex<Integer> cur, Set<Vertex<Integer>> whiteSet, Set<Vertex<Integer>> graySet, Set<Vertex<Integer>> blackSet) {
        //move current to gray set from white set and then explore it.
        moveVertex(cur, whiteSet, graySet);
        for (Vertex<Integer> neighbor : cur.getAdjacentVertexes()) {
            //if in black set means already explored so continue.
            if (blackSet.contains(neighbor)) continue;
            //if in gray set then cycle found.
            if (graySet.contains(neighbor)) return true;
            if (dfs(neighbor, whiteSet, graySet, blackSet)) return true;
        }
        //move vertex from gray set to black set when done exploring.
        moveVertex(cur, graySet, blackSet);
        return false;
    }


    /**
     * move vertex from srcSet to destSet
     */
    private void moveVertex(Vertex<Integer> vertex, Set<Vertex<Integer>> srcSet, Set<Vertex<Integer>> destSet) {
        srcSet.remove(vertex);
        destSet.add(vertex);
    }


    public static void main(String args[]) {
        Graph<Integer> graph = new Graph<>(true);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 3);
        graph.addEdge(4, 1);
        graph.addEdge(4, 5);
        graph.addEdge(5, 6);
        graph.addEdge(6, 4);
        CycleInDirectedGraph cdg = new CycleInDirectedGraph();
        System.out.println(cdg.hasCycle(graph));
    }
}
