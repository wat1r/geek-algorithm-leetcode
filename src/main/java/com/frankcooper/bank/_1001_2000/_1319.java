package com.frankcooper.bank._1001_2000;

public class _1319 {

    public int makeConnected(int n, int[][] connections) {
        UnionFind uf = new UnionFind(n);
        for (int[] c : connections) {
            int x = c[0], y = c[1];
            uf.union(x, y);
        }
        if (uf.getRest() < uf.getCount() - 1) return -1;
        return uf.getCount() - 1;
    }


    class UnionFind {
        int[] parents;
        int[] ranks;
        int count;
        int rest;

        public UnionFind(int n) {
            parents = new int[n];
            ranks = new int[n];
            for (int i = 0; i < n; i++) parents[i] = i;
            count = n;
        }

        public int find(int x) {
            if (x != parents[x]) {
                parents[x] = find(parents[x]);
            }
            return parents[x];
        }

        public boolean union(int x, int y) {
            int rootX = find(x), rootY = find(y);
            if (rootX == rootY) {
                rest++;
                return false;
            } else {
                if (ranks[rootX] > ranks[rootY]) parents[rootY] = rootX;
                if (ranks[rootX] < ranks[rootY]) parents[rootX] = rootY;
                if (ranks[rootX] == ranks[rootY]) {
                    parents[rootY] = rootX;
                    ranks[rootY]++;
                }
                count--;
                return true;
            }
        }

        public int getCount() {
            return count;
        }

        public int getRest() {
            return rest;
        }

    }


}
