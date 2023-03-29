package com.frankcooper.platform.leetcode.bank._1501_2000;

import java.util.*;

import org.junit.Assert;

public class _1641 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

            handler.countVowelStrings(2);
        }

        public int countVowelStrings(int n) {
            int[][] dp = new int[n][5];
            for (int j = 0; j < 5; j++) {
                dp[0][j] = 1;
            }
            for (int i = 1; i < n; i++) {
                dp[i][0] = dp[i - 1][0];
                for (int j = 1; j < 5; j++) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
            return Arrays.stream(dp[n - 1]).sum();
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        public int countVowelStrings(int n) {
            //分别用数字0-4表示字符a,e,i,o,u
            //记dp[i][j]表示长度为i+1，以j为结尾的字典序排列的字符串的数量
            //当i=0,长度为1的时候 dp[0][j] =1 ;
            //当i>0，dp[i][j] = sum(dp[i-1][k])其中k从0当当前的j
            //dp[i]的计算只涉及到dp[i-1]部分，dp[i]等价于dp[i-1]的前缀和
            int[] dp = new int[5];
            Arrays.fill(dp, 1);
            for (int i = 1; i < n; i++) {
                for (int j = 1; j < 5; j++) {
                    dp[j] += dp[j - 1];
                }
            }
            return Arrays.stream(dp).sum();
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }

        public int countVowelStrings(int n) {
            //dp[i][j] 其中j从0-4分别表示字母a, e, i, o, u, i表示当前有多少个字母
            int[][] dp = new int[n + 1][5];
            for (int j = 0; j < 5; j++) {
                dp[1][j] = 1;//只有一个字母的时候
            }
            for (int i = 2; i <= n; i++) {
                for (int j = 4; j >= 0; j--) {
                    // System.out.printf("j:%d ",j);
                    for (int k = j; k >= 0; k--) {
                        dp[i][j] += dp[i - 1][k];
                    }
                }
            }
            int res = 0;
            for (int j = 0; j < 5; j++) {
                res += dp[n][j];
            }

            return res;
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }

        public int countVowelStrings(int n) {
            int[][] dp = new int[n][5];
            for (int i = 0; i < 5; ++i) dp[0][i] = 1;
            for (int i = 1; i < n; ++i) {
                dp[i][0] = dp[i - 1][0];    // a前只能是a
                dp[i][1] = dp[i - 1][0] + dp[i - 1][1];     // b前可以是a和b
                dp[i][2] = dp[i - 1][0] + dp[i - 1][1] + dp[i - 1][2];
                dp[i][3] = dp[i - 1][0] + dp[i - 1][1] + dp[i - 1][2] + dp[i - 1][3];
                dp[i][4] = dp[i - 1][0] + dp[i - 1][1] + dp[i - 1][2] + dp[i - 1][3] + dp[i - 1][4];
            }
            return Arrays.stream(dp[n - 1]).sum();
        }
    }
}
