package com.frankcooper.platform.leetcode.bank._301_400;

import java.util.*;

public class _310 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int n = 6;
            int[][] edges = {{3, 0}, {3, 1}, {3, 2}, {3, 4}, {5, 4}};
            handler.findMinHeightTrees(n, edges);

        }

        public List<Integer> findMinHeightTrees(int n, int[][] edges) {
            List<Integer> res = new ArrayList<>();
            if (n == 1) {
                res.add(0);
                return res;
            }
            List<List<Integer>> graph = new ArrayList<>();
            for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
            int[] outdegrees = new int[n];
            for (int[] e : edges) {
                int u = e[0], v = e[1];
                outdegrees[u]++;
                outdegrees[v]++;
                graph.get(u).add(v);
                graph.get(v).add(u);
            }
            Queue<Integer> q = new LinkedList<>();
            for (int i = 0; i < n; i++) {
                if (outdegrees[i] == 1) q.offer(i);
            }
            while (!q.isEmpty()) {
                res = new ArrayList<>();
                int size = q.size();
                for (int i = 0; i < size; i++) {
                    int u = q.poll();
                    res.add(u);
                    List<Integer> vs = graph.get(u);
                    for (int v : vs) {
                        outdegrees[v]--;
                        if (outdegrees[v] == 1) q.offer(v);

                    }
                }
            }
            return res;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            int n = 4;
            int[][] edges = new int[][]{{1, 0}, {1, 2}, {1, 3}};
            handler.findMinHeightTrees(n, edges);

        }

        public List<Integer> findMinHeightTrees(int n, int[][] edges) {
            List<Integer> res = new ArrayList<>();
            if (n == 1) {
                res.add(0);
                return res;
            }
            List<Set<Integer>> adj = new ArrayList<>();
            for (int i = 0; i < n; i++) adj.add(new HashSet<>());
            for (int[] e : edges) {
                int u = e[0], v = e[1];
                adj.get(u).add(v);
                adj.get(v).add(u);
            }
            //叶子节点
            List<Integer> leaves = new ArrayList<>();
            for (int i = 0; i < n; i++) if (adj.get(i).size() == 1) leaves.add(i);
            while (n > 2) {
                n -= leaves.size();
                List<Integer> tmp = new ArrayList<>();
                for (int u : leaves) {
                    Iterator<Integer> it = adj.get(u).iterator();
                    while (it.hasNext()) {
                        int v = it.next();
                        adj.get(v).remove(u);
                        if (adj.get(v).size() == 1) tmp.add(v);

                    }
                }
                leaves = tmp;
            }
            return leaves;
        }

    }


    //https://leetcode-cn.com/problems/minimum-height-trees/solution/by-ac_oier-7xio/
    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }

        int N = 20010, M = 2 * N;
        int[] head = new int[N];
        int[] to = new int[M];
        int[] next = new int[M];
        int idx = 0;
        int[] f1 = new int[N], f2 = new int[N];
        int[] g = new int[N];
        int[] p = new int[N];

        private void add(int a, int b) {
            next[idx] = head[a];
            to[idx] = b;
            head[idx++] = a;
        }

        //从当前节点开始往下dfs
        private int dfsBottom(int u, int fa) {
            for (int x = head[u]; x != -1; x = next[x]) {
                int v = to[x];
                if (v == fa) continue;
                int height = dfsBottom(v, u) + 1;
                if (height > f1[u]) {
                    f2[u] = f1[u];
                    f1[u] = height;
                    p[u] = v;
                } else if (height > f2[u]) {
                    f2[u] = height;
                }
            }
            return f1[u];
        }

//        private int dfsTop(int u, int fa) {
//            for (int x = head[u]; x != -1; x = next[x]) {
//                int v = to[x];
//                if (v == fa) continue;
//
//            }
//        }


        //其中 f[u] 代表在以 0 号点为根节点的树中，以 u 节点为子树根节点时，往下的最大高度；g[u]代表在以 0 号点为根节点的树中，以 u 节点为子节点时，往上的最大高度
        public List<Integer> findMinHeightTrees(int n, int[][] edges) {

            return null;
        }

    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
