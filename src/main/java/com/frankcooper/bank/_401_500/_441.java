package com.frankcooper.bank._401_500;

import java.util.*;

import org.junit.Assert;

public class _441 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }

        public int arrangeCoins(int n) {
            int sum = 0, ans = 1;
            for (int i = 1; i <= n; i++) {
                if (sum > n) break;
                sum += i;
                ans = i;
            }
            return sum > n ? ans - 1 : ans;
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
