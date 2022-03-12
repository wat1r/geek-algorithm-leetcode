package com.frankcooper.interview;

import java.util.*;

import org.junit.Assert;

public class _17_01 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            handler.add(8,13);

        }


        public int add(int a, int b) {
            while (b != 0) {
                int sum = a ^ b;
                int carry = (a & b) << 1;
                a =sum;
                b = carry;
            }
            return a;

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
