package com.frankcooper.bank.bi_weekly;

import java.util.*;

import com.alibaba.fastjson.JSONObject;
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

    static class _3rd_1 {
        public static void main(String[] args) {
            _3rd_1 handler = new _3rd_1();
            char[][] box = {{'#', '.', '*', '.'},
                    {'#', '#', '*', '.'}};
            handler.rotateTheBox(box);
        }

        /**
         * 先转置后处理盒子
         *
         * @param box
         * @return
         */
        public char[][] rotateTheBox(char[][] box) {
            int m = box.length, n = box[0].length;
            char[][] matrix = new char[n][m];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {//顺时针翻转90°的结果
                    matrix[j][m - i - 1] = box[i][j];
                }
            }
//            System.out.printf("%s\n", JSONObject.toJSONString(matrix));
            for (int j = 0; j < m; j++) {//遍历每一列
                int d = n - 1;//d用于盒子下落的能到达的最低位置
                for (int i = n - 1; i >= 0; i--) {//从底部开始往上遍历
                    if (matrix[i][j] == '*') {//如果当前是障碍物，d最高只能到达i-1，也就是当前障碍物上面的一个位置，
                        d = i - 1;
                    } else if (matrix[i][j] == '#') {//当前是 # 这个位置可能会下移
                        matrix[i][j] = '.';//统一降落处理
                        matrix[d--][j] = '#';//盒子掉入的位置
                    }
                }

            }
            return matrix;

        }
    }

    static class _3rd_2 {
        /**
         * 先处理盒子，再转置
         *
         * @param box
         * @return
         */
        public char[][] rotateTheBox(char[][] box) {
            int m = box.length, n = box[0].length;
            for (int i = 0; i < m; i++) {//遍历每一行
                int p = n - 1;
                for (int j = n - 1; j >= 0; --j) {//从右往左遍历
                    if (box[i][j] == '*') p = j - 1;
                    else if (box[i][j] == '#') {
                        box[i][j] = '.';
                        box[i][p--] = '#';
                    }
                }
            }
            char[][] mat = new char[n][m];
            for (int j = m - 1; j >= 0; --j) {
                for (int i = 0; i < n; i++) {
                    mat[i][j] = box[m - 1 - j][i];
                }
            }
            return mat;

        }
    }


    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
