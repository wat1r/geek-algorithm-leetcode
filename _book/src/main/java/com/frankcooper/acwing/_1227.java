package com.frankcooper.platform.acwing;

import java.util.*;

import org.junit.Assert;

public class _1227 {

    static class _1st {

        static final int M = 100010;
        static int N, K;//N块巧克力，至少K块巧克力
        static int[] H = new int[M];//长
        static int[] W = new int[M];//宽

        public static void main(String[] args) {
            Scanner in = new Scanner(System.in);
            N = in.nextInt();
            K = in.nextInt();
            for (int i = 0; i < N; i++) {
                H[i] = in.nextInt();
                W[i] = in.nextInt();
            }
            int l = 1, r = (int) 1e5;//
            while (l < r) {
                //[l,mid-1]这个区间是满足要求的
                //[mid,r]这个区间是满足要求的 mid一个可能的值
                int mid = (l + r + 1) / 2;//上取整
                if (check(mid)) l = mid;
                else r = mid - 1;
            }
            System.out.println(r);

        }

        /**
         * 给定一个mid值，用这个mid值，挨个拍下来，切出巧克力，是否大于K块，我们希望这个mid尽可能的小
         * 也就是能切出尽可能多的巧克力 分母越小，除数越大
         *
         * @param mid
         * @return
         */
        private static boolean check(int mid) {
            long res = 0;//能切出的巧克力的个数
            for (int i = 0; i < N; i++) {
                res += (long) H[i] / mid * (W[i] / mid);
                if (res >= K) return true;
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
