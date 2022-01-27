package com.frankcooper.bank._701_1000;

import java.util.*;

import org.junit.Assert;

public class _744 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            char[] le = "cfj".toCharArray();
            char ta = 'g';
            Assert.assertEquals('j', handler.nextGreatestLetter(le, ta));

            /**
             * ["c","f","j"]
             * "k"
             * 输出 "j"
             * 预期 "c"
             */
        }

        //需要临界点判断下
        public char nextGreatestLetter(char[] le, char ta) {
            if (le == null || le.length == 0) return ta;
            int l = 0, r = le.length - 1;
            while (l < r) {
                int mid = l + (r - l) / 2;
                if (le[mid] <= ta) l = mid + 1;
                else r = mid;
            }
            return le[l] <= ta ? (l == le.length - 1 ? le[0] : le[l + 1]) : le[l];
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        public char nextGreatestLetter(char[] letters, char target) {
            if (letters == null || letters.length == 0) {
                return target;
            }
            int len = letters.length;
            int l = 0, r = len - 1;
            while (l <= r) {
                int mid = l + (r - l) / 2;
                if (letters[mid] <= target) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
            return l < len ? letters[l] : letters[0];
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }

        public char nextGreatestLetter(char[] le, char ta) {
            if (le == null || le.length == 0) return ta;
            int l = 0, r = le.length - 1;
            while (l < r) {
                int mid = l + (r - l + 1) / 2;
                if (le[mid] <= ta) l = mid;
                else r = mid - 1;
            }
            return le[l] <= ta ? (l == le.length - 1 ? le[0] : le[l + 1]) : le[l];
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
