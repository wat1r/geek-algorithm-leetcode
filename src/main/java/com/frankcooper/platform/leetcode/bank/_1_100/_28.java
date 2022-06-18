package com.frankcooper.platform.leetcode.bank._1_100;

public class _28 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        public int strStr(String haystack, String needle) {
            if (haystack == null || needle == null || haystack.length() < needle.length()) {
                return -1;
            }
            if (needle.length() == 0) {
                return 0;
            }
            for (int i = 0; i < haystack.length(); i++) {
                if (i + needle.length() > haystack.length()) break;
                if (haystack.substring(i, i + needle.length()).equals(needle)) return i;
            }
            return -1;
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
