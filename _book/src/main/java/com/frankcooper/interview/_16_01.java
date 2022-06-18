package com.frankcooper.platform.leetcode.interview;

/*
import java.util.*;
import org.junit.Assert;
*/
public class _16_01 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }

        public int[] swapNumbers(int[] nums) {
            int a = nums[0], b = nums[1];
            a ^= b;
            b ^= a;
            a ^= b;
            return new int[]{a, b};
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
