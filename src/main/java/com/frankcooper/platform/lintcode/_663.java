package com.frankcooper.platform.lintcode;


import java.util.LinkedList;
import java.util.Queue;

public class _663 {

    static _663 handler = new _663();




    public static void main(String[] args) {
        int[][] grid = {{INF, -1, 0, INF}, {INF, INF, INF, -1}, {INF, -1, INF, -1}, {0, -1, INF, INF}};
        handler.wallsAndGates(grid);
    }


    /**
     * 同leetcode-286
     */
    static Integer INF = Integer.MAX_VALUE;
    int m, n;
    int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public void wallsAndGates(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                //找到以0值为结束的点为起点
                if (grid[i][j] == 0) queue.offer(new int[]{i, j});
            }
        }
        int dist = 0;
        while (!queue.isEmpty()) {
            dist += 1;//层数，水波纹一样向外扩散
            int size = queue.size();
            for (int k = 0; k < size; k++) {
                int[] curr = queue.poll();
                int i = curr[0], j = curr[1];//弹出当前的值
                for (int[] dir : dirs) {
                    int nextI = i + dir[0], nextJ = j + dir[1];
                    //几个条件：
                    //1.必须要在区域范围内
                    //2.不是0，也就是不是门
                    //3、不是墙，也就是-1，要走的通
                    //4.第一次走INF这个点，已经走过了不用再走了
                    if (inArea(nextI, nextJ) && grid[nextI][nextJ] != 0 && grid[nextI][nextJ] != -1 && grid[nextI][nextJ]==INF) {
                        grid[nextI][nextJ] = dist;
                        queue.offer(new int[]{nextI, nextJ});
//                        PrintUtils.printMatrix(grid, 10);
                    }
                }
            }
        }
    }

    private boolean inArea(int i, int j) {
        return i >= 0 && i < m && j >= 0 && j < n;
    }

}
