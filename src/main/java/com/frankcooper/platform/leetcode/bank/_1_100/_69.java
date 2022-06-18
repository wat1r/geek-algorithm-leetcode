package com.frankcooper.platform.leetcode.bank._1_100;

/*import java.util.*;
import org.junit.Assert;*/
public class _69 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int x = 2147483647;
            handler.mySqrt(x);

        }

        public int mySqrt1(int x) {
            long l = 0, r = x;
            while (l < r) {
                long m = l + (r - l) / 2;
                long t = m * m;
                if (t == x) return (int) m;
                else if (t > x) r = m;
                else if (t < x) l = m + 1;
            }
            return (int) (l * l == x ? l : l - 1);
        }

        public int mySqrt(int x) {
            //将x+1 最后位置的时候l-1返回
            long l = 0, r = (long) x + 1;
            while (l < r) {
                long m = l + r >> 1;
                long t = m * m;
                if (t == x) return (int) m;
                else if (t > x) r = m;
                else if (t < x) l = m + 1;
            }
            return (int) (l - 1);
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            System.out.println(2 << 15);
            System.out.println(Math.pow(2, 16));
        }

        //上取整
        public int mySqrt(int x) {
            long l = 0, r = x;
            while (l < r) {
                long mid = l + r + 1 >> 1;
                if (mid * mid <= x) {
                    l = mid;
                } else {
                    r = mid - 1;
                }
            }
            return (int) l;
        }


    }


    //牛顿迭代法
    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }


        int y;

        public int mySqrt(int x) {
            y = x;
            if (x == 0) return 0;
            return (int) helper(x);
        }

        private double helper(double x) {
            double res = (x + y / x) / 2;
            return res == x ? x : helper(res);
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
