package com.frankcooper.platform.leetcode.bank._1_100;

import java.util.ArrayList;
import java.util.List;

public class _77 {

    static class _1st {
        List<List<Integer>> res = new ArrayList<>();

        public List<List<Integer>> combine(int n, int k) {
            dfs(1, n, k, new ArrayList<>());
            return res;
        }

        private void dfs(int i, int n, int k, List<Integer> sub) {
            if (sub.size() == k) {
                res.add(new ArrayList<>(sub));
                return;
            }
            if (i > n) return;//注意返回，超过n
            sub.add(i);
            dfs(i + 1, n, k, sub);
            sub.remove(sub.size() - 1);
            dfs(i + 1, n, k, sub);
        }
    }

    /**
     * 使用了vis数组
     */
    static class _2nd {


        List<List<Integer>> res = new ArrayList<>();

        public List<List<Integer>> combine(int n, int k) {
            dfs(1, n, k, new ArrayList<>(), new boolean[n + 1]);
            return res;
        }

        private void dfs(int i, int n, int k, List<Integer> sub, boolean[] vis) {
            if (sub.size() == k) {
                res.add(new ArrayList<>(sub));
                return;
            }
            for (int j = i; j <= n; j++) {
                if (vis[j]) continue;
                vis[j] = true;
                sub.add(j);
                dfs(j, n, k, sub, vis);
                sub.remove(sub.size() - 1);
                vis[j] = false;
            }
        }
    }

    static class _3rd {
        List<List<Integer>> res = new ArrayList<>();

        public List<List<Integer>> combine(int n, int k) {
            dfs(new ArrayList<>(), 1, n, k);
            return res;
        }

        private void dfs(ArrayList<Integer> sub, int idx, int n, int k) {
            if (sub.size() == k) {
                res.add(new ArrayList<>(sub));
                return;
            }
            for (int i = idx; i <= n; i++) {
                sub.add(i);
                dfs(sub, i + 1, n, k);
                sub.remove(sub.size() - 1);
            }
        }
    }


    static class _4th {
        List<List<Integer>> res = new ArrayList<>();

        public List<List<Integer>> combine(int n, int k) {
            dfs(new ArrayList<>(), 1, n, k);
            return res;
        }


        public void dfs(List<Integer> sub, int idx, int n, int k) {
            if (sub.size() == k) {
                res.add(new ArrayList<>(sub));
                return;
            }

            for (int i = idx; i <= n; i++) {
                sub.add(i);
                dfs(sub, i + 1, n, k);
                sub.remove(sub.size() - 1);
            }
        }
    }
}
