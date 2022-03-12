package com.frankcooper.lintcode;

import java.util.*;
import org.junit.Assert;
public class _891 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }

        public boolean validPalindrome(String s) {
            int n = s.length(), l = 0, r = n - 1;
            while (l < r) {
                if (s.charAt(l) != s.charAt(r)) {
                    return check(s, l + 1, r) || check(s, l, r - 1);
                }
                l++;
                r--;
            }
            return true;

        }

        private boolean check(String s, int l, int r) {
            while (l < r) {
                if (s.charAt(l++) != s.charAt(r--)) return false;
            }
            return true;
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
