package com.frankcooper.bank._101_200;

import java.util.*;

import org.junit.Assert;

public class _168 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

            System.out.printf("%d\n", 2591 % 16);
//            Assert.assertEquals("AB", handler.convertToTitle(28));
//            Assert.assertEquals("ZY", handler.convertToTitle(701));
//            handler.convert(28);
            handler.convert(26);
//            handler.convert(701);
        }

        private String convert(int n) {
            StringBuilder ans = new StringBuilder();
            while (n > 0) {
                int c = n % 26;
                ans.insert(0, (char) ('A' + c - 1));
                n /= 26;
            }
            return ans.toString();
        }


        public String convertToTitle(int n) {

            StringBuilder res = new StringBuilder();
            while (n > 0) {
                n--;
                res.append((char) ('A' + (n % 26)));
                n /= 26;
            }
            return res.reverse().toString();
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
