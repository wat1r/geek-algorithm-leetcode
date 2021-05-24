package com.frankcooper.interview;

import java.util.*;

import org.junit.Assert;

public class _01_03 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            String S = "Mr John Smith    ";
            int length = 13;
            handler.replaceSpaces(S, length);
        }


        public String replaceSpaces(String S, int length) {
            char[] chas = S.toCharArray();
            int idx = chas.length - 1;
            for (int i = length - 1; i >= 0; --i) {
                if (chas[i] == ' ') {
                    chas[idx--] = '0';
                    chas[idx--] = '2';
                    chas[idx--] = '%';
                } else {
                    chas[idx--] = chas[i];
                }
            }
            return new String(chas, idx + 1, chas.length - idx - 1);
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
