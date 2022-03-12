package com.frankcooper.bank._301_400;


import java.util.*;

public class _377 {

    static class _1st {

        public static void main(String[] args) {
            _1st handler = new _1st();
            int[] nums = new int[]{1, 2, 3};
            int target = 4;
            handler.combinationSum4(nums, target);
        }

        int res = 0;

        List<List<Integer>> resList = new ArrayList<>();

        public int combinationSum4(int[] nums, int target) {
            dfs(nums, target, 0, new ArrayList<>());
            return res;
        }


        private void dfs(int[] nums, int target, int idx, List<Integer> sub) {
            if (target < 0) return;
            if (target == 0) {
                res += 1;
                sub.forEach(System.out::print);
                System.out.println();
                return;
            }
//            System.out.printf("%d\n", idx);
            for (int i = 0; i < nums.length; i++) {
                sub.add(nums[i]);
                dfs(nums, target - nums[i], i, sub);
                sub.remove(sub.size() - 1);
            }
        }
    }

    static class _2nd {

        /*ETL*/
        public int combinationSum4(int[] nums, int target) {
            int[] cache = new int[target + 1];
//            cache[0] = 1; 这种 [3,33,333]
//10000 超时
            Arrays.fill(cache, -1);
            return dfs(nums, target, cache);
        }


        private int dfs(int[] nums, int target, int[] cache) {
            if (target < 0) return 0;
            if (cache[target] != -1) return cache[target];

            if (target == 0) {
                return 1;
            }
            int ans = 0;


            for (int i = 0; i < nums.length; i++) {
                ans += dfs(nums, target - nums[i], cache);
            }
//            cache[target] = ans;
            return cache[target] = ans;
        }
    }

    static class _3rd {
        /*动态规划：通过*/
        public int combinationSum4(int[] nums, int target) {
            int[] f = new int[target + 1];
            f[0] = 1;
            for (int t = 0; t <= target; t++) {
                for (int i = 0; i < nums.length; i++) {
                    if (nums[i] > t) continue;
                    f[t] += f[t - nums[i]];

                }
            }
            return f[target];
        }
    }

    static class _4th {

        /**
         * 记忆化
         */
        Integer[] memo;

        public int combinationSum4(int[] nums, int target) {
            memo = new Integer[target + 1];
            return dfs(nums, target);
        }

        private int dfs(int[] nums, int target) {
            if (target < 0) return 0;
            if (memo[target] != null) return memo[target];
            if (target == 0) return 1;
            int res = 0;
            for (int i = 0; i < nums.length; i++) {
                res += dfs(nums, target - nums[i]);
            }
            return memo[target] = res;
        }
    }

    static class _5th {
        public int combinationSum4(int[] nums, int target) {
            int[] f = new int[target + 1];//f[i]表示在使用nums数组形成i的组合的数量
            f[0] = 1;
            for (int i = 0; i <= target; i++) {
                for (int j = 0; j < nums.length; j++) {
                    if (nums[j] > i) continue;
                    f[i] += f[i - nums[j]];
                }
            }
            return f[target];
        }
    }
}
