package com.frankcooper.platform.leetcode.bank._301_400;

import java.util.*;

import org.junit.Assert;

public class _354 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        //TLE
        public int maxEnvelopes(int[][] envelopes) {
            int n = envelopes.length;
            Arrays.sort(envelopes, (a, b) -> a[0] != b[0] ? a[0] - b[0] : b[1] - a[1]);//按w的升序，h的降序排列
            int[] f = new int[n];
            Arrays.fill(f, 1);
            int res = 1;
            for (int i = 1; i < n; i++) {
                for (int j = 0; j < i; j++) {
                    if (envelopes[j][1] < envelopes[i][1]) {
                        f[i] = Math.max(f[i], f[j] + 1);
                    }
                }
                res = Math.max(res, f[i]);
            }
            return res;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }


        //设 f[j] 表示 h 的前 i 个元素可以组成的长度为 j 的最长严格递增子序列的末尾元素的最小值，如果不存在长度为 j 的最长严格递增子序列，对应的 f 值无定义。在定义范围内，可以看出 f 值是严格单调递增的，因为越长的子序列的末尾元素显然越大
        public int maxEnvelopes(int[][] envelopes) {
            int n = envelopes.length;
            Arrays.sort(envelopes, (a, b) -> a[0] != b[0] ? a[0] - b[0] : b[1] - a[1]);//按w的升序，h的降序排列
            List<Integer> f = new ArrayList<>();
            f.add(envelopes[0][1]);
            for (int i = 1; i < n; i++) {
                int target = envelopes[i][1];
                if (target > f.get(f.size() - 1)) {
                    f.add(target);
                } else {
                    int index = binarySearch(f, target);
                    f.set(index, target);
                }
            }
            return f.size();
        }

        public int binarySearch(List<Integer> f, int target) {
            int lo = 0, hi = f.size() - 1;
            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;
                if (f.get(mid) < target) {
                    lo = mid + 1;
                } else {
                    hi = mid;
                }
            }
            return lo;
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
