package com.frankcooper.platform.leetcode.bank._901_1000;

/*import java.util.*;
import org.junit.Assert;*/
public class _983 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

            int[] days = {1, 4, 6, 7, 8, 20};
            int[] costs = {2, 7, 15};
            handler.mincostTickets(days, costs);
        }

        public int mincostTickets(int[] days, int[] costs) {
            int n = days.length;
            int[] dp = new int[400];
            for (int i = n - 1, d = days[n - 1]; d >= days[0]; d--) {
                if (d == days[i]) {
                    dp[d] = Math.min(dp[d + 1] + costs[0], Math.min(dp[d + 7] + costs[1], dp[d + 30] + costs[2]));
                    i--;
                } else {
                    dp[d] = dp[d + 1];
                }
            }
            return dp[days[0]];
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
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
