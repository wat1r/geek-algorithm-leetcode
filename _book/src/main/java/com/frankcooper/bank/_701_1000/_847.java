package com.frankcooper.bank._901_1000;

import java.util.*;

import org.junit.Assert;

public class _847 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        public int shortestPathLength(int[][] graph) {
            int n = graph.length;
            Queue<int[]> queue = new LinkedList<>();
            boolean[][] visited = new boolean[n][1 << n];
            for (int i = 0; i < n; i++) {
                queue.offer(new int[]{i, 1 << i, 0});
                visited[i][1 << i] = true;
            }
            int ans = 0;
            while (!queue.isEmpty()) {
                int[] tuple = queue.poll();
                int u = tuple[0], mask = tuple[1], dist = tuple[2];
                if (mask == (1 << n) - 1) {
                    ans = dist;
                    break;
                }
                // 搜索相邻的节点
                for (int v : graph[u]) {
                    // 将 mask 的第 v 位置为 1
                    int maskV = mask | (1 << v);
                    if (!visited[v][maskV]) {
                        queue.offer(new int[]{v, maskV, dist + 1});
                        visited[v][maskV] = true;
                    }
                }
            }
            return ans;
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
