package com.frankcooper.other;

import java.util.Arrays;

public class FrogJump {


    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
        }

        int INF = Integer.MAX_VALUE / 2;

        public int countMinJumps(int[] jumps) {
            return helper(jumps, 0);
        }

        public int helper(int[] jumps, int currIdx) {
            if (currIdx == jumps.length - 1) return 0;
            if (jumps[currIdx] == 0) return INF;
            int total = INF;
            int start = currIdx + 1, end = currIdx + jumps[currIdx];
            while (start < jumps.length && start <= end) {
                int min = helper(jumps, start++);
                if (min != INF) total = Math.min(total, min + 1);
            }
            return total;
        }
    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }


        int INF = Integer.MAX_VALUE / 2;
        Integer[] memo;

        public int countMinJumps(int[] jumps) {
            memo = new Integer[jumps.length];
            return helper(jumps, 0);
        }

        public int helper(int[] jumps, int currIdx) {
            if (currIdx == jumps.length - 1) return 0;
            if (jumps[currIdx] == 0) return INF;
            if (memo[currIdx] != null) return memo[currIdx];
            int total = INF;
            int start = currIdx + 1, end = currIdx + jumps[currIdx];
            while (start < jumps.length && start <= end) {
                int min = helper(jumps, start++);
                if (min != INF) total = Math.min(total, min + 1);
            }
            return memo[currIdx] = total;
        }

    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }

        int INF = Integer.MAX_VALUE / 2;

        public int countMinJumps(int[] jumps) {
            int n = jumps.length;
            int[] f = new int[n];
            Arrays.fill(f, INF);
            f[0] = 0;
            for (int start = 0; start < n - 1; start++) {
                for (int end = start + 1; end < jumps[start] + start && end < n; end++) {
                    f[end] = Math.min(f[end], f[start] + 1);
                }
            }
            return f[n - 1];
        }
    }


}
