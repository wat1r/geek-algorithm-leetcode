package com.frankcooper.classic.floyd;

import com.frankcooper.swordoffer.utils.PrintUtils;

public class Floyd2nd {


    class _2nd {
//        public void testTwo() {
//            init();
//            Scanner sc = new Scanner(System.in);
//            n = sc.nextInt();//顶点数量
//            m = sc.nextInt();//边的条数
//            int u, v, w;
//            while (m-- > 0) {
//                u = sc.nextInt();//from
//                v = sc.nextInt();//to
//                w = sc.nextInt();//weigh
//                G[u][v] = G[v][u] = w;
//            }
////            floyd();
//            System.out.println(G[1][n]);
//        }


//    strictfp

//        static void floyd() {
//            for (int k = 1; k <= n; k++) {
//                for (int i = 1; i <= n; i++) {
//                    for (int j = 1; j <= n; j++) {
//                        G[i][j] = Math.min(G[i][j], G[i][k] + G[k][j]);
//                    }
//                }
//            }
//        }
    }


    static class _1st {

        class Edge {
            private int u;
            private int v;
            private int w;

            public Edge(int u, int v, int w) {
                this.u = u;
                this.v = v;
                this.w = w;
            }
        }


        int N = 4;//顶点的个数
        int E = 8;//边的条数
        int[][] G = new int[N][N];//顶点之间最短距离的矩阵

        int[][] path = new int[N][N];
        int[][] dist = new int[N][N];

        Integer INF = Integer.MAX_VALUE;


        void init() {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (i != j) G[i][j] = INF;
                    path[i][j] = -1;
                }
            }
        }

        static _1st first = new _1st();

        public static void main(String[] args) {
            first.testOne();
        }


        public void testOne() {
            init();
            initEdge();
            PrintUtils.printMatrix(G, 10);
            PrintUtils.printMatrix(path, 1);
            floyd(path, dist);
            printHelper();
        }

        private void printHelper() {
            // 打印floyd最短路径的结果
            System.out.printf("floyd dist: \n");
            PrintUtils.printMatrix(dist, 2);
            System.out.printf("floyd path: \n");
            PrintUtils.printMatrix(path, 2);
            int u = 3, v = 0;
            System.out.printf("%d--->", u);
            printPath(path, u, v);
            System.out.printf("%d\n", v);
        }


        private void printPath(int[][] path, int u, int v) {
            if (path[u][v] == -1) {
                return;
            }
            int mid = path[u][v];
            System.out.printf("%d--->", mid);
            printPath(path, u, mid);
            printPath(path, mid, v);

        }

        private void initEdge() {
            Edge[] edges = new Edge[E];
            edges[0] = new Edge(0, 1, 5);
            edges[1] = new Edge(0, 3, 7);
            edges[2] = new Edge(1, 2, 4);
            edges[3] = new Edge(1, 3, 2);
            edges[4] = new Edge(2, 0, 3);
            edges[5] = new Edge(2, 1, 3);
            edges[6] = new Edge(2, 3, 2);
            edges[7] = new Edge(3, 2, 1);
            for (int i = 0; i < E; i++) {
                G[edges[i].u][edges[i].v] = edges[i].w;
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
        public void floyd(int[][] path, int[][] dist) {
            // 初始化  N // 顶点集合
            //G 邻接矩阵 i =j 时为0 初始化时， 没有路径时是INF 其他位置是u->v的权值
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    dist[i][j] = G[i][j];    // "顶点i"到"顶点j"的路径长度为"i到j的权值"。
//                    if (G[i][j] != INF && G[i][j] != 0) path[i][j] = j; // "顶点i"到"顶点j"的最短路径是经过顶点j。
                }
            }
            // 计算最短路径
            for (int k = 0; k < N; k++) {
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        // 如果经过下标为k顶点路径比原两点间路径更短，则更新dist[i][j]和path[i][j]
                        int tmp = (dist[i][k] == INF || dist[k][j] == INF) ? INF : (dist[i][k] + dist[k][j]);
                        if (dist[i][j] > tmp) {
                            // "i到j最短路径"对应的值设，为更小的一个(即经过k)
                            dist[i][j] = tmp;
                            // "i到j最短路径"对应的路径，经过k
                            path[i][j] = k;
                        }
                    }
                }
            }
        }


    }


}
