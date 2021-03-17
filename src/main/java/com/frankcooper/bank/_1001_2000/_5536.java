package com.frankcooper.bank._1001_2000;

import java.util.*;

public class _5536 {

    static _5536 handler = new _5536();

    public static void main(String[] args) {
        int n = 5;
        int[][] roads = {{0, 1}, {0, 3}, {1, 2}, {1, 3}, {2, 3}, {2, 4}};
        n = 8;
        roads = new int[][]{{0, 1}, {1, 2}, {2, 3}, {2, 4}, {5, 6}, {5, 7}};
        //5
        //[[2,3],[0,3],[0,4],[4,1]]
        handler.maximalNetworkRank(n, roads);

    }


    public int maximalNetworkRank(int n, int[][] roads) {
        if (roads == null || roads.length == 0 || roads[0].length == 0) return 0;
        HashMap<Integer, Integer> graph = new HashMap<>();
        boolean[][] visited = new boolean[n][n];
        for (int[] road : roads) {
            int s = road[0], e = road[1];
            graph.put(s, graph.getOrDefault(s, 0) + 1);
            graph.put(e, graph.getOrDefault(e, 0) + 1);
            visited[s][e] = true;
            visited[e][s] = true;
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int seg = 0;
                seg += graph.getOrDefault(i, 0);
                seg += graph.getOrDefault(j, 0);
                if (visited[i][j]) {
                    seg--;
                }
                System.out.printf("i:%d,j:%d,seg:%d\n", i, j, seg);
                res = Math.max(res, seg);
            }
        }
        return res;
    }





}
