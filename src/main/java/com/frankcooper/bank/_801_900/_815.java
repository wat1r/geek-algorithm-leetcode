package com.frankcooper.bank._801_900;

import java.util.*;

public class _815 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[][] routes = {{1, 2, 7}, {3, 6, 7}};
            int source = 1, target = 6;
            handler.numBusesToDestination(routes, source, target);

        }



        //抽象每个「路线」为一个点，当不同「路线」之间存在「公共车站」则为其增加一条边权为 1 的无向边。


        public int numBusesToDestination(int[][] routes, int source, int target) {
            if (source == target) return 0;
            int n = routes.length;
            Map<Integer, List<Integer>> rec = new HashMap<>();//k是公交站点的编号，v是公交路线的编号，即routes数组的编号
            boolean[][] edge = new boolean[n][n];//公交路线是否可以联通, 两条公交路线有公共的站点
            for (int i = 0; i < n; i++) {
                for (int site : routes[i]) {
                    List<Integer> list = rec.getOrDefault(site, new ArrayList<>());
                    for (int j : list) {
                        edge[i][j] = true;
                        edge[j][i] = true;
                    }
                    list.add(i);
                    rec.put(site, list);

                }
            }
            int[] dis = new int[n];
            Arrays.fill(dis, -1);
            Queue<Integer> q = new LinkedList<>();
            //将「起点车站」所能直接进入的「路线」入队，查询路线的可达「路线」 查询「终点车站」是否包含在「路线」
            for (int site : rec.getOrDefault(source, new ArrayList<>())) {
                dis[site] = 1;
                q.offer(site);
            }
            while (!q.isEmpty()) {
                int u = q.poll();
                for (int v = 0; v < n; v++) {
                    if (edge[u][v] && dis[v] == -1) {
                        dis[v] = dis[u] + 1;
                        q.offer(v);
                    }
                }
            }
            int res = Integer.MAX_VALUE;
            for (int site : rec.getOrDefault(target, new ArrayList<>())) {
                if (dis[site] != -1) res = Math.min(res, dis[site]);
            }
            return res == Integer.MAX_VALUE ? -1 : res;

        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }






    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
