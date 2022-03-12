package com.frankcooper.bank._401_500;

import java.util.*;

import org.junit.Assert;

public class _441 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
//            handler.arrangeCoins(8);
//            handler.arrangeCoins(1);
//            handler.arrangeCoins(10);
            handler.arrangeCoins(1804289383);

        }

        public int arrangeCoins(int n) {
            if (n == 1 || n == 0) return n;
            int l = 0, r = n;
            while (l < r) {
                int mid = l + (r - l) / 2;
                long sum = (long) mid * (1 + mid) / 2;
                if (sum == n) return mid;
                else if (sum > n) r = mid;
                else l = mid + 1;
            }
            return l - 1;
        }
    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        public int arrangeCoins(int n) {
            long l = 0, r = n;
            while (l < r) {
                long mid = l + (r - l + 1) / 2;//向上取
                long sum = mid * (mid + 1) / 2;
                if (sum == n) return (int) mid;
                if (sum > n) r = mid - 1;
                else l = mid;
            }
            return (int) r;
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
