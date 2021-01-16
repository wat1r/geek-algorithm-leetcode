package com.frankcooper.bank;

public class _947 {


    static class _1st {

        public int removeStones(int[][] stones) {
            int n = stones.length;
            int ans = 0;
            UnionFind uf = new UnionFind(n);
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    int[] a = stones[i], b = stones[j];
                    if (a[0] == b[0] || a[1] == b[1]) {
//                        if()
                    }
                }
            }

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
                if (x != parents[x]) parents[x] = find(parents[x]);
                return parents[x];
            }


            public boolean union(int x, int y) {
                int rootX = find(x), rootY = find(y);
                if (rootX == rootY) return false;
                if (ranks[rootX] > rootY) parents[rootY] = rootX;
                else if (ranks[rootX] < ranks[rootY]) parents[rootX] = rootY;
                else if (ranks[rootX] == ranks[rootY]) {
                    parents[rootX] = rootY;
                    ranks[rootX]++;
                }
                return true;
            }

            public boolean connect(int x, int y) {
                return find(x) == find(y);
            }


        }

    }


}
