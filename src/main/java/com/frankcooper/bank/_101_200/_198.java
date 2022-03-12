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

    static class _7th {
        public int rob(int[] nums) {
            int n = nums.length;
            int[][] f = new int[n][2];
            f[0][0] = 0;
            f[0][1] = nums[0];
            for (int i = 1; i < n; i++) {
                f[i][0] = Math.max(f[i - 1][0], f[i - 1][1]);
                f[i][1] = nums[i] + f[i - 1][0];
            }
            return Math.max(f[n - 1][0], f[n - 1][1]);
        }
    }

    static class _8th {
        public int rob(int[] nums) {
            int n = nums.length;
            if (n == 1) return nums[0];
            int[] f = new int[n];
            f[0] = nums[0];
            f[1] = Math.max(nums[0], nums[1]);
            for (int i = 2; i < n; i++) {
                f[i] = Math.max(f[i - 1], f[i - 2] + nums[i]);
            }
            return f[n - 1];
        }
    }


    static class _9th {
        public int rob(int[] nums) {
            int n = nums.length;
            if (n == 1) return nums[0];
            int prev = nums[0], cur = Math.max(nums[0], nums[1]);
            for (int i = 2; i < n; i++) {
                int t = cur;
                cur = Math.max(prev + nums[i], cur);
                prev = t;
            }
            return cur;
        }
    }

    static class _10th {
        public int rob(int[] nums) {
            int n = nums.length;
            int[][] f = new int[n + 1][2];//0表示今天没偷
            for (int i = 1; i <= n; i++) {
                f[i][0] = Math.max(f[i - 1][0], f[i - 1][1]);
                f[i][1] = f[i - 1][0] + nums[i - 1];
            }
            return Math.max(f[n][0], f[n][1]);
        }
    }

    static class _10th_1 {
        public int rob(int[] nums) {
            int n = nums.length;
            int[][] f = new int[n][2];//0表示今天没偷
            f[0][0] = 0;
            f[0][1] = nums[0];
            for (int i = 1; i < n; i++) {
                f[i][0] = Math.max(f[i - 1][0], f[i - 1][1]);
                f[i][1] = f[i - 1][0] + nums[i];
            }
            return Math.max(f[n - 1][0], f[n - 1][1]);
        }
    }

    static class _10th_2 {
        public int rob(int[] nums) {
            int n = nums.length;
            if (n == 0) return 0;
            int[] f = new int[n + 1];
            f[0] = 0;
            f[1] = nums[0];
            for (int i = 2; i <= n; i++) {
                f[i] = Math.max(f[i - 1], f[i - 2] + nums[i - 1]);
            }
            return f[n];
        }
    }

    static class _10_3 {
        public int rob(int[] nums) {
            //到达前一个房间时，获得的最大收益
            int prev = 0;
            int cur = 0;
            for (int x : nums) {
                //max{前一个房间收益，前前一个房间收益+当前房间价值}
                int t = Math.max(cur, prev + x);
                //滚动
                prev = cur;
                cur = t;
            }
            return cur;
        }
    }
}
