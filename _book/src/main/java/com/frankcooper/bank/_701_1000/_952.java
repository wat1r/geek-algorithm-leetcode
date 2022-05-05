package com.frankcooper.bank._701_1000;

import org.junit.Assert;

import java.util.Arrays;

/*import java.util.*;
import org.junit.Assert;*/
public class _952 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[] nums = {2, 3, 6, 7, 4, 12, 21, 39};
            Assert.assertEquals(8, handler.largestComponentSize(nums));
            nums = new int[]{20, 50, 9, 63};
//            Assert.assertEquals(2, handler.largestComponentSize(nums));

        }


        int N = 100010;

        class UnionFind {
            int[] parent;
            int[] rank;

            public UnionFind(int[] nums) {
                parent = new int[N];
                rank = new int[N];
                for (int i = 2; i < N; i++) {
                    parent[i] = i;
                }
                Arrays.fill(rank, 1);
            }

            public int find(int i) {
                if (parent[i] != i) parent[i] = find(parent[i]);
                return parent[i];
            }

            public void union(int i, int j) {
                int rootx = find(i);
                int rooty = find(j);
                if (rootx != rooty) {
                    if (rank[rootx] > rank[rooty]) {
                        parent[rooty] = rootx;
                        rank[rootx]++;
                    } else if (rank[rooty] > rank[rootx]) {
                        parent[rootx] = rooty;
                        rank[rooty]++;
                    } else {
                        parent[rooty] = rootx;
                        rank[rootx]++;
                    }
                }
            }
        }

        public int largestComponentSize(int[] nums) {
            UnionFind uf = new UnionFind(nums);
            int maxx = 0;
            for (int x : nums) {
                int j = x;
                for (int i = 2; i <= x / i; i++) {
                    boolean flag = false;
                    while (x % i == 0) {
                        x /= i;
                        flag = true;
                    }
                    if (flag) uf.union(j, i);
                }
                if (x > 1) uf.union(j, x);
            }
            int[] cnt = new int[N];
            for (int x : nums) {
                int i = uf.find(x);
                maxx = Math.max(maxx, ++cnt[i]);
            }
            return maxx;
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
