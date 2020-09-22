package com.frankcooper.classic.floyd;

import java.util.Map;
import java.util.Scanner;

public class Floyd2nd {





















    static class _1st {

        static int N = 1005;
        static int[][] G = new int[N][N];


        static int n, m;

        static void init() {
            for (int i = 1; i < N; i++) {
                for (int j = 1; j < N; j++) {
                    G[i][j] = 0x3f3f3f3f;
                }
            }
        }


        public static void main(String[] args) {
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

    }


}
