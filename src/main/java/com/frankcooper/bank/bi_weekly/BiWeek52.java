package com.frankcooper.bank.bi_weekly;

import java.util.*;

import org.junit.Assert;

public class BiWeek52 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            System.out.println(handler.sortSentence("is2 sentence4 This1 a3"));
        }


        public String sortSentence(String s) {
            String[] arr = s.split("\\s+");
            String[] res = new String[arr.length];
            for (String a : arr) {
                int i = a.charAt(a.length() - 1) - '1';
                res[i] = a.substring(0, a.length() - 1);
            }
            StringBuilder sb = new StringBuilder();
            for (String x : res) sb.append(x).append(" ");
            return sb.toString().trim();
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();

        }

        public int[] memLeak(int memory1, int memory2) {
            int i = 1;
            while (i <= memory1 || i <= memory2) {
                if (memory1 >= memory2) memory1 -= i;
                else memory2 -= i;
                i++;
            }
            return new int[]{i, memory1, memory2};
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
            char[][] box = {{'#', '.', '#'}};
            handler.rotateTheBox(box);
        }


        public char[][] rotateTheBox(char[][] box) {

            int m = box.length, n = box[0].length;
            for (int i = 0; i < m; i++) {
                int j = n - 1, t = n - 1;
                while (j >= 0) {
                    if (box[i][j] == '*' || j == 0)//遇到障碍
                    {
                        int p = t;
                        for (int k = t; k >= j; k--) {
                            if (box[i][k] == '#') {
                                box[i][p] = '#';
                                if (k != p) box[i][k] = '.';
                                p--;
                            }
                        }
                    }
                    j--;
                }
            }

            char[][] res = new char[n][m];
            int dst = m - 1;
            for (int i = 0; i < m; i++, dst--) {
                for (int j = 0; j < n; j++) {
                    res[j][dst] = box[i][j];
                }
            }
            return res;
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
