package com.frankcooper.platform.lintcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by FrankCooper
 * Date 2020/8/30 17:42
 * Description
 */
public class _788 {


    static _788 handler = new _788();


    public static void main(String[] args) {

        int[][] maze = {{0, 0, 1, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 1, 0}, {1, 1, 0, 1, 1}, {0, 0, 0, 0, 0}};
        int[] start = {0, 4};
        int[] destination = {4, 4};
        handler.shortestDistance(maze, start, destination);

    }


    int m, n;
    int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        m = maze.length;
        n = maze[0].length;
        int[][] dist = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dist[i][j] = Integer.MAX_VALUE;
            }
        }
//        Arrays.fill(dist, Integer.MAX_VALUE);
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(start);
        dist[start[0]][start[1]] = 0;
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int currX = curr[0], currY = curr[1];
            for (int[] d : directions) {
                int distance = dist[currX][currY];
                int nextX = currX + d[0], nextY = currY + d[1];
                while (inArea(nextX, nextY) && maze[nextX][nextY] == 0) {
                    nextX += d[0];
                    nextY += d[1];
                    distance += 1;
                }
                nextX -= d[0];
                nextY -= d[1];
//                int delta = Math.abs(nextX - currX) + Math.abs(nextY - currY);
                if (distance < dist[nextX][nextY]) {
                    dist[nextX][nextY] = distance;
                    if (nextX != destination[0] || nextY != destination[1])
                        queue.offer(new int[]{nextX, nextY});
                }
            }
        }
        return dist[destination[0]][destination[1]] == Integer.MAX_VALUE ? -1 : dist[destination[0]][destination[1]];
    }


    private boolean inArea(int x, int y) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }


}
