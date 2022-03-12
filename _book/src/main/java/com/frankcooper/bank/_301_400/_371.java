package com.frankcooper.bank._301_400;


import com.frankcooper.utils.PrintUtils;
import org.junit.Assert;

import java.util.Iterator;
import java.util.List;

public class _371 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
//            Assert.assertEquals(handler.getSum(1, 2), 3);
//            Assert.assertEquals(handler.getSum(-2, 3), 1);
//            Assert.assertEquals(handler.getSum(2, 3), 5);
            Assert.assertEquals(handler.getSum(3, 4), 7);
        }


        public int getSum(int a, int b) {
            int res = 0, carry = 0;
            for (int i = 0; i < 32; i++) {
                int bit_a = a >> i & 1;
                int bit_b = b >> i & 1;
                if ((bit_a & bit_b) == 1) {
                    res |= carry << i;
                    carry = 1;
                } else if ((bit_a ^ bit_b) == 1) {
                    if (carry == 1) carry = 1;
                    else res |= 1 << i;
                } else if (a != 0 && b != 0) {
                    res |= carry << i;
                }

                a >>= i;
                b >>= i;
            }
            return res;


        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
//            Assert.assertEquals(handler.getSum(3, 4), 7);
            Assert.assertEquals(12, handler.getSum(5, 7));
        }


        /**
         * https://leetcode-cn.com/problems/sum-of-two-integers/solution/li-yong-wei-cao-zuo-shi-xian-liang-shu-qiu-he-by-p/
         *
         * @param a
         * @param b
         * @return
         */

        public int getSum(int a, int b) {
            while (b != 0) {
                System.out.printf("a:%s\nb:%s\n", PrintUtils.toBinaryString(a, 4), PrintUtils.toBinaryString(b, 4));
                int t = a ^ b;
                System.out.printf("t:%s\n", PrintUtils.toBinaryString(t, 4));
                b = (a & b) << 1;
                System.out.printf("b':%s\n", PrintUtils.toBinaryString(b, 4));
                a = t;
            }
            return a;
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
