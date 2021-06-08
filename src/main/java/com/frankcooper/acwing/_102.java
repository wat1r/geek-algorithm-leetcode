package com.frankcooper.acwing;

import java.util.*;

import org.junit.Assert;

public class _102 {

    static class _1st {


        final static int N = 100_005;
        static double[] preSum = new double[N];//前缀和
        static int[] cows = new int[N];
        static int n, f;//n块地，f至少包含的地的数量

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            n = scanner.nextInt();
            f = scanner.nextInt();
            for (int i = 1; i <= n; i++) cows[i] = scanner.nextInt();
            double l = 0, r = 2000;
            while ((r - l) > Math.pow(10, -5)) {
                double mid = (l + r) / 2;
                if (check(mid)) l = mid;
                else r = mid;
            }
            System.out.println((int) (r * 1000));
        }


        /**
         * 是否找到一个 一段连续的区间且区间长度不小于F且平均数大于平均数avg
         * 做成前缀和的 都减去avg这个基准的数
         * 双指针保证 i 与 j  的距离大于 f
         *
         * @param avg
         * @return
         */
        private static boolean check(double avg) {
            for (int i = 1; i <= n; i++) preSum[i] = preSum[i - 1] + cows[i] - avg;
            double minv = 0;
            for (int i = 0, j = f; j <= n; i++, j++) {
                minv = Math.min(minv, preSum[i]);
                if (preSum[j] - minv >= 0) return true;
            }
            return false;
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
