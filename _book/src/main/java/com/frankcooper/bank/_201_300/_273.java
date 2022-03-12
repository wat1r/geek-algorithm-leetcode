package com.frankcooper.bank._201_300;

import java.util.*;

import org.junit.Assert;

public class _273 {

    static class _1st {

        public static void main(String[] args) {
            _1st handler = new _1st();
            StringBuilder sb = new StringBuilder();
            int num = 1234567891;
            handler.numberToWords(num);
//            handler.helper(10002, sb);
            System.out.printf("%s", "dd");
        }


        private final String[] THOUSAND = {"", "Thousand", "Million", "Billion"};
        private final String[] LESS_THAN_TWENTY = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
        private final String[] HUNDRED = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

        public String numberToWords(int num) {
            if (num == 0) return "Zero";
            StringBuilder res = new StringBuilder();
            int index = 0;
            while (num > 0) {
                if (num % 1000 != 0) {
                    StringBuilder sb = new StringBuilder();
                    helper(num % 1000, sb);
                    res.insert(0, sb.append(THOUSAND[index]).append(" "));
                }
                index++;
                num /= 1000;
            }
            return res.toString().trim();
        }


        private void helper(int num, StringBuilder sb) {
            if (num == 0) return;
            if (num < 20) sb.append(LESS_THAN_TWENTY[num]).append(" ");
            else if (num < 100) {
                sb.append(HUNDRED[num / 10]).append(" ");
                helper(num % 10, sb);
            } else {
                sb.append(LESS_THAN_TWENTY[num / 100]).append(" Hundred").append(" ");
                helper(num % 100, sb);
            }
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
