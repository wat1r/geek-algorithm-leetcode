package com.frankcooper.platform.leetcode.bank._1501_2000;

public class _1760 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }

        public int minimumSize(int[] nums, int maxOperations) {
            long res = 0;
            long lo = 1, hi = (int) 1e9;
            while (lo < hi) {
                long mid = lo + (hi - lo) / 2;
                if (check(nums, maxOperations, mid)) {
                    hi = mid-1;
                    res = mid;
                } else {
                    lo = mid + 1;
                }
            }
            return (int) res;
        }

        public boolean check(int[] nums, int maxOperations, long cost) {
            int res = 0;
            for (int x : nums) {
                if (x % cost == 0) res += x / (cost - 1);
                else res += x / cost;
            }
            return res <= maxOperations;
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
