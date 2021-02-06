package com.frankcooper.bank;

import com.frankcooper.swordoffer.utils.PrintUtils;

public class _1334 {

    public static void main(String[] args) {
        _1st handler = new _1st();
        int N = 4, edges[][] = PrintUtils.processSymbol("[[0,1,3],[1,2,1],[1,3,4],[2,3,1]]"), distanceThreshold = 4;
        handler.findTheCity(N, edges, distanceThreshold);
    }


    /**
     * floyd
     */
    static class _1st {
        public int findTheCity(int N, int[][] edges, int distanceThreshold) {
            int INF = Integer.MAX_VALUE;
            int[][] graph = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    graph[i][j] = (i == j) ? 0 : INF;
                }
            }
            for (int[] e : edges) {
                int u = e[0], v = e[1], w = e[2];
                graph[u][v] = w;
                graph[v][u] = w;
            }

            for (int k = 0; k < N; k++) {
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        if (i == j || graph[i][k] == INF || graph[k][j] == INF) continue;
                        graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                    }
                }
            }
            PrintUtils.printMatrix(graph);
            int res = -1, min = N + 1;
            for (int i = 0; i < N; i++) {
                int cnt = 0;
                for (int j = 0; j < N; j++) {
                    if (i != j && graph[i][j] <= distanceThreshold) {
                        cnt++;
                    }
                }
                if (min >= cnt) {
                    min = cnt;
                    res = i;
                }
            }
            return res;
        }
    }


}
