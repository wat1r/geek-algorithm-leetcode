package com.frankcooper.pack;

import java.util.Scanner;

/**
 * Created by FrankCooper
 * Date 2020/3/26 19:17
 * Description 二维费用的背包问题
 */
public class FeePack {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();//物品的件数N
        int V = scanner.nextInt();//背包的容量V
        int M = scanner.nextInt();//背包能承受的最大重量M
        int[] v = new int[N + 1];//长度为N的数组，第i个元素表示第i件物品的体积
        int[] w = new int[N + 1];//长度为N的数组，第i个元素表示第i件物品的价值
        int[] m = new int[N + 1];//长度为N的数组，第i个元素表示第i件物品的最大承受重量
        for (int i = 1; i <= N; i++) {//N行 每行两个数，空格分开，v[i]表示是第i个物品的体积，m[i]是第i个物品的重量,w[i]是第i个物品的价值
            v[i] = scanner.nextInt();
            m[i] = scanner.nextInt();
            w[i] = scanner.nextInt();
        }
        scanner.close();
        System.out.println(feePackProcess1st(N, V, M, v, w, m));
    }

    public static int feePackProcess1st(int N, int V, int M, int[] v, int[] w, int[] m) {
        int[][] dp = new int[V + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = V; j >= v[i]; j--) {
                for (int k = M; k >= m[i]; k--) {
                    dp[j][k] = Math.max(dp[j][k], dp[j - v[i]][k - m[i]] + w[i]);
                }
            }
        }
        return dp[V][M];
    }

}
