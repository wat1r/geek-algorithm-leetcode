package com.frankcooper.platform.acwing;

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


        private void process() {
            Scanner in = new Scanner(System.in);
            String a = in.next(), b = in.next();
            String aa = getMin(a), bb = getMin(b);
            if (aa.equals(bb)) {
                System.out.println("Yes");
                System.out.println(aa);
            } else {
                System.out.println("No");
            }

        }

        public String getMin(String s) {
            int n = s.length();
            s = s + s;
            char[] ch = s.toCharArray();
            int i = 0, j = 1;
            while (i < n && j < n) {
                int k = 0;
                while (k < n && ch[i + k] == ch[j + k]) k++;
                if (ch[i + k] > ch[j + k]) {
                    i += k + 1;
                } else {
                    j += k + 1;
                }
                if (i == j) j++;
            }
            int t = Math.min(i, j);
            return s.substring(t, t + n);
        }


    }


}
