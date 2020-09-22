package com.frankcooper.classic.floyd;

import com.frankcooper.swordoffer.utils.PrintUtils;

import java.util.Scanner;

public class Floyd2nd {


    static class _1st {

        static int N = 1005;
        static int[][] G = new int[N][N];


        static int n, m;

        Integer INF = 0x3f3f3f3f;
        int[][] dist;

        void init() {
            for (int i = 1; i < N; i++) {
                for (int j = 1; j < N; j++) {
                    if (i != j) G[i][j] = INF;
                }
            }
        }

        static _1st first = new _1st();

        public static void main(String[] args) {
            first.testOne();
        }


        public void testOne() {
            init();
//            PrintUtils.printMatrix();

        }


        public void testTwo() {
            init();
            Scanner sc = new Scanner(System.in);
            n = sc.nextInt();//顶点数量
            m = sc.nextInt();//边的条数
            int u, v, w;
            while (m-- > 0) {
                u = sc.nextInt();//from
                v = sc.nextInt();//to
                w = sc.nextInt();//weigh
                G[u][v] = G[v][u] = w;
            }
            floyd();
            System.out.println(G[1][n]);
        }


//    strictfp

        static void floyd() {
            for (int k = 1; k <= n; k++) {
                for (int i = 1; i <= n; i++) {
                    for (int j = 1; j <= n; j++) {
                        G[i][j] = Math.min(G[i][j], G[i][k] + G[k][j]);
                    }
                }
            }
        }





        /*
         * floyd最短路径。
         * 即，统计图中各个顶点间的最短路径。
         *
         * 参数说明：
         *     path -- 路径。path[i][j]=k表示，"顶点i"到"顶点j"的最短路径会经过顶点k。
         *     dist -- 长度数组。即，dist[i][j]=sum表示，"顶点i"到"顶点j"的最短路径的长度是sum。
         */
      /*  public void floyd(int[][] path, int[][] dist) {

            // 初始化  mVexs // 顶点集合
            //mMatrix 邻接矩阵 i =j 时为0 初始化时， 没有路径时是INF 其他位置是u->v的权值
            for (int i = 0; i < mVexs.length; i++) {
                for (int j = 0; j < mVexs.length; j++) {
                    dist[i][j] = mMatrix[i][j];    // "顶点i"到"顶点j"的路径长度为"i到j的权值"。
                    path[i][j] = j;                // "顶点i"到"顶点j"的最短路径是经过顶点j。
                }
            }

            // 计算最短路径
            for (int k = 0; k < mVexs.length; k++) {
                for (int i = 0; i < mVexs.length; i++) {
                    for (int j = 0; j < mVexs.length; j++) {
                        // 如果经过下标为k顶点路径比原两点间路径更短，则更新dist[i][j]和path[i][j]
                        int tmp = (dist[i][k] == INF || dist[k][j] == INF) ? INF : (dist[i][k] + dist[k][j]);
                        if (dist[i][j] > tmp) {
                            // "i到j最短路径"对应的值设，为更小的一个(即经过k)
                            dist[i][j] = tmp;
                            // "i到j最短路径"对应的路径，经过k
                            path[i][j] = path[i][k];
                        }
                    }
                }
            }

            // 打印floyd最短路径的结果
            System.out.printf("floyd: \n");
            for (int i = 0; i < mVexs.length; i++) {
                for (int j = 0; j < mVexs.length; j++)
                    System.out.printf("%2d  ", dist[i][j]);
                System.out.printf("\n");
            }
        }*/


    }


}
