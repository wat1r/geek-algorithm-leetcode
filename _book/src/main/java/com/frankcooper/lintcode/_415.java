package com.frankcooper.lintcode;

import java.util.*;

import org.junit.Assert;

public class _415 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            String s = "A man, a plan, a canal: Panama";
//            handler.isPalindrome(s);
            s = ".,";
            Assert.assertTrue(handler.isPalindrome(s));

        }


        public boolean isPalindrome(String s) {
            int n = s.length(), l = 0, r = n - 1;
            while (l < r) {
                while (l < r && !(Character.isLetter(s.charAt(l)) || Character.isDigit(s.charAt(l)))) l++;
                while (r > l && !(Character.isLetter(s.charAt(r)) || Character.isDigit(s.charAt(r)))) r--;
                if ((s.charAt(l) & '_') != (s.charAt(r) & '_')) return false;
                l++;
                r--;
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
