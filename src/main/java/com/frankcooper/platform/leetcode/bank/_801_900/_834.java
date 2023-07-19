package com.frankcooper.platform.leetcode.bank._801_900;

import java.util.*;

import org.junit.Assert;

public class _834 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        int[] ans, size, dp;
        List<List<Integer>> graph;

        public int[] sumOfDistancesInTree(int n, int[][] edges) {
            //初始化+建图
            ans = new int[n];
            size = new int[n];
            //dp[u] 表示以 u 为根的子树，它的所有子节点到它的距离之和
            dp = new int[n];
            graph = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                graph.add(new ArrayList<>());
            }
            for (int[] e : edges) {
                int u = e[0], v = e[1];
                graph.get(u).add(v);
                graph.get(v).add(u);
            }
            dfs(0, -1);
            cal(0, -1);
            return ans;

        }

        public void dfs(int u, int fa) {
            size[u] = 1;
            dp[u] = 0;
            for (int v : graph.get(u)) {
                if (v != fa) {
                    dfs(v, u);
                    dp[u] += dp[v] + size[v];
                    size[u] += size[v];
                }
            }
        }

        public void cal(int u, int fa) {
            ans[u] = dp[u];
            for (int v : graph.get(u)) {
                if (v != fa) {
                    int pu = dp[u], pv = dp[v];
                    int su = size[u], sv = size[v];

                    dp[u] -= dp[v] + size[v];
                    size[u] -= size[v];
                    dp[v] += dp[u] + size[u];
                    size[v] += size[u];

                    cal(v, u);

                    dp[u] = pu;
                    dp[v] = pv;
                    size[u] = su;
                    size[v] = sv;


                }
            }
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
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
