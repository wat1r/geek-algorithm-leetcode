package com.frankcooper.bank._701_800;

import com.frankcooper.utils.PrintUtils;

import java.util.*;

public class _787 {

    public static void main(String[] args) {
        _1st handler = new _1st();
/*        int N = 3;
        int[][] edges = PrintUtils.processSymbol("[[0,1,100],[1,2,100],[0,2,500]]");
        int src = 0, dst = 2, K = 1;*/
        int N = 3;
        int[][] edges = PrintUtils.processSymbol("[[0,1,100],[1,2,100],[0,2,500]]");
        int src = 0, dst = 2, K = 0;
        handler.findCheapestPrice(N, edges, src, dst, K);
    }


    /**
     * bfs
     */
    static class _1st {
        public int findCheapestPrice(int N, int[][] flights, int src, int dst, int K) {
            //构建图 u->v w
            Map<Integer, List<int[]>> graph = new HashMap<>();
            for (int[] f : flights) {
                graph.putIfAbsent(f[0], new ArrayList<>());
                graph.get(f[0]).add(new int[]{f[1], f[2]});
            }
            Queue<int[]> q = new LinkedList<>();
            q.offer(new int[]{src, 0});//0 表示当前点，1表示的是到达当前点的距离dist
            int ans = Integer.MAX_VALUE;
            int level = 0;
            while (!q.isEmpty()) {
                int size = q.size();
                for (int i = 0; i < size; i++) {
                    int[] curr = q.poll();
                    int u = curr[0], dist = curr[1];
                    if (u == dst) ans = Math.min(ans, dist);//找到目的地站
                    if (graph.containsKey(u)) {
                        for (int[] next : graph.get(u)) {
                            if (dist + next[1] > ans) continue;//松弛
                            q.offer(new int[]{next[0], dist + next[1]});
                        }
                    }
                }
                //先比较level 与K 再执行++ 的，bfs的层数，类似水波纹的一圈一圈的，一层表示经过一个站
                if (level++ > K) break;
            }
            return ans == Integer.MAX_VALUE ? -1 : ans;
        }
    }


    /**
     * dfs
     */
    static class _2nd {
        Map<Integer, List<int[]>> graph;
        int ans = Integer.MAX_VALUE;

        public int findCheapestPrice(int N, int[][] flights, int src, int dst, int K) {
            //构建图 u->v w
            graph = new HashMap<>();
            for (int[] f : flights) {
                graph.putIfAbsent(f[0], new ArrayList<>());
                graph.get(f[0]).add(new int[]{f[1], f[2]});
            }
            dfs(src, dst, K + 1, 0);
            return ans == Integer.MAX_VALUE ? -1 : ans;
        }


        private void dfs(int src, int dst, int K, int dist) {
            if (K < 0) return;
            if (src == dst) {
                ans = dist;
                return;
            }
            if (graph.containsKey(src)) {
                for (int[] f : graph.get(src)) {
                    int u = f[0], w = f[1];
                    if (dist + w > ans) continue;
                    dfs(u, dst, K - 1, dist + w);
                }
            }
        }


    }


    static class _3rd {
        public int findCheapestPrice(int N, int[][] flights, int src, int dst, int K) {
            Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();
            for (int[] f : flights) {
                graph.putIfAbsent(f[0], new HashMap<>());
                graph.get(f[0]).put(f[1], f[2]);
            }
            Queue<int[]> pq = new PriorityQueue<>((a, b) -> (a[0] - b[0]));
            pq.offer(new int[]{0, src, K + 1});
            while (!pq.isEmpty()) {
                int[] curr = pq.poll();
                int dist = curr[0], u = curr[1], stops = curr[2];
                if (u == dst) return dist;
                if (stops > 0) {
                    if (graph.containsKey(u)) {
                        for (Map.Entry<Integer, Integer> e : graph.get(u).entrySet()) {
                            int v = e.getKey(), w = e.getValue();
                            pq.offer(new int[]{dist + w, v, stops - 1});
                        }
                    }
                }

            }
            return -1;
        }
    }


    static class _4th {
        int INF = Integer.MAX_VALUE;

        public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
            int[] f = new int[n];
            Arrays.fill(f, INF);
            f[src] = 0;
            for (int i = 0; i <= K; i++) {
                int[] t = Arrays.copyOf(f, n);
                for (int[] x : flights) {
                    int cur = x[0], next = x[1], price = x[2];
                    if (f[cur] == INF) continue;
                    t[next] = Math.min(t[next], f[cur] + price);
                }
                f = t;
            }
            return f[dst] == INF ? -1 : f[dst];
        }

    }


}
