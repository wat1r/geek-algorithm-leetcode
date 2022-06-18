package com.frankcooper.platform.leetcode.bank._901_1000;

public class _959 {


    public static void main(String[] args) {

    }


    class _1st {


        public int regionsBySlashes(String[] grid) {
            int N = grid.length, R = grid.length, C = grid[0].length();
            UnionFind uf = new UnionFind(N * N * 4);
            for (int r = 0; r < R; r++) {
                char[] rows = grid[r].toCharArray();
                for (int c = 0; c < C; c++) {
                    int idx = 4 * (r * C + c);
                    char curr = rows[c];
                    if (curr != '/') {
                        uf.union(idx + 0, idx + 1);
                        uf.union(idx + 3, idx + 2);
                    }
                    if (curr != '\\') {
                        uf.union(idx + 0, idx + 3);
                        uf.union(idx + 1, idx + 2);
                    }
                    if (r - 1 >= 0)
                        uf.union(idx + 0, (idx - 4 * N) + 2);
                    if (c - 1 >= 0)
                        uf.union(idx + 3, (idx - 4) + 1);
                }
            }
            return uf.getCount();
        }


        class UnionFind {
            int count;
            int[] parents;
            int[] ranks;


            public int getCount() {
                return count;
            }

            public UnionFind(int n) {
                count = n;
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
                    ranks[rootX]++;
                }
                count--;
                return true;
            }

            public boolean connect(int x, int y) {
                int rootX = find(x);
                int rootY = find(y);
                return rootX == rootY;
            }

        }
    }

}
