package com.frankcooper.platform.acwing;

import java.util.*;

import org.junit.Assert;

public class _680 {

    static class _1st {

        /**
         * 保留两位小数(k) 一般精度保留到Math.pow(10,-(k+2))
         * 浮点数二分  不需要考虑边界问题
         *
         * @param args
         */
        static final int T = (int) 1e9;
        static int N, M;
        static int[] L;

        public static void main(String[] args) {
            Scanner in = new Scanner(System.in);
            N = in.nextInt();
            M = in.nextInt();
            L = new int[N];
            for (int i = 0; i < N; i++) L[i] = in.nextInt();
            double l = 0, r = T;
            /**
             *如果不想处理精度问题，算下check函数的时间复杂度 O(N) 也就是100_000
             * 拿(int)1e7 / 100_000 = 100次
             *for (int i = 0; i < 100; i++) {
             *
             * }
             */
            while ((r - l) > Math.pow(10, -4)) {
                double mid = (l + r) / 2;
                if (check(mid)) l = mid;
                else r = mid;
            }
            //保留小数点后两位
            System.out.printf("%.2f", l);

        }


        /**
         * mid成立-> [mid,r]
         * mid不成立->[l,mid]
         */
        private static boolean check(double mid) {
            int res = 0;
            for (int i = 0; i < N; i++) {
                res += L[i] / mid;
                if (res >= M) return true;
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
