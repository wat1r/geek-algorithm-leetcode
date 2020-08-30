package com.frankcooper.lintcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by FrankCooper
 * Date 2020/8/30 9:24
 * Description
 */
public class _787 {


    public static void main(String[] args) {

    }

    int m, n;
    int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public boolean hasPath(int[][] maze, int[] start, int[] destination) {

        m = maze.length;
        n = maze[0].length;
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(start);
        visited[start[0]][start[1]] = true;
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            for (int[] d : directions) {
                int nextX = curr[0], nextY = curr[1];
                while (inArea(nextX, nextY) && maze[nextX][nextY] == 0) {
                    nextX += d[0];
                    nextY += d[1];
                }
                nextX -= d[0];
                nextY -= d[1];
                if (!visited[nextX][nextY]) {
                    if (nextX == destination[0] && nextY == destination[1]) return true;
                    queue.offer(new int[]{nextX, nextY});
                    visited[nextX][nextY] = true;
                }
            }
        }
        return false;
    }


    private boolean inArea(int x, int y) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }

}
