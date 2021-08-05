package com.frankcooper.bank._701_1000;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class _802 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        public List<Integer> eventualSafeNodes(int[][] graph) {
            List<Integer> res = new ArrayList<>();
            int n = graph.length;
            int[] color = new int[n];
            for (int i = 0; i < n; i++) {
                if (dfs(graph, color, i)) res.add(i);
            }
            return res;
        }

        public boolean dfs(int[][] graph, int[] color, int x) {
            if (color[x] > 0) return color[x] == 2;
            color[x] = 1;
            for (int y : graph[x]) {
                if (!dfs(graph, color, y)) return false;
            }
            color[x] = 2;
            return true;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        int[][] graph;

        public List<Integer> eventualSafeNodes(int[][] graph) {
            this.graph = graph;
            List<Integer> res = new ArrayList<>();
            int n = graph.length;
            boolean[] vis = new boolean[n];
            boolean[] stk = new boolean[n];
            for (int i = 0; i < n; i++) {
                if (!isCyclic(i, vis, stk)) res.add(i);
            }
            return res;
        }

        private boolean isCyclic(int i, boolean[] vis, boolean[] stk) {
            if (stk[i]) return true;
            if (vis[i]) return false;
            stk[i] = true;
            vis[i] = true;
            for (int x : graph[i]) {
                if (isCyclic(x, vis, stk)) return true;
            }
            stk[i] = false;
            return false;
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }

        public List<Integer> eventualSafeNodes(int[][] graph) {
            int n = graph.length;
            List<List<Integer>> reverseGraph = new ArrayList<>();
            for (int i = 0; i < n; i++) reverseGraph.add(new ArrayList<>());
            int[] indegrees = new int[n];//入度数组
            for (int u = 0; u < n; u++) {
                for (int v : graph[u]) {
                    reverseGraph.get(v).add(u);
                }
                indegrees[u] = graph[u].length;
            }
            Queue<Integer> q = new LinkedList<>();
            for (int u = 0; u < n; u++) {
                if (indegrees[u] == 0) q.offer(u);
            }
            while (!q.isEmpty()) {
                int v = q.poll();
                for (int u : reverseGraph.get(v)) {
                    if (--indegrees[u] == 0) q.offer(u);
                }
            }
            List<Integer> res = new ArrayList<>();
            for (int u = 0; u < n; u++) {
                if (indegrees[u] == 0) res.add(u);
            }

            return res;
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
