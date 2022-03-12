package com.frankcooper.interview;

import java.util.*;

import org.junit.Assert;

public class _17_16 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        public int massage(int[] nums) {
            if (nums == null || nums.length == 0) return 0;
            int n = nums.length;
            //f[i]表示前i个元素能获得的最大时间
            int[] f = new int[n];
            for (int i = 0; i < n; i++) {
                if (i == 0) {
                    f[0] = nums[0];
                    continue;
                } else if (i == 1) {
                    f[1] = Math.max(nums[0], nums[1]);
                    continue;
                }
                f[i] = Math.max(f[i - 1], f[i - 2] + nums[i]);
            }
            return f[n - 1];
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
