package com.frankcooper.swordoffer_II;

import java.util.*;

public class Sword_II_110 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }

        int n;
        int[][] graph;

        public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
            this.graph = graph;
            n = graph.length;
            return dfs(0);
        }


        private List<List<Integer>> dfs(int u) {
            List<List<Integer>> res = new ArrayList<>();
            if (u == n - 1) {
                List<Integer> sub = new ArrayList<>();
                sub.add(u);
                res.add(sub);
                return res;
            }
            for (int v : graph[u]) {
                List<List<Integer>> paths = dfs(v);
                for (List<Integer> path : paths) {
                    path.add(0, u);
                    res.add(path);
                }
            }
            return res;
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
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
