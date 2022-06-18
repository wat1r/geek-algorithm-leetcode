package com.frankcooper.platform.leetcode.interview;

import java.util.*;

import org.junit.Assert;

public class _16_14 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }

        public int[] bestLine(int[][] ps) {
            Map<Integer, Integer> map = new HashMap<>();
            Map<Integer, int[]> record = new HashMap<>();
            int maxCnt = 0, maxHash = 0;
            int[] res = new int[2];
            for (int i = 0; i < ps.length - 1; i++) {
                for (int j = i + 1; j < ps.length; j++) {
                    int x1 = ps[i][0], y1 = ps[i][1], x2 = ps[j][0], y2 = ps[j][1];
                    long A = y2 - y1;//y2-y1;
                    long B = x1 - x2;//x1-x2
                    long C = x2 * y1 - x1 * y2;//x2*y1 - x1*y2
                    long gcd = gcd(gcd(A, B), C);
                    A /= gcd;
                    B /= gcd;
                    C /= gcd;
                    int hash = hash((int) A, (int) B, (int) C);
                    int cnt = map.getOrDefault(hash, 0) + 1;
                    map.put(hash, cnt);
                    if (cnt == 1) {
                        record.put(hash, new int[]{i, j});
                    }
                    if (maxCnt < cnt) {
                        maxCnt = cnt;
                        maxHash = hash;
                        res = record.get(hash);
                    } else if (maxCnt == cnt) {
                        int[] p1 = record.get(maxHash), p2 = record.get(hash);
                        if (p1[0] > p2[0] || p1[0] == p2[0] && p1[1] > p2[1]) {
                            maxHash = hash;
                            res = p2;
                        }
                    }
                }
            }
            return res;
        }

        private long gcd(long a, long b) {
            return b == 0 ? a : gcd(b, a % b);
        }

        //随便写的hash方法。反正数据量也不大。冲突可能性小
        private int hash(int a, int b, int c) {
            a = (a ^ (a >>> 16) & 0x0000ffff) << 20;
            b = (b ^ (b >>> 16) & 0x0000ffff) << 10;
            c = c ^ (c >>> 16) & 0x00000ffff;
            return a | b | c;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        public int[] bestLine(int[][] ps) {
            int[] res = new int[2];
            int n = ps.length;
            int maxCnt = 0;
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    int cnt = 2;
                    int x1 = ps[i][0] - ps[j][0];
                    int y1 = ps[i][1] - ps[j][1];
                    for (int k = j + 1; k < n; k++) {
                        int x2 = ps[i][0] - ps[k][0];
                        int y2 = ps[i][1] - ps[k][1];
                        if (x1 * y2 == y1 * x2) ++cnt;
                    }
                    if (cnt > maxCnt) {
                        maxCnt = cnt;
                        res[0] = i;
                        res[1] = j;
                    }
                }
            }
            return res;
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
