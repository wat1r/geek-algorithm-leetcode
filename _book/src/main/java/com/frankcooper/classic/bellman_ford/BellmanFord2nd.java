package com.frankcooper.classic.bellman_ford;

public class BellmanFord2nd {

    static BellmanFord2nd handler = new BellmanFord2nd();

    public static void main(String[] args) {

        handler.testOne();

    }


    public void testOne() {
        n = 5;
        e = 9;
        Edge[] edges = new Edge[e];
        edges[0] = new Edge(0, 1, 6);
        edges[1] = new Edge(0, 3, 7);
        edges[2] = new Edge(1, 2, 5);
        edges[3] = new Edge(1, 3, 8);
        edges[4] = new Edge(1, 4, -4);
        edges[5] = new Edge(2, 1, -2);
        edges[6] = new Edge(3, 2, -3);
        edges[7] = new Edge(3, 4, 9);
        edges[8] = new Edge(4, 0, 2);
        int s = 0;
        dis = new int[n];
        pre = new int[n];
        bellmanFord(s, edges);


    }


    public class Edge {
        public int u;
        public int v;
        public int w;

        public Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }


    int n;//顶点个数
    int e;//边的数量
    Integer INF = Integer.MAX_VALUE;
    int[] dis;//存储当前顶点的路径
    int[] pre;//存储上来自的顶点

    /**
     * @param s 起点
     * @return
     */
    public boolean bellmanFord(int s, Edge[] edges) {
        //初始化
        for (int i = 0; i < n; i++) {
            dis[i] = i == s ? 0 : INF;
        }
        //每一轮的顶点，对所有的edges做松弛
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < e; j++) {
                if (dis[edges[j].v] > dis[edges[j].u] + edges[j].w) {//relax操作
                    dis[edges[j].v] = dis[edges[j].u] + edges[j].w;
                    pre[edges[j].v] = edges[j].u;
                }
            }
        }
        //
        boolean f = false;
        for (int i = 0; i < e; i++) {
            if (dis[edges[i].v] > dis[edges[i].u] + edges[i].w) {
                f = true;
                break;
            }
        }
//        printPath();
        return f;
    }


    public void printPath() {
        for (int i = 0; i < n; i++) {
            System.out.printf("v:%d,dis:%d,", i, dis[i]);
            getPath(i);
        }
    }


    public void getPath(int root) {
        while (root != pre[root]) {
            System.out.printf("%d<--", root);
            root = pre[root];
        }
        if (root == pre[root]) {
            System.out.printf("%d\n", root);
        }

    }


}
