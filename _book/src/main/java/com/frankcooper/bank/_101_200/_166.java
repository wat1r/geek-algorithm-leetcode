package com.frankcooper.bank._101_200;

import java.util.*;

import org.junit.Assert;

public class _166 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

            int numerator = 4, denominator = 333;
            Assert.assertEquals("0.(012)", handler.fractionToDecimal(numerator, denominator));

        }

        public String fractionToDecimal(int numerator, int denominator) {
            if (numerator == 0) {
                return "0";
            }
            StringBuilder fraction = new StringBuilder();
            // If either one is negative (not both)
            if (numerator < 0 ^ denominator < 0) {
                fraction.append("-");
            }
            // Convert to Long or else abs(-2147483648) overflows
            long dividend = Math.abs(Long.valueOf(numerator));
            long divisor = Math.abs(Long.valueOf(denominator));
            fraction.append(String.valueOf(dividend / divisor));
            long remainder = dividend % divisor;
            if (remainder == 0) {
                return fraction.toString();
            }
            fraction.append(".");
            Map<Long, Integer> map = new HashMap<>();
            while (remainder != 0) {
                if (map.containsKey(remainder)) {
                    fraction.insert(map.get(remainder), "(");
                    fraction.append(")");
                    break;
                }
                map.put(remainder, fraction.length());
                remainder *= 10;
                fraction.append(String.valueOf(remainder / divisor));
                remainder %= divisor;
            }
            return fraction.toString();
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
