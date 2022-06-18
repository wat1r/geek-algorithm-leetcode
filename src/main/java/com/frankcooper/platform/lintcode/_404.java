package com.frankcooper.platform.lintcode;

public class _404 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[] A = new int[]{1, 2, 3, 4};
            int start = 1, end = 3;
            handler.subarraySumII(A, start, end);
        }


        public int subarraySumII(int[] A, int start, int end) {
            int n = A.length;
            int[] preSum = new int[n + 1];
            for (int i = 0; i < n; i++) preSum[i + 1] = preSum[i] + A[i];
            int res = 0;
            int l = 0, r = 0;
            for (int i = 0; i <= n; i++) {
                while (r < i && preSum[i] - preSum[r] >= start) r++;
                while (l <= r && preSum[i] - preSum[l] > end) l++;
                res += r - l;
            }
            return res;
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
