package com.frankcooper.platform.leetcode.bank._2001_3000;

import java.util.*;

import org.junit.Assert;

public class _2038 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        public boolean winnerOfGame(String colors) {
            int n = colors.length();
            char[] ch = colors.toCharArray();
            int a = 0, b = 0;
            for (int i = 1; i < n - 1; i++) {
                if (ch[i] == ch[i - 1] && ch[i] == ch[i + 1]) {
                    if (ch[i] == 'A') a++;
                    if (ch[i] == 'B') b++;
                }
            }
            return a > b;
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
