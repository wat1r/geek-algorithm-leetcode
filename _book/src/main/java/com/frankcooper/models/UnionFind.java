package com.frankcooper.models;

public class UnionFind {
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
