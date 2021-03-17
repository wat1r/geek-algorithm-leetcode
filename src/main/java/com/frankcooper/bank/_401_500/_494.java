package com.frankcooper.bank._401_500;


public class _494 {

    static class _1st {
        /*二维*/
        public int findTargetSumWays(int[] nums, int S) {
            int sum = 0;
            for (int num : nums) sum += num;
            if (S > sum || (S + sum) % 2 == 1) return 0;
            int W = (S + sum) / 2;
            int n = nums.length;
            int[][] f = new int[n + 1][W + 1];//[0...i]范围，能获取到凑成j的种类的个数
            f[0][0] = 1;
            for (int i = 1; i <= n; i++) {
                for (int j = 0; j <= W; j++) {
                    if (j >= nums[i - 1]) {//   当前背包的容量大于nums[i-1] 这个元素可以装，也可以不装
                        f[i][j] = f[i - 1][j] + f[i - 1][j - nums[i - 1]];
                    } else {
                        f[i][j] = f[i - 1][j];//装不下
                    }
                }
            }
            return f[n][W];
        }
    }


    static class _2nd {
        /*一维*/
        public int findTargetSumWays(int[] nums, int S) {
            int sum = 0;
            for (int num : nums) sum += num;
            if (S > sum || (S + sum) % 2 == 1) return 0;
            int W = (S + sum) / 2;
            int n = nums.length;
            int[] f = new int[W + 1];
            f[0] = 1;
            for (int i = 1; i <= n; i++) {
                for (int j = W; j >= nums[i - 1]; --j) {
                    f[j] += f[j - nums[i - 1]];
                }
            }
            return f[W];
        }
    }

}
