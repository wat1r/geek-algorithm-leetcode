package com.frankcooper.interview;

import java.util.ArrayList;
import java.util.List;

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

//        handler.pondSizes(land);


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
