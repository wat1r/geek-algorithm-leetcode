package com.frankcooper.bank._501_1000;

import java.util.*;

/**
 * Created by FrankCooper
 * Date 2020/10/3 21:16
 * Description 欧拉回路
 */
public class _753 {


    static _753 handler = new _753();


    public static void main(String[] args) {
        _3rd handler = new _3rd();
//        _2nd handler = new _2nd();
        handler.crackSafe(2, 2);

    }


    class _1st {
        Set<Integer> visited = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        int k;
        int highest;


        public String crackSafe(int n, int k) {
            highest = (int) Math.pow(10, n - 1);
            this.k = k;
            dfs(0);
            for (int i = 1; i < n; i++) {
                sb.append("0");
            }
            return sb.toString();
        }

        private void dfs(int curr) {
            for (int x = 0; x < k; x++) {
                int next = curr * 10 + x;
                if (!visited.contains(next)) {
                    visited.add(next);
                    dfs(next % highest);
                    sb.append(x);
                }
            }
        }
    }


    static class _2nd {
        Set<Integer> visited = new HashSet<>();
        StringBuilder res = new StringBuilder();
        int h, k;

        public String crackSafe(int n, int k) {
            h = (int) Math.pow(10, n - 1);
            this.k = k;
            dfs(0);
            for (int i = 1; i < n; i++) res.append('0');
            return res.toString();
        }

        private void dfs(int u) {
            for (int i = 0; i < k; i++) {
                int v = u * 10 + i;
                if (!visited.contains(v)) {
                    visited.add(v);
                    dfs(v % h);
                    res.append(i);
                }
            }
        }

    }

    /**
     * If ch='0', then we first check if the last (n-1) chars + '0' is an unused combination. It's possible that this combination is successful and gives us valid password (still stored in pwd and not removed because we returned true). If our subproblem returns false, that tells us that our dfs path is bad and doesn't result in a valid password string. In this case we need to undo our last actions (marking that last combination as used and adding the last character).
     *
     * Assume pwd ends with S (last n-1 chars).
     * Check if there's a pwd ending with S + '0'
     *
     * Case 1 (dfs path is good):
     * If this works, we'll find out at the bottom of our dfs path (base case where we're able to visit all k^n nodes)
     * Bubble true back up and don't touch our stringbuilder pwd
     *
     * Case 2 (dfs path is bad):
     * DFS fails and returns false and doesn't return true back to the caller
     * Now remove the fact we've seen S + '0' because we'll no longer use this combination at dfs on this level of recursion
     * Now remove the last character '0' because the DFS failed, showing '0' is not viable at this level
     */

    static class _3rd {


        public String crackSafe(int n, int k) {
            //初始化的扩散seed
            StringBuilder seed = new StringBuilder(String.join("", Collections.nCopies(n, "0")));
            Set<String> vis = new HashSet<>();
            vis.add(seed.toString());
            int t = (int) Math.pow(k, n);//目标的组合数量
            dfs(seed, vis, t, n, k);
            return seed.toString();
        }

        private boolean dfs(StringBuilder seed, Set<String> vis, int t, int n, int k) {
            if (vis.size() == t) return true;//出口条件，组合数到达目标的组合数量
            String last = seed.substring(seed.length() - n + 1);//最后的n-1个字符
            for (char c = '0'; c < '0' + k; c++) {
                String tmp = last + c;
                if (!vis.contains(tmp)) {
                    vis.add(tmp);
                    seed.append(c);
                    if (dfs(seed, vis, t, n, k)) return true;
                    vis.remove(tmp);
                    seed.deleteCharAt(seed.length() - 1);
                }
            }
            return false;
        }


    }

}
