package com.frankcooper.platform.leetcode.bank.week;

import java.util.*;

//https://leetcode-cn.com/contest/cmbchina-2022spring
public class Week288_0 {


    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            /*
            "e RSg c R cf"
            10
            "Singing dancing in the rain"
            10
            "Hello World"
            2
            "Hello World"
            5
            "Hello"
            0
            ===
            "e RSg c R"
            "Singing in the rain"
            "World"
            "Hello World"
            ""
             */

        }

        public String deleteText(String s, int index) {
            if (s.charAt(index) == ' ') return s;
            int l = index;
            while (l - 1 >= 0 && s.charAt(l - 1) != ' ') l--;
            int r = index;
            int n = s.length();
            while (r + 1 <= n - 1 && s.charAt(r + 1) != ' ') r++;
            if (l == 0 && r == n - 1) return "";
            if (l == 0) return s.substring(r + 2);
            if (r == n - 1) return s.substring(0, l - 1);
            //  System.out.printf("%d" ,r );
            return s.substring(0, l - 1) + " " + s.substring(r + 2);
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        public int numFlowers(int[][] roads) {
            int N = 100_010;
            int[] f = new int[N];
            for (int[] r : roads) {
                f[r[0]]++;
                f[r[1]]++;
            }
            int res = 0;
            for (int i = 0; i < N; i++) res = Math.max(res, f[i]);
            return res + 1;
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
            int height = 2, width = 2;
            int[] indices = {2, 5, 6, 7, 8, 10, 11};
            //[2]
//            handler.lightSticks(height, width, indices);
            height = 1;
            width = 2;
            indices = new int[]{3};
//         [0,1,2,3,4,5]
//            handler.lightSticks(height, width, indices);
            height = 1;
            width = 1;
            indices = new int[]{0, 3};
            //[]
            handler.lightSticks(height, width, indices);
        }


        public int[] lightSticks(int height, int width, int[] indices) {
            int V = (height + 1) * (width + 1);//顶点的数量[0...V-1]
            Set<Integer> set = new HashSet<>();
            for (int x : indices) set.add(x);
            List<List<Integer>> graph = new ArrayList<>();
            //建图
            buildGraph(height, width, V, set, graph);
            int minn = Integer.MAX_VALUE;
            List<Integer> resList = new ArrayList<>();
            for (int k = 0; k < V; k++) {
                if (graph.get(k).isEmpty()) continue;
                int[] dist = new int[V];
                Arrays.fill(dist, Integer.MAX_VALUE);
                dist[k] = 0;
                Queue<Integer> q = new LinkedList<>();
                q.offer(k);
                while (!q.isEmpty()) {
                    int size = q.size();
                    for (int i = 0; i < size; i++) {
                        int u = q.poll();
                        for (int v : graph.get(u)) {
                            if (dist[v] != Integer.MAX_VALUE) continue;
                            q.offer(v);
                            dist[v] = dist[u] + 1;
                        }
                    }
                }
                int time = 0;
                for (int i = 0; i < V; i++) {
                    if (graph.get(i).isEmpty()) continue;
                    time = Math.max(time, dist[i]);
                }
                if (time <= minn) {
                    if (time < minn) resList.clear();
                    resList.add(k);
                    minn = time;
                }
            }
            if (minn == Integer.MAX_VALUE) return new int[]{};
            int[] res = new int[resList.size()];
            for (int i = 0; i < res.length; i++) res[i] = resList.get(i);
            return res;
        }

        public void buildGraph(int height, int width, int V, Set<Integer> set, List<List<Integer>> graph) {
            for (int i = 0; i < V; i++) graph.add(new ArrayList<>());
            int idx = 0;//边的编号
            for (int i = 0; i <= height; i++) {
                for (int j = 0; j < width; j++) {//当前的元素为left，拿其右边的一个 < width,非<=width
                    int left = i * (width + 1) + j;//根据[x,y]坐标来映射顶点的序号
                    int right = left + 1;
                    if (!set.contains(idx)) {
                        graph.get(left).add(right);
                        graph.get(right).add(left);
                    }
                    idx++;
                }
                if (i != height) {//没有到最后一行
                    for (int j = 0; j <= width; j++) {
                        int up = i * (width + 1) + j;//上一层
                        int down = (i + 1) * (width + 1) + j;//下一层
                        if (!set.contains(idx)) {
                            graph.get(up).add(down);
                            graph.get(down).add(up);
                        }
                        idx++;
                    }
                }
            }
        }
    }


    static class _3rd_1 {
        public static void main(String[] args) {
            _3rd_1 handler = new _3rd_1();
            int height = 2, width = 2;
            int[] indices = {2, 5, 6, 7, 8, 10, 11};
            //[2]
//            handler.lightSticks(height, width, indices);
            height = 1;
            width = 2;
            indices = new int[]{3};
//         [0,1,2,3,4,5]
//            handler.lightSticks(height, width, indices);
            height = 1;
            width = 1;
            indices = new int[]{0, 3};
            //[]
            handler.lightSticks(height, width, indices);
        }


        public int[] lightSticks(int height, int width, int[] indices) {
            int V = (height + 1) * (width + 1);//顶点的数量[0...V-1]
            Set<Integer> set = new HashSet<>();
            for (int x : indices) set.add(x);
            List<List<Integer>> graph = new ArrayList<>();
            //建图
            buildGraph(height, width, V, set, graph);
            //有效节点的数量（有相邻节点的顶点）
            int validV = 0;
            for (int i = 0; i < V; i++) {
                if (!graph.get(i).isEmpty()) validV++;
            }
            int minn = Integer.MAX_VALUE;
            List<Integer> resList = new ArrayList<>();
            for (int k = 0; k < V; k++) {
                if (graph.get(k).isEmpty()) continue;
                boolean[] vis = new boolean[V];
                vis[k] = true;
                Queue<Integer> q = new LinkedList<>();
                q.offer(k);
                int visCnt = 1;//访问过的顶点数量
                int time = 0;//耗时 bfs的层数
                while (!q.isEmpty()) {
                    int size = q.size();
                    for (int i = 0; i < size; i++) {
                        int u = q.poll();
                        for (int v : graph.get(u)) {
                            if (vis[v]) continue;
                            q.offer(v);
                            visCnt++;
                            vis[v] = true;
                        }
                    }
                    time++;
                }
                if (visCnt < validV) continue;
                if (time <= minn) {
                    if (time < minn) resList.clear();
                    resList.add(k);
                    minn = time;
                }
            }
            int[] res = new int[resList.size()];
            for (int i = 0; i < res.length; i++) res[i] = resList.get(i);
            return res;
        }

        public void buildGraph(int height, int width, int V, Set<Integer> set, List<List<Integer>> graph) {
            for (int i = 0; i < V; i++) graph.add(new ArrayList<>());
            int idx = 0;//边的编号
            for (int i = 0; i <= height; i++) {
                for (int j = 0; j < width; j++) {//当前的元素为left，拿其右边的一个 < width,非<=width
                    int left = i * (width + 1) + j;//根据[x,y]坐标来映射顶点的序号
                    int right = left + 1;
                    if (!set.contains(idx)) {
                        graph.get(left).add(right);
                        graph.get(right).add(left);
                    }
                    idx++;
                }
                if (i != height) {//没有到最后一行
                    for (int j = 0; j <= width; j++) {
                        int up = i * (width + 1) + j;//上一层
                        int down = (i + 1) * (width + 1) + j;//下一层
                        if (!set.contains(idx)) {
                            graph.get(up).add(down);
                            graph.get(down).add(up);
                        }
                        idx++;
                    }
                }
            }
        }
    }


    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
