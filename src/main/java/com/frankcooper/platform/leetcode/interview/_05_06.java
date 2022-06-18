package com.frankcooper.platform.leetcode.interview;

import com.frankcooper.utils.PrintUtils;
import org.junit.Assert;

public class _05_06 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int A = 29, B = 15;
            Assert.assertEquals(2, handler.convertInteger(A, B));
        }


        public int convertInteger(int A, int B) {
            PrintUtils.toBinaryString(A, 6);
            PrintUtils.toBinaryString(B, 6);
            int C = A ^ B;
            PrintUtils.toBinaryString(C, 6);
            int res = 0;
            while (C != 0) {
                C &= (C - 1);
                res++;
                
            }
            return res;
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
