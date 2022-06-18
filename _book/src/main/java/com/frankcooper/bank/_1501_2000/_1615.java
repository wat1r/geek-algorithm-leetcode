package com.frankcooper.platform.leetcode.bank._1501_2000;

import com.frankcooper.utils.PrintUtils;

import java.util.HashMap;
import java.util.Map;

public class _1615 {

    static _1615 handler = new _1615();


    public static void main(String[] args) {
        handler.maximalNetworkRank1st(4
                , PrintUtils.processSymbol("[[0,1],[0,3],[1,2],[1,3]]"));
    }


    public int maximalNetworkRank1st(int n, int[][] roads) {
        Map<Integer, Integer> graph = new HashMap<>();
        boolean[][] vis = new boolean[n][n];
        for (int[] r : roads) {
            int s = r[0], e = r[1];
            graph.put(s, graph.getOrDefault(s, 0) + 1);
            graph.put(e, graph.getOrDefault(e, 0) + 1);
            vis[s][e] = true;
            vis[e][s] = true;
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int seg = graph.getOrDefault(i, 0);
                seg += graph.getOrDefault(j, 0);
                if (vis[i][j]) seg--;
                System.out.printf("i:%d,j:%d,seg:%d\n", i, j, seg);
                res = Math.max(res, seg);
            }
        }
        return res;
    }


    public int maximalNetworkRank(int n, int[][] roads) {
        int[][] graph = new int[n][n]; //图
        int[] indegree = new int[n]; //入度
        for (int[] r : roads) {
            int s = r[0], e = r[1];
            graph[s][e]++;
            graph[e][s]++;
            indegree[s]++;
            indegree[e]++;
        }
        int res = 0;
        for (int s = 0; s < n; s++) {
            for (int e = s + 1; e < n; e++) {
                res = Math.max(res, indegree[s] + indegree[e] - graph[s][e]); //去掉重复的边
            }
        }
        return res;
    }


}
