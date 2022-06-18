package com.frankcooper.platform.leetcode.bank._1_100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _39 {
    static class _1st {
        List<List<Integer>> res = new ArrayList<>();

        public List<List<Integer>> combinationSum(int[] nums, int t) {
            dfs(nums, t, 0, new ArrayList<>());
            return res;
        }

        private void dfs(int[] nums, int t, int idx, List<Integer> sub) {
            if (t < 0) return;
            if (t == 0) {
                res.add(new ArrayList<>(sub));
                return;
            }

            for (int i = idx; i < nums.length; i++) {
                sub.add(nums[i]);
                dfs(nums, t - nums[i], i, sub);
                sub.remove(sub.size() - 1);
            }
        }
    }

    static class _2nd {
        List<List<Integer>> res = new ArrayList<>();


        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            Arrays.sort(candidates);
            dfs(candidates, target, 0, new ArrayList<>());
            return res;
        }


        private void dfs(int[] nums, int target, int idx, List<Integer> sub) {
            if (target < 0) return;
            if (target == 0) {
                res.add(new ArrayList<>(sub));
                return;
            }

            for (int i = idx; i < nums.length; i++) {
                sub.add(nums[i]);
                dfs(nums, target - nums[i], i, sub);
                sub.remove(sub.size() - 1);
            }
        }
    }

    static class _3rd {

        public static void main(String[] args) {
            _3rd handler = new _3rd();
            int[] candidates = new int[]{2, 3, 6, 7};
            int target = 7;
            handler.combinationSum(candidates, target);

        }

        List<List<Integer>> res = new ArrayList<>();
        int target;

        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            Arrays.sort(candidates);
            this.target = target;
            dfs(candidates, 0, 0, new ArrayList<>());
            return res;
        }


        private void dfs(int[] nums, int sum, int idx, List<Integer> sub) {
            if (sum > this.target) return;
            if (sum == this.target) {
                res.add(new ArrayList<>(sub));
                return;
            }

            for (int i = idx; i < nums.length; i++) {
                sub.add(nums[i]);
                dfs(nums, sum + nums[i], i, sub);
                sub.remove(sub.size() - 1);
            }
        }
    }
}
