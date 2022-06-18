package com.frankcooper.platform.leetcode.interview;

import java.util.*;

import org.junit.Assert;

public class _05_08 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        /**
         * https://leetcode-cn.com/problems/draw-line-lcci/solution/javawei-yun-suan-by-loser-11/
         */

        public int[] drawLine(int length, int w, int x1, int x2, int y) {
            int[] res = new int[length];
            int offset = y * w / 32;
            int start = x1 / 32 + offset;
            int end = x2 / 32 + offset;
            for (int i = start; i <= end; i++) {
                res[i] = -1;
            }
            res[start] = res[start] >>> x1 % 32;
            res[end] = res[end] & Integer.MIN_VALUE >> x2 % 32;
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
