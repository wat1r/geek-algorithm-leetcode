package com.frankcooper.lintcode;

import java.util.*;

import org.junit.Assert;

public class _1870 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            String str = "00010011";
            handler.stringCount(str);

        }


        public int stringCount(String str) {
            int n = str.length(), j = 0;
            int res = 0;
            for (int i = 0; i < n; i++) {
                if (str.charAt(i) != '0') continue;
                j = Math.max(i + 1, j);
                while (j < n && str.charAt(j) == '0') j++;
                res += j - i;
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
