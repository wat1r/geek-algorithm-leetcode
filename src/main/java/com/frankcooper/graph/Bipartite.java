package com.frankcooper.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author: wangzhou(Frank Cooper)
 * @date: 2021/8/6 16:43
 * @description:
 */
public class Bipartite {


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

        public static void main(String[] args) {
            int G[][] = {{0, 1, 0, 1},
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


    static class _2nd {

    }


}
