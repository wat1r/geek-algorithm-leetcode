package com.frankcooper.bank.week;

import java.util.*;

import org.junit.Assert;

public class Week246 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }

        //找到最靠右的奇数位
        public String largestOddNumber(String num) {
            int i = 0, end = -1;
            for (i = 0; i < num.length(); i++) {
                if ((num.charAt(i) - '0') % 2 == 1) end = i;
            }
            return end == -1 ? "" : num.substring(0, end + 1);
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            String startTime = "00:47", finishTime = "00:57";
            handler.numberOfRounds(startTime, finishTime);
        }


        public int numberOfRounds(String startTime, String finishTime) {
            int s = transform(startTime), e = transform(finishTime);
            if (s > e) e += 24 * 60;
            s = (s + 14) / 15;//上取整
            e /= 15;//下取整
            return s > e ? 0 : e - s;

        }


        private int transform(String s) {
            String[] arr = s.split(":");
            int h = Integer.parseInt(arr[0]), m = Integer.parseInt(arr[1]);
            return h * 60 + m;
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }

        int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        int R, C;
        int[][] grid1;
        int[][] grid2;

        public int countSubIslands(int[][] grid1, int[][] grid2) {
            R = grid2.length;
            C = grid2[0].length;
            this.grid1 = grid1;
            this.grid2 = grid2;
            int res = 0;
            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    if (grid2[r][c] == 1 && dfs(r, c)) {
                        res++;
                    }
                }
            }
            return res;
        }


        private boolean dfs(int r, int c) {
            boolean f = true;
            grid2[r][c] = 0;
            if (grid1[r][c] != 1) f = false;
            for (int[] d : dirs) {
                int nr = r + d[0], nc = c + d[1];
                if (nr >= 0 && nr < R && nc >= 0 && nc < C && grid2[nr][nc] == 1) {
                    if (!dfs(nr, nc)) f = false;
                }
            }
            return f;
        }

    }


    static class _3rd_1 {

        int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        int R, C;
        int[][] grid1;
        int[][] grid2;

        public int countSubIslands(int[][] grid1, int[][] grid2) {
            R = grid2.length;
            C = grid2[0].length;
            this.grid1 = grid1;
            this.grid2 = grid2;
            int res = 0;
            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    if (grid2[r][c] == 1 && bfs(r, c)) {
                        res++;
                    }
                }
            }
            return res;
        }

        private boolean bfs(int r, int c) {
            Queue<int[]> queue = new LinkedList<>();
            queue.offer(new int[]{r, c});
            grid2[r][c] = 0;
            boolean f = true;
            if (grid1[r][c] != 1) f = false;
            while (!queue.isEmpty()) {
                int[] cur = queue.poll();
                for (int[] d : dirs) {
                    int nr = cur[0] + d[0], nc = cur[1] + d[1];
                    if (nr >= 0 && nr < R && nc >= 0 && nc < C && grid2[nr][nc] == 1) {
                        queue.offer(new int[]{nr, nc});
                        grid2[nr][nc] = 0;
                        if (grid1[nr][nc] != 1) f = false;
                    }
                }
            }
            return f;
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
