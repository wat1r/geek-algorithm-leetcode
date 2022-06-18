package com.frankcooper.platform.leetcode.bank._1001_1500;

import java.util.*;

import org.junit.Assert;

public class _1111 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

            String seq = "(()())";
            handler.maxDepthAfterSplit(seq);
        }

        public int[] maxDepthAfterSplit(String seq) {

            int[] res = new int[seq.length()];
            for (int i = 0; i < seq.length(); i++) {
//                res[i] = seq.charAt(i) == '(' ? (i & 1) : ((i + 1) & 1);
                res[i] = (seq.charAt(i) ^ i) & 1;
            }
            return res;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        public int[] maxDepthAfterSplit(String seq) {
            int[] res = new int[seq.length()];
            int depth = 0;
            for (int i = 0; i < seq.length(); i++) {
                if (seq.charAt(i) == '(') {
                    depth++;
                    res[i] = depth % 2;
                } else {
                    res[i] = depth % 2;
                    depth--;
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
