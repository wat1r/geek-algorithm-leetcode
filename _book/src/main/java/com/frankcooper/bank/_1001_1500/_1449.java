package com.frankcooper.bank._1001_1500;

import java.util.*;

import org.junit.Assert;

public class _1449 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[] cost = {4, 3, 2, 5, 6, 7, 2, 5, 5};
            int target = 9;
            handler.largestNumber(cost, target);
        }


        //dp[i+1][j]表示 前i个数，且花费的成本恰好是j时的最大位数
        public String largestNumber(int[] cost, int target) {
            int n = cost.length;
            String[] dp = new String[target + 1];
            Arrays.fill(dp, "#");
            dp[0] = "";
            for (int i = 1; i <= n; i++) {
                System.out.printf("i:%d\n", i);
                for (int j = 1; j <= target; j++) {
                    System.out.printf("  j:%d,cost[%d]:%d\n", j, i - 1, cost[i - 1]);
                    if (j >= cost[i - 1] && !dp[j - cost[i - 1]].equals("#")) {
                        String a = dp[j];
                        String b = i + dp[j - cost[i - 1]];
                        System.out.printf("  a:%s,b:%s\n", a, b);
                        dp[j] = transform(a, b);
                    }
                }
            }
            return dp[target].equals("#") ? "0" : dp[target];
        }

        private String transform(String a, String b) {
            if (a.length() == b.length()) return a.compareTo(b) > 0 ? a : b;
            return a.length() > b.length() ? a : b;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            int[] cost = {4, 3, 2, 5, 6, 7, 2, 5, 5};
            int target = 9;
            handler.largestNumber(cost, target);
        }
        //top-down

        String[] dp = new String[5005];

        public String largestNumber(int[] cost, int target) {
            if (target <= 0) return target == 0 ? "" : "0";
            if (dp[target] == null) {
                dp[target] = "0";
                for (int i = 1; i <= 9; i++) {
                    String res = largestNumber(cost, target - cost[i - 1]);
                    if (!res.equals("0") && res.length() + 1 >= dp[target].length()) {
                        System.out.printf("%s\n", i + res);
                        dp[target] = i + res;

                    }
                }
            }
            return dp[target];
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
