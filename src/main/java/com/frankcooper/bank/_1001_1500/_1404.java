package com.frankcooper.bank._1001_1500;

import java.util.*;

import org.junit.Assert;

public class _1404 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            Assert.assertEquals(6,handler.numSteps("1101"));
            Assert.assertEquals(1,handler.numSteps("10"));
            Assert.assertEquals(0,handler.numSteps("1"));


        }

        public int numSteps(String s) {
            long num = 0;
            for (int i = s.length() - 1; i >= 0; --i) {
                num |= ((s.charAt(i) - '0') << (s.length() - 1 - i));
            }
            return (int) dfs(num);
        }

        public long dfs(long num) {
            if (num == 1) return 0;
            if (num % 2 == 0) return dfs(num / 2) + 1;
            else return dfs(num + 1) + 1;
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
