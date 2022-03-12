package com.frankcooper.classic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Date 2020/9/13
 * @Author Frank Cooper
 * @Description 链接
 * https://juejin.im/post/6858481283715039240#heading-7
 * MST:Minimum Spanning Tree
 */
public class KruskalMST {


    public static void main(String[] args) {
        int N = 7;
        int[][] edges = {{0, 1, 4},
                {0, 5, 8},
                {1, 2, 8},
                {1, 5, 11},
                {2, 3, 3},
                {2, 6, 2},
                {3, 4, 3},
                {4, 5, 8},
                {4, 6, 6},
                {5, 6, 7},
        };
        KruskalMST kruskal = new KruskalMST(N, edges);
        int mstCost = kruskal.getMstCost();
        System.out.println("最小生成树的权值之和：" + mstCost);
        List<int[]> mst = kruskal.getMst();
        System.out.println("最小生成树的边的列表：");
        for (int[] edge : mst) {
            System.out.println("[" + edge[0] + "-" + edge[1] + "]" + "，权值：" + edge[2]);
        }
    }


    //最小生成树的权值之和
    private int mstCost;

    public int getMstCost() {
        return mstCost;
    }

    private List<int[]> mst;


    public List<int[]> getMst() {
        return mst;
    }

    /**
     * @param V
     * @param edges 边的集合，起始点，权值
     */
    public KruskalMST(int V, int[][] edges) {
        int E = edges.length;
        mst = new ArrayList<>(E - 1);
        //按权重o[2]进行排序，从小到大
        Arrays.sort(edges, (o1, o2) -> o1[2] - o2[2]);
        UnionFind uf = new UnionFind(V);
        int count = 0;
        for (int[] edge : edges) {
            if (uf.isConnected(edge[0], edge[1])) continue;
            uf.union(edge[0], edge[1]);
            this.mstCost += edge[2];
            this.mst.add(new int[]{edge[0], edge[1], edge[2]});
            count++;
            if (count == V - 1) break;
        }

    }

    class UnionFind {
        int[] parents;
        int[] ranks;

        public UnionFind(int n) {
            parents = new int[n];
            ranks = new int[n];
            for (int i = 0; i < n; i++) {
                parents[i] = i;
            }
        }


        public int find(int x) {
            if (x != parents[x]) {
                parents[x] = find(parents[x]);
            }
            System.out.println(x + ":" + parents[x]);
            return parents[x];
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) return;
            if (ranks[rootX] > ranks[rootY]) parents[rootY] = rootX;
            if (ranks[rootX] < ranks[rootY]) parents[rootX] = rootY;
            if (ranks[rootX] == ranks[rootY]) {
                parents[rootY] = rootX;
                ranks[rootY]++;
            }
        }

        public boolean isConnected(int x, int y) {
            return find(x) == find(y);
        }
    }

}
