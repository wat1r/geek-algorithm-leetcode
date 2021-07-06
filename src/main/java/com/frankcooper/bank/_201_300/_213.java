package com.frankcooper.bank._201_300;

import java.util.*;

import org.junit.Assert;

public class _213 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        public int rob(int[] nums) {
            int n = nums.length;
            if (n == 0) return 0;
            if (n == 1) return nums[0];
            int[][] f = new int[n][2];
            //不选第一间
            for (int i = 1; i < n - 1; i++) {
                f[i][0] = Math.max(f[i - 1][0], f[i - 1][1]);
                f[i][1] = f[i - 1][0] + nums[i];
            }
            //选了倒数第2间 VS 选了倒数第二间 选了倒数第一间
            int x = Math.max(f[n - 2][1], f[n - 2][0] + nums[n - 1]);
            //选了第一间
            f[0][0] = 0;
            f[0][1] = nums[0];
            for (int i = 1; i < n - 1; i++) {
                f[i][0] = Math.max(f[i - 1][0], f[i - 1][1]);
                f[i][1] = f[i - 1][0] + nums[i];
            }
            int y = Math.max(f[n - 2][0], f[n - 2][1]);
            return Math.max(x,y);
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
