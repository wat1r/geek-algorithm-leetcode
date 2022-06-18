package com.frankcooper.platform.leetcode.interview;

import java.util.*;

import org.junit.Assert;

public class _08_05 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        public int multiply(int A, int B) {
            //保持A小于B
            if (A > B) {
                int T = A;
                A = B;
                B = T;
            }
            return dfs(A, B);
        }

        //A为小的因数
        public int dfs(int A, int B) {
            if (A == 0) return 0;//为0的时候，结果为0
            if (A == 1) return B;//为1的时候，B本身
            if (A == 2) return B << 1;//B扩大一倍 *2
            return dfs(2, B) + dfs(A - 2, B);//大于2的情况，先拆分出2 两部分加起来
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            int A = 12, B = 13;
            handler.multiply(A, B);
        }


        public int multiply(int A, int B) {
            //保持A小于B
            if (A > B) {
                int T = A;
                A = B;
                B = T;
            }
            int res = 0, i = 0;
            while (A > 0) {// 处理二进制
                if ((A & 1) == 1) {
                    res += B << i;
                }
                A >>= 1;
                i++;
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
