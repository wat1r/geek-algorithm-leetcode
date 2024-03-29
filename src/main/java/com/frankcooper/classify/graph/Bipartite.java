package com.frankcooper.classify.graph;

import java.util.*;

/**
 * @author: wangzhou(Frank Cooper)
 * @date: 2021/8/6 16:43
 * @description:
 */
public class Bipartite {


    /**
     * 基础版的 图是连通的
     */
    static class _1st {
        final static int V = 4;

        public boolean isBipartite(int[][] G, int src) {
            int[] colors = new int[V];
            Arrays.fill(colors, -1);
            colors[src] = 1;
            Queue<Integer> q = new LinkedList<>();
            q.offer(src);
            while (!q.isEmpty()) {
                int u = q.poll();
                if (G[u][u] == 1) return false;
                for (int v = 0; v < V; v++) {
                    if (G[u][v] == 1 && colors[v] == -1) {
                        colors[v] = 1 - colors[u];
                        q.offer(v);
                    } else if (G[u][v] == 1 && colors[v] == colors[u]) return false;
                }
            }
            return true;
        }

        public static void main(String[] args) {            int G[][] = {{0, 1, 0, 1},
                    {1, 0, 1, 0},
                    {0, 1, 0, 1},
                    {1, 0, 1, 0}
            };
            _1st b = new _1st();
            if (b.isBipartite(G, 0))
                System.out.println("Yes");
            else
                System.out.println("No");

            //Yes
        }
    }


    /**
     * 图不一定是连通的
     */
    static class _2nd {
        final static int V = 4;

        public static boolean isBipartiteUtil(int[][] G, int src, int[] colors) {
            colors[src] = 1;
            Queue<Integer> q = new LinkedList<>();
            q.offer(src);
            while (!q.isEmpty()) {
                int u = q.poll();
                //自依赖
                if (G[u][u] == 1) return false;
                for (int v = 0; v < V; v++) {
                    //u与v之间存在一条边&& v没有被染色过
                    if (G[u][v] == 1 && colors[v] == -1) {
                        colors[v] = 1 - colors[u];
                        q.offer(v);
                    } else if (G[u][v] == 1 && colors[v] == colors[u]) {//u与v之间存在一条边，但是u和v的颜色相同，不满足二分图
                        return false;
                    }
                }
            }
            return true;
        }


        public static boolean isBipartite(int[][] G) {
            int[] colors = new int[V];
            Arrays.fill(colors, -1);
            for (int u = 0; u < V; u++) {
                if (colors[u] == -1) {
                    if (!isBipartiteUtil(G, u, colors)) return false;
                }
            }
            return true;
        }

        public static void main(String[] args) {
            int G[][] = {{0, 1, 0, 1},
                    {1, 0, 1, 0},
                    {0, 1, 0, 1},
                    {1, 0, 1, 0}};

            if (isBipartite(G))
                System.out.println("Yes");
            else
                System.out.println("No");
        }
    }

    //通用版本
    static class _3rd {

        static class Pair {
            int v;//vertex
            int c;//color

            public Pair(int v, int c) {
                this.v = v;
                this.c = c;
            }
        }


        public static boolean isBipartite(int V, List<List<Integer>> adj) {
            int[] colors = new int[V];
            Arrays.fill(colors, -1);
            Queue<Pair> q = new LinkedList<>();
            for (int i = 0; i < V; i++) {
                if (colors[i] == -1) {//当前节点i未被着色
                    q.offer(new Pair(i, 0));//一开始赋一个颜色0
                    colors[i] = 0;//修改colors数组的值
                    while (!q.isEmpty()) {
                        Pair cur = q.poll();
                        int u = cur.v;//当前节点u
                        int c = cur.c;//当前节点u的颜色
                        for (int v : adj.get(u)) {
                            if (colors[v] == c) return false;//相邻的节点颜色相同，不符合二分图
                            if (colors[v] == -1) {
                                colors[v] = 1 - c;//修改colors数组的值，翻转与上一个节点的颜色
                                q.offer(new Pair(v, colors[v]));
                            }
                        }

                    }
                }
            }
            return true;
        }

        public static void main(String args[]) {

            int V, E;
            V = 4;
            E = 8;

            // adjacency list for storing graph
            List<List<Integer>> adj = new ArrayList<>();

            for (int i = 0; i < V; i++) {
                adj.add(new ArrayList<Integer>());
            }

            adj.get(0).add(1);
            adj.get(0).add(3);

            adj.get(1).add(0);
            adj.get(1).add(2);

            adj.get(2).add(1);
            adj.get(2).add(3);

            adj.get(3).add(0);
            adj.get(3).add(2);

            boolean ans = isBipartite(V, adj);

            // returns 1 if bipatite graph is possible
            if (ans)
                System.out.println("Yes");

                // returns 0 if bipartite graph is not possible
            else
                System.out.println("No");

        }
    }


    static class _4th {
        static final int V = 4;


        public static boolean isBipartiteUtil(int[][] G, int[] colors, int u, int c) {
            if (colors[u] != -1 && colors[u] != c) return false;
            colors[u] = c;
            boolean res = true;
            for (int v = 0; v < V; v++) {
                if (G[u][v] == 1) {
                    if (colors[v] == -1) {
                        res &= isBipartiteUtil(G, colors, v, 1 - c);
                    }
                    if (colors[v] != -1 && colors[v] != 1 - c) return false;
                }
                if (!res) return false;
            }
            return true;
        }


        static boolean isBipartite(int G[][]) {
            int[] color = new int[V];
            for (int i = 0; i < V; i++)
                color[i] = -1;
            // start is vertex 0;
            int u = 0;
            // two colors 1 and 0
            return isBipartiteUtil(G, color, u, 1);
        }

        // Driver Code
        public static void main(String[] args) {
            int G[][] = {{0, 1, 0, 1},
                    {1, 0, 1, 0},
                    {0, 1, 0, 1},
                    {1, 0, 1, 0}};

            if (isBipartite(G))
                System.out.print("Yes");
            else
                System.out.print("No");
        }

    }
}
