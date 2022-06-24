package com.frankcooper.platform.leetcode.bank._101_200;

import org.junit.Assert;

public class _161 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            String s = "ab", t = "acb";
            Assert.assertTrue(handler.isOneEditDistance(s, t));
            s = "cab";
            t = "ad";
            Assert.assertFalse(handler.isOneEditDistance(s, t));
            s = "1203";
            t = "1213";
            Assert.assertTrue(handler.isOneEditDistance(s, t));


        }


        public boolean isOneEditDistance(String s, String t) {
            int sn = s.length(), tn = t.length();
            //维持s的长度小于t
            if (sn > tn) {
                return isOneEditDistance(t, s);
            }
            if (tn - sn > 1) return false;//相隔大于1时，返回false
            for (int i = 0; i < sn; i++) {
                if (s.charAt(i) != t.charAt(i)) {
                    //s与t的长度相同，比较后面的
                    if (sn == tn) {
                        return s.substring(i + 1).equals(t.substring(i + 1));
                    } else {//s与t的长度不同 s的字符短
                        return s.substring(i).equals(t.substring(i + 1));
                    }
                }
            }
            return sn + 1 == tn;
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
