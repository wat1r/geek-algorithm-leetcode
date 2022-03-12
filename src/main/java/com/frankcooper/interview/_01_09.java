package com.frankcooper.interview;

import java.util.*;

import org.junit.Assert;

public class _01_09 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

            String s1 = "waterbottle", s2 = "erbottlewat";
            s1 = "";
            s2 = "";
            s1 = "aba";
            s2 = "bab";
            handler.isFlipedString(s1, s2);

        }


        public boolean isFlipedString(String s1, String s2) {
            int n1 = s1.length(), n2 = s2.length();
            if (n1 != n2) return false;
            String s = s2 + s2;
            return s.contains(s1);
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
