package com.frankcooper.platform.leetcode.bank._1501_2000;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*import java.util.*;
import org.junit.Assert;*/
public class _1547 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        public int minCost(int n, int[] _cuts) {
            Arrays.sort(_cuts);
            int m = _cuts.length;
            int[] cuts = new int[m + 2];
            cuts[0] = 0;
            System.arraycopy(_cuts, 0, cuts, 1, m);
            cuts[m + 1] = n;
            int[][] f = new int[m + 2][m + 2];
            for (int i = m; i >= 1; i--) {
                for (int j = i; j <= m; j++) {
                    f[i][j] = (i == j ? 0 : Integer.MAX_VALUE);
                    for (int k = i; k <= j; k++) {
                        f[i][j] = Math.min(f[i][j], f[i][k - 1] + f[k + 1][j]);
                    }
                    f[i][j] += cuts[j + 1] - cuts[i - 1];
                }
            }
            return f[1][m];
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        public int minCost(int n, int[] cuts) {
            List<Integer> list = new ArrayList<>();
            list.add(0);
            list.add(n);
            for (int num : cuts) {
                list.add(num);
            }
            Collections.sort(list);
            int m = list.size();
            int[][] dp = new int[m][m];
            for (int len = 2; len < m; len++) {//枚举区间
                for (int i = 0; i + len < m; i++) {//枚举左端点
                    int j = i + len;//右端点
                    dp[i][j] = Integer.MAX_VALUE;
                    // 枚举最后一个分割点
                    for (int k = i + 1; k < j; k++) {
                        dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j] + list.get(j) - list.get(i));
                    }
                }
            }
            return dp[0][m - 1];
        }


    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
