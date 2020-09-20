package com.frankcooper.bank;

import java.util.*;

/**
 * Created by FrankCooper
 * Date 2020/9/20 10:06
 * Description
 */
public class _743 {


    /**
     * Dijkstra
     */
    class _1st {


        public int networkDelayTime(int[][] times, int N, int K) {

            Map<Integer, List<int[]>> graph = new HashMap<>();
            //初始化邻接表
            for (int[] time : times) {
                graph.computeIfAbsent(time[0], t -> new ArrayList<>()).add(new int[]{time[1], time[2]});
            }
            int[] dis = new int[N + 1];
            Arrays.fill(dis, Integer.MAX_VALUE);
            boolean[] vis = new boolean[N + 1];
            dis[K] = 0;
            dis[0] = 0;
            PriorityQueue<Integer> pq = new PriorityQueue<>(((o1, o2) -> dis[o1] - dis[o2]));
            pq.offer(K);
            while (!pq.isEmpty()) {
                Integer curr = pq.poll();
                if (vis[curr]) continue;
                vis[curr] = true;
                List<int[]> neighbours = graph.getOrDefault(curr, new ArrayList<>());
                for (int[] neighbour : neighbours) {
                    int to = neighbour[0];
                    if (vis[to]) continue;
                    dis[to] = Math.min(dis[to], dis[curr] + neighbour[1]);
                    pq.offer(to);
                }
            }
            for (int d : dis) System.out.print(d + " ");
            int max = 0;
            for (int d : dis) max = Math.max(max, d);
            return max == Integer.MAX_VALUE ? -1 : max;
        }

    }


}
