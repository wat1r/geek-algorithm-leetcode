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
            //多设置了0这个索引的冗余，距离设置为0
            dis[K] = 0;
            dis[0] = 0;
            //优先队列，按距离从小到大排列
            PriorityQueue<Integer> pq = new PriorityQueue<>(((o1, o2) -> dis[o1] - dis[o2]));
            pq.offer(K);
            while (!pq.isEmpty()) {
                //弹出
                Integer curr = pq.poll();
                //访问过不用再执行下面的逻辑
                if (vis[curr]) continue;
                //标记访问
                vis[curr] = true;
                //获取到当前节点的邻居节点
                List<int[]> neighbours = graph.getOrDefault(curr, new ArrayList<>());
                for (int[] neighbour : neighbours) {
                    int to = neighbour[0];
                    if (vis[to]) continue;
                    //如果当前dis[to]大，则更新之
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
