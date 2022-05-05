package com.frankcooper.lintcode;

import java.util.*;

import org.junit.Assert;

public class _291 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }


        public long getSecondDiameter(int[][] edge) {
            int n = 10010;
            List<List<Integer>> graph = new ArrayList<>();
            for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
            for (int i = 0; i < n; i++) {
                int u = edge[i][0], v = edge[i][1], w = edge[i][2];
                graph.get(i).add(v);
                graph.get(v).add(u);

            }
            int res = 0;
            for (int i = 0; i < n; i++) {
//                res = Math.max(res, bfs(i, graph, s));
            }
            return res;
        }


        private int bfs(int start, List<List<Integer>> graph, String s) {
            Queue<Integer> q = new ArrayDeque<>();
            q.offer(start);
            int depth = 0;
            boolean[] vis = new boolean[s.length()];
            while (!q.isEmpty()) {
                int size = q.size();
                for (int i = 0; i < size; i++) {
                    int u = q.poll();
                    if (vis[u]) continue;
                    for (int v : graph.get(u)) {
                        if (vis[v]) continue;
                        if (s.charAt(v) == s.charAt(u)) continue;
                        q.offer(v);
                    }
                    vis[u] = true;
                }
                depth++;
            }
            return depth;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        public long getSecondDiameter(int[][] edges) {
            int n = edges.length + 1;
            Map<Integer, Map<Integer, Integer>> graph = buildGraph(n, edges);
            //找到第一直径的两个端点
            long[][] oneEnd = bfs(graph, 0);
            long[][] twoEnd = bfs(graph, (int) oneEnd[0][0]);
            //第二直径的两个端点只能从oneEnd 和 twoEnd这个两个第一直径的端点出发
            long[][] xEnd = bfs(graph, (int) oneEnd[0][0]);
            long[][] yEnd = bfs(graph, (int) twoEnd[0][0]);
            return Math.max(xEnd[1][1], yEnd[1][1]);
        }


        //
        private long[][] bfs(Map<Integer, Map<Integer, Integer>> graph, int start) {
            Queue<Integer> q = new ArrayDeque<>();
            Map<Integer, Long> dist = new HashMap<>();
            q.offer(start);
            dist.put(start, 0L);
            //两个端点的距离，和端点
            long distOne = 0, nodeOne = -1;
            long distTwo = 0, nodeTwo = -1;
            while (!q.isEmpty()) {
                int cur = q.poll();
                for (Map.Entry<Integer, Integer> ne : graph.get(cur).entrySet()) {
                    int next = ne.getKey(), len = ne.getValue();
                    if (dist.containsKey(next)) continue;
                    dist.put(next, dist.get(cur) + len);
                    q.offer(next);
                    //更新第一个距离的端点，和第二个距离的端点
                    if (dist.get(next) > distOne) {
                        distTwo = distOne;
                        nodeTwo = nodeOne;
                        distOne = dist.get(next);
                        nodeOne = next;
                    } else if (dist.get(next) > distTwo) {
                        distTwo = dist.get(next);
                        nodeTwo = next;
                    }
                }
            }
            return new long[][]{{nodeOne, distOne}, {nodeTwo, distTwo}};
        }


        private Map<Integer, Map<Integer, Integer>> buildGraph(int n, int[][] edges) {
            Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();
            for (int i = 0; i < n; i++) graph.put(i, new HashMap<>());
            for (int[] edge : edges) {
                graph.get(edge[0]).put(edge[1], edge[2]);
                graph.get(edge[1]).put(edge[0], edge[2]);
            }
            return graph;
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
