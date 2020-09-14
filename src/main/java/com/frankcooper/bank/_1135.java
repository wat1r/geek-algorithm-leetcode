package com.frankcooper.bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @Date 2020/9/14
 * @Author Frank Cooper
 * @Description
 */
public class _1135 {


//    TODO Kruskal实现
    //https://www.cnblogs.com/xym4869/p/12874684.html




    /**
     * @param N           顶点的数量
     * @param connections 顶点与边之间的信息
     * @return
     */
    public int minimumCost(int N, int[][] connections) {
        if (N <= 1 || connections == null || connections.length < N - 1) return -1;
        Map<Integer, ArrayList<int[]>> graph = new HashMap<>();
        //构造图
        for (int[] connect : connections) {
            int start = connect[0];
            int end = connect[1];
            int weigh = connect[2];
            graph.putIfAbsent(start, new ArrayList<>());
            graph.get(start).add(new int[]{end, weigh});
            graph.putIfAbsent(end, new ArrayList<>());
            graph.get(end).add(new int[]{start, weigh});
        }
        //选取第一个为端点
        boolean[] visited = new boolean[N];
        int start = connections[0][0];
        //下标索引从0开始，需要-1
        visited[start - 1] = true;
        PriorityQueue<int[]> pq = new PriorityQueue<>(((o1, o2) -> o1[1] - o2[1]));
        pq.addAll(graph.get(start));
        int minCost = 1;
        int count = 1;
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            //该顶点被访问过，跳过，找下一个顶点
            if (visited[curr[0] - 1]) continue;
            pq.addAll(graph.get(curr[0]));
            //更新最小生成树权值，以及访问顶点数量
            minCost += curr[1];
            count++;
            //当端点被访问结束，即可退出
            if (count == N) return minCost;
        }
        return -1;
    }


}
