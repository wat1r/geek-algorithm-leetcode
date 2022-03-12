package com.frankcooper.bank._101_200;

/**
 * @Date 2020/7/16
 * @Author Frank Cooper
 * @Description
 */
public class _174 {


    public static void main(String[] args) {

    }


    public int calculateMinimumHP(int[][] dungeon) {
        if (dungeon == null || dungeon.length == 0) return 0;
        int row = dungeon.length, col = dungeon[0].length;
        int[][] dp = new int[row][col];
        dp[row - 1][col - 1] = Math.max(1, 1 - dungeon[row - 1][col - 1]);
        //第row-1行：最后一行
        for (int j = col - 2; j >= 0; j--) {
            dp[row - 1][j] = Math.max(1, dp[row - 1][j + 1] - dungeon[row - 1][j]);
        }
        //第col-1列：最后一列
        for (int i = row - 2; i >= 0; i--) {
            dp[i][col - 1] = Math.max(1, dp[i + 1][col - 1] - dungeon[i][col - 1]);
        }
        //一般情况
        for (int i = row - 2; i >= 0; i--) {
            for (int j = col - 2; j >= 0; j--) {
                dp[i][j] = Math.max(Math.min(dp[i + 1][j], dp[i][j + 1]) - dungeon[i][j], 1);
            }
        }
        return dp[0][0];
    }
}
