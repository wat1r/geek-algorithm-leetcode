package com.frankcooper.bank;

import com.frankcooper.swordoffer.utils.PrintUtils;

import java.util.*;

public class _5538 {

    static _5538 handler = new _5538();

    public static void main(String[] args) {
        handler.testOne();
    }


    private void testOne() {

        _1st first = new _1st();
        _2nd second = new _2nd();
        int n = 4;
        int[][] edges = {{1, 2}, {2, 3}, {2, 4}};
        first.countSubgraphsForEachDiameter(n, edges);
//        second.countSubgraphsForEachDiameter(n, edges);

    }


    class _2nd {

        //https://leetcode-cn.com/problems/count-subtrees-with-max-distance-between-cities/solution/5538-java-6msfloyedbfszhuang-ya-by-zhangyixing/
        Integer INF = Integer.MAX_VALUE;
        public int[] countSubgraphsForEachDiameter(int n, int[][] edges) {
            int[][] dist = new int[n][n];
            for (int i = 0; i < n; i++) {
                Arrays.fill(dist[i], INF);
                dist[i][i] = 0;
            }
            //状态压缩存储 dp[j]表示子树j的最大距离
            int[] dp = new int[1 << n];
            for (int[] edge : edges) {
                int s = edge[0] - 1, e = edge[1] - 1;
                dist[s][e] = 1;
                dist[e][s] = 1;
                dp[(1 << s) + (1 << e)] = 1;
            }
            for (int k = 0; k < n; k++) {
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if (dist[i][k] != INF && dist[k][j] != INF) {
                            dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                        }
                    }
                }
            }
            for (int j = 1; j < dp.length; j++) {
                if (dp[j] == 0) continue;
                for (int i = 0; i < n; i++) {
                    if (((1 << i) & j) != 0 || dp[j + (1 << i)] != 0) continue;
                    for (int k = 0; k < n; k++) {
                        if (((1 << k) & j) != 0 && dist[i][k] == 1) {
                            dp[j + (1 << i)] = dp[i];
                            break;
                        }
                    }
                    if (dp[(j + (1 << i))] == 0) continue;
                    for (int kk = 0; kk < n; kk++) {
                        if (((1 << kk) & j) != 0) {
                            dp[j + (1 << i)] = Math.max(dp[j + (1 << i)], dist[i][kk]);
                        }
                    }
                }
            }
            // 统计结果 由于下标从1开始
            // ans[0]其实记录的是最大距离为1的子树有多少棵 统计时要-1
            int[] ans = new int[n - 1];
            for (int j = 0; j < dp.length; j++) {
                if (dp[j] != 0) ans[dp[j] - 1]++;
            }
            return ans;
        }


    }


    class _1st {
        int[][] dis;
        int n;
        int[][] edges;


        public int[] countSubgraphsForEachDiameter(int n, int[][] edges) {

            this.n = n;
            this.edges = edges;
            initDis();
            calDis();
            PrintUtils.printMatrix(dis);
            int[] res = new int[n - 1];
            int state = 1 << n;
            for (int i = 3; i < state; i++) {
                int d = check(i);
                if (d < 1) continue;
                res[d - 1]++;
            }
            return res;
        }


        //初始化dis
        private void initDis() {
            dis = new int[n + 1][n + 1];
            for (int i = 1; i <= n; i++) Arrays.fill(dis[i], -1);
            for (int[] edge : edges) {
                dis[edge[0]][edge[1]] = 1;
                dis[edge[1]][edge[0]] = 1;
            }
        }


        //计算任意两点间的距离
        private void calDis() {
            for (int i = 1; i <= n; i++) {
                //int[] 0 表示点，1表示距离
                Queue<int[]> queue = new LinkedList<>();
                queue.offer(new int[]{i, 0});
                boolean[] vis = new boolean[n + 1];
                while (!queue.isEmpty()) {
                    int[] curr = queue.poll();
                    vis[curr[0]] = true;
                    dis[curr[0]][i] = dis[i][curr[0]] = curr[1];
                    for (int j = 1; j <= n; j++) {
                        if (dis[curr[0]][j] == 1 && !vis[j]) {
                            queue.offer(new int[]{j, curr[1] + 1});
                        }
                    }
                }
            }
        }

        //检查子树是否合法，如果不合法返回-1， 否则返回子树最大距离
        private int check(int state) {
            List<Integer> list = new ArrayList<>();
            int i = 0;
            while (i < dis.length - 1) {
                int tmp = (state & (1 << i));
                if (tmp > 0) {
                    list.add(i + 1);
                }
                i++;
            }
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(list.get(0));
            int cnt = 0;
            Set<Integer> set = new HashSet<>();
            while (!queue.isEmpty()) {
                int curr = queue.poll();
                if (set.contains(curr)) {
                    continue;
                }
                set.add(curr);
                cnt++;
                for (i = 0; i < list.size(); i++) {
                    if (dis[list.get(i)][curr] == 1) {
                        queue.offer(list.get(i));
                    }
                }
            }
            if (cnt != list.size()) return -1;
            int max = 0;
            for (i = 0; i < list.size(); i++) {
                int x = list.get(i);
                for (int j = i + 1; j < list.size(); j++) {
                    int y = list.get(j);
                    if (dis[x][y] == -1) return -1;
                    max = Math.max(max, dis[x][y]);
                }
            }
            return max;
        }


    }


}
