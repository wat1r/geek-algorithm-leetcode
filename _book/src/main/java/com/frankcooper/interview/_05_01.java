package com.frankcooper.interview;

import java.util.*;

import com.frankcooper.utils.PrintUtils;
import org.junit.Assert;

public class _05_01 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        public int insertBits(int N, int M, int i, int j) {
            for (int k = i; k <= j; k++) {
                //1 << k  将1 左移k位
                //~(1 << k) 取反后 第k位为0，其他位为1
                //&= ~(1 << k)  & 运算后，k位肯定为0，1&1 才是1，其他情况都是0
                //这一步的操作相当于将[i,j]位全部置为0
                N &= ~(1 << k);
            }
            //加上M 移位后结果
            N += (M << i);
            //return N | (M<<i);
            return N;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();

            int N = 1000;
            int M = 19;
            int i = 2, j = 6;
            handler.insertBits(N, M, i, j);

        }


        public int insertBits(int N, int M, int i, int j) {
            // 1.把N的i到j位置为0
            for (int k = i; k <= j; k++) {
                //取第k位，如果不是0就将k位以下全部抹掉
                if ((N & (1 << k)) != 0) {
                    //1^1的结果是0 也可以写成 N-=(1<<k)
                    N ^= (1 << k);
                }
            }
            return N | M << i;
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
