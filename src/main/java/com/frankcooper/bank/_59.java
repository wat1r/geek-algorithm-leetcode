package com.frankcooper.bank;

public class _59 {

    public static void main(String[] args) {

    }


    static class _1st {

        public static void main(String[] args) {
            _1st handler = new _1st();
            handler.generateMatrix(3);
        }

        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        public int[][] generateMatrix(int n) {
            int[][] res = new int[n][n];
            int r = 0, c = 0, d = 0;
            for (int i = 1; i <= n * n; i++) {
                res[r][c] = i;
                System.out.printf("d:%d\n", d);
                int nr = r + dirs[d][0], nc = c + dirs[d][1];
                System.out.printf("%d,%d\n", nr, nc);
                if (nr < 0 || nr >= n || nc < 0 || nc >= n || res[nr][nc] != 0) {
                    d = (d + 1) % 4;
                }
                r += dirs[d][0];
                c += dirs[d][1];

            }
            return res;
        }
    }
}
