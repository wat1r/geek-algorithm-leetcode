package com.frankcooper.bank._2001_3000;

import java.util.*;

import org.junit.Assert;

public class _2222 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

            Assert.assertEquals(6, handler.numberOfWays("001101"));

        }


        public long numberOfWays(String s) {
            int n = s.length();
            long[] one = new long[n + 1];//记录'1'的数量，剩下的便是'0'的数量
            for (int i = 1; i <= n; i++) {
                one[i] = one[i - 1] + (s.charAt(i - 1) == '1' ? 1 : 0);
            }
            long res = 0;
            for (int i = 0; i < n; i++) {
                char c = s.charAt(i);
                if (c == '0') {
                    //形成101这样的结构
                    long a = one[i + 1], b = one[n] - one[i + 1];
                    res += a * b;
                } else {
                    //形成010这样的结构
                    long a = i + 1 - one[i + 1], b = n - i - (one[n] - one[i]);
                    res += a * b;
                }
            }
            return res;
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }


        public long numberOfWays(String s) {
            long tot_zero = 0, cur_zero = 0;//统计所有'0'的数量 当前所有'0'的数量
            int n = s.length();
            for (int i = 0; i < n; i++) {
                if (s.charAt(i) == '0') tot_zero++;
            }
            long res = 0;
            for (int i = 0; i < n; i++) {
                char c = s.charAt(i);
                if (c == '1') {//010
                    res += cur_zero * (tot_zero - cur_zero);
                } else {//101
                    long cur_one = i - cur_zero;
                    // n - 所有的0 剩下所有的1 再减去当前的1 得到剩下的1
                    res += cur_one * (n - tot_zero - cur_one);
                    cur_zero++;
                }
            }
            return res;

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
