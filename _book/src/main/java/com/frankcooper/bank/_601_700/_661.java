package com.frankcooper.bank._601_700;

/*import java.util.*;
import org.junit.Assert;*/
public class _661 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        int[][] dirs = new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}};


        public int[][] imageSmoother(int[][] img) {
            int R = img.length, C = img[0].length;
            int[][] res = new int[R][C];
            for (int i = 0; i < R * C; i++) {
                int r = i / C, c = i % C;
                int sum = img[r][c];
                int cnt = 1;
                for (int[] d : dirs) {
                    int nr = r + d[0], nc = c + d[1];
                    if (nr >= 0 && nr < R && nc >= 0 && nc < C) {
                        sum += img[nr][nc];
                        cnt += 1;
                    }
                }
                res[r][c] = sum / cnt;
            }
            return res;
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            int[][] M = {{100, 200, 100}, {200, 50, 200}, {100, 200, 100}};
            handler.imageSmoother(M);
        }

        int[][] dirs = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

        public int[][] imageSmoother(int[][] img) {
            int R = img.length, C = img[0].length;
            for (int r = 0; r < R; ++r)
                for (int c = 0; c < C; ++c) {
                    int sum = img[r][c], cnt = 1;
                    for (int k = 0; k < dirs.length; ++k) {
                        int nr = r + dirs[k][0], nc = c + dirs[k][1];
                        if (nr < 0 || nr > R - 1 || nc < 0 || nc > C - 1) continue;
                        sum += (img[nr][nc] & 0xFF);
                        cnt++;
                    }
                    img[r][c] |= ((sum / cnt) << 8);
                }

            for (int r = 0; r < R; ++r)
                for (int c = 0; c < C; ++c)
                    img[r][c] >>= 8;
            return img;
        }

    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }

        class Solution {
            public int[][] imageSmoother(int[][] img) {
                int R = img.length, C = img[0].length;
                int[][] preSum = new int[R + 5][C + 5];
                for (int r = 1; r <= R; r++) {
                    for (int c = 1; c <= C; c++) {
                        preSum[r][c] = preSum[r - 1][c] + preSum[r][c - 1] - preSum[r - 1][c - 1] + img[r - 1][c - 1];
                    }
                }
                int[][] res = new int[R][C];
                for (int i = 0; i < R; i++) {
                    for (int j = 0; j < C; j++) {
                        int a = Math.max(0, i - 1), b = Math.max(0, j - 1);
                        int c = Math.min(R - 1, i + 1), d = Math.min(C - 1, j + 1);
                        int cnt = (c - a + 1) * (d - b + 1);
                        int sum = preSum[c + 1][d + 1] - preSum[a][d + 1] - preSum[c + 1][b] + preSum[a][b];
                        res[i][j] = sum / cnt;
                    }
                }
                return res;
            }
        }

    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
