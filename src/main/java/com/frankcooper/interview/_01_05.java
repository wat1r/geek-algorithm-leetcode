package com.frankcooper.interview;

import java.util.*;

import org.junit.Assert;

public class _01_05 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }

        /**
         * @param first
         * @param second
         * @return
         */
        public boolean oneEditAway(String first, String second) {
            int m = first.length(), n = second.length();
            int[][] dp = new int[m + 1][n + 1];//两个字符的前i j 个字符变成一样的，编辑距离
            for (int i = 0; i <= m; i++) dp[i][0] = i;
            for (int j = 0; j <= n; j++) dp[0][j] = j;
            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    if (first.charAt(i - 1) == second.charAt(j - 1)) dp[i][j] = dp[i - 1][j - 1];
                    else dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                }
            }
            return dp[m][n] <= 1;//不编辑或者只有一次编辑的机会
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        public boolean oneEditAway(String first, String second) {
            if (first.length() < second.length()) {
                String t = first;
                first = second;
                second = t;
            }
            //维持n1的长度大于n2
            int n1 = first.length(), n2 = second.length();
            if (n1 - n2 > 1) return false;
            for (int i = 0; i < n2; i++) {
                //找到第一个不相等的情况，后比较剩余部分
                //1.长度相同：leetcode 与 leetkode 找到 'c' 和 'k'，然后比较 'ode' 和 'ode' 是否相同
                //2.长度不同: leetcode 与 leetode  'c' 和 'o' 不相同，然后比较 'ode' 和 'ode' 是否相同
                if (first.charAt(i) != second.charAt(i)) {
                    return first.substring(i + 1).equals(second.substring(n1 == n2 ? i + 1 : i));
                }
            }
            return true;
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
