package com.frankcooper.interview;

import java.util.*;

import org.junit.Assert;

public class _01_06 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        public String compressString(String S) {
            StringBuilder ans = new StringBuilder();
            int i = 0, j = 0, n = S.length();
            while (j < n) {
                while (j < n && S.charAt(j) == S.charAt(i)) {
                    j++;
                }
                ans.append(S.charAt(i)).append(j - i);
                i = j;
            }
            return ans.toString().length() >= n ? S : ans.toString();
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
