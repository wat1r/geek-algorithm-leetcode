package com.frankcooper.other;

import java.util.*;

/**
 * @Date 2020/9/13
 * @Author Frank Cooper
 * @Description
 */
public class PrimMST {

    public static void main(String[] args) {
        int V = 7;
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
        PrimMST prim = new PrimMST(V, edges);
        int mstCost = prim.getMstCost();
        System.out.println("最小生成树的权值之和：" + mstCost);
        List<int[]> mst = prim.getMst();
        System.out.println("最小生成树的边的列表：");
        for (int[] edge : mst) {
            System.out.println("[" + edge[0] + "-" + edge[1] + "]" + "，权值：" + edge[2]);
        }
    }


    private int mstCost;

    public int getMstCost() {
        return mstCost;
    }

    private List<int[]> mst;


    public List<int[]> getMst() {
        return mst;
    }


    public PrimMST(int V, int[][] edges) {
        int E = edges.length;
        mst = new ArrayList<>(E - 1);
        Set<int[]>[] adjects = new HashSet[V];
        for (int i = 0; i < V; i++) {
            adjects[i] = new HashSet<>();
        }
        for (int[] edge : edges) {
            adjects[edge[0]].add(new int[]{edge[0], edge[1], edge[2]});
            adjects[edge[1]].add(new int[]{edge[0], edge[1], edge[2]});
        }
        boolean[] visited = new boolean[V];
        visited[0] = true;
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(E, (o1, o2) -> o1[2] - o2[2]);
        minHeap.addAll(adjects[1]);
        int count = 0;
        while (!minHeap.isEmpty()) {
            int[] edge = minHeap.poll();
            if (visited[edge[0]] && visited[edge[1]]) {
                continue;
            }
            this.mstCost += edge[2];
            mst.add(new int[]{edge[0], edge[1], edge[2]});
            count++;
            if (count == (V - 1)) {
                break;
            }
            int newV;
            if (visited[edge[0]]) {
                newV = edge[1];
            } else {
                newV = edge[0];
            }
            visited[newV] = true;
            for (int[] successor : adjects[newV]) {
                if (!visited[successor[1]]) {
                    minHeap.add(successor);
                }
            }
        }


    }
}
