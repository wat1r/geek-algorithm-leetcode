package com.frankcooper.bank._201_300;

import java.util.*;

import org.junit.Assert;

public class _201 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int left = 1, right = 2147483647;
//            Assert.assertEquals(handler.rangeBitwiseAnd(left, right), 0);
            left = 9;
            right = 12;
            Assert.assertEquals(handler.rangeBitwiseAnd(left, right), 8);
        }


        public int rangeBitwiseAnd(int left, int right) {//公共前缀 二进制
            int bit = 0;
            while (left != right) {
                left >>= 1;
                right >>= 1;
                bit++;
            }
            return left << bit;//
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
