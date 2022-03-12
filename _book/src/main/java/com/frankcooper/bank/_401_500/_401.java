package com.frankcooper.bank._401_500;

import java.util.*;

import org.junit.Assert;

public class _401 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            handler.readBinaryWatch(1);

        }


        public List<String> readBinaryWatch(int num) {
            List<String> res = new ArrayList<>();
            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 60; j++) {
                    if (countOne(i) + countOne(j) == num) {
                        String t = i + ":" + (j < 10 ? "0" + j : j);
                        res.add(t);
                    }
                }
            }
            return res;
        }


        public int countOne(int x) {
            int res = 0;
            while (x != 0) {
                res += x & 1;
                x >>>= 1;
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
