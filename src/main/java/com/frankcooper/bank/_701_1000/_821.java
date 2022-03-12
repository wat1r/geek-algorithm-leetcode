package com.frankcooper.bank._701_1000;

import java.util.*;

import org.junit.Assert;

public class _821 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


            String s = "loveleetcode";
            char c = 'e';
            handler.shortestToChar(s, c);

        }

        public int[] shortestToChar(String s, char c) {

            char[] ch = s.toCharArray();
            int n = ch.length;
            int[] ans = new int[n];
            int i = 0, j = 0;
            while (j < n) {
                while (ch[j] != c) {
                    j++;
                }
                while (i != j) {
                    ans[i] = j - i;
                    i++;
                }
                i++;
                j++;
            }
            return ans;
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
