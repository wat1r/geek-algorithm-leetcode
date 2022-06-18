package com.frankcooper.platform.nowcoder.swordoffer;

import java.util.*;
import org.junit.Assert;
public class JZ42 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        public int FindGreatestSumOfSubArray(int[] array) {
            if (array == null || array.length == 0) return 0;
            int n = array.length;
            //f[i]表示以array[i-1]这个数为结尾的，连续子数组的最大和
            int[] f = new int[n + 1];
            int res = -101;
            for (int i = 1; i <= n; i++) {
                //当前这个数array[i-1]和前一个数的连续子数组形成一个最大的连续子数组：[...i-2,i-1]
                //当前这个数array[i-1]自己形成一个子数组[i-1]
                //两者取最大值，不断更新全局变量
                f[i] = Math.max(f[i - 1] + array[i - 1], array[i - 1]);
                res = Math.max(res, f[i]);
            }
            return res;
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
