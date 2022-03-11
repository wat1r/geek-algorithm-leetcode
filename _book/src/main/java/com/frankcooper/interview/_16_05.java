package com.frankcooper.interview;

import java.util.*;

import org.junit.Assert;

public class _16_05 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            handler.trailingZeroes(25);
        }


        /**
         * 尾数为0，说明有10，说明因子有2*5
         * 显然2比5多，5的个数就是尾数0的个数
         *
         * @param n
         * @return
         */

        public int trailingZeroes(int n) {
            int count = 0;
            while (n >= 5) {
                n /= 5;
                count += n;
            }

            return count;
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
