package com.frankcooper.platform.leetcode.bank.week;

import java.util.*;

import org.junit.Assert;

public class Week289 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            Assert.assertEquals("27", handler.digitSum("01234567890", 2));

        }


        public String digitSum(String s, int k) {
            while (s.length() > k) {
                String t = "";
                for (int i = 0; i < s.length(); i += k) {
                    String sub = s.substring(i, Math.min(i + k, s.length()));
                    int num = 0;
                    for (int j = 0; j < sub.length(); j++) {
                        num += sub.charAt(j) - '0';
                    }
                    t += num;
                }
                s = t;
            }
            return s;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            Assert.assertEquals(4, handler.minimumRounds(new int[]{2, 2, 3, 3, 2, 4, 4, 4, 4, 4}));
        }

        public int minimumRounds(int[] tasks) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int x : tasks) map.put(x, map.getOrDefault(x, 0) + 1);
            int res = 0;
            for (int x : map.keySet()) {
                int num = 0;
                int cnt = map.get(x);
                if (cnt == 1) return -1;
                int a = cnt / 3;
                int t = cnt - a * 3;
                if (t == 1 || t == 2) {
                    res += a + 1;
                } else if (t == 0) {
                    res += a;
                }
            }
            return res;
        }
    }

    //前缀和
    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }


        public int maxTrailingZeros(int[][] grid) {
            //m 行 * n 列
            int m = grid.length, n = grid[0].length;
            //grid[i-1][j-1]包含左边的2，5因子总个数 r2 r5
            //grid[i-1][j-1]包含上边的2，5因子总个数 c2 c5
            int[][] r2 = new int[m + 1][n + 1];
            int[][] r5 = new int[m + 1][n + 1];
            int[][] c2 = new int[m + 1][n + 1];
            int[][] c5 = new int[m + 1][n + 1];
            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    int cur = grid[i - 1][j - 1];
                    int cnt2 = getFactor(cur, 2);
                    int cnt5 = getFactor(cur, 5);
                    //求前缀和
                    r2[i][j] = r2[i][j - 1] + cnt2;
                    r5[i][j] = r5[i][j - 1] + cnt5;
                    c2[i][j] = c2[i - 1][j] + cnt2;
                    c5[i][j] = c5[i - 1][j] + cnt5;
                }
            }
            int res = 0;
            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    //grid[i-1][j-1]是拐弯的点，横竖的2或者5的因子取最小值
                    //左边开始出发，然后向上拐弯
                    int leftUp = Math.min(r2[i][j] + c2[i - 1][j], r5[i][j] + c5[i - 1][j]);
                    res = Math.max(res, leftUp);
                    //左边开始出发，然后向下拐弯
                    int leftDown = Math.min(r2[i][j] + c2[m][j] - c2[i][j], r5[i][j] + c5[m][j] - c5[i][j]);
                    res = Math.max(res, leftDown);
                    //右边开始出发，然后向上拐弯
                    int rightUp = Math.min(r2[i][n] - r2[i][j] + c2[i][j], r5[i][n] - r5[i][j] + c5[i][j]);
                    res = Math.max(res, rightUp);
                    //右边开始出发，然后向下拐弯
                    int rightDown = Math.min(r2[i][n] - r2[i][j] + c2[m][j] - c2[i - 1][j],
                            r5[i][n] - r5[i][j] + c5[m][j] - c5[i - 1][j]);
                    res = Math.max(res, rightDown);
                }
            }
            return res;
        }


        //获得某个数num对因子factor的个数
        private int getFactor(int num, int factor) {
            int k = 0;
            while (num % factor == 0) {
                num /= factor;
                k++;
            }
            return k;
        }


    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }

        public int longestPath(int[] parent, String s) {
            int n = parent.length;
            List<List<Integer>> graph = new ArrayList<>();
            for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
            for (int i = 0; i < n; i++) {
                if (i != 0) {
                    int u = i, v = parent[i];
                    graph.get(i).add(v);
                    graph.get(v).add(u);
                }
            }
            int res = 0;
            for (int i = 0; i < n; i++) {
                res = Math.max(res, bfs(i, graph, s));
            }
            return res;
        }

        private int bfs(int start, List<List<Integer>> graph, String s) {
            Queue<Integer> q = new ArrayDeque<>();
            q.offer(start);
            int depth = 0;
            boolean[] vis = new boolean[s.length()];
            while (!q.isEmpty()) {
                int size = q.size();
                for (int i = 0; i < size; i++) {
                    int u = q.poll();
                    if (vis[u]) continue;
                    for (int v : graph.get(u)) {
                        if (vis[v]) continue;
                        if (s.charAt(v) == s.charAt(u)) continue;
                        q.offer(v);
                    }
                    vis[u] = true;
                }
                depth++;
            }
            return depth;
        }
    }

    static class _4th_1 {
        public static void main(String[] args) {
            _4th_1 handler = new _4th_1();
            int[] parent = {-1, 0, 0, 1, 1, 2};
            String s = "abacbe";
            Assert.assertEquals(3, handler.longestPath(parent, s));
        }

        int res = 0;
        List<List<Integer>> graph = new ArrayList<>();

        public int longestPath(int[] parent, String s) {
            int n = parent.length;
            for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
            for (int i = 1; i < n; i++) graph.get(parent[i]).add(i);
            dfs(0, s);
            return res;
        }

        //son node
        private int dfs(int sn, String s) {
            int a = 0, b = 0;
            for (int fn : graph.get(sn)) {
                if (s.charAt(sn) == s.charAt(fn)) {
                    dfs(fn, s);
                } else {
                    int t = dfs(fn, s);
                    //更新第二大的直径和第一大的直径
                    if (t > a) {
                        b = a;
                        a = t;
                    } else if (t > b) {
                        b = t;
                    }
                }
            }
            res = Math.max(res, a + b + 1);
            return a + 1;
        }
    }
}
