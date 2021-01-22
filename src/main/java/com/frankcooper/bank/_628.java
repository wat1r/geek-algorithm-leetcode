package com.frankcooper.bank;

import org.omg.CORBA.INTERNAL;

import java.util.Arrays;

public class _628 {


    class _1st {
        public int maximumProduct(int[] nums) {
            if (nums == null || nums.length < 3) return 0;
            Arrays.sort(nums);
            int n = nums.length;
            int ans = 0;
            if (nums[0] < 0 && nums[1] < 0) ans = nums[0] * nums[1] * nums[n - 1];
            ans = Math.max(ans, nums[n - 3] * nums[n - 2] * nums[n - 1]);
            return ans;
        }
    }


    class _2nd {
        public int maximumProduct(int[] nums) {
            int[][] f = new int[2][4];
            f[0][0] = f[1][0] = 1;
            for (int j = 3; j > 0; j--) {
                f[0][j] = Integer.MIN_VALUE;
                f[1][j] = Integer.MAX_VALUE;
            }
            for (int i = 0; i < nums.length; i++) {
                for (int j = 3; j > 0; j--) {
                    if (f[0][j - 1] == Integer.MIN_VALUE) continue;
                    f[0][j] = Math.max(f[0][j], Math.max(f[0][j - 1] * nums[i], f[1][j - 1] * nums[i]));
                    f[1][j] = Math.min(f[1][j], Math.min(f[0][j - 1] * nums[i], f[1][j - 1] * nums[i]));
                }
            }
            return f[0][3];
        }
    }


}
