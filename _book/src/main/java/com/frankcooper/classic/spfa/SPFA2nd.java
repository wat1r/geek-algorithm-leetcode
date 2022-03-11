package com.frankcooper.classic.spfa;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://www.cnblogs.com/liuzhen1995/p/6535025.html
 */

public class SPFA2nd {


    //内部类，用于存放图的具体边数据
    class Edge {
        public int u;  //边的起点
        public int v;  //边的终点
        public int w;   //边的权值

        Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }


    static SPFA2nd handler = new SPFA2nd();


    public static void main(String[] args) {
        handler.testOne();
    }


    private void testOne() {
        System.out.println("请输入一个图的顶点总数n起点下标s和边总数p：");
        int n = 6;
        int s = 0;
        int p = 8;
        Edge[] edges = new Edge[p];
        edges[0] = new Edge(0, 2, 10);
        edges[1] = new Edge(0, 4, 30);
        edges[2] = new Edge(0, 5, 100);
        edges[3] = new Edge(1, 2, 5);
        edges[4] = new Edge(2, 3, 50);
        edges[5] = new Edge(3, 5, 10);
        edges[6] = new Edge(4, 3, 20);
        edges[7] = new Edge(4, 5, 60);
        if (getShortestPaths(n, s, edges)) {
            for (int i = 0; i < dis.length; i++) {
                System.out.print(dis[i] + " ");
            }
        } else
            System.out.println("给定图存在负环，没有最短距离");

    }

    public long[] dis;         //用于得到第s个顶点到其它顶点之间的最短距离

    /*
     * 参数n:给定图的顶点个数
     * 参数s:求取第s个顶点到其它所有顶点之间的最短距离
     * 参数edge:给定图的具体边
     * 函数功能：如果给定图不含负权回路，则可以得到最终结果，如果含有负权回路，则不能得到最终结果
     */
    public boolean getShortestPaths(int n, int s, Edge[] edges) {
        List<Integer> queue = new ArrayList<>();
        dis = new long[n];
        boolean[] used = new boolean[n];
        int[] num = new int[n];
        Arrays.fill(dis,Integer.MAX_VALUE);
        dis[s] = 0;     //第s个顶点到自身距离为0
        used[s] = true;    //表示第s个顶点进入数组队
        num[s] = 1;       //表示第s个顶点已被遍历一次
        queue.add(s);      //第s个顶点入队
        while (queue.size() != 0) {
            int a = queue.get(0);   //获取数组队中第一个元素
            queue.remove(0);         //删除数组队中第一个元素
            System.out.println(a);
            for (int i = 0; i < edges.length; i++) {
                //当queue的第一个元素等于边edges[i]的起点时
                if (a == edges[i].u && dis[edges[i].v] > dis[edges[i].u] + edges[i].w) {
                    dis[edges[i].v] = dis[edges[i].u] + edges[i].w;
                    if (!used[edges[i].v]) {
                        queue.add(edges[i].v);
                        num[edges[i].v]++;
                        if (num[edges[i].v] > n) return false;
                        used[edges[i].v] = true;   //表示边edges[i]的终点b已进入数组队
                    }
                }
            }
            used[a] = false;        //顶点a出数组对
        }
        return true;
    }


}
