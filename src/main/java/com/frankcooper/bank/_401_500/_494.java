package com.frankcooper.bank._401_500;


import java.util.HashMap;
import java.util.Map;

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


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
            int[] nums = {1, 1, 1, 1, 1};
            int target = 3;
            handler.findTargetSumWays(nums, target);
        }


        int target;
        int res = 0;

        public int findTargetSumWays(int[] nums, int target) {
            this.target = target;
            dfs(nums, 0, 0);
            return res;
        }

        /***
         * 遍历到idx，对于idx，有两种选择，一种是取+ 一种是取-
         * @param nums 数组
         * @param idx 当前数组遍历的到的位置
         * @param sum 当前已经获得的和
         */
        private void dfs(int[] nums, int idx, int sum) {
            //当idx到达数组末尾
            if (idx == nums.length) {
                if (sum == target) res++;//和正好相等，答案+1
                return;
            }
            dfs(nums, idx + 1, sum + nums[idx]);
            dfs(nums, idx + 1, sum - nums[idx]);
        }


    }


    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
            int[] nums = {1, 1, 1, 1, 1};
            int target = 3;
            handler.findTargetSumWays(nums, target);
        }

        int target;
        HashMap<Map<Integer, Integer>, Integer> cache = new HashMap<>();

        public int findTargetSumWays(int[] nums, int target) {
            this.target = target;
            return dfs(nums, 0, 0);
        }

        /***
         * 遍历到idx，对于idx，有两种选择，一种是取+ 一种是取-
         * 返回的是对于idx sum 这组合的key，符合条件的组合数量
         * @param nums 数组
         * @param idx 当前数组遍历的到的位置
         * @param sum 当前已经获得的和
         */
        private int dfs(int[] nums, int idx, int sum) {
            //当idx到达数组末尾
            Map<Integer, Integer> key = new HashMap<Integer, Integer>() {{
                put(idx, sum);
            }};
            if (cache.containsKey(key)) {
                return cache.get(key);
            }
            if (idx == nums.length) {
                if (sum == target) return 1;//和正好相等，答案+1
                return 0;
            }
            int positive = dfs(nums, idx + 1, sum + nums[idx]);
            int negative = dfs(nums, idx + 1, sum - nums[idx]);
            int value = positive + negative;
            cache.put(key, value);
            return value;
        }
    }


    static class _5th {


        public static void main(String[] args) {


        }


        public int findTargetSumWays(int[] nums, int target) {
            int n = nums.length;
            int sum = 0;
            for (int x : nums) sum += x;
            if (target > sum || (sum + target) % 2 == 1) return 0;
            int W = (sum + target) / 2;
            int[][] f = new int[n + 1][W + 1];//[0..i]中能组成j的方案的数量
            f[0][0] = 1;//[0..0]中能形成0的方案数量，即不选 此为一种方案数量
            for (int i = 1; i <= n; i++) {
                for (int j = 0; j <= W; j++) {
                    if (j >= nums[i - 1]) {//目标数是j 对于当前[i-1]这个元素，可以选，也可以不选，即两者方案数的和
                        f[i][j] = f[i - 1][j] + f[i - 1][j - nums[i - 1]];
                    } else {//选了的话 j -nums[i-1]变成负数，不符合
                        f[i][j] = f[i - 1][j];
                    }
                }
            }
            return f[n][W];
        }
//
    }


    static class _6th {
        public static void main(String[] args) {

        }

        public int findTargetSumWays(int[] nums, int target) {
            int n = nums.length;
            int sum = 0;
            for (int x : nums) sum += x;
            if (target > sum || (sum + target) % 2 == 1) return 0;
            int W = (sum + target) / 2;
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
