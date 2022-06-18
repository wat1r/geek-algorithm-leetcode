package com.frankcooper.classify.graph;

import java.util.*;

public class ShortestPath {
    //无权图的最短路径
    static class _1st {
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


    //有向无环图的最短路径
    static class _2nd {
        static final int INF = Integer.MAX_VALUE;

        class AdjListNode {
            int v;//顶点
            int w;//权值

            public AdjListNode(int v, int w) {
                this.v = v;
                this.w = w;
            }

            public int getV() {
                return v;
            }

            public int getW() {
                return w;
            }
        }

        class Graph {
            int V;//顶点的数量
            List<AdjListNode>[] adj;

            public Graph(int v) {
                V = v;
                adj = new List[V];
                for (int i = 0; i < V; i++) {
                    adj[i] = new LinkedList<>();
                }
            }

            void addEdge(int u, int v, int w) {
                AdjListNode node = new AdjListNode(v, w);
                adj[u].add(node);
            }


            void topologicalSortUtil(int u, boolean[] vis, Stack<Integer> stk) {
                vis[u] = true;
                for (AdjListNode node : adj[u]) {
                    if (!vis[node.getV()]) {
                        topologicalSortUtil(node.getV(), vis, stk);
                    }
                }
                stk.push(u);
            }

            //从源点开始找最短路径
            void shortestPath(int s) {
                Stack<Integer> stk = new Stack<>();
                int[] dist = new int[V];
                Arrays.fill(dist, INF);
                boolean[] vis = new boolean[V];
                for (int i = 0; i < V; i++) {
                    if (!vis[i]) topologicalSortUtil(i, vis, stk);
                }
                dist[s] = 0;
                while (!stk.isEmpty()) {
                    int u = stk.pop();
                    if (dist[u] != INF) {
                        for (AdjListNode node : adj[u]) {
                            if (dist[node.getV()] > dist[u] + node.getW()) {
                                dist[node.getV()] = dist[u] + node.getW();
                            }
                        }
                    }
                }
                // Print the calculated shortest distances
                for (int i = 0; i < V; i++) {
                    if (dist[i] == INF)
                        System.out.print("INF ");
                    else
                        System.out.print(dist[i] + " ");
                }
            }

            // Method to create a new graph instance through an object
            // of ShortestPath class.
            Graph newGraph(int number) {
                return new Graph(number);
            }


        }

        public static void main(String args[]) {
            // Create a graph given in the above diagram.  Here vertex
            // numbers are 0, 1, 2, 3, 4, 5 with following mappings:
            // 0=r, 1=s, 2=t, 3=x, 4=y, 5=z
            _2nd t = new _2nd();
            t.entrance();
        }

        public void entrance() {
            Graph g = new Graph(6);
            g.addEdge(0, 1, 5);
            g.addEdge(0, 2, 3);
            g.addEdge(1, 3, 6);
            g.addEdge(1, 2, 2);
            g.addEdge(2, 4, 4);
            g.addEdge(2, 5, 2);
            g.addEdge(2, 3, 7);
            g.addEdge(3, 4, -1);
            g.addEdge(4, 5, -2);

            int s = 1;
            System.out.println("Following are shortest distances " +
                    "from source " + s);
            g.shortestPath(s);
            /**
             * Following are shortest distances from source 1
             * INF 0 2 6 5 3
             */
        }
    }


}
