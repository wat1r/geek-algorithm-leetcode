package com.frankcooper.bank;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Date 2020/8/25
 * @Author Frank Cooper
 * @Description
 */
public class _542 {

    static _542 handler = new _542();


    public static void main(String[] args) {
        int[][] matrix = {{0, 0, 0},
                {0, 1, 0},
                {1, 1, 1}};
        handler.updateMatrix(matrix);
    }

    int[][] directions = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};//四个方向
    int m, n;

    public int[][] updateMatrix(int[][] matrix) {

        m = matrix.length;
        n = matrix[0].length;

        int[][] res = new int[m][n];
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    //初始化时，queue有多个源点
                    queue.offer(new int[]{i, j});
                    visited[i][j] = true;
                }
            }
        }
        int dist = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            //当前层数的所有节点都需要拿出来
            for (int k = 0; k < size; k++) {
                int[] curr = queue.poll();
                //当前的curr坐标
                int currX = curr[0], currY = curr[1];
                if (matrix[currX][currY] == 1) res[currX][currY] = dist; //赋值
                for (int[] d : directions) {
                    //由单当前的curr坐标生出的next坐标，上下左右四个方向
                    int nextX = currX + d[0];
                    int nextY = currY + d[1];
                    //坐标越界或是已经放进queue中，跳过
                    if (!inArea(nextX, nextY) || visited[nextX][nextY]) {
                        continue;
                    }
                    //放进queue中且标记被放进去
                    queue.offer(new int[]{nextX, nextY});
                    visited[nextX][nextY] = true;
                }
            }
            dist++;//每一层后+1
        }
        return res;
    }


    private boolean inArea(int i, int j) {
        return i >= 0 && i < m && j >= 0 && j < n;
    }
}
