package com.frankcooper.platform.leetcode.bank._1001_1500;


/**
 * Created by FrankCooper
 * Date 2020/3/23 21:51
 * Description
 */
//
public class _1143 {
    static _1143 handler = new _1143();

    public static void main(String[] args) {
        String text1 = "abcde", text2 = "ace";
        handler.longestCommonSubsequence3rd(text1, text2);
    }

    /*
    ##### 方法1：`DP` 基础版

[![8H6EDO.jpg](https://s1.ax1x.com/2020/03/23/8H6EDO.jpg)](https://imgchr.com/i/8H6EDO)

- 对于`0`位置**未添加空字符串**

- $dp[i][j]$表示的是$s1[0...i-1]$与$s2[0...j-1]$的最长公共子序列的长度,要求的是$dp[m-1][n-1]$

- 当$s1[i]==s2[j]$,说明这两个字符是公共的字符，只要考察其子问题，$dp[i-1][j-1]$的长度即可，在此基础上`+1`,

- 当$s1[i]!=s2[j]$,说明这两个字符不是公共的字符，只要考察其两个子问题，$dp[i-1][j],dp[i][j-1]$,取$max$

- 动态转移方程：

   $$dp[i][j] = \begin{cases} dp[i-1][j-1]+1 & \text{s1[i]==s2[j]}\\ max(dp[i-1][j],dp[i][j-1])& \text{s1[i]!=s2[j]} \end{cases}$$

     */
    public int longestCommonSubsequence(String text1, String text2) {
        if (text1 == null || text2 == null || text1.length() == 0 || text2.length() == 0) return 0;
        char[] chas1 = text1.toCharArray();
        char[] chas2 = text2.toCharArray();
        int m = chas1.length, n = chas2.length;
        int[][] dp = new int[m][n];
        dp[0][0] = chas1[0] == chas2[0] ? 1 : 0;
        for (int i = 1; i < m; i++) dp[i][0] = chas1[i] == chas2[0] ? 1 : dp[i - 1][0];
        for (int j = 1; j < n; j++) dp[0][j] = chas1[0] == chas2[j] ? 1 : dp[0][j - 1];
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                if (chas1[i] == chas2[j]) dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
            }
        }
        return dp[m - 1][n - 1];
    }

    /*
    ##### 方法2：`DP` 优化版

[![8H2sqf.jpg](https://s1.ax1x.com/2020/03/23/8H2sqf.jpg)](https://imgchr.com/i/8H2sqf)

- 对于`0`位置**添加空字符串**
- $dp[i][j]$表示的是$s1[0...i]$与$s2[0...j]$的最长公共子序列的长度,要求的是$dp[m][n]$

- 注意$s1|s2$的位置是错位了一个，其长度达不到$m|n$
     */
    public int longestCommonSubsequence2nd(String text1, String text2) {
        if (text1 == null || text2 == null || text1.length() == 0 || text2.length() == 0) return 0;
        int m = text1.length(), n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) dp[i][j] = dp[i - 1][j - 1] + 1;
                else dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
            }
        }
        return dp[m][n];
    }

    /*
    ##### 方法3：`DP`压缩空间版`O(1)`

- 准备一个`dp` `n+1`的长度，其实的`0`位置存放一个空字符串

[![8H0OG6.jpg](https://s1.ax1x.com/2020/03/23/8H0OG6.jpg)](https://imgchr.com/i/8H0OG6)

[![8HD96U.jpg](https://s1.ax1x.com/2020/03/23/8HD96U.jpg)](https://imgchr.com/i/8HD96U)

- 准备几个变量：

  - $last$:表示是当前$dp[j](dp[i][j])$左上角的数，相当于$dp[i-1][j-1]$,初始化的时候为`0`
  - $tmp$:表示是当前$dp[j](dp[i][j])$正上方的数，相当于$dp[i- 1][j]$
  - $dp[j-1]$:表示是当前$dp[j](dp[i][j])$左边的数，相当于$dp[i][j-1]$
  - 每一轮结束后，$last$的值都向前滚动一个，变成正上方的数，也就是$tmp$


     */
    public int longestCommonSubsequence3rd(String text1, String text2) {
        if (text1 == null || text2 == null || text1.length() == 0 || text2.length() == 0) return 0;
        int m = text1.length(), n = text2.length();
        int[] dp = new int[n + 1];
        int tmp = 0;
        for (int i = 1; i <= m; i++) {
            int last = 0;
            for (int j = 1; j <= n; j++) {
                tmp = dp[j];
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) dp[j] = last + 1;
                else dp[j] = Math.max(tmp, dp[j - 1]);
                last = tmp;
            }
//            System.out.println(JSON.toJSONString(dp));
        }
        return dp[n];
    }


    static class _1st {


        public static void main(String[] args) {

        }

        public int longestCommonSubsequence(String text1, String text2) {
            int m = text1.length(), n = text2.length();
            int[][] dp = new int[m + 1][n + 1];
            for (int i = 1; i <= m; i++) {
                char c1 = text1.charAt(i - 1);
                for (int j = 1; j <= n; j++) {
                    char c2 = text2.charAt(j - 1);
                    if (c1 == c2) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    } else {
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                    }

                }
            }
            return dp[m][n];

        }


    }

}
