package com.frankcooper.bank._1001_2000;

import java.util.*;

import org.junit.Assert;

public class _1482 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[] nums = new int[]{7, 7, 7, 7, 12, 7, 7};
            int m = 2, k = 3;
//            Assert.assertEquals(12, handler.minDays(nums, m, k));
            nums = new int[]{1, 10, 3, 10, 2};
            m = 3;
            k = 2;
//            Assert.assertEquals(-1, handler.minDays(nums, m, k));
            nums = new int[]{1, 10, 3, 10, 2};
            m = 3;
            k = 1;
//            Assert.assertEquals(3, handler.minDays(nums, m, k));
            nums = new int[]{1000000000, 1000000000};
            m = k = 1;
            Assert.assertEquals(1000000000, handler.minDays(nums, m, k));
            nums = new int[]{1, 10, 2, 9, 3, 8, 4, 7, 5, 6};
            m = 4;
            k = 2;
            Assert.assertEquals(9, handler.minDays(nums, m, k));


        }

        boolean[] mark  ;
        public int minDays(int[] nums, int m, int k) {
            int lo = 1, hi = (int) 1e9 + 1;
//            System.out.printf("%d\n",nums.length);
            mark = new boolean[nums.length];
            int res = -1;
            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;
                if (check(nums, m, k, mid)) {
                    res = mid;
                    hi = mid;
                } else {
                    lo = mid + 1;
                }
            }
            return res;
        }

        /**
         * 找到最小的天数
         *
         * @return
         */
        private boolean check(int[] nums, int m, int k, int day) {
            int prev = -1;
            for (int i = 0; i < nums.length; i++) {
                mark[i] = nums[i] <= day;//当前的花束开花周期小于给定的day
                if (mark[i]) {
                    if (i - prev >= k) {//记录上一个位置，往前推k个位置
                        m--;//已经凑够一束花
                        if (m == 0) return true;//已经凑够了
                        prev = i;
                    }
                } else {
                    prev = i;
                }
            }
            return false;
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
