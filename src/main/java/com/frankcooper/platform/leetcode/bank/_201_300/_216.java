package com.frankcooper.platform.leetcode.bank._201_300;

import java.util.*;

public class _216 {
    static class _1st {

        List<List<Integer>> res = new ArrayList<>();
        int k, n;

        public List<List<Integer>> combinationSum3(int k, int n) {
            this.k = k;
            this.n = n;
            dfs(0, 0, 1, new ArrayList<>());
            return res;
        }

        private void dfs(int count, int sum, int idx, List<Integer> sub) {
            if (sum > n || count > k) return;
            if (sum == n && count == k) {
                res.add(new ArrayList<>(sub));
                return;
            }
            for (int i = idx; i <= 9; i++) {

                sub.add(i);
                dfs(count + 1, sum + i, i + 1, sub);
                sub.remove(sub.size() - 1);
            }

        }
    }
}
