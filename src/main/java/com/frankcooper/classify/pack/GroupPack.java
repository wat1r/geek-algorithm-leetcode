package com.frankcooper.classify.pack;

import java.util.Scanner;

/**
 * Created by FrankCooper
 * Date 2020/3/26 21:43
 * Description
 */
public class GroupPack {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();//物品的件数N
        int V = scanner.nextInt();//背包的容量V
        int[] s = new int[N + 1];//长度为N的数组，第i组物品的个数
        int[][] v = new int[N + 1][];//长度为N的数组，v[i][j]第i个组物品第j个的体积
        int[][] w = new int[N + 1][];//长度为N的数组，w[i][j]第i个组物品第j个价值
        for (int i = 1; i <= N; i++) {
            s[i] = scanner.nextInt();
            v[i] = new int[s[i] + 1];
            w[i] = new int[s[i] + 1];
            for (int j = 1; j <= s[i]; j++) {
                v[i][j] = scanner.nextInt();
                w[i][j] = scanner.nextInt();
            }
        }
        scanner.close();
        System.out.println(groupPackProcess1st(N, V, v, w, s));
    }

    public static int groupPackProcess1st(int N, int V, int[][] v, int[][] w, int[] s) {
        int[][] dp = new int[N + 1][V + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= V; j++) {
                for (int k = 0; k <= s[i]; k++) {
                    if (j >= v[i][k]) dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - v[i][k]] + w[i][k]);
                }
            }
        }
        return dp[N][V];
    }

    public static int groupPackProcess2nd(int N, int V, int[][] v, int[][] w, int[] s) {
        int[] dp = new int[V + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = V; j >= 0; j--) {
                for (int k = 0; k <= s[i]; k++) {
                    if (j >= v[i][k]) dp[j] = Math.max(dp[j], dp[j - v[i][k]] + w[i][k]);
                }
            }
        }
        return dp[V];
    }
}
