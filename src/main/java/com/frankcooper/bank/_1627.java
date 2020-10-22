package com.frankcooper.bank;

import java.util.ArrayList;
import java.util.List;

public class _1627 {

    static _1627 handler = new _1627();

    public static void main(String[] args) {
        int n = 6;
        int threshold = 2;
        int[][] queries = {{1, 4}, {2, 5}, {3, 6}};
        handler.areConnected(n, threshold, queries);
    }


    public List<Boolean> areConnected(int n, int threshold, int[][] queries) {
        UnionFind uf = new UnionFind(n);
        for (int i = threshold + 1; i <= n; i++) {
            int times = 2;
            while (i * times <= n) {
                int x = i - 1;
                int y = i * times - 1;
                uf.unoin(x, y);
                times += 1;
            }
        }
        List<Boolean> res = new ArrayList<>();
        for (int[] q : queries) {
            int x = q[0] - 1, y = q[1] - 1;
            res.add(uf.connect(x, y));
        }
        return res;
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

        public boolean unoin(int x, int y) {
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
