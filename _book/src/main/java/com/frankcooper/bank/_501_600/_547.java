package com.frankcooper.bank._501_600;

import com.frankcooper.utils.PrintUtils;

import java.util.LinkedList;
import java.util.Queue;

public class _547 {
    static _2nd handler = new _2nd();

    public static void main(String[] args) {
        int[][] isConnected = PrintUtils.processSymbol("[[1,1,0],[1,1,0],[0,0,1]]");
        handler.findCircleNum(isConnected);
    }


    class _1st {
        public int findCircleNum(int[][] isConnected) {
            int n = isConnected.length;
            UnionFind uf = new UnionFind(n);
            int circle = n;
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (isConnected[i][j] == 1 && uf.union(i, j)) circle--;
                }
            }
            return circle;

        }


        class UnionFind {
            int[] parents;
            int[] ranks;

            public UnionFind(int n) {
                parents = new int[n];
                ranks = new int[n];
                for (int i = 0; i < n; i++) parents[i] = i;
            }

            public int find(int x) {
                if (x != parents[x]) {
                    parents[x] = find(parents[x]);
                }
                return parents[x];
            }

            public boolean union(int x, int y) {
                int rootX = find(x), rootY = find(y);
                if (rootX == rootY) return false;
                if (ranks[rootX] > ranks[rootY]) parents[rootY] = rootX;
                if (ranks[rootX] < ranks[rootY]) parents[rootX] = rootY;
                if (ranks[rootX] == ranks[rootY]) {
                    parents[rootY] = rootX;
                    ranks[rootY]++;
                }
                return true;
            }

            public boolean connect(int x, int y) {
                int rootX = find(x);
                int rootY = find(y);
                return rootX == rootY;
            }

        }
    }


    static class _2nd {


        public int findCircleNum(int[][] isConnected) {
            int n = isConnected.length;
            boolean[] vis = new boolean[n];//记录当前点是否被访问过
            int circles = 0;
            for (int i = 0; i < n; i++) {
                if (!vis[i]) {//当前点没有被访问过，新的独立的联通区域，+1
                    dfs(isConnected, vis, i, n);
                    circles++;
                }
            }
            return circles;
        }

        private void dfs(int[][] isConnected, boolean[] vis, int i, int n) {
            for (int j = 0; j < n; j++) {
                if (!vis[j] && isConnected[i][j] == 1) {//表示访问过，i->j点是联通的，且j点第一次被访问
                    vis[j] = true;
                    dfs(isConnected, vis, j, n);
                }
            }
        }


    }


    static class _3rd {


        public int findCircleNum(int[][] isConnected) {
            int n = isConnected.length, circles = 0;
            boolean[] vis = new boolean[n];
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(0);
            for (int i = 0; i < n; i++) {
                if (!vis[i]) {
                    vis[i] = true;
                    circles++;
                    queue.offer(i);
                    while (!queue.isEmpty()) {
                        int x = queue.poll();
                        for (int y = 0; y < n; y++) {
                            if (!vis[y] && isConnected[x][y] == 1) {
                                vis[y] = true;
                                queue.offer(y);
                            }
                        }
                    }
                }
            }
            return circles;
        }

    }

}
