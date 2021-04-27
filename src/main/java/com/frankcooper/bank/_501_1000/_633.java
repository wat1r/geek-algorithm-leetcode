package com.frankcooper.bank._501_1000;

import java.util.*;

import org.junit.Assert;

public class _633 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
//            Assert.assertTrue(handler.judgeSquareSum(5));
//            Assert.assertTrue(handler.judgeSquareSum(4));
            Assert.assertTrue(handler.judgeSquareSum(2));

            //2147483645  101/124
        }


        public boolean judgeSquareSum(int c) {
            int lo = 0, hi = (int) Math.sqrt(c);
            while (hi > 0) {
                while (lo <= hi) {
                    int t = lo * lo + hi * hi;
                    if (t == c) return true;
                    else if (t > c) break;
                    else if (t < c) lo++;
                }
                lo = 0;
                hi--;
            }
            return c == 0;
        }


//        public boolean check(int target, int lo, int hi) {//判断 lo 和 hi之间能不能有两个数平方和 = target 固定了 hi
//            while (lo < hi) {
//
//            }
//
//        }

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
