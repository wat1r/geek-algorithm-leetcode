package com.frankcooper.platform.leetcode.bank._901_1000;

import java.util.HashSet;
import java.util.Set;

public class _947 {


    static class _1st {

        public int removeStones(int[][] stones) {
            int n = stones.length;
            UnionFind uf = new UnionFind(20000);
            for (int[] s : stones) {
                uf.union(10001 + s[0], s[1]);
            }
            Set<Integer> set = new HashSet<>();
            for (int[] s : stones) {
                set.add(uf.find(s[0]));
            }

            return n - set.size();
        }


        class UnionFind {

            int[] parents;
            int[] ranks;
            int count;


            public UnionFind(int n) {
                parents = new int[n];
                ranks = new int[n];
                for (int i = 0; i < n; i++) parents[i] = i;
                for (int i = 0; i < n; i++) ranks[i] = 1;
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
                    ranks[rootY]++;
                }
                return true;
            }

            public boolean connect(int x, int y) {
                return find(x) == find(y);
            }


        }

    }


}
