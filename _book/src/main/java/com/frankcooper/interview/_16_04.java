package com.frankcooper.platform.leetcode.interview;

import java.util.*;

import org.junit.Assert;

public class _16_04 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        public String tictactoe(String[] board) {
            int N = board.length;
            //行的值，列的值，主对角线的值，副对角线的值
            int rows = 0, cols = 0, dig1 = 0, dig2 = 0;
            boolean f = false;//判断是否有空格
            for (int i = 0; i < N; i++) {
                rows = 0;
                cols = 0;
                dig1 += board[i].charAt(i);
                dig2 += board[i].charAt(N - 1 - i);
                for (int j = 0; j < N; j++) {
                    rows += board[i].charAt(j);
                    cols += board[j].charAt(i);
                    if (board[i].charAt(j) == ' ') f = true;
                }
                //判断行列
                if (rows == 'X' * N || cols == 'X' * N) return "X";
                else if (rows == 'O' * N || cols == 'O' * N) return "O";
            }
            //判断主对角线 X先手
            if (dig1 == 'X' * N || dig2 == 'X' * N) return "X";
            if (dig1 == 'O' * N || dig2 == 'O' * N) return "O";
            if (f) return "Pending";
            return "Draw";
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
