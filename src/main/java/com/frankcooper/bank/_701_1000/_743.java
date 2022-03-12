package com.frankcooper.bank._701_1000;

import com.alibaba.fastjson.JSON;
import com.frankcooper.utils.PrintUtils;

import java.util.*;

/**
 * Created by FrankCooper
 * Date 2020/9/20 10:06
 * Description
 */
public class _743 {


    /**
     * Dijkstra
     */
    class _1st {
        Integer INF = Integer.MAX_VALUE;

        public int networkDelayTime(int[][] times, int N, int K) {
            Map<Integer, List<int[]>> graph = new HashMap<>();
            //初始化邻接表
            for (int[] time : times) {
                graph.computeIfAbsent(time[0], t -> new ArrayList<>()).add(new int[]{time[1], time[2]});
            }
            int[] dis = new int[N + 1];
            Arrays.fill(dis, INF);
            boolean[] vis = new boolean[N + 1];
            //多设置了0这个索引的冗余，距离设置为0
            dis[K] = 0;
            dis[0] = 0;
            //优先队列，按距离从小到大排列
            PriorityQueue<Integer> pq = new PriorityQueue<>(((o1, o2) -> dis[o1] - dis[o2]));
            pq.offer(K);
            while (!pq.isEmpty()) {
                //弹出
                Integer curr = pq.poll();
                //访问过不用再执行下面的逻辑
                if (vis[curr]) continue;
                //标记访问
                vis[curr] = true;
                //获取到当前节点的邻居节点
                List<int[]> neighbours = graph.getOrDefault(curr, new ArrayList<>());
                for (int[] neighbour : neighbours) {
                    int to = neighbour[0];
                    if (vis[to]) continue;
                    //如果当前dis[to]大，则更新之
                    dis[to] = Math.min(dis[to], dis[curr] + neighbour[1]);
                    pq.offer(to);
                }
            }
            for (int d : dis) System.out.print(d + " ");
            int max = 0;
            for (int d : dis) max = Math.max(max, d);
            return max == INF ? -1 : max;
        }
    }


    static _743 handler = new _743();

    public static void main(String[] args) {
//        handler.testTwo();
        int[][] times = PrintUtils.processSymbol("[[2,1,1],[2,3,1],[3,4,1]]");
//        int[][] times = PrintUtils.processSymbol("[[2,1,2],[2,3,1],[3,4,1]]");
        int N = 4, K = 2;
//        _6th handler = new _6th();
        _5th handler = new _5th();
        handler.networkDelayTime(times, N, K);
    }

    private void testTwo() {
//        _2nd second = new _2nd();
//        _3rd second = new _3rd();
//        _2nd_1 second = new _2nd_1();
        _4th second = new _4th();
        int[][] graph = new int[][]{{2, 1, 1}, {2, 3, 1}, {3, 4, 1}};
        int N = 4;
        int K = 2;
//        graph = new int[][]{{1, 2, 1}, {2, 1, 3}};
//        N = 2;
//        K = 2;
//        graph = new int[][]{{4, 2, 76}, {1, 3, 79}, {3, 1, 81}, {4, 3, 30}, {2, 1, 47}, {1, 5, 61}, {1, 4, 99}, {3, 4, 68}, {3, 5, 46}, {4, 1, 6}, {5, 4, 7}, {5, 3, 44}, {4, 5, 19}, {2, 3, 13}, {3, 2, 18}, {1, 2, 0}, {5, 1, 25}, {2, 5, 58}, {2, 4, 77}, {5, 2, 74}};
//        N = 5;
//        K = 3;
        second.networkDelayTime(graph, N, K);
        System.out.println("-------------------");
//        second.getShortestPaths(N, K, second.initEdge(graph));
    }


    class _2nd {


        /**
         * Bellman-Ford
         */

        Integer INF = Integer.MAX_VALUE;

        /**
         * @param edges 边
         * @param N     顶点数
         * @param K     其实顶点
         * @return
         */
        public int networkDelayTime(int[][] edges, int N, int K) {

            int[] dist = new int[N + 1];
            Arrays.fill(dist, INF);
            dist[K] = 0;
            for (int i = 1; i <= N; i++) {
                for (int j = 0; j < edges.length; j++) {
                    System.out.println(JSON.toJSONString(edges[j]));
                    if (dist[edges[j][0]] != INF && dist[edges[j][1]] > dist[edges[j][0]] + edges[j][2]) {
                        dist[edges[j][1]] = dist[edges[j][0]] + edges[j][2];
                    }
                }
            }
            int max = 0;
            for (int i = 1; i <= N; ++i) max = Math.max(max, dist[i]);
            return max == INF ? -1 : max;
        }
    }


    class _3rd {

        /**
         * SPFA
         */
        Integer INF = Integer.MAX_VALUE;

        public int networkDelayTime(int[][] edges, int N, int K) {
            List<Integer> queue = new ArrayList<>();
            long[] dist = new long[N + 1];
            boolean[] used = new boolean[N + 1];
            int[] nums = new int[N + 1];
            Arrays.fill(dist, INF);
            dist[K] = 0;
            used[K] = true;
            queue.add(K);
            while (!queue.isEmpty()) {
                int a = queue.get(0);
                queue.remove(0);
                for (int i = 0; i < edges.length; i++) {
                    int u = edges[i][0], v = edges[i][1], w = edges[i][2];
                    System.out.printf("u:%d,v:%d,w:%d\n", u, v, w);
                    if (a == u && dist[v] > dist[u] + w) {
                        dist[v] = dist[u] + w;
                        if (!used[v]) {
                            queue.add(v);
                            nums[v]++;
                            if (nums[v] >= N) continue;
                            used[v] = true;
                        }
                    }
                }
                used[a] = false;//本轮结束
            }
            long max = 0;
            for (int i = 1; i <= N; ++i) max = Math.max(max, dist[i]);
            return max == INF ? -1 : (int) max;
        }


        public long[] dis;         //用于得到第s个顶点到其它顶点之间的最短距离

        /*
         * 参数n:给定图的顶点个数
         * 参数s:求取第s个顶点到其它所有顶点之间的最短距离
         * 参数edge:给定图的具体边
         * 函数功能：如果给定图不含负权回路，则可以得到最终结果，如果含有负权回路，则不能得到最终结果
         */
        public boolean getShortestPaths(int n, int s, Edge[] edges) {
            List<Integer> queue = new ArrayList<>();
            dis = new long[n + 1];
            boolean[] used = new boolean[n + 1];
            int[] num = new int[n + 1];
            Arrays.fill(dis, Integer.MAX_VALUE);
            dis[s] = 0;     //第s个顶点到自身距离为0
            used[s] = true;    //表示第s个顶点进入数组队
            num[s] = 1;       //表示第s个顶点已被遍历一次
            queue.add(s);      //第s个顶点入队
            while (queue.size() != 0) {
                int a = queue.get(0);   //获取数组队中第一个元素
                queue.remove(0);         //删除数组队中第一个元素
                System.out.println(a);
                for (int i = 0; i < edges.length; i++) {
                    //当queue的第一个元素等于边edges[i]的起点时
                    System.out.printf("u:%d,v:%d,w:%d\n", edges[i].u, edges[i].v, edges[i].w);
                    if (a == edges[i].u && dis[edges[i].v] > dis[edges[i].u] + edges[i].w) {
                        dis[edges[i].v] = dis[edges[i].u] + edges[i].w;
                        if (!used[edges[i].v]) {
                            queue.add(edges[i].v);
                            num[edges[i].v]++;
                            if (num[edges[i].v] >= n) return false;
                            used[edges[i].v] = true;   //表示边edges[i]的终点b已进入数组队
                        }
                    }
                }
                used[a] = false;        //顶点a出数组对
            }
            return true;
        }


        public Edge[] initEdge(int[][] graph) {
            Edge[] edges = new Edge[graph.length];
            for (int i = 0; i < graph.length; ++i) {
                edges[i] = new Edge(graph[i][0], graph[i][1], graph[i][2]);
            }
            return edges;
        }


        //内部类，用于存放图的具体边数据
        class Edge {
            public int u;  //边的起点
            public int v;  //边的终点
            public int w;   //边的权值

            Edge(int u, int v, int w) {
                this.u = u;
                this.v = v;
                this.w = w;
            }
        }

    }


    class _4th {
        Integer INF = Integer.MAX_VALUE;

        public int networkDelayTime(int[][] edges, int N, int K) {
            int[][] dist = new int[N + 1][N + 1];
            for (int i = 1; i <= N; ++i) {
                for (int j = 1; j <= N; ++j) {
                    dist[i][j] = i == j ? 0 : INF;
                }
            }
            for (int[] edge : edges) {
                dist[edge[0]][edge[1]] = edge[2];
            }
            for (int a = 1; a <= N; ++a) {
                for (int i = 1; i <= N; ++i) {
                    for (int j = 1; j <= N; ++j) {
                        int tmp = (dist[i][a] == INF || dist[a][j] == INF) ? INF : dist[i][a] + dist[a][j];
                        if (dist[i][j] > tmp) {
                            dist[i][j] = tmp;
                        }
                    }
                }
            }
            int max = -1;
            //找K出发的点，K->i，最长的路径
            for (int i = 1; i <= N; ++i) {
                max = Math.max(max, dist[K][i]);
            }
            return max == INF ? -1 : max;
        }
    }


    /**
     * bfs
     */
    static class _5th {
        public int networkDelayTime(int[][] times, int N, int K) {
            int INF = Integer.MAX_VALUE / 2;
            //构建graph
            Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();
            for (int[] t : times) {
                graph.putIfAbsent(t[0], new HashMap<>());
                graph.get(t[0]).put(t[1], t[2]);
            }
            int[] dist = new int[N + 1];
            Arrays.fill(dist, INF);//需要初始化成INF，防止溢出，取INF/2
            Queue<Integer> q = new LinkedList<>();
            q.offer(K);
            dist[K] = 0;
            while (!q.isEmpty()) {
                int u = q.poll();
                if (graph.containsKey(u)) {
                    for (Map.Entry<Integer, Integer> e : graph.get(u).entrySet()) {
                        int v = e.getKey(), w = e.getValue();
                        if (dist[u] + w < dist[v]) {//松弛
                            dist[v] = dist[u] + w;
                            q.offer(v);
                        }
                    }
                }
            }
            int ans = 0;
            //跳过0这个不存在的节点
            for (int i = 1; i <= N; i++) {
                ans = Math.max(ans, dist[i]);
            }
            return ans == INF ? -1 : ans;
        }


    }


    /**
     * bfs PriorityQueue
     */
    static class _6th {
        public int networkDelayTime(int[][] times, int N, int K) {
            System.out.printf("%d\n", K);
            Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();
            for (int[] t : times) {
                graph.putIfAbsent(t[0], new HashMap<>());
                graph.get(t[0]).put(t[1], t[2]);
            }
            //采用优先队列存储， 0指的是当前走的距离，1指的是走到当前的节点Node 按距离从小到大排序，这个很关键
            Queue<int[]> pq = new PriorityQueue<>((a, b) -> (a[0] - b[0]));
            pq.offer(new int[]{0, K});//起始从K 节点开始，走到K节点花了0的距离
            boolean[] visited = new boolean[N + 1]; //下标从1开始，多放一个
            int ans = 0;//结果集
            while (!pq.isEmpty()) {
                int[] curr = pq.poll();
                //0 是走到当前节点的距离 1 是
                int currNode = curr[1], currDist = curr[0];
                if (visited[currNode]) continue;//当前点如果访问过，不需要再访问其邻居节点
                visited[currNode] = true;
                ans = currDist;
                N--;//所有的节点被遍历完，可以提前结束
                if (N == 0) return ans;
                if (graph.containsKey(currNode)) {//访问邻居节点，并更新当前的dist距离
                    for (int next : graph.get(currNode).keySet()) {
                        pq.offer(new int[]{currDist + graph.get(currNode).get(next), next});
                    }
                }
            }
            return N == 0 ? ans : -1;
        }
    }


    /**
     * dfs
     */
    static class _7th {


        Map<Integer, Map<Integer, Integer>> graph;
        int[] dist;

        public int networkDelayTime(int[][] times, int N, int K) {
            int INF = Integer.MAX_VALUE / 2;
            //构建graph
            graph = new HashMap<>();
            for (int[] t : times) {
                graph.putIfAbsent(t[0], new HashMap<>());
                graph.get(t[0]).put(t[1], t[2]);
            }
            dist = new int[N + 1];
            Arrays.fill(dist, INF);//需要初始化成INF，防止溢出，取INF/2
            dist[K] = 0;
            dfs(K);
            int ans = 0;
            //跳过0这个不存在的节点
            for (int i = 1; i <= N; i++) {
                ans = Math.max(ans, dist[i]);
            }
            return ans == INF ? -1 : ans;
        }

        private void dfs(int u) {
            if (graph.containsKey(u)) {
                for (Map.Entry<Integer, Integer> e : graph.get(u).entrySet()) {
                    int v = e.getKey(), w = e.getValue();
                    if (dist[u] + w < dist[v]) {
                        dist[v] = dist[u] + w;
                        dfs(v);
                    }
                }
            }
        }
    }


    /**
     * Floyd
     */
    static class _8th {
        public int networkDelayTime(int[][] times, int N, int K) {
            int INF = Integer.MAX_VALUE / 2;
            int[][] dist = new int[N + 1][N + 1];
            for (int i = 0; i <= N; i++) Arrays.fill(dist[i], INF);
            for (int[] t : times) {
                int u = t[0], v = t[1], w = t[2];
                dist[u][v] = w;
            }
            dist[K][K] = 0;
            for (int k = 1; k <= N; k++) {
                for (int u = 1; u <= N; u++) {
                    for (int v = 1; v <= N; v++) {
                        dist[u][v] = Math.min(dist[u][v], dist[u][k] + dist[k][v]);
                    }
                }
            }
            int ans = -1;
            for (int i = 1; i <= N; i++) {
                ans = Math.max(ans, dist[K][i]);
            }
            return ans == INF ? -1 : ans;
        }


    }

}
