package com.frankcooper.bank._1001_1500;

import java.util.*;

import org.junit.Assert;

public class _1245 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[][] edges = {{0, 1}, {1, 2}, {2, 3}, {1, 4}, {4, 5}};
            Assert.assertEquals(4, handler.treeDiameter(edges));

        }


        public int treeDiameter(int[][] edges) {
            int n = edges.length;
            //建图
            List<List<Integer>> graph = new ArrayList<>();
            for (int i = 0; i <= n; i++) graph.add(new ArrayList<>());
            for (int[] edge : edges) {
                int u = edge[0], v = edge[1];
                graph.get(u).add(v);
                graph.get(v).add(u);
            }
            int res = 0;
            for (int i = 0; i <= n; i++) {
                //最长路径的端点的度一定为1
                if (graph.get(i).size() == 1) {
                    res = Math.max(res, dfs(graph, -1, i));
                }
            }
            //直径是边的数量，2个点之间有一条边
            return res - 1;

        }

        /**
         * @param graph
         * @param prev  前驱节点
         * @param cur   当前节点
         * @return
         */
        private int dfs(List<List<Integer>> graph, int prev, int cur) {
            int res = 0;
            for (int ne : graph.get(cur)) {
                if (ne != prev) {//如果不是当前节点，则进入到下一层dfs
                    res = Math.max(res, dfs(graph, cur, ne));
                }
            }
            return res + 1;//加上当前节点
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
