package com.frankcooper.bank;

import java.util.LinkedList;
import java.util.Queue;

public class _785 {


    public static void main(String[] args) {
        _2nd handler = new _2nd();
//        int[][] graph = PrintUtils.processSymbol("[[1,2,3],[0,2],[0,1,3],[0,2]]");
        int[][] graph = {{1, 3}, {0, 2}, {1, 3}, {0, 2}};
        handler.isBipartite(graph);
    }


    static class _1st {
        public boolean isBipartite(int[][] graph) {
            int[] visited = new int[graph.length];
            for (int i = 0; i < graph.length; i++) {
                //当前点没有被访问过且染色失败，返回false
                if (visited[i] == 0 && !dfs(graph, i, 1, visited)) return false;
            }
            return true;
        }


        /**
         * @param graph   图
         * @param curr    当前处理的顶点
         * @param color   当前顶点即将被染的颜色
         * @param visited 记录顶点是否被访问过
         * @return 成功染色，返回true，失败染色返回false
         */
        public boolean dfs(int[][] graph, int curr, int color, int[] visited) {
            if (visited[curr] != 0) {
                return visited[curr] == color;
            }
            visited[curr] = color;
            for (int next : graph[curr]) {
                if (!dfs(graph, next, -color, visited)) return false;
            }
            return true;
        }


    }


    /**
     * bfs
     */
    static class _2nd {

        public boolean isBipartite(int[][] graph) {
            int[] v = new int[graph.length];
            for (int i = 0; i < graph.length; i++) {
                //对没有染色并且有邻居的进行下面的循环
                if (v[i] == 0 && graph[i].length > 0) {
                    Queue<Integer> q = new LinkedList<>();
                    q.offer(i);
                    v[i] = 1;
                    while (!q.isEmpty()) {
                        int size = q.size();
                        for (int j = 0; j < size; j++) {
                            int curr = q.poll();
                            for (int next : graph[curr]) {
                                //如果curr的邻居处于没有被染色的状态,染上一与curr相反的颜色，curr为1,next为-1，curr为-1，next为1
                                if (v[next] == 0) {
                                    q.offer(next);
                                    v[next] = -1 * v[curr];
                                }
                                //这时候next已经染上色了，开始对其染色进行判断，如果next与curr同色，不符合题意
                                else if (v[curr] == v[next]) {
                                    return false;
                                }
                            }
                        }
                    }
                }
            }
            return true;
        }

    }


}
