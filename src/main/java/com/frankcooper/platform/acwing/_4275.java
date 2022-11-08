package com.frankcooper.platform.acwing;

import java.util.*;

import org.junit.Assert;

public class _4275 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }


        static int N = 1010, INF = Integer.MAX_VALUE;
        static int n, m;
        static int[] dist = new int[N];//距离
        static int[][] g = new int[N][N];//点之间的权值
        static int[] q = new int[N];//队列
        static boolean[] st = new boolean[N];

        private static void process() {
            //初始化
            for (int i = 0; i < N; i++) {
                Arrays.fill(g[i], INF);
            }
            Scanner sc = new Scanner(System.in);
            n = sc.nextInt();
            m = sc.nextInt();
            while (m-- > 0) {
                int a = sc.nextInt(), b = sc.nextInt(), c = sc.nextInt();
                g[a][b] = c;
                g[b][a] = c;
            }
            int k = sc.nextInt();
            while (k-- > 0) {
                for (int i = 0; i < n; i++) {
                    q[i] = sc.nextInt();
                }
                if (dijkstra()) {
                    System.out.println("Yes");
                } else {
                    System.out.println("No");
                }
            }

        }

        //判断是否是Dijkstra序列
        private static boolean dijkstra() {
            Arrays.fill(dist, INF);
            Arrays.fill(st, false);
            dist[q[0]] = 0;
            for (int i = 0; i < n; i++) {
                int t = q[i];
                for (int j = 1; j <= n; j++) {
                    if (!st[j] && dist[j] < dist[t]) {
                        return false;
                    }
                }
                st[t] = true;
                for (int j = 1; j <= n; j++) {
                    dist[j] = Math.min(dist[j], dist[t] + g[t][j]);
                }
            }
            return true;
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
