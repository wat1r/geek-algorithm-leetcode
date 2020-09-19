package com.frankcooper.other.dijkstra;

import com.frankcooper.swordoffer.utils.PrintUtils;

/**
 * Created by FrankCooper
 * Date 2020/9/19 22:17
 * Description
 */
public class Dijkstra {


    static Dijkstra handler = new Dijkstra();

    public static void main(String[] args) {
        handler.testOne();
    }

    private void testOne() {
        int n = 6;
        int v = 0;

        int[][] edges = new int[n][n];
        edges[0][2] = 10;
        edges[0][4] = 30;
        edges[0][5] = 100;
        edges[1][2] = 5;
        edges[2][3] = 50;
        edges[3][5] = 10;
        edges[4][3] = 20;
        edges[4][5] = 60;
        _1st first = new _1st(edges);
        first.dijkstra(v, n);
    }


    class _1st {


        private int[][] edges;


        public _1st(int[][] edges) {
            this.edges = edges;
        }

        /**
         * @param v 起始点
         * @param n 顶点的个数
         * @return 最短路径
         */
        public int[] dijkstra(int v, int n) {
            boolean[] st = new boolean[n];//顶点是否被访问的标记
            int[] dis = new int[n];//最短距离
//            int[][] edges = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (edges[i][j] == 0) {
                        edges[i][j] = Integer.MAX_VALUE;
                        edges[j][i] = Integer.MAX_VALUE;
                    }
                }
            }
            PrintUtils.printMatrix(edges, 10);
            for (int i = 0; i < n; i++) {
                dis[i] = edges[v][i];
            }
            st[v] = true;
            //处理源点到其余顶点的最短路径
            for (int i = 0; i < n; i++) {
                int min = Integer.MAX_VALUE;
                int index = -1;
                for (int j = 0; j < n; j++) {
                    if (!st[j]) {
                        if (dis[j] < min) {
                            index = j;
                            min = dis[j];
                        }
                    }
                }
                if (index != -1) {
                    st[index] = true;
                    for (int w = 0; w < n; w++) {
                        if (!st[w]) {
                            if (edges[index][w] != Integer.MAX_VALUE && (min + edges[index][w] < dis[w])) {
                                dis[w] = min + edges[index][w];
                            }
                        }
                    }
                }

            }
            return dis;

        }


    }


}
