package com.frankcooper.bank._1001_2000;

import java.util.*;

import org.junit.Assert;

public class _1283 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[] nums = new int[]{1, 2, 5, 9};
            int theshold = 6;
//            Assert.assertEquals(5, handler.smallestDivisor(nums, theshold));

            nums = new int[]{2, 3, 5, 7, 11};
            theshold = 11;
            Assert.assertEquals(3, handler.smallestDivisor(nums, theshold));


        }

        public int smallestDivisor(int[] nums, int threshold) {
            int tmp = 0;
            long lo = 1, hi = 0;
            for (int x : nums) hi += x;
            while (lo < hi) {
                long mid = lo + (hi - lo) / 2;
                tmp = check(nums, mid);
                if (tmp <= threshold) hi = mid;
                else lo = mid + 1;
            }
            return (int) lo;
        }

        //判断target 这个除数 s所得到的结果是否大于 threshold
        public int check(int[] nums, long target) {
            int sum = 0;
            for (int x : nums) {
                sum += (x + target) / (target + 1);
//                if (x % target == 0) sum += x / target;
//                else sum += (x / target + 1);
            }
            return sum;
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
