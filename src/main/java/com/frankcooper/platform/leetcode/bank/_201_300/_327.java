package com.frankcooper.platform.leetcode.bank._201_300;

import java.util.*;

public class _327 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
        }


        public int countRangeSum(int[] nums, int lower, int upper) {
            int n = nums.length;
            long[] preSum = new long[n + 1];
            for (int i = 1; i <= n; i++) preSum[i] = preSum[i - 1] + nums[i - 1];
            Set<Long> set = new TreeSet<>();
            for (long x : preSum) {
                set.add(x);
                set.add(x - lower);
                set.add(x - upper);
            }
            int M = 1;
            Map<Long, Integer> map = new HashMap<>();
            for (long x : set) {
                map.put(x, M++);
            }
            BIT bit = new BIT(M);
            int res = 0;
            for (long x : preSum) {
                int lo = map.get(x - upper), hi = map.get(x - lower);
                int index = map.get(x);
                res += bit.query(hi) - bit.query(lo - 1);
                bit.update(index, 1);
            }
            return res;

        }


        static class BIT {
            int[] tree;
            int n;

            public BIT(int n) {
                this.n = n;
                this.tree = new int[n + 1];
            }

            public static int lowbit(int x) {
                return x & (-x);
            }

            public void update(int x, int d) {
                while (x <= n) {
                    tree[x] += d;
                    x += lowbit(x);
                }
            }

            public int query(int x) {
                int ans = 0;
                while (x != 0) {
                    ans += tree[x];
                    x -= lowbit(x);
                }
                return ans;
            }
        }
    }


    static class BIT {
        int[] tree;
        int n;

        public BIT(int n) {
            this.n = n;
            this.tree = new int[n + 1];
        }

        public static int lowbit(int x) {
            return x & (-x);
        }

        public void update(int x, int d) {
            while (x <= n) {
                tree[x] += d;
                x += lowbit(x);
            }
        }

        public int query(int x) {
            int ans = 0;
            while (x != 0) {
                ans += tree[x];
                x -= lowbit(x);
            }
            return ans;
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
