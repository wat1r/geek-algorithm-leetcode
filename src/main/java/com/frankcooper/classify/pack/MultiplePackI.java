package com.frankcooper.classify.pack;

import java.util.Scanner;

/**
 * Created by FrankCooper
 * Date 2020/3/26 0:16
 * Description
 */
public class MultiplePackI {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();//物品的件数N
        int V = scanner.nextInt();//背包的容量V
        int[] v = new int[N + 1];//长度为N的数组，第i个元素表示第i件物品的体积
        int[] w = new int[N + 1];//长度为N的数组，第i个元素表示第i件物品的价值
        int[] s = new int[N + 1];//长度为N的数组，第i个元素表示第i件物品的最大数量
        for (int i = 1; i <= N; i++) {//N行 每行两个数，空格分开，v[i]表示是第i个物品的体积，w[i]是第i个物品的价值
            v[i] = scanner.nextInt();
            w[i] = scanner.nextInt();
            s[i] = scanner.nextInt();
        }
        scanner.close();
//        System.out.println(zeroOnePackExecutor1st(N, V, v, w));
//        System.out.println(zeroOnePackExecutor2nd(N, V, v, w));

    }

    /*

     */
    public static int multiplePackProcessI1st(int N, int V, int[] v, int[] w, int[] s) {
        int[] dp = new int[V + 1];
        dp[0] = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = V; j >= v[i]; j--) {
                for (int k = 0; k <= s[i] && k * v[i] <= j; k++) {
                    dp[j] = Math.max(dp[j], dp[j - k * v[i]] + k * w[i]);
                }
            }
        }
        return dp[V];
    }
}
