package com.frankcooper.platform.leetcode.bank._2001_3000;

import java.util.*;

import org.junit.Assert;

public class _2316 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        public long countPairs(int n, int[][] edges) {
            UnionFind uf = new UnionFind(n);
            for (int[] e : edges) {
                int x = e[0], y = e[1];
                uf.union(x, y);
            }
            long res = 0;
            for (int i = 0; i < n; i++) {
                res += n - uf.sizes[uf.find(i)];
            }
            return res / 2;
        }


        class UnionFind {
            int[] parents;
            int[] sizes;

            public UnionFind(int n) {
                parents = new int[n];
                for (int i = 0; i < n; i++) {
                    parents[i] = i;
                }
                sizes = new int[n];
                Arrays.fill(sizes, 1);
            }

            public int find(int x) {
                if (parents[x] == x) {
                    return x;
                } else {
                    parents[x] = find(parents[x]);
                    return parents[x];
                }
            }

            public void union(int x, int y) {
                int rx = find(x), ry = find(y);
                if (rx == ry) {
                    return;
                }
                if (sizes[rx] > sizes[ry]) {
                    parents[ry] = rx;
                    sizes[rx] += sizes[ry];
                } else {
                    parents[rx] = ry;
                    sizes[ry] += sizes[rx];
                }
            }
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();

            int n = 7;
            int[][] edges = {{0, 2}, {0, 5}, {2, 4}, {1, 6}, {5, 4}};
            Assert.assertEquals(14, handler.countPairs(n, edges));
        }

        public long countPairs(int n, int[][] edges) {
            List<Integer>[] graph = new List[n];
            Arrays.setAll(graph, e -> new ArrayList<>());
            for (int[] e : edges) {
                int u = e[0], v = e[1];
                graph[u].add(v);
                graph[v].add(u);
            }
            boolean[] vis = new boolean[n];
            long res = 0;
            for (int i = 0; i < n; i++) {
                if (!vis[i]) {
                    long count = dfs(i, graph, vis);
                    System.out.printf("%d,%d\n", i, count);
                    res += count * (n - count);
                }
            }
            return res / 2;
        }


        public int dfs(int u, List<Integer>[] graph, boolean[] vis) {
            vis[u] = true;
            int size = 1;
            for (int v : graph[u]) {
                if (!vis[v]) {
                    size += dfs(v, graph, vis);
                }
            }
            return size;
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
            int n = 7;
            int[][] edges = {{0, 2}, {0, 5}, {2, 4}, {1, 6}, {5, 4}};
            Assert.assertEquals(14, handler.countPairs(n, edges));

        }

        public long countPairs(int n, int[][] edges) {
            List<Integer>[] g = new ArrayList[n];
            Arrays.setAll(g, e -> new ArrayList<>());
            for (int[] e : edges) {
                int x = e[0], y = e[1];
                g[x].add(y);
                g[y].add(x); // 建图
            }

            boolean[] vis = new boolean[n];
            long ans = 0;
            for (int i = 0, total = 0; i < n; i++) {
                if (!vis[i]) { // 未访问的点：说明找到了一个新的连通块
                    int size = dfs(i, g, vis);
                    ans += (long) size * total;
                    total += size;
                }
            }
            return ans;
        }

        private int dfs(int x, List<Integer>[] g, boolean[] vis) {
            vis[x] = true; // 避免重复访问同一个点
            int size = 1;
            for (int y : g[x]) {
                if (!vis[y]) {
                    size += dfs(y, g, vis);
                }
            }
            return size;
        }
//
//        作者：灵茶山艾府
//        链接：https://leetcode.cn/problems/count-unreachable-pairs-of-nodes-in-an-undirected-graph/
//        来源：力扣（LeetCode）
//        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
