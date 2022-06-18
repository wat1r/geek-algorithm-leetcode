package com.frankcooper.platform.leetcode.bank._1_100;

//import com.frankcooper.platform.leetcode.swordoffer.utils.PrintUtils;

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
            //转化成long类型，进行移位操作
            long N = n;
            //当n为负数的时候，x^n = 1/(x^(-n))
            if (N < 0) {
                x = 1 / x;
                N = -N;
            }
            double res = 1.0;
            while (N != 0) {
                if ((N & 1) == 1) res *= x;//多余一位x累乘到res
                x *= x;//底数x翻倍
                N >>= 1;//N减半
            }
            return res;
        }
    }

    static class _4th_2 {
        public static void main(String[] args) {
            _4th_2 handler = new _4th_2();
            double x = 2.00000;
            int n = -2;
            handler.myPow(x, n);
        }

        public double myPow(double x, int n) {
            double res = 1.0;
            for (int i = n; i != 0; i /= 2) {
                if ((i & 1) == 1) {
                    res *= x;
                }
                x *= x;
            }
            //如 x = 2.00000   n=-2这样的case，当n是负数的时候，取倒数
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
            //对n<0的情况单独判断处理 当n为负数的时候，取相反数 -(n+1)
            if (n < 0) {
                return 1 / x * myPow(1 / x, -(n + 1));
            }
            return ((n % 2) == 1) ? x * myPow(x * x, n / 2) : myPow(x * x, n / 2);
        }


    }

    static class _4th_4 {
        public static void main(String[] args) {
            _4th_4 handler = new _4th_4();
            double x = 2.00000;
            int n = -2;
            n = -2147483648;
            handler.myPow(x, n);
        }

        public double myPow(double x, int n) {
            if (n == 0) return 1;
            //对于MIN_VALUE这个数时，特判
            if (n == Integer.MIN_VALUE) {
                x = x * x;
                n = n / 2;
            }
            //n为负数的时候，取相反数
            if (n < 0) {
                n = -n;
                x = 1 / x;
            }
            return (n % 2 == 0) ? myPow(x * x, n / 2) : x * myPow(x * x, n / 2);
        }
    }
}
