package com.frankcooper.bank;

import java.util.Arrays;

public class _72 {

    static class _1st {
        /*dp*/
        public int minDistance(String word1, String word2) {
            int m = word1.length(), n = word2.length();
            int[][] f = new int[m + 1][n + 1];
            for (int i = 0; i <= m; i++) f[i][0] = i;
            for (int j = 0; j <= n; j++) f[0][j] = j;
            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    char c1 = word1.charAt(i - 1), c2 = word2.charAt(j - 1);
                    if (c1 == c2) f[i][j] = f[i - 1][j - 1];
                    else {
                        f[i][j] = Math.min(f[i - 1][j - 1], Math.min(f[i - 1][j], f[i][j - 1])) + 1;
                    }
                }
            }
            return f[m][n];
        }
    }

    static class _2nd {

        /*记忆化递归*/
        int[][] cache;

        public int minDistance(String word1, String word2) {
            int m = word1.length(), n = word2.length();
            cache = new int[m + 1][n + 1];
            for (int i = 0; i <= m; i++) Arrays.fill(cache[i], -1);
            return dfs(word1, word2, m, n);
        }

        private int dfs(String word1, String word2, int m, int n) {
            if (m == 0 || n == 0) return m == 0 ? n : m;
            if (cache[m][n] != -1) return cache[m][n];
            int ans = 0;
            if (word1.charAt(m - 1) == word2.charAt(n - 1)) {
                ans = dfs(word1, word2, m - 1, n - 1);
            } else {
                ans = Math.min(dfs(word1, word2, m - 1, n - 1),
                        Math.min(dfs(word1, word2, m - 1, n), dfs(word1, word2, m, n - 1))) + 1;
            }
            return cache[m][n] = ans;
        }
    }
}
