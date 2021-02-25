package com.frankcooper.bank;

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
}
