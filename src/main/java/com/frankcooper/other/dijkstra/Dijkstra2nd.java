package com.frankcooper.other.dijkstra;

import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * Created by FrankCooper
 * Date 2020/9/20 7:51
 * Description 优先队列的实现版本
 */
public class Dijkstra2nd {

    static class Edge {
        public int v, w, next;

        public Edge(int v, int w, int next) {
            this.v = v;//to
            this.w = w;//权重
            this.next = next;
        }
    }

    static class Node implements Comparable<Node> {
        public int v, c;

        public Node(int v, int c) {
            this.v = v;
            this.c = c;
        }

        @Override
        public int compareTo(Node o) {
            if (c > o.c) {
                return 1;
            } else if (c == o.c) {
                return 0;
            } else {
                return -1;
            }
        }
    }

    static final int N = 1050;
    static final int M = 200050;
    static final int INF = 0x3f3f3f3f;
    static int[] dis = new int[N];
    static Edge[] edges = new Edge[M];
    static int cnt = 0;
    static int[] head = new int[N];
    static boolean[] vis = new boolean[N];
    static int n, m, s, t;
    static int u, v, w;

    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        while (scanner.hasNextInt()) {
//            n = scanner.nextInt();//顶点的个数
//            m = scanner.nextInt();//边的条数
        n = 6;
        m = 8;
        int[][] arr = new int[n + 1][n + 1];
        arr[1][3] = 10;
        arr[1][5] = 30;
        arr[1][6] = 100;
        arr[2][3] = 5;
        arr[3][4] = 50;
        arr[4][6] = 10;
        arr[5][4] = 20;
        arr[5][6] = 60;
        init();
//        if (n == 0 && m == 0) {
//            break;
//        }
//            for (int i = 0; i < m; i++) {
        for (int i = 1; i <= n; i++) {
//                u = scanner.nextInt();
//                v = scanner.nextInt();
//                w = scanner.nextInt();
            for (int j = 1; j <= n; j++) {
                if (arr[i][j] != 0) {
                    u = i;
                    v = j;
                    w = arr[i][j];
                    addEdge(u, v, w);
                }

            }

        }
        s = 1;
//        s = 0;
        t = n;
        Dijkstra();
        System.out.println(dis[t]);
        for (int d : dis) {
//            System.out.print(d + " ");
        }
//        }
    }

    static void init() {
        cnt = 0;
        for (int i = 1; i <= n; i++) {
            head[i] = -1;
        }
    }

    static void addEdge(int u, int v, int w) {
        edges[cnt] = new Edge(v, w, head[u]);
        head[u] = cnt++;
        edges[cnt] = new Edge(u, w, head[v]);
        head[v] = cnt++;
    }

    public static void Dijkstra() {
        for (int i = 1; i <= n; i++) {
            vis[i] = false;
            dis[i] = INF;
        }
        PriorityQueue<Node> q = new PriorityQueue<>();
        dis[s] = 0;
        q.add(new Node(s, 0));
        Node tmp;
        while (!q.isEmpty()) {
            tmp = q.poll();
            int u = tmp.v;
            if (vis[u]) {
                continue;
            }
            vis[u] = true;
            for (int i = head[u]; i != -1; i = edges[i].next) {
                int v = edges[i].v;
                int w = edges[i].w;
                if (!vis[v] && dis[v] > dis[u] + w) {
                    dis[v] = dis[u] + w;
                    q.add(new Node(v, dis[v]));
                }
            }
        }
    }

}
