package com.frankcooper.bank._1_100;

import java.util.*;

public class _40 {

    static class _1st {
        List<List<Integer>> res = new ArrayList<>();

        public List<List<Integer>> combinationSum2(int[] nums, int t) {
            Arrays.sort(nums);//sort
            dfs(nums, 0, t, new ArrayList<>());
            return res;
        }

        private void dfs(int[] nums, int idx, int t, List<Integer> sub) {
            if (t < 0) return;
            if (t == 0) {
                res.add(new ArrayList<>(sub));
                return;
            }

            for (int i = idx; i < nums.length; i++) {
                if (i > idx && nums[i - 1] == nums[i]) continue;//skip duplicate candidate
                sub.add(nums[i]);
                dfs(nums, i + 1, t - nums[i], sub);
                sub.remove(sub.size() - 1);
            }
        }
    }
}
