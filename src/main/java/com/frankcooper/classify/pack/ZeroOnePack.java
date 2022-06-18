package com.frankcooper.classify.pack;

import java.util.Scanner;

/**
 * Created by FrankCooper
 * Date 2020/3/24 21:54
 * Description
 */
public class ZeroOnePack {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();//物品的件数N
        int V = scanner.nextInt();//背包的容量V
        int[] v = new int[N + 1];//长度为N的数组，第i个元素表示第i件物品的体积
        int[] w = new int[N + 1];//长度为N的数组，第i个元素表示第i件物品的价值
        for (int i = 1; i <= N; i++) {//N行 每行两个数，空格分开，v[i]表示是第i个物品的体积，w[i]是第i个物品的价值
            v[i] = scanner.nextInt();
            w[i] = scanner.nextInt();
        }
        scanner.close();
//        System.out.println(zeroOnePackExecutor1st(N, V, v, w));
        System.out.println(zeroOnePackExecutor2nd(N, V, v, w));

    }


    /*
#### 01背包问题

[![8OGrUx.jpg](https://s1.ax1x.com/2020/03/24/8OGrUx.jpg)](https://imgchr.com/i/8OGrUx)

##### 方法1：`DP`二维版
- $dp[i][j]$表示的是选择前$i$个物品，背包容量为$j$的情况下，背包中物品的最大价值

  - 当不选择当前的第$i$件物品时，$dp[i][j]=dp[i-1][j]$
  - 当选择当前的第$i$件物品时,$dp[i][j]=dp[i-1][j-v[i]]+w[i]$,当前第$i$件物品的价值$w[i]$+前$i-1$件物品抛去$w[i]$后的最大价值$dp[i-1][j-v[i]]$,要求$j>=v[i]$,负数没有意义
  - 取上述两个值的$max$
- $base case$:$dp[0][0]$表示的是当选择第$0$个物品的时候，即在没有物品时，背包体积为$0$时，不装任何东西的时候,$dp[0][0]=0$
     */
    public static int zeroOnePackExecutor1st(int N, int V, int[] v, int[] w) {
        int[][] dp = new int[N + 1][V + 1];
        dp[0][0] = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= V; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= v[i]) dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - v[i]] + w[i]);
//                System.out.print(String.format("%s ", dp[i][j]));
            }
//            System.out.println();
        }
        return dp[N][V];
    }

    /*


    ##### 方法2：`DP`压缩空间版($j$采取逆序的方式)

    - 准备一个一维的$dp$数组，去掉了$i$，只保留了$j$,如果正向遍历的话，是要这么写的

    - ```java
      for (int j = 0; j <= V; j++) {
          if (j >= v[i]) dp[j] = Math.max(dp[j], dp[j - v[i]] + w[i]);
      }
      ```
    ```json
    0 0 0 0 0 0
    0 2 4 6 8 10
    0 2 4 6 8 10
    0 2 4 6 8 10
    0 2 4 6 8 10
    10
    ```
    - 将$dp[i][j]=max(dp[i-1][j],dp[i-1][j-v[i]]+w[i])$变成了$dp[i][j]=max(dp[i-1][j],dp[i][j-v[i]]+w[i])$，正向遍历而来时，$i-1$的值已经计算过并被覆盖了，避免这个问题，需要采用倒序遍历,先更新$i$,再更新$i-1$

    - ```java
      for (int j = V; j >= v[i]; j--) {
          dp[j] = Math.max(dp[j], dp[j - v[i]] + w[i]);
      }
      ```


    ```json
    0 2 2 2 2 2
    0 2 4 6 6 6
    0 2 4 6 6 8
    0 2 4 6 6 8
    8
    ```
    - 解释了为何需要倒序：https://www.cnblogs.com/lanhj/archive/2012/12/05/2802437.html
     */
    public static int zeroOnePackExecutor2nd(int N, int V, int[] v, int[] w) {
        int[] dp = new int[V + 1];
        dp[0] = 0;
        for (int i = 1; i <= N; i++) {
//            for (int j = 0; j <= V; j++) {
//                if (j >= v[i]) {
//                    dp[j] = Math.max(dp[j], dp[j - v[i]] + w[i]);
//                }
//            }
            for (int j = V; j >= v[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - v[i]] + w[i]);
            }
            for (int j = 0; j <= V; j++) {
                System.out.print(dp[j]);
                System.out.print(" ");
            }
            System.out.print("\n");
        }
        return dp[V];
    }
}
