package com.frankcooper.bank._1501_2000;

import java.util.*;

import org.junit.Assert;

public class _1786 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        public String mergeAlternately(String w1, String w2) {
            StringBuilder res = new StringBuilder();
            int i = 0, j = 0;
            while (i < w1.length() && j < w2.length()) {
                res.append(w1.charAt(i++));
                res.append(w2.charAt(j++));
            }
            while (i < w1.length()) res.append(w1.charAt(i++));
            while (j < w2.length()) res.append(w2.charAt(j++));
            return res.toString();
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
