package com.frankcooper.bank._1_100;

import java.util.*;

import org.junit.Assert;

public class _58 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        public int lengthOfLastWord(String s) {
            if (s == null || s.length() == 0) return 0;
            s = s.trim();
            int res = 0;
            for (int i = s.length() - 1; i >= 0; i--) {
                if (s.charAt(i) == ' ') {
                    break;
                }
                res++;
            }
            return res;
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
