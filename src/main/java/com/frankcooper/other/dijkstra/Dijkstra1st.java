package com.frankcooper.other.dijkstra;

import com.frankcooper.swordoffer.utils.PrintUtils;

/**
 * Created by FrankCooper
 * Date 2020/9/19 22:17
 * Description
 */
public class Dijkstra1st {


    static Dijkstra1st handler = new Dijkstra1st();

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


        /**
         * 基于玲姐矩阵的实现
         */


        private int[][] edges;


        public _1st(int[][] edges) {
            this.edges = edges;
        }

        //https://blog.csdn.net/qq_38410730/article/details/79587768

        /**
         * @param v 起始点
         * @param n 顶点的个数
         * @return 最短路径
         */
        public int[] dijkstra(int v, int n) {
            boolean[] st = new boolean[n];//顶点是否被访问的标记
            int[] distance = new int[n];//最短距离
//            int[][] edges = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (edges[i][j] == 0) {
                        edges[i][j] = Integer.MAX_VALUE;
                    }
                    if (edges[j][i] == 0) {
                        edges[j][i] = Integer.MAX_VALUE;
                    }
                }
            }
            PrintUtils.printMatrix(edges, 10);
            for (int i = 0; i < n; i++) {
                distance[i] = edges[v][i];
            }
            st[v] = true;
            //处理源点到其余顶点的最短路径
            for (int i = 0; i < n; i++) {
                int min = Integer.MAX_VALUE;
                int index = -1;
                //比较源点到其余各点的路径长度，找出最小路径已经最小路径对应的下标
                for (int j = 0; j < n; j++) {
                    if (!st[j]) {
                        //小于的时候，证明该点要可达，才进入下面的轮转
                        if (distance[j] < min) {
                            index = j;
                            min = distance[j];
                        }
                    }
                }
                //快要结束时，找不到index的
                if (index != -1) {
                    st[index] = true;
                    for (int w = 0; w < n; w++) {
                        if (!st[w]) {
                            //第一个条件是判断当前的u->v是否是可达的，初始化时标注了MAX为不可达
                            //第二个条件是min+u->v的距离要更短，则更新之
                            if (edges[index][w] != Integer.MAX_VALUE && (min + edges[index][w] < distance[w])) {
                                distance[w] = min + edges[index][w];
                            }
                        }
                    }
                }

            }
            for (int i : distance) System.out.print(i + " ");
            return distance;

        }

    }


    class _2nd{




    }

}
