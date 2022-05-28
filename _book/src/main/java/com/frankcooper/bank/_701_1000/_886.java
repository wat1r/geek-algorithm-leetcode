package com.frankcooper.bank._901_1000;

import com.frankcooper.utils.PrintUtils;

import java.util.*;

public class _886 {


    public static void main(String[] args) {
        _2nd handler = new _2nd();

        int N = 4;
        int[][] dislikes = PrintUtils.processSymbol("[[1,2],[1,3],[2,4]]");
        handler.possibleBipartition(N, dislikes);
    }


    /**
     * bfs
     */
    static class _1st {
        public boolean possibleBipartition(int N, int[][] dislikes) {
            //v 表示从1 到 N 的节点，其各自的颜色，初始化为0， 两种颜色 1 和-1
            int[] v = new int[N + 1];
            //构造图
            List<Integer>[] graph = new List[N + 1];
            for (int i = 1; i <= N; ++i) graph[i] = new ArrayList<>();
            //无向图
            for (int[] d : dislikes) {
                graph[d[0]].add(d[1]);
                graph[d[1]].add(d[0]);
            }
            for (int i = 1; i <= N; ++i) {
                if (v[i] != 0) continue;//已经染色的不需要再染色了
                Queue<Integer> q = new LinkedList<>();
                q.offer(i);
                v[i] = 1;//染色1
                while (!q.isEmpty()) {
                    int curr = q.poll();
                    for (int next : graph[curr]) {
                        if (v[next] != 0) {//next如果是和curr一样的颜色，说明是同类，但是他们各自不喜欢，返回false
                            if (v[curr] == v[next]) return false;
                        } else {
                            q.offer(next);
                            v[next] = -v[curr];//翻转next的颜色为curr的相反数
                        }
                    }
                }
            }
            return true;
        }
    }


    /**
     * dfs
     */
    static class _2nd {
        public boolean possibleBipartition(int N, int[][] dislikes) {
            int[] visited = new int[N + 1];
            List<Integer>[] graph = new List[N + 1];
            for (int i = 1; i <= N; ++i) graph[i] = new ArrayList<>();
            for (int[] d : dislikes) {
                graph[d[0]].add(d[1]);
                graph[d[1]].add(d[0]);
            }
            for (int i = 1; i <= N; ++i) {
                if (visited[i] == 0 && !dfs(graph, i, 1, visited)) return false;
            }
            return true;
        }


        public boolean dfs(List<Integer>[] graph, int curr, int color, int[] visited) {
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

    private void getGraph(int N, int[][] dislikes) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int[] d : dislikes) {
            int s = d[0], t = d[1];
            graph.putIfAbsent(s, new HashSet<>());
            graph.putIfAbsent(t, new HashSet<>());
            graph.get(s).add(t);
            graph.get(t).add(s);
        }
    }


}
