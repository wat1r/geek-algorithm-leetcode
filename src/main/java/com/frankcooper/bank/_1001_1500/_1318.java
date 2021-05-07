package com.frankcooper.bank._1001_1500;

import java.util.*;

import org.junit.Assert;

public class _1318 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
//            Assert.assertEquals(handler.minFlips(2, 6, 5), 3);
            Assert.assertEquals(handler.minFlips(8, 3, 5), 3);

        }


        public int minFlips(int a, int b, int c) {
            int res = 0;
            while (c != 0) {
                if ((c & 1) == 1) {//判断c的低位
                    if ((a & 1) == 0 && (b & 1) == 0) res++;
                } else {
                    if ((a & 1) == 1) res++;
                    if ((b & 1) == 1) res++;
                }
                c >>= 1;
                a >>= 1;
                b >>= 1;
            }
            while (a != 0) {//a b 高位数1 的个数
                if ((a & 1) == 1) res++;
                a >>= 1;
            }
            while (b != 0) {
                if ((b & 1) == 1) res++;
                b >>= 1;
            }
            return res;
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();

        }


        public int minFlips(int a, int b, int c) {//道理与上面类似，更加优雅点
            int res = 0;
            for (int i = 0; i < 32; i++) {
                int bit_a = a >> i & 1;
                int bit_b = b >> i & 1;
                int bit_c = c >> i & 1;
                if (bit_c == 0) {
                    res += bit_a + bit_b;
                } else {
                    res += (bit_a + bit_b == 0) ? 1 : 0;
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
