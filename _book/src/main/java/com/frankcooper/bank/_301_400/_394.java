package com.frankcooper.bank._301_400;

import java.util.*;

import org.junit.Assert;

public class _394 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }


        public String decodeString(String s) {

            LinkedList<Integer> numList = new LinkedList<>();
            LinkedList<String> strList = new LinkedList<>();
            int multi = 0;
            StringBuilder res = new StringBuilder();
            char[] chas = s.toCharArray();
            for (int i = 0; i < chas.length; i++) {
                char c = chas[i];
                if (c == '[') {
                    numList.addLast(multi);
                    strList.addLast(res.toString());
                    multi = 0;
                    res = new StringBuilder();
                } else if (c == ']') {
                    StringBuilder tmp = new StringBuilder();
                    int curMulti = numList.pollLast();
                    for (int j = 0; j < curMulti; j++) {
                        tmp.append(res);
                    }
                    res = new StringBuilder(strList.pollLast() + tmp);
                } else if (c >= '0' && c <= '9') {
                    multi = multi * 10 + Integer.valueOf(c + "");
                } else {
                    res.append(c);
                }
            }
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
