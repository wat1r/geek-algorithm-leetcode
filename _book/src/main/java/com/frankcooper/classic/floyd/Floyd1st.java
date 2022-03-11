package com.frankcooper.classic.floyd;


import java.io.IOException;
import java.util.Scanner;

/**
 * https://www.cnblogs.com/skywang12345/p/3711532.html?utm_source=tuicool&utm_medium=referral
 */

public class Floyd1st {

    public class MatrixUDG {

        private int mEdgNum;        // 边的数量
        private char[] mVexs;       // 顶点集合
        private int[][] mMatrix;    // 邻接矩阵
        private static final int INF = Integer.MAX_VALUE;   // 最大值


        public MatrixUDG() {

            // 输入"顶点数"和"边数"
            System.out.printf("input vertex number: ");
            int vlen = readInt();
            System.out.printf("input edge number: ");
            int elen = readInt();
            if (vlen < 1 || elen < 1 || (elen > (vlen * (vlen - 1)))) {
                System.out.printf("input error: invalid parameters!\n");
                return;
            }

            // 初始化"顶点"
            mVexs = new char[vlen];
            for (int i = 0; i < mVexs.length; i++) {
                System.out.printf("vertex(%d): ", i);
                mVexs[i] = readChar();
            }

            // 1. 初始化"边"的权值
            mEdgNum = elen;
            mMatrix = new int[vlen][vlen];
            for (int i = 0; i < vlen; i++) {
                for (int j = 0; j < vlen; j++) {
                    if (i == j)
                        mMatrix[i][j] = 0;
                    else
                        mMatrix[i][j] = INF;
                }
            }
            // 2. 初始化"边"的权值: 根据用户的输入进行初始化
            for (int i = 0; i < elen; i++) {
                // 读取边的起始顶点,结束顶点,权值
                System.out.printf("edge(%d):", i);
                char c1 = readChar();       // 读取"起始顶点"
                char c2 = readChar();       // 读取"结束顶点"
                int weight = readInt();     // 读取"权值"

                int p1 = getPosition(c1);
                int p2 = getPosition(c2);
                if (p1 == -1 || p2 == -1) {
                    System.out.printf("input error: invalid edge!\n");
                    return;
                }

                mMatrix[p1][p2] = weight;
                mMatrix[p2][p1] = weight;
            }


        }


        /*
         * 返回ch位置
         */
        private int getPosition(char ch) {
            for (int i = 0; i < mVexs.length; i++)
                if (mVexs[i] == ch)
                    return i;
            return -1;
        }


        /*
         * 读取一个输入字符
         */
        private int readInt() {
            Scanner scanner = new Scanner(System.in);
            return scanner.nextInt();
        }


        /*
         * 读取一个输入字符
         */
        private char readChar() {
            char ch = '0';
            do {
                try {
                    ch = (char) System.in.read();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } while (!((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')));

            return ch;
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
        }
    }

    int[][] path;

    /**
     * 递归求i-->j的路径
     *
     * @param i 起点
     * @param j 终点
     */
    public void findPath(int i, int j) {
        int k = path[i][j];
        if (k == -1) return; //i->j没有直达路径的时候，为-1
        findPath(i, k);//i->k
        System.out.printf("%d ", k);
        findPath(k, j);//k->j
    }


}
