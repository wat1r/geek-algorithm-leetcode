package com.frankcooper.bank._1_100;

import java.util.ArrayList;
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
}
