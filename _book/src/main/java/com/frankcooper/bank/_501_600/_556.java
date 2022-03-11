package com.frankcooper.bank._501_600;

import java.util.*;

import org.junit.Assert;

public class _556 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int n = 158476531;
//            Assert.assertEquals(158513467, handler.nextGreaterElement(n));
            n = 21;
//            Assert.assertEquals(-1, handler.nextGreaterElement(n));
            n = 2147483486;
            Assert.assertEquals(-1, handler.nextGreaterElement(n));

        }

        public int nextGreaterElement(int n) {
            char[] ch = (n + "").toCharArray();
            int i = ch.length - 2;//从后到前，找第一个元素比前一个元素小
            while (i >= 0 && ch[i + 1] <= ch[i]) {
                i--;
            }
            if (i < 0) return -1;//21这种case，严格从左到右递减
            int j = ch.length - 1;
            while (j > 0 && ch[j] <= ch[i]) {
                j--;
            }
            swap(ch, i, j);
            reverse(ch, i + 1, ch.length - 1);
            try {
                return Integer.parseInt(new String(ch));
            } catch (Exception e) {
                return -1;
            }
        }


        private void reverse(char[] ch, int s, int e) {
            while (s < e) {
                swap(ch, s++, e--);
            }
        }

        private void swap(char[] ch, int i, int j) {
            char t = ch[i];
            ch[i] = ch[j];
            ch[j] = t;
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
