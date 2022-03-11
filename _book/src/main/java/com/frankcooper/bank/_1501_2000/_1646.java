package com.frankcooper.bank._1501_2000;

import java.util.*;

import org.junit.Assert;

public class _1646 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }

        //模拟
        public int getMaximumGenerated(int n) {
            if (n == 0 || n == 1) return n;
            int res = 0;
            int[] nums = new int[n + 1];
            nums[0] = 0;
            nums[1] = 1;
            for (int i = 0; i <= n; i++) {
                if (i >= 1 && 2 * i <= n) {
                    nums[2 * i] = nums[i];
                    res = Math.max(res, nums[2 * i]);
                }
                if (2 * i >= 1 && 2 * i + 1 <= n) {
                    nums[2 * i + 1] = nums[i] + nums[i + 1];
                    res = Math.max(res, nums[2 * i + 1]);
                }
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
