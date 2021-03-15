package com.frankcooper.bank;

import com.frankcooper._Model;
import com.frankcooper.swordoffer.utils.PrintUtils;

public class _50 {


    public static void main(String[] args) {


//        System.out.println(PrintUtils.addZeroForNum(Integer.toBinaryString(77), 10));

        _2nd handler = new _2nd();
        handler.myPow(2.0, 77);
    }


    static class _1st {
        public double myPow(double x, int n) {
            long N = n;
            return N >= 0 ? helper(x, N) : 1.0 / helper(x, -N);
        }

        public double helper(double x, long N) {
            if (N == 0) return 1.0;
            double y = helper(x, N / 2);
            double res;
            if (N % 2 == 0) res = y * y;
            else res = y * y * x;
            return res;
        }
    }


    static class _2nd {
        public double myPow(double x, int n) {
            long N = n;
            return N >= 0 ? helper(x, N) : 1.0 / helper(x, -N);
        }

        public double helper(double x, long N) {
            double res = 1.0;
            double x_c = x;
            while (N > 0) {
                if (N % 2 == 1) {
                    res *= x_c;
                }
                x_c *= x_c;
                N /= 2;
            }
            return res;
        }

    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }

        public double myPow(double x, int n) {

            long N = n;
            if (N < 0) {
                x = 1 / x;
                N = -N;
            }
            double ans = 1.0;
            while (N > 0) {
                if ((N & 1) == 1) ans *= x;
                x *= x;
                N >>= 1;
            }
            return ans;
        }
    }
}
