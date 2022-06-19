package com.frankcooper.platform.leetcode.bank.week;

import java.util.*;

import org.junit.Assert;

public class Week298 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        public String greatestLetter(String s) {
            int[] arr = new int[128];
            for (char c : s.toCharArray()) {
                arr[c]++;
            }
            for (char c = 'Z'; c >= 'A'; c--) {
                if (arr[c + 32] >= 1 && arr[c] >= 1) return String.valueOf(c);
            }
            return "";
        }


    }

    static class _1st_1 {
        public static void main(String[] args) {
            _1st_1 handler = new _1st_1();
            String s = "lEeTcOdE";
            handler.greatestLetter(s);
        }

        public String greatestLetter(String s) {
            long t = 0;
            int n = s.length();
            for (int i = 0; i < n; i++) {
                t |= (1L << (s.charAt(i) - 'A'));//低位存大写字符，高位存小写字符
            }
            for (int i = 25; i >= 0; i--) {
                if (((t >> i) & (t >> (i + 32))) == 1) {
                    return String.valueOf((char) ('A' + i));
                }
            }
            return "";
        }
    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }


        public int minimumNumbers(int num, int k) {
            if (num == 0) return 0;
            for (int n = 1; n <= num; n++) {
                int t = num - k * n;
                if (t < 0) break;
                if (t % 10 == 0) {
                    return n;
                }
            }
            return -1;
        }
    }

    static class _2nd_1 {
        public int minimumNumbers(int num, int k) {
            if (num == 0) return 0;
            for (int n = 1; n <= 10; n++) {
                int t = num - k * n;
                if (t < 0) break;
                if (t % 10 == 0) {
                    return n;
                }
            }
            return -1;
        }
    }

    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
            String s = "1001010";
            int k = 5;
//            handler.longestSubsequence(s, k);
            //"0"
            //583196182
            //"000101010011011001011101111000111111100001011000000100010000111100000011111001000111100111101001111001011101001011011101001011011001111111010011100011110111010000010000010111001001111101100001111"
            //300429827
            //ans : 108
            s = "000101010011011001011101111000111111100001011000000100010000111100000011111001000111100111101001111001011101001011011101001011011001111111010011100011110111010000010000010111001001111101100001111";
            k = 300429827;
//            Assert.assertEquals(108, handler.longestSubsequence(s, k));
            //"101100011000010101011100011111111001011101000101000010001100101110010010011000100010001110011111000100100101110000100010010010100010"
            //978095074
            //ans:84
            s = "101100011000010101011100011111111001011101000101000010001100101110010010011000100010001110011111000100100101110000100010010010100010";
            k = 978095074;
            //  536870912
//            Assert.assertEquals(84, handler.longestSubsequence(s, k));
            s = "00101001";
            k = 1;
//            Assert.assertEquals(6, handler.longestSubsequence(s, k));


            s = "1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
            k = 1000000000;
            Assert.assertEquals(500, handler.longestSubsequence(s, k));

        }

        //AC   WA了5次
        public int longestSubsequence(String s, int k) {
            char[] ch = s.toCharArray();
            int n = ch.length;
            int zero = 0;
            for (char c : ch) zero += c == '0' ? 1 : 0;
            long t = 0;
            int pre = 0;
            for (int i = n - 1; i >= 0; i--) {
                if (ch[i] == '1') {
                    //位数太多，会溢出
                    if (n - i - 1 >= 32) return zero;
//                    System.out.printf("%d %d \n ", i, 1 << (n - i - 1));
                    int delta = 1 << (n - i - 1);
                    if (pre > delta) return zero;
                    pre = delta;
                    t += delta;
                    if (t > k) {
                        return zero;
                    }
                    zero++;
                }
            }
            return zero;
        }
    }

    static class _3rd_1 {
        //2^30 =1073741824 > 1e9 也就是说低位的i最大值第30位
        public int longestSubsequence(String s, int k) {
            char[] ch = s.toCharArray();
            int n = ch.length;
            int t = 0, res = 0;
            for (int i = n - 1; i >= 0; i--) {
                if (ch[i] == '1') {
                    //位数太多，会溢出
                    if (n - i - 1 > 30) {
                        continue;
                    }
                    t |= (1 << (n - i - 1));
                    if (t > k) {//大于k，当前位的1不能被使用
                        continue;
                    }
                    res++;
                } else {
                    res++;
                }
            }
            return res;
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }


        public long sellingWood(int m, int n, int[][] prices) {
            int[][] pr = new int[m + 1][n + 1];
            for (int[] p : prices) {
                pr[p[0]][p[1]] = p[2];
            }
            //f[i][j]表示切割高为i宽为j的木块，能得到的最多的钱数
            long[][] f = new long[m + 1][n + 1];
            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    f[i][j] = pr[i][j];//不切割，直接卖
                    for (int k = 1; k < i; k++) {
                        f[i][j] = Math.max(f[i][j], f[k][j] + f[i - k][j]);
                    }
                    for (int k = 1; k < j; k++) {
                        f[i][j] = Math.max(f[i][j], f[i][k] + f[i][j - k]);
                    }
                }
            }
            return f[m][n];
        }
    }
}
