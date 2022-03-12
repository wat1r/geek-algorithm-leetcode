package com.frankcooper.bank._1001_1500;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Date 2020/8/26
 * @Author Frank Cooper
 * @Description
 */
public class _1162 {


    public static void main(String[] args) {

    }


    public int maxDistance(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int m = grid.length, n = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) queue.add(new int[]{i, j});
            }
        }
        //全为0或者全为1的时候返回-1
        if (queue.isEmpty() || queue.size() == m * n) return -1;
        int[][] dirs = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
        int res = -1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int k = 0; k < size; k++) {
                int[] curr = queue.poll();
                int currX = curr[0], currY = curr[1];
                for (int[] d : dirs) {
                    int nextX = currX + d[0], nextY = currY + d[1];
                    if (nextX < 0 || nextX >= m || nextY < 0 || nextY >= n || grid[nextX][nextY] != 0) continue;
                    queue.offer(new int[]{nextX, nextY});
                    grid[nextX][nextY] = grid[currX][currY] + 1;
                    res = Math.max(res, grid[nextX][nextY] - 1);
                }
            }
        }
        return res;
    }


    public int maxDistance1st(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int m = grid.length, n = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) queue.add(new int[]{i, j});
            }
        }
        //全为0或者全为1的时候返回-1
        if (queue.isEmpty() || queue.size() == m * n) return -1;
        int[][] dirs = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
        int level = -1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int k = 0; k < size; k++) {
                int[] curr = queue.poll();
                int currX = curr[0], currY = curr[1];
                for (int[] d : dirs) {
                    int nextX = currX + d[0], nextY = currY + d[1];
                    if (nextX < 0 || nextX >= m || nextY < 0 || nextY >= n || grid[nextX][nextY] == 1) continue;
                    queue.offer(new int[]{nextX, nextY});
                    grid[nextX][nextY] = 1;
                }
            }
            level++;
        }
        return level;
    }


}
