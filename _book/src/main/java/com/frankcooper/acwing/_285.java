package com.frankcooper.acwing;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;
import java.util.Scanner;

public class _285 {


    static _285 handler = new _285();


    public static void main(String[] args) {

    }


    static class Adj {
        static Adj adj = new Adj();

        public static void main(String[] args) {

        }

        /**
         * cnt_edge是给每一个边标号，从0开始。
         * edges[i].v 表示第i条边指向哪个点，edge[i].next表示第i条边的下一条边的序号。
         * head[u]表示以第u为初始结点的边的序号
         */
        class Edge {
            int v;
            int w;
            int next;
        }

        int N = 10;
        Edge[] edges = new Edge[2 * N];
        int cnt_edge = 0;
        int[] head = new int[N];

        public void add(int uu, int vv, int ww) {
            edges[cnt_edge].v = vv;
            edges[cnt_edge].w = ww;
            edges[cnt_edge].next = head[cnt_edge];
            head[uu] = cnt_edge++;
        }

    }


    static class Adj1 {

        static Adj1 adj = new Adj1();

        public static void main(String[] args) {
            adj.process();
        }


        int N = 5;
        int idx = 0;
        int[] head = new int[6]; // 存储的是顶点的下标
        int[] edge = new int[6];
        int[] next = new int[6]; //顶点的下标


        public void add(int u, int v) {
            edge[idx] = v;
            next[idx] = head[u];
            head[u] = idx++;
        }


        public void process() {
            Arrays.fill(head, -1);
            addEdge();
            print();
        }

        private void print() {
            for (int i = 1; i <= N; i++) {
                System.out.printf("%d\n", i);
                for (int j = head[i]; j != -1; j = next[j]) {
                    System.out.printf("--> %d\n", edge[j]);
                }
            }
        }

        private void addEdge() {
            add(1, 2);
            add(2, 3);
            add(2, 5);
            add(3, 5);
            add(5, 1);
            add(5, 4);
        }


    }


    static class Main {

        static Main main = new Main();

        public static void main(String[] args) {
            main.process();
        }


        //        int N = 6010; //N名员工
        int n = 8; //N名员工
        int[] happy = new int[n]; //员工的快乐指数 H
        int[] head = new int[n]; //
        int[] edge = new int[n];//边
        int[] next = new int[n];//
        int idx = 0;
        int[][] f = new int[n][2];
        boolean[] hasFather = new boolean[n];

        private void process() {
            Scanner in = new Scanner(System.in);
            n = in.nextInt();
            for (int i = 1; i <= n; i++) happy[i] = in.nextInt();
            Arrays.fill(head, -1);
            for (int i = 0; i < n - 1; i++) {
                int a = in.nextInt(), b = in.nextInt();
                add(b, a);
                hasFather[a] = true;
            }
            int root = 1;
            while (hasFather[root]) root++;
            dfs(root);
            System.out.println(Math.max(f[root][0], f[root][1]));
        }

        private void dfs(int u) {
            f[u][1] = happy[u];
            for (int i = head[u]; i != -1; i = next[i]) {
                int j = edge[i];
                dfs(j);
                f[u][1] += f[j][0];
                f[u][0] += Math.max(f[j][1], f[j][0]);
            }


        }

        /**
         * @param a father
         * @param b son
         */
        private void add(int a, int b) {
            edge[idx] = b;
            next[idx] = head[a];
            head[a] = idx++;
        }

    }


}
