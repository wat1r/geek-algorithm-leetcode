package com.frankcooper.bank._2001_3000;

import java.util.*;

import org.junit.Assert;

public class _2039 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }


        /*
         * // 建立邻接表存储树
         * int[] head = new int[N];    // 存储邻接表的表头
         * int[] edge = new int[N];    // 按输入顺序存储每条边指向的节点
         * int[] next = new int[N];    // 记录邻接表中当前节点的下一个节点
         * int idx = 1;                // 记录边的序号,边的序号从1开始吧
         *
         * public void add(int a, int b){
         *     // 第idx边指向b
         *     edge[idx] = b;
         *     // 采用头插法
         *     // 第idx边的下一个节点是上一个时刻的头节点
         *     next[idx] = head[a];
         *     // 当前链表头节点更新，指向第idx边
         *     head[a] = idx;
         *     // idx++ 更新边序号
         *     idx++;
         * }
         */


        static int V = 100_005;//顶点数量
        static int E = V * 2;//边的数量
        static int[] head = new int[V];//头节点数组
        static int[] edge = new int[E];//按输入顺序存储每条边指向的节点
        static int[] next = new int[E];//记录邻接表中当前节点的下一个节点
        int idx = 0;//记录边的序号,边的序号从0开始 不能使用static
        static int INF = Integer.MAX_VALUE / 2;
        static int[] dist = new int[V];

        public void addEdge(int u, int v) {
            edge[idx] = v;// 第idx边指向v
            next[idx] = head[u];//采用头插法 第idx边的下一个节点是上一个时刻的头节点
            head[u] = idx; // 当前链表头节点更新，指向第idx边
            idx++;// idx++ 更新边序号
        }

        public int networkBecomesIdle(int[][] edges, int[] patience) {
            Arrays.fill(head, -1);
            Arrays.fill(dist, INF);
            for (int[] e : edges) {
                addEdge(e[0], e[1]);
                addEdge(e[1], e[0]);
            }
            Deque<Integer> deque = new ArrayDeque<>();
            deque.addLast(0);
            dist[0] = 0;
            while (!deque.isEmpty()) {
                int u = deque.pollFirst();
                for (int i = head[u]; i != -1; i = next[i]) {
                    int v = edge[i];
                    if (dist[v] != INF) continue;
                    dist[v] = dist[u] + 1;
                    deque.addLast(v);
                }
            }
            int res = 0;
            for (int i = 1; i < patience.length; i++) {
                int d = dist[i] * 2;
                int t = patience[i];
                int cur = d <= t ? d : (d - 1) / t * t + d;
                if (cur > res) res = cur;
            }
            return res + 1;
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
