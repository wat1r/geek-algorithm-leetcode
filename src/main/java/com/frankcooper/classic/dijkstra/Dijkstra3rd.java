package com.frankcooper.classic.dijkstra;

import com.alibaba.fastjson.JSON;

import java.util.*;

/**
 * Created by FrankCooper
 * Date 2020/9/20 10:37
 * Description
 */
public class Dijkstra3rd {


    static Dijkstra3rd handler = new Dijkstra3rd();

    public static void main(String[] args) {
        handler.testOne();
    }


    private void testOne() {
        int n = 6;//顶点数量
        int s = 0;//起点的下标索引
        int e = 8;//边的数量


        int[][] edges = new int[e][3];
        edges[0] = new int[]{0, 2, 10};
        edges[1] = new int[]{0, 4, 30};
        edges[2] = new int[]{0, 5, 100};
        edges[3] = new int[]{1, 2, 5};
        edges[4] = new int[]{2, 3, 50};
        edges[5] = new int[]{3, 5, 10};
        edges[6] = new int[]{4, 3, 20};
        edges[7] = new int[]{4, 5, 60};
        System.out.println(JSON.toJSONString(edges));
        dijkstra(edges, s, n);

    }


    /**
     * @param edges 传入的边
     * @param s     起始顶点
     * @param n
     * @return
     */
    public int[] dijkstra(int[][] edges, int s, int n) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] edge : edges)
            graph.computeIfAbsent(edge[0], e -> new ArrayList<>()).add(new int[]{edge[1], edge[2]});
        int[] dis = new int[n];
        Arrays.fill(dis, Integer.MAX_VALUE);
        boolean[] vis = new boolean[n];
        dis[s] = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(((o1, o2) -> dis[o1] - dis[o2]));
        pq.offer(s);
        while (!pq.isEmpty()) {
            int curr = pq.poll();
            if (vis[curr]) continue;
            vis[curr] = true;
            List<int[]> nexts = graph.getOrDefault(curr, new ArrayList<>());
            for (int[] next : nexts) {
                int to = next[0];
                int weigh = next[1];
                if (vis[to]) continue;
                if (dis[to] > dis[curr] + weigh) {
                    dis[to] = dis[curr] + weigh;
                }
                pq.offer(to);
            }
        }
        for (int d : dis) System.out.print(d + " ");
        return dis;
    }


}
