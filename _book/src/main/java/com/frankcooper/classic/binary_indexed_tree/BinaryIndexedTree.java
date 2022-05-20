package com.frankcooper.classic.binary_indexed_tree;

public class BinaryIndexedTree {

    static class _1st {
        int N = 10010;
        int[] tree = new int[N];

        int lowbit(int x) {
            return -x & x;
        }

        int query(int x) {
            int sum = 0;
            for (int i = x; i >= 0; i -= lowbit(i)) sum += tree[i];
            return sum;
        }

        void update(int x, int v) {
            for (int i = x; i <= N; i += lowbit(i)) tree[i] += v;
        }


        int query(int l, int r) {
            return query(r + 1) - query(l);
        }
    }


    static class _2nd {
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
}

