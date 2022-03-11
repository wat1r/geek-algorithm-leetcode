package com.frankcooper.bank._501_600;

import java.util.*;

import org.junit.Assert;

public class _541 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


            String s = "abcdefg";
            char[] ch = s.toCharArray();
//            handler.reverse(ch, 0, ch.length - 1);
            s = "abcdefgabcdefg";
            int k = 3;
            handler.reverseStr(s, k);
        }


        public String reverseStr(String s, int k) {
            char[] ch = s.toCharArray();
            int i = 0, j = 2 * k;
            while (i < ch.length) {
                int end = i + k - 1 >= ch.length ? ch.length - 1 : i + k - 1;
                reverse(ch, i, end);
                i = j;
                j = i + 2 * k;
            }
            return String.valueOf(ch);
        }


        private void reverse(char[] ch, int i, int j) {
            while (i < j) {
                char t = ch[i];
                ch[i++] = ch[j];
                ch[j--] = t;
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
