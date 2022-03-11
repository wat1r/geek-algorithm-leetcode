package com.frankcooper.bank._701_1000;

import java.util.*;

import org.junit.Assert;

public class _917 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

            String s =
                    "ab-cd";

            handler.reverseOnlyLetters(s);
        }


        public String reverseOnlyLetters(String s) {

            char[] ch = s.toCharArray();
            int l = 0, r = ch.length - 1;
            while (l < r) {
                if (Character.isLetter(ch[l]) && Character.isLetter(ch[r])) {
                    char t = ch[l];
                    ch[l++] = ch[r];
                    ch[r--] = t;
                } else if (!Character.isLetter(ch[l])) l++;
                else if (!Character.isLetter(ch[r])) r--;
            }
            return String.valueOf(ch);
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
