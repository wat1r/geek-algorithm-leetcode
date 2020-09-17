package com.frankcooper.bank;

import java.sql.Struct;

public class _685 {


    static _685 handler = new _685();


    public static void main(String[] args) {
        int[][] edges = {{1, 2}, {2, 3}, {3, 1}, {1, 4}};
        edges = new int[][]{{2, 1}, {3, 1}, {4, 2}, {1, 4}};
        int[] arr = handler.findRedundantDirectedConnection(edges);
        System.out.printf("%d--->%d\n", arr[0], arr[1]);

    }


    public int[] findRedundantDirectedConnection(int[][] edges) {

        int n = edges.length;
        //预处理，准备当前节点的入度
        int[] indegrees = new int[n + 1];
        for (int[] edge : edges) {
            indegrees[edge[1]]++;
        }
        //入度为2
        for (int i = n - 1; i >= 0; i--) {
            if (indegrees[edges[i][1]] == 2) {
                if (!hasCycle(edges, n, i)) {
                    return edges[i];
                }
            }
        }
        //入度为1
        for (int i = n - 1; i >= 0; i--) {
            if (indegrees[edges[i][1]] == 1) {
                if (!hasCycle(edges, n, i)) {
                    return edges[i];
                }
            }
        }
        return null;
    }


    /**
     * 判断移除当前的idx，也就是edges[idx]这条边后，有没有环出现
     *
     * @param edges
     * @param n
     * @param idx
     * @return
     */
    private boolean hasCycle(int[][] edges, int n, int idx) {
        UnionFind uf = new UnionFind(n + 1);
        for (int i = 0; i < n; ++i) {
            //跳过的方式是，该索引节点不加入到联通分量中，相当于remove这条edge
            if (i == idx) continue;
            //开始合并，如果两个点已经是联通的，union他们，会返回false
            //这时候第二次联通会失败，说明两个点已经在一个联通分量了，这时候说明有环，返回true
            if (!uf.unoin(edges[i][0], edges[i][1])) {
                return true;
            }
        }
        return false;
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

        /**
         * 是否合并成功，如果根节点相同，说明已经联通过了，不需要再次联通，返回false
         * 如果根节点不同，开始联通，也就是能联通，返回true
         *
         * @param x
         * @param y
         * @return
         */
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
    }


}
