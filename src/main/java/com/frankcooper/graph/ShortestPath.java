package com.frankcooper.graph;

import java.util.*;

public class ShortestPath {

    static class _1st {

    }


    private static void addEdge(List<List<Integer>> adj, int u, int v) {
        adj.get(u).add(v);
        adj.get(v).add(u);
    }

    /**
     * @param adj  邻接表
     * @param src  源点
     * @param dest 终点
     * @param V    顶点的个数
     */
    public static void printShortestDistance(List<List<Integer>> adj, int src, int dest, int V) {
        int[] dist = new int[V];
        int[] pred = new int[V];
        if (!bfs(adj, src, dest, V, pred, dist)) {
            System.out.println("Given source and destination are not connected");
            return;
        }
        List<Integer> path = new ArrayList<>();
        int cur = dest;
        path.add(cur);
        while (pred[cur] != -1) {
            path.add(pred[cur]);
            cur = pred[cur];
        }
        // Print distance
        System.out.println("Shortest path length is: " + dist[dest]);
        // Print path
        System.out.println("Path is ::");
        for (int i = path.size() - 1; i >= 0; i--) {
            System.out.print(path.get(i) + " ");
        }
    }


    private static boolean bfs(List<List<Integer>> adj, int src, int dest, int V, int[] pred, int[] dist) {
        //存顶点的邻接点将要被访问的
        Queue<Integer> queue = new LinkedList<>();
        //记录当前节点至少被访问过一次
        boolean[] vis = new boolean[V];
        //初始化dist 和 pred 初始距离为MAX 直接前驱节点初始化为-1 不存在的节点
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(pred, -1);
        //src点作为起始点开始转bfs遍历
        vis[src] = true;
        dist[src] = 0;
        queue.offer(src);
        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (int v : adj.get(u)) {
                if (!vis[v]) {//u相邻的节点v，v没有被访问过
                    vis[v] = true;
                    dist[v] = dist[u] + 1;
                    pred[v] = u;
                    queue.offer(v);//当前的节点v的直接前驱节点修改为u
                    if (v == dest) return true;//找到了
                }
            }
        }
        return false;
    }


    public static void main(String args[]) {
        // No of vertices
        int V = 8;
        // Adjacency list for storing which vertices are connected
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        // Creating graph given in the above diagram.
        // add_edge function takes adjacency list, source
        // and destination vertex as argument and forms
        // an edge between them.
        addEdge(adj, 0, 1);
        addEdge(adj, 0, 3);
        addEdge(adj, 1, 2);
        addEdge(adj, 3, 4);
        addEdge(adj, 3, 7);
        addEdge(adj, 4, 5);
        addEdge(adj, 4, 6);
        addEdge(adj, 4, 7);
        addEdge(adj, 5, 6);
        addEdge(adj, 6, 7);
        int source = 0, dest = 7;
        printShortestDistance(adj, source, dest, V);
        /**
         * Shortest path length is: 2
         * Path is ::
         * 0 3 7
         */
    }

}
