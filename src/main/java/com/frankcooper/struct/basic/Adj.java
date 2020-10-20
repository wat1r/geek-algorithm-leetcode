package com.frankcooper.struct.basic;

import java.util.Arrays;

public class Adj {


    /**
     * 数组表示无权值有向图的邻接表
     */
    static class Adj1 {

        static Adj1 handler = new Adj1();

        public static void main(String[] args) {
            handler.process();
        }

        int E = 6;// 边的总数量，当图为无向图时，一条边的两个端点建立邻接表时，均会记录该边，一条边会被记录两次
        int V = 5; //顶点的总数量
        int idx = 0; //标记当前边的编号
        int[] head = new int[V << 1]; //每个顶点其中一条边的编号
        Edge[] edge = new Edge[E << 1]; //边的数量


        private void process() {
            init();
            print();
        }


        private void init() {
            Arrays.fill(head, -1);
//            Arrays.fill(edge, new Edge(0, 0));
            for (int i = 0; i < (E << 1); i++) {
                edge[i] = new Edge();
                edge[i].to = 0;
                edge[i].next = 0;
            }
            addEdge(1, 2);
            addEdge(2, 3);
            addEdge(2, 5);
            addEdge(3, 5);
            addEdge(5, 1);
            addEdge(5, 4);
        }


        class Edge {
            int to; // 边所指向的点
            int next; //下一条边的编号

            public Edge() {
            }

            public Edge(int to, int next) {
                this.to = to;
                this.next = next;
            }
        }

        //u是起点，v是一条边的终点，添加一条边
        private void addEdge(int u, int v) {
            edge[++idx].to = v;
            edge[idx].next = head[u]; //在u顶点的邻接表这个栈的顶部加入一条边（头插法）
            head[u] = idx;//top为加入边的编号，加入后要更新head，使得head记录邻接表栈顶边的编号
        }

        private void print() {
            for (int i = 1; i <= V; i++) {//遍历所有顶点
                System.out.printf("%d\n", i);
                for (int j = head[i]; j != -1; j = edge[j].next) {
                    System.out.printf("--> %d\n", edge[j].to);
                }
            }
        }


    }


}
