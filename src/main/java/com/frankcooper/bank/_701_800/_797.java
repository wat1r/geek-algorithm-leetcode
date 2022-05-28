package com.frankcooper.bank._701_800;

import java.util.*;

public class _797 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        Map<Integer, List<Integer>> map = new HashMap<>();
        List<List<Integer>> res = new ArrayList<>();
        int end;

        public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
            this.end = graph.length - 1;
            for (int i = 0; i < graph.length; i++) {
                int[] vs = graph[i];
                if (vs.length == 0) continue;//建图
                List<Integer> t = map.getOrDefault(i, new ArrayList<>());
                for (int v : vs) {
                    t.add(v);
                }
                map.put(i, t);
            }
            List<Integer> path = new ArrayList<>();
            path.add(0);
            dfs(0, path);
            return res;
        }

        private void dfs(int cur, List<Integer> path) {
            if (cur == end) {
                res.add(new ArrayList<>(path));
                return;
            }
            List<Integer> nexts = map.getOrDefault(cur, new ArrayList<>());
            for (int next : nexts) {
                path.add(next);
                dfs(next, path);
                path.remove(path.size() - 1);
            }
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            int[][] graph = {{1, 2}, {3}, {3}, {}};
            List<List<Integer>> res = handler.allPathsSourceTarget(graph);
            System.out.printf("");
        }

        //已经建好图了，不需要再建图
        int[][] graph;
        int n;


        public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
            this.graph = graph;
            this.n = graph.length;
            return dfs(0);
        }


        private List<List<Integer>> dfs(int u) {
            List<List<Integer>> res = new ArrayList<>();
            if (u == n - 1) {
                List<Integer> path = new ArrayList<>();
                path.add(u);
                res.add(path);
                return res;
            }
            for (int next : graph[u]) {
                List<List<Integer>> paths = dfs(next);
                for (List<Integer> path : paths) {
                    path.add(0, u);
                    res.add(path);
                }
            }
            return res;
        }

    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }

        int[][] graph;
        int n;
        List<List<Integer>> res = new ArrayList<>();

        public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
            this.graph = graph;
            this.n = graph.length;
            dfs(0, new ArrayList<>());
            return res;
        }

        private void dfs(int u, List<Integer> path) {
            path.add(u);
            if (u == n - 1) {
                res.add(new ArrayList<>(path));
            }
            for (int v : graph[u]) {
                dfs(v, path);
            }
            path.remove(path.size() - 1);
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }


        public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
            int n = graph.length;
            List<List<Integer>> res = new ArrayList<>();
            Queue<List<Integer>> q = new LinkedList<List<Integer>>() {{
                List<Integer> list = new ArrayList<>();
                list.add(0);
                add(list);
            }};
            while (!q.isEmpty()) {
                List<Integer> cur = q.poll();
                if (cur.get(cur.size() - 1) == n - 1) {
                    res.add(cur);
                }
                int vIdx = cur.get(cur.size() - 1);
                for (int v : graph[vIdx]) {
                    List<Integer> next = new ArrayList<>(cur);
                    next.add(v);
                    q.offer(next);
                }
            }
            return res;
        }
    }
}
