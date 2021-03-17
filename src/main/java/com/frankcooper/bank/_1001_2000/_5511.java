package com.frankcooper.bank._1001_2000;

/**
 * @Date 2020/9/13
 * @Author Frank Cooper
 * @Description
 */
public class _5511 {


    public int numSpecial(int[][] mat) {
        if (mat == null || mat.length == 0 || mat[0].length == 0) return 0;
        int rows = mat.length, cols = mat[0].length;
        //做两个数组，一个行数组，一个列数组，分别计算mat[i][j]=1时的个数，最后统计局只为1的个数
        int[] row = new int[rows];
        int[] col = new int[cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (mat[i][j] == 1) {
                    row[i]++;
                    col[j]++;
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (mat[i][j] == 1 && row[i] == 1 && col[j] == 1) {
                    ans++;
                }
            }
        }
        return ans;
    }


}
