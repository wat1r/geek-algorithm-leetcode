package com.frankcooper.bank._1001_1500;

import java.util.LinkedList;
import java.util.Queue;

public class _1034 {

    public static void main(String[] args) {

//        _1st handler = new _1st();
//        _2nd handler = new _2nd();
        _3rd handler = new _3rd();
/*        int[][] grid = PrintUtils.processSymbol("[[1,1,1],[1,1,1],[1,1,1]]");
        int r0 = 1, c0 = 1, color = 2;
        handler.colorBorder(grid, r0, c0, color);*/
        /**
         * 2nd
         * [[1,2,1,2,1,2],[2,2,2,2,1,2],[1,2,2,2,1,2]]
         * 1
         * 3
         * 1
         */
/*        grid = PrintUtils.processSymbol("[[1,2,1,2,1,2],[2,2,2,2,1,2],[1,2,2,2,1,2]]");
        int r0 = 1, c0 = 3, color = 1;
        handler.colorBorder(grid, r0, c0, color);*/
/*        int[][] grid = PrintUtils.processSymbol("[[1,1],[1,2]]");
        int r0 = 0, c0 = 0, color = 3;
        handler.colorBorder(grid, r0, c0, color);*/

        int[][] grid = new int[][]{
                {1, 1, 1, 1, 2, 2},
                {3, 3, 3, 3, 2, 2},
                {3, 3, 3, 2, 3, 3},
                {3, 3, 2, 3, 3, 3},
                {3, 2, 2, 2, 3, 3},
                {3, 2, 2, 2, 3, 3},
                {3, 3, 2, 2, 3, 3}
        };
        int color = 4;
        int r0 = 5, c0 = 1;
        handler.colorBorder(grid, r0, c0, color);
    }


    /**
     * 边界特性：首先值等于初始值origin；四个方向至少有一个方向的元素不等于origin（或是边界）。
     * 对于搜索到的位置，检查该点是否是边界，如果是边界则将其置为color。
     */
    static class _1st {


        int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        int R, C;
        boolean[][] visited;
        int oldColor;


        public int[][] colorBorder(int[][] grid, int r0, int c0, int newColor) {
            R = grid.length;
            C = grid[0].length;
            visited = new boolean[R][C];
            oldColor = grid[r0][c0];
            dfs(grid, r0, c0, newColor);
            return grid;
        }


        public int dfs(int[][] grid, int r, int c, int newColor) {
//            System.out.printf("(%d,%d)\n", r, c);
            if (!inArea(r, c)) return 0;
            if (visited[r][c]) return 1;
            if (grid[r][c] != oldColor) return 0;
            visited[r][c] = true;
            int cnt = 0;
            for (int[] d : dirs) {
                int nr = r + d[0], nc = c + d[1];
//                if (d[0] == -1) System.out.printf("上\n");
//                if (d[0] == 1) System.out.printf("下\n");
//                if (d[1] == -1) System.out.printf("左\n");
//                if (d[1] == 1) System.out.printf("右\n");
                cnt += dfs(grid, nr, nc, newColor);
            }
            if (r == 1 && c == 1) {
//                System.out.printf("%s\n", "d");
            }
            if (cnt < 4) grid[r][c] = newColor;
            return 1;
        }


        public boolean inArea(int r, int c) {
            return r >= 0 && r < R && c >= 0 && c < C;
        }
    }


    /**
     * dfs
     */
    static class _2nd {
        int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        int R, C;
        int[][] res;

        public int[][] colorBorder(int[][] grid, int r0, int c0, int color) {
            R = grid.length;
            C = grid[0].length;
            res = new int[R][C];
            for (int r = 0; r < R; r++)
                for (int c = 0; c < C; c++)
                    res[r][c] = grid[r][c];
            dfs(grid, r0, c0, color);
            return res;
        }


        public void dfs(int[][] grid, int r, int c, int color) {

            res[r][c] *= -1;
            boolean isEdge = false;
            boolean f = false;
            for (int[] d : dirs) {
                int nr = r + d[0], nc = c + d[1];
                if (d[0] == -1) System.out.printf("上\n");
                if (d[0] == 1) System.out.printf("下\n");
                if (d[1] == -1) System.out.printf("左\n");
                if (d[1] == 1) System.out.printf("右\n");
                //当不在区域范围内或者当前颜色与旁边的颜色不同时
                System.out.printf("(%d,%d)<--->(%d,%d)\n", r, c, nr, nc);
                if (inArea(nr, nc) && grid[r][c] != grid[nr][nc]) {
                    System.out.printf("---\n");
                }
                if (!inArea(nr, nc) || grid[r][c] != grid[nr][nc]) {
                    isEdge = true;
                }
                //res[nr][nc]首次翻转的时候（变成负数）一定会进下面的dfs，当再次被翻转后，其变成与grid的原坐标相同的时候，不会再进
                if (inArea(nr, nc) && res[nr][nc] == grid[r][c]) {
                    dfs(grid, nr, nc, color);
                }

            }
            res[r][c] *= -1;
            if (isEdge) res[r][c] = color;
        }

        public boolean inArea(int r, int c) {
            return r >= 0 && r < R && c >= 0 && c < C;
        }

    }


    static class _3rd {
        int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        int R, C;

        public int[][] colorBorder(int[][] grid, int r0, int c0, int color) {
            R = grid.length;
            C = grid[0].length;
            int oldColor = grid[r0][c0];
            boolean[][] visited = new boolean[R][C];
            Queue<int[]> queue = new LinkedList<>();
            queue.offer(new int[]{r0, c0});
            visited[r0][c0] = true;
            while (!queue.isEmpty()) {
                int[] p = queue.poll();
                int r = p[0], c = p[1];
                if (isBorder(r, c)) grid[r][c] = color;
                for (int[] d : dirs) {
                    if (d[0] == -1) System.out.printf("上\n");
                    if (d[0] == 1) System.out.printf("下\n");
                    if (d[1] == -1) System.out.printf("左\n");
                    if (d[1] == 1) System.out.printf("右\n");
                    int nr = r + d[0], nc = c + d[1];
                    System.out.printf("(%d,%d)<--->(%d,%d)\n", r, c, nr, nc);
                    if (!inArea(nr, nc) || visited[nr][nc]) continue;
                    if (grid[nr][nc] == oldColor) {//挨着的点不是原来的oldColor，当前点便是异类
                        queue.offer(new int[]{nr, nc});
                        visited[nr][nc] = true;
                    } else {
                        grid[r][c] = color;
                    }
                }
            }
            return grid;
        }

        //注意是或
        public boolean isBorder(int r, int c) {
            return r == 0 || r == R - 1 || c == 0 || c == C - 1;
        }


        public boolean inArea(int r, int c) {
            return r >= 0 && r < R && c >= 0 && c < C;
        }
    }

}
