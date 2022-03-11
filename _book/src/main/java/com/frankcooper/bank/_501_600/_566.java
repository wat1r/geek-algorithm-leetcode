package com.frankcooper.bank._501_600;

public class _566 {

    static class _1st {
        public int[][] matrixReshape(int[][] nums, int r, int c) {
            int R = nums.length, C = nums[0].length;
            //   System.out.printf("%d,%d\n",R,C);
            if ((r * c) > (R * C)) return nums;
            int[][] ans = new int[r][c];
            int cr = 0, cc = 0;
            int idx = 0;
            //  System.out.printf("%d,%d\n",cr,cc);
            while (cr < r) {
                // System.out.printf("%d,%d\n",cr,cc);
                while (cc < c) {
                    // System.out.printf("%d,%d\n",cr,cc);
                    ans[cr][cc++] = nums[idx / C][idx % C];
                    idx++;
                }
                cr++;
                cc = 0;
            }
            return ans;
        }
    }

    static class _2nd {

        public static void main(String[] args) {
            _2nd handler = new _2nd();
            int[][] mat = {{1, 2}};
            int r = 1, c = 1;
            handler.matrixReshape(mat, r, c);
        }

        /**
         * 54/57
         * @param mat
         * @param r
         * @param c
         * @return
         */
        public int[][] matrixReshape(int[][] mat, int r, int c) {
            int m = mat.length, n = mat[0].length;
            if (m * n < r * c) return mat;
            int[][] res = new int[r][c];
            int i = 0, j = 0;
            int idx = 0;
            while (i < r) {
                while (j < c) {
                    res[i][j] = mat[idx / n][idx % n];
                    j++;
                    idx++;
                }
                j = 0;
                i++;
            }
            return res;

        }
    }

    static class _3rd {
        public int[][] matrixReshape(int[][] nums, int r, int c) {
            int m = nums.length;
            int n = nums[0].length;
            if (m * n != r * c) {
                return nums;
            }

            int[][] ans = new int[r][c];
            for (int x = 0; x < m * n; ++x) {
                ans[x / c][x % c] = nums[x / n][x % n];
            }
            return ans;
        }

    }
}
