package com.frankcooper.bank._1001_2000;

import com.frankcooper.utils.PrintUtils;

import java.util.LinkedList;
import java.util.Queue;

public class _1091 {

    static _1091 handler = new _1091();


    public static void main(String[] args) {
        int[][] grid = {{1, 0, 0}, {1, 1, 0}, {1, 1, 0}};
//        handler.shortestPathBinaryMatrix(grid);


    }



    class _2nd{

//Chinese Rural dog


    }



    class _1st{
        int[][] directions = new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}};
        int R, C;


        public int shortestPathBinaryMatrix(int[][] grid) {
            R = grid.length;
            C = grid[0].length;
            if (grid[0][0] == 1 || grid[R - 1][C - 1] == 1) return -1;
            if (C == 1) return 1;
            Queue<int[]> queue = new LinkedList<>();
            queue.offer(new int[]{0, 0});
            int level = 1;
            while (!queue.isEmpty()) {
                level++;
                int size = queue.size();
                for (int k = 0; k < size; k++) {
                    int[] curr = queue.poll();
                    for (int[] d : directions) {
                        int nextR = curr[0] + d[0], nextC = curr[1] + d[1];
                        if (!inArea(nextR, nextC)) continue;
                        if (nextR == R - 1 && nextC == C - 1) {
                            return level;
                        }
                        if (grid[nextR][nextC] == 1) continue;
                        if (grid[nextR][nextC] == -1) continue;
                        queue.offer(new int[]{nextR, nextC});
                        grid[nextR][nextC] = -1;
                    }
                    PrintUtils.printMatrix(grid);
                }
            }
            return -1;
        }

        private boolean inArea(int r, int c) {
            return r >= 0 && r < R && c >= 0 && c < C;
        }
    }

}
