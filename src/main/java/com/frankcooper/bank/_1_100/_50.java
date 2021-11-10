package com.frankcooper.bank._1_100;

//import com.frankcooper.swordoffer.utils.PrintUtils;

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


    static class _4th {

        public static void main(String[] args) {

        }

        public double myPow(double x, int n) {
            long N = n;
            if (N < 0) {
                x = 1 / x;
                N = -N;

            }
            double res = 1.0;
            while (N != 0) {
                if ((N & 1) == 1) res *= x;
                x *= x;
                N >>= 1;
            }
            return res;
        }

    }

    static class _4th_1 {
        public static void main(String[] args) {

        }

        public double myPow(double x, int n) {
            long N = n;
            if (N < 0) {
                x = 1 / x;
                N = -N;
            }
            double res = 1.0;
            while (N != 0) {
                if ((N & 1) == 1) res *= x;
                x *= x;
                N >>= 1;
            }
            return res;
        }
    }

    static class _4th_2 {
        public double myPow(double x, int n) {
            double res = 1.0;
            for (int i = n; i != 0; i /= 2) {
                if ((i & 1) == 1) {
                    res *= x;
                }
                x *= x;
            }
            return n < 0 ? 1 / res : res;
        }
    }

    static class _4th_3 {
        //注意 整数溢出 <0 的处理方式

        /**
         * When n is negative, we are reducing it value by one and then converting to positive integer.
         * e.g. n = -2 is converted into n = 1
         * Similarly, n = -2147483648 is converted to n = 2147483647
         * <p>
         * Since we reduced value by one we are accommodating that at the end by multiplying it with 1/x.
         *
         * @param x
         * @param n
         * @return
         */
        public double myPow(double x, int n) {
            if (n == 0) return 1.0;
            if (n < 0) {
                return 1 / x * myPow(1 / x, -(n + 1));
            }
            return ((n % 2) == 1) ? x * myPow(x * x, n / 2) : myPow(x * x, n / 2);
        }


    }

    static class _4th_4 {
        public double myPow(double x, int n) {
            if (n == 0) return 1;
            if (n == Integer.MIN_VALUE) {
                x = x * x;
                n = n / 2;
            }
            if (n < 0) {
                n = -n;
                x = 1 / x;
            }

            return (n % 2 == 0) ? myPow(x * x, n / 2) : x * myPow(x * x, n / 2);
        }
    }
}
