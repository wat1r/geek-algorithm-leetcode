package com.frankcooper.bank;

import com.alibaba.fastjson.JSON;

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
        handler.testTwo();
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


}
