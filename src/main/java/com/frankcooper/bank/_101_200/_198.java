package com.frankcooper.bank._101_200;

public class _198 {


    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }

        public int rob(int[] nums) {
            return helper(nums, nums.length - 1);
        }

        public int helper(int[] nums, int i) {
            if (i < 0) return 0;
            int steal = nums[i] + helper(nums, i - 2);
            int non_steal = helper(nums, i - 1);
            return Math.max(steal, non_steal);
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        Integer[] memo;

        public int rob(int[] nums) {
            memo = new Integer[nums.length];
            return helper(nums, 0);
        }

        /**
         * @param nums
         * @param i    表示当前的房间号
         * @return
         */
        public int helper(int[] nums, int i) {
            if (i >= nums.length) return 0;
            if (memo[i] != null) return memo[i];
            int steal = nums[i] + helper(nums, i + 2);
            int non_steal = helper(nums, i + 1);
            return memo[i] = Math.max(steal, non_steal);
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }

        Integer[] memo;

        public int rob(int[] nums) {
            memo = new Integer[nums.length];
            return helper(nums, nums.length - 1);
        }

        public int helper(int[] nums, int i) {
            if (i < 0) return 0;
            if (memo[i] != null) return memo[i];
            int steal = nums[i] + helper(nums, i - 2);
            int non_steal = helper(nums, i - 1);
            return memo[i] = Math.max(steal, non_steal);
        }
    }


    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }

        public int rob(int[] nums) {
            int n = nums.length;
            int[][] f = new int[n + 1][2];
            for (int i = 1; i <= n; i++) {
                f[i][0] = Math.max(f[i - 1][0], f[i - 1][1]);
                f[i][1] = nums[i - 1] + f[i - 1][0];
            }
            return Math.max(f[n][0], f[n][1]);
        }
    }

    static class _5th {

        public int rob(int[] nums) {
            int prev1 = 0, prev2 = 0; //表示rob与否的价值
            for (int v : nums) {
                int t = prev1;
                prev1 = Math.max(prev1, prev2);
                prev2 = t + v;
            }
            return Math.max(prev1, prev2);
        }

    }

    static class _6th {
        public int rob(int[] nums) {
            int n = nums.length;
            int[][] f = new int[n + 1][2];//[0]表示偷了，[]
            for (int i = 1; i <= n; i++) {
                f[i][0] = Math.max(f[i - 1][0], f[i - 1][1]);
                f[i][1] = nums[i - 1] + f[i - 1][0];
            }
            return Math.max(f[n][1], f[n][0]);
        }
    }
}
