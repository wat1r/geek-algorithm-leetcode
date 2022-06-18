package com.frankcooper.platform.lintcode;

import java.util.*;

public class _1031 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        public boolean isBipartite(int[][] graph) {
            int V = graph.length;
            Queue<Integer> q = new LinkedList<>();
            int[] colors = new int[V];
            for (int i = 0; i < V; i++) {
                if (colors[i] == 0) {
                    q.offer(i);
                    colors[i] = 1;
                    while (!q.isEmpty()) {
                        int u = q.poll();
                        for (int v : graph[u]) {
                            if (colors[v] == colors[u]) return false;
                            if (colors[v] == 0) {
                                colors[v] = -colors[u];
                                q.offer(v);
                            }
                        }
                    }
                }
            }
            return true;
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
