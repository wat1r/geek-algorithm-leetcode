package com.frankcooper.interview;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Date 2020/8/27
 * @Author Frank Cooper
 * @Description
 */
public class _16_19 {

    static _16_19 handler = new _16_19();

    public static void main(String[] args) {

        int[][] land = {
                {0, 2, 1, 0},
                {0, 1, 0, 1},
                {1, 1, 0, 1},
                {0, 1, 0, 1}
        };

        handler.pondSizes(land);


    }


    int m, n;
    int[][] directions = new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}};


    public int[] pondSizes(int[][] land) {
        if (land == null || land.length == 0) return new int[]{};
        List<Integer> res = new ArrayList<>();
        m = land.length;
        n = land[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (land[i][j] == 0) {
                    Queue<int[]> queue = new LinkedList<>();
                    queue.offer(new int[]{i, j});
                    land[i][j] = -1;
                    int count = 1;
                    while (!queue.isEmpty()) {
                        int[] curr = queue.poll();
                        for (int[] d : directions) {
                            int nextX = curr[0] + d[0], nextY = curr[1] + d[1];
                            if (inArea(nextX, nextY) && land[nextX][nextY] == 0) {
                                queue.offer(new int[]{nextX, nextY});
                                land[nextX][nextY] = -1;
                                count++;

                            }
                        }
                    }
                    res.add(count);
                }
            }
        }
        return res.stream().sorted().mapToInt(Integer::intValue).toArray();
    }

    private boolean inArea(int i, int j) {
        return i >= 0 && i < m && j >= 0 && j < n;
    }


    class _1st {
        int m, n;
        int[][] directions = new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}};

        List<Integer> res = new ArrayList<>();

        public int[] pondSizes(int[][] land) {
            if (land == null || land.length == 0) return new int[]{};
            m = land.length;
            n = land[0].length;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (land[i][j] == 0) {
                        int count = dfs(land, i, j);
                        if (count != 0) res.add(count);
                    }
                }
            }
            int[] resArr = res.stream().sorted().mapToInt(Integer::intValue).toArray();
            return resArr;
        }

        private int dfs(int[][] land, int i, int j) {
            int count = 0;
            if (land[i][j] == 0) {
                land[i][j] = -1;
                count++;
            }
            for (int[] d : directions) {
                int nextI = i + d[0], nextJ = j + d[1];
                if (inArea(nextI, nextJ) && land[nextI][nextJ] == 0) {
                    count += dfs(land, nextI, nextJ);
                }
            }
            return count;
        }

        private boolean inArea(int i, int j) {
            return i >= 0 && i < m && j >= 0 && j < n;
        }
    }

}
