package com.frankcooper.bank.LCP;

import java.net.Inet4Address;
import java.util.*;

import com.frankcooper.utils.PrintUtils;
import org.junit.Assert;

public class LCP07 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int n = 5;
            int[][] relation = PrintUtils.processSymbol("[[0,2],[2,1],[3,4],[2,3],[1,4],[2,0],[0,4]]");
            int k = 3;
            Assert.assertEquals(3, handler.numWays(n, relation, k));

        }


        public int numWays(int n, int[][] relation, int k) {
            List<List<Integer>> edges = new ArrayList<>();
            for (int i = 0; i < n; i++) edges.add(new ArrayList<>());
            for (int[] x : relation) edges.get(x[0]).add(x[1]);
            Queue<Integer> q = new LinkedList<>();
            q.offer(0);
            int step = 0;
            while (!q.isEmpty() && step < k) {
                step++;
                int size = q.size();
                for (int i = 0; i < size; i++) {
                    int u = q.poll();
                    List<Integer> vs = edges.get(u);
                    for (int v : vs) {
                        q.offer(v);
                    }
                }
            }
            int res = 0;
            if (step == k) {//到达指定层数
                while (!q.isEmpty()) {
                    if (q.poll() == n - 1) res++;
                }
            }
            return res;
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        List<List<Integer>> edges = new ArrayList<>();
        int res = 0;
        int k;
        int n;

        public int numWays(int n, int[][] relation, int k) {
            this.k = k;
            this.n = n;
            for (int i = 0; i < n; i++) edges.add(new ArrayList<>());
            for (int[] x : relation) edges.get(x[0]).add(x[1]);
            dfs(0, 0);
            return res;
        }

        private void dfs(int u, int step) {
            if (step == k) {
                if (u == n - 1) res++;
                return;
            }
            List<Integer> vs = edges.get(u);
            for (int v : vs) dfs(v, step + 1);
        }


    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }


        public int numWays(int n, int[][] relation, int k) {
            /**
             * f[i][j] 经过i轮传递，到达j这个小朋友这里时的方案数
             */
            int[][] f = new int[k + 1][n];
            f[0][0] = 1; //不经过传递，自己到自己，只有一种方案数
            for (int i = 1; i <= k; i++) {
                for (int j = 1; j <= relation.length; j++) {
                    int u = relation[j - 1][0], v = relation[j - 1][1];
                    f[i][v] += f[i - 1][u];
                }
            }
            return f[k][n - 1];
        }

    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }

        public int numWays(int n, int[][] relation, int k) {
            /**
             * f[j] 经过i轮传递，到达j这个小朋友这里时的方案数
             */
            int[] f = new int[n];
            f[0] = 1; //不经过传递，自己到自己，只有一种方案数
            for (int i = 1; i <= k; i++) {
                int[] t = new int[n];
                for (int j = 1; j <= relation.length; j++) {
                    int u = relation[j - 1][0], v = relation[j - 1][1];
                    t[v] += f[u];
                }
                f = t;
            }
            return f[n - 1];
        }
    }
}
