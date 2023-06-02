package com.frankcooper.platform.leetcode.bank._2001_3000;

import java.util.*;

import org.junit.Assert;

public class _2517 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[] price = {13, 5, 1, 8, 21, 2};
            int k = 3;
            handler.maximumTastiness(price, k);

        }


        public int maximumTastiness(int[] price, int k) {
            Arrays.sort(price);
            int n = price.length;
            int l = 0, r = price[n - 1] - price[0];
            while (l < r) {
                int mid = l + (r - l + 1) / 2;//上取整
                if (check(price, k, mid)) {//mid是个可能的值
                    l = mid;
                } else {
                    r = mid - 1;//配合上取整 mid肯定不是一个可能的值
                }
            }
            return l;
        }

        //判断能否在price中找到至少k个数，这k个数的相邻差，均大于t，能则返回true，不能则返回false
        public boolean check(int[] price, int k, int t) {
            int cnt = 0, prev = Integer.MIN_VALUE / 2;
            for (int curr : price) {
                if (curr - prev >= t) {
                    prev = curr;
                    cnt++;
                }
            }
            return cnt >= k;
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
