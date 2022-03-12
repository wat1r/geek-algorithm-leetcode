package com.frankcooper.mixed.skill;

import java.util.Arrays;

public class AdjStruct {


    public static class Adj {
        static int V = 10;
        static int num = 0;//编号计数器
        static int head[] = new int[V];//当前边的编号
        static int to[] = new int[V << 1];//当前边的终点
        static int W[] = new int[V << 1];//当前边的权值
        static int next[] = new int[V << 1];//下一条边的编号


        static void add(int v1, int v2, int w) {
            ++num;
            next[num] = head[v1];
            to[num] = v2;
            W[num] = w;//
            head[v1] = num;//保存当前边的编号
        }

        static int n, m;

        static void print() {
            //邻接表访问
            for (int i = 1; i <= n; i++) {
                for (int j = head[i]; j != 0; j = next[j]) {
                    System.out.printf("%d, %d , %d \n", i, to[j], W[j]);
                }
            }
        }


        public static void main(String[] args) {
            Adj handler = new Adj();
            n = 4;
            m = 6;
//            Arrays.fill(head, -1);
            //添加带权值的边
            add(1, 2, 10);
            add(1, 3, 7);
            add(3, 4, 9);
            add(2, 3, 8);
            add(2, 4, 12);
            add(1, 4, 11);
            // 打印
            print();
        }
    }
}
