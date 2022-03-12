package com.frankcooper.interview;

import java.util.*;

import com.frankcooper.utils.PrintUtils;
import org.junit.Assert;

public class _04_01 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int n = 3;
            int[][] graph = PrintUtils.processSymbol("[[0,1],[0,2],[1,2],[1,2]]");
            int start = 0;
            int target = 2;
            handler.findWhetherExistsPath(n, graph, start, target);

        }

        List<Integer>[] adj;

        public boolean findWhetherExistsPath(int n, int[][] graph, int start, int target) {
            this.adj = new ArrayList[n];
            buildAdj(graph);
            boolean[] vis = new boolean[n];
            Queue<Integer> q = new LinkedList<>();
            q.offer(start);
            vis[start] = true;
            while (!q.isEmpty()) {
                int size = q.size();
                for (int i = 0; i < size; i++) {
                    int cur = q.poll();
                    List<Integer> nextList = adj[cur];
                    if (nextList == null) continue;
                    for (int next : nextList) {
                        if (next == target) return true;
                        if (vis[next]) continue;
                        vis[next] = true;
                        q.offer(next);
                    }
                }
            }
            return false;
        }

        private void buildAdj(int[][] graph) {
            for (int[] edge : graph) {
                int u = edge[0], v = edge[1];
                if (adj[u] == null) adj[u] = new ArrayList<>();
                adj[u].add(v);
            }
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }


        public boolean findWhetherExistsPath(int n, int[][] graph, int start, int target) {
            buildGraph(graph);
            Queue<Integer> q = new LinkedList<>();
            boolean[] vis = new boolean[n];
            q.offer(start);
            vis[start] = true;
            while (!q.isEmpty()) {
                int size = q.size();
                for (int i = 0; i < size; i++) {
                    int cur = q.poll();
                    Set<Integer> nextSet = g.get(cur);
                    if (nextSet == null) continue;
                    for (int next : nextSet) {
                        if (next == target) return true;
                        if (vis[next]) continue;
                        vis[next] = true;
                        q.offer(next);
                    }
                }
            }
            return false;
        }


        Map<Integer, Set<Integer>> g = new HashMap<>();//图

        private void buildGraph(int[][] graph) {
            for (int[] e : graph) {
                int u = e[0], v = e[1];
                Set<Integer> set = g.getOrDefault(u, new HashSet<>());
                set.add(v);
                g.put(u, set);
            }
        }


    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }

        boolean[] vis;
        int n;
        int[][] graph;

        public boolean findWhetherExistsPath(int n, int[][] graph, int start, int target) {
            this.n = n;
            this.vis = new boolean[this.n];
            this.graph = graph;
            return dfs(start, target);
        }


        private boolean dfs(int start, int target) {
            for (int i = 0; i < n; i++) {
                if (!vis[i]) {
                    if (graph[i][0] == start && graph[i][1] == target) {
                        return true;
                    }
                    vis[i] = true;
                    if (graph[i][1] == target && dfs(start, graph[i][0])) return true;
                    vis[i] = false;
                }
            }
            return false;
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();

        }

        boolean[] vis;
        int n;
        List<Integer>[] g;

        public boolean findWhetherExistsPath(int n, int[][] graph, int start, int target) {
            this.n = n;
            this.vis = new boolean[this.n];
            this.g = new ArrayList[this.n];
            buildGraph(graph);
            return dfs(start, target);
        }

        private boolean dfs(int start, int target) {
            if (start == target) return true;
            if (vis[start]) return false;
            vis[start] = true;
            List<Integer> nextList = g[start];
            if (nextList == null) return false;
            for (int next : nextList) {
                if (dfs(next, target)) return true;
            }
            return false;
        }

        private void buildGraph(int[][] graph) {
            for (int[] e : graph) {
                int u = e[0], v = e[1];
                if (g[u] == null) g[u] = new ArrayList<>();
                g[u].add(v);
            }
        }
    }
}
