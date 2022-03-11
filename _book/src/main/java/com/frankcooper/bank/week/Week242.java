package com.frankcooper.bank.week;

import java.util.*;

import org.junit.Assert;

public class Week242 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            String s = "110100010";
            s = "111000";
            s = "1";
            s = "11111";
            s = "1101";
            handler.checkZeroOnes(s);
        }


        public boolean checkZeroOnes(String s) {
            int ol = -1, or = 0;
            int zl = -1, zr = 0;
            int one = 0, zero = 0;
            int i = 0;
            while (i < s.length()) {
                if (s.charAt(i) == '1') {
                    or = i;
                    one = Math.max(one, or - ol);
                    zl = i;
                } else {
                    zr = i;
                    zero = Math.max(zero, zr - zl);
                    ol = i;
                }
                i++;
            }
            return one > zero;

        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            int[] dist = {1, 3, 2};
            double hour = 6;
            dist = new int[]{1, 3, 2};
            hour = 2.7;
            dist = new int[]{1, 1, 100000};
            hour = 2.01;
            handler.minSpeedOnTime(dist, hour);
        }


        public int minSpeedOnTime(int[] dist, double hour) {
            int res = -1;
            int l = 1, r = (int) 1e9;
            while (l < r) {
                int mid = l + (r - l) / 2;
                if (check(mid, hour, dist)) {
                    res = mid;
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }
            return res;
        }


        private boolean check(int mid, double hour, int[] dist) {//耗费的时间可以在hour范围内走完，说明mid值还可以小
            double cost = 0;
            for (int i = 0; i < dist.length - 1; i++) {
                cost += Math.ceil(dist[i] * 1.0 / mid);
            }
            cost += dist[dist.length - 1] * 1.0 / mid;
            return cost <= hour;
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
            String s = "011010";
            int minJump = 2;
            int maxJump = 3;
            s = "0111111111111111111111111111111101111101111111111111111110";
            minJump = 5;
            maxJump = 26;
            Assert.assertFalse(handler.canReach(s, minJump, maxJump));
        }

        public boolean canReach(String s, int minJump, int maxJump) {
            int n = s.length();
            boolean[][] dp = new boolean[n][n];
            dp[0][0] = true;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '1') continue;
//                boolean f = false;
                for (int j = i + minJump; j <= Math.min(i + maxJump, n - 1); j++) {
                    if (s.charAt(j) == '0') {
                        dp[i][j] = true;
                    }

                }
            }
            for (int i = 0; i < n; i++) {
                if (dp[i][n - 1]) return true;
            }
            return false;
        }
    }


    static class _3rd_1 {


        public boolean canReach(String s, int minJump, int maxJump) {
            int n = s.length();
            boolean[] dp = new boolean[n];
            dp[0] = true;
            for (int i = 0; i < n; i++) {
                if (!dp[i]) continue;
                //!dp[j] 精髓
                for (int j = i + minJump; j <= Math.min(i + maxJump, n - 1) && !dp[j]; j++) {
//                    if (!dp[j]) continue;
                    if (s.charAt(j) == '0') dp[j] = true;
                }
            }
            return dp[n - 1];
        }
    }


    static class _3rd_2 {
        public boolean canReach(String s, int minJump, int maxJump) {
            int n = s.length();
            int[] preSum = new int[n];
            for (int i = 0; i < minJump; i++) {
                preSum[i] = 1;
            }
            int[] dp = new int[n];
            for (int i = minJump; i < n; i++) {
                int l = i - maxJump, r = i - minJump;
                if (s.charAt(i) == '0') {
                    int t = preSum[r] - (l <= 0 ? 0 : preSum[l - 1]);
                    if (t != 0) dp[i] = 1;
                }
                preSum[i] = preSum[i - 1] + dp[i];
            }
            return dp[n - 1] == 1;

        }
    }

    static class _3rd_3 {
        public static void main(String[] args) {
            _3rd_3 handler = new _3rd_3();
            String s = "011010";
            int minJump = 2;
            int maxJump = 3;
            s = "0000000000";
            minJump = 2;
            maxJump = 5;
            Assert.assertFalse(handler.canReach(s, minJump, maxJump));
        }

        /**
         * "0000000000"
         * 2
         * 5
         **/
        public boolean canReach(String s, int minJump, int maxJump) {
            int n = s.length();
            int[] sum = new int[n];
            int[] dp = new int[n];
            sum[0] = 1;
            dp[0] = 1;
            for (int i = 1; i < n; i++) {
                if (s.charAt(i) == '0') {
                    int l = i - maxJump, r = i - minJump;
                    if (r < 0) continue;
                    int t = sum[r] - (l <= 0 ? 0 : sum[l - 1]);
                    if (t > 0) dp[i] = 1;
                }
                sum[i] = sum[i - 1] + dp[i];
            }
            return dp[n - 1] == 1;
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }


        public int stoneGameVIII(int[] stones) {
            int n = stones.length;
            int[] preSum = new int[n];
            preSum[0] = stones[0];
            for (int i = 1; i < n; i++) preSum[i] = preSum[i - 1] + stones[i];
            int[] dp = new int[n];
            dp[n - 1] = preSum[n - 1];
            for (int i = n - 2; i >= 1; --i) {
                dp[i] = Math.max(dp[i + 1], preSum[i] - dp[i + 1]);
            }
            return dp[1];
        }

    }
}
