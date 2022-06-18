package com.frankcooper.classify.pack;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by FrankCooper
 * Date 2020/3/26 16:51
 * Description
 */
public class MultiplePackII {
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
        System.out.println(multiplePackProcessII1st(N, V, v, w, s));
    }

    //考查多重背包的二进制优化方法
    public static int multiplePackProcessII1st(int N, int V, int[] v, int[] w, int[] s) {
        int[] dp = new int[V + 1];
        List<Good> list = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            int total = s[i];
            for (int k = 1; k < total; k += 2) {
                total -= k;
                list.add(new Good(v[i] * k, w[i] * k));
            }
            if (total > 0) list.add(new Good(v[i] * total, w[i] * total));
        }
        for (Good good : list) {
            for (int j = V; j >= good.v; j--) {
                dp[j] = Math.max(dp[j], dp[j - good.v] + good.w);
            }
        }
        return dp[V];
    }


    static class Good {
        int v, w;

        public Good(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }


}
