package com.frankcooper.bank.week;

import java.util.*;

import org.junit.Assert;

public class Week294 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
        }

        public int percentageLetter(String s, char letter) {
            int[] cnt = new int[26];
            for (char c : s.toCharArray()) cnt[c - 'a']++;
            return cnt[letter - 'a'] * 100 / s.length();
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        public int maximumBags(int[] capacity, int[] rocks, int additionalRocks) {
            int n = capacity.length;
            int[] delta = new int[n];
            for (int i = 0; i < n; i++) {
                delta[i] = capacity[i] <= rocks[i] ? 0 : capacity[i] - rocks[i];
            }
            Arrays.sort(delta);
            int sum = 0;
            for (int i = 0; i < n; i++) {
                sum += delta[i];
                if (sum > additionalRocks) return i;
                if (i == n - 1) return n;
            }
            return 0;
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }


        public int minimumLines(int[][] sp) {
            if (sp.length == 0 || sp.length == 1) return 0;
            if (sp.length == 2) return 1;
            Arrays.sort(sp, (a, b) -> {
                if (a[0] == b[0]) return a[1] - b[1];
                return a[0] - b[0];
            });
            int cnt = 1;
            int[] p = sp[0], p1 = sp[1];
            for (int i = 2; i < sp.length; i++) {
                int[] p2 = sp[i];
                if (check(p, p1, p2)) {
                    continue;
                }
                cnt++;
                p = sp[i - 1];
                p1 = sp[i];
            }
            return cnt;
        }


        private boolean check(int[] p, int[] p1, int[] p2) {
            int x0 = p[0], y0 = p[1];
            int x1 = p1[0], y1 = p1[1];
            int x2 = p2[0], y2 = p2[1];
            return (long) (x1 - x0) * (y2 - y1) == (long) (x2 - x1) * (y1 - y0);

        }
    }

    //MLE
    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
            int[] s = new int[]{747, 812, 112, 1230, 1426, 1477, 1388, 976, 849, 1431, 1885, 1845, 1070, 1980, 280, 1075, 232, 1330, 1868, 1696, 1361, 1822, 524, 1899, 1904, 538, 731, 985, 279, 1608, 1558, 930, 1232, 1497, 875, 1850, 1173, 805, 1720, 33, 233, 330, 1429, 1688, 281, 362, 1963, 927, 1688, 256, 1594, 1823, 743, 553, 1633, 1898, 1101, 1278, 717, 522, 1926, 1451, 119, 1283, 1016, 194, 780, 1436, 1233, 710, 1608, 523, 874, 1779, 1822, 134, 1984};
            handler.totalStrength(s);
        }

        //MLE 67 / 80
        public int totalStrength(int[] strength) {
            int MOD = (int) 1e9 + 7;
            int n = strength.length;
            long[] pre = new long[n + 1];
            for (int i = 0; i < n; i++) {
                pre[i + 1] = pre[i] + strength[i];
            }
            long res = 0;
            //dp[i][j]为[i...j]范围内的最小值
            int[][] dp = new int[n][n];
            for (int i = 0; i < n; i++) Arrays.fill(dp[i], Integer.MAX_VALUE);
            for (int j = 0; j < n; j++) {
                for (int i = 0; i <= j; i++) {
                    if (j > 0) dp[i][j] = Math.min(dp[i][j - 1], strength[j]);
                    else dp[i][j] = Math.min(dp[i][j], strength[j]);
                    res += ((long) dp[i][j] * (pre[j + 1] - pre[i])) % MOD;
                    res %= MOD;
                }
            }
            return (int) res % MOD;
        }
    }

    static class _4th_1 {


        public int totalStrength(int[] strength) {
            int MOD = (int) 1e9 + 7;
            int n = strength.length;
            int[] left = new int[n], right = new int[n];
            Arrays.fill(right, n);
            Deque<Integer> stk = new ArrayDeque<>();
            for (int i = 0; i < n; i++) {
                while (!stk.isEmpty() && strength[stk.peek()] >= strength[i]) {
                    right[stk.pop()] = i;
                }
                left[i] = stk.isEmpty() ? -1 : stk.peek();
                stk.push(i);
            }
            long sum = 0;
            long[] preSum = new long[n + 2];
            for (int i = 1; i <= n; i++) {
                sum += strength[i - 1];
                preSum[i + 1] = (int) ((preSum[i] + sum) % MOD);
            }
            long res = 0;
            for (int i = 0; i < n; i++) {
                int l = left[i] + 1, r = right[i] - 1;
                long tot = ((long) (i - l + 1) * (preSum[r + 2] - preSum[i + 1]) - (long) (r - i + 1) * (preSum[i + 1] - preSum[l])) % MOD;
                res = (res + strength[i] * tot) % MOD;
            }
            return (int) (res + MOD) % MOD;
        }
    }
}
