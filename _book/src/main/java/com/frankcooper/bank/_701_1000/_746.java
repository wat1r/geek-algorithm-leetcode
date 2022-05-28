package com.frankcooper.bank._901_1000;

import java.util.*;

import org.junit.Assert;

public class _746 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        public int minCostClimbingStairs(int[] cost) {
            int n = cost.length;
            int[] f = new int[n];
            f[0] = cost[0];
//            f[1] = Math.min(cost[1], cost[0] + cost[1]);
            f[1] = cost[1];
            for (int i = 2; i < n; i++) {
                f[i] = Math.min(f[i - 1], f[i - 2]) + cost[i];
            }
            //可能是从倒数第一或是倒数第二跳过阶梯顶
            return Math.min(f[n - 1], f[n - 2]);
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
