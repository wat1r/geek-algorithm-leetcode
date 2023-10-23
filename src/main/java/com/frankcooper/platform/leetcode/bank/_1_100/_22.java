package com.frankcooper.platform.leetcode.bank._1_100;

import java.util.ArrayList;
import java.util.List;

public class _22 {


    static class _1st {
        public List<String> generateParenthesis(int n) {
            List<String> ans = new ArrayList<>();
            dfs("", n, n, ans);
            return ans;
        }

        private void dfs(String currStr, int l, int r, List<String> ans) {
            if (l == 0 && r == 0) {
                ans.add(currStr);
                return;
            }
            if (l > r) return;
            if (l > 0) dfs(currStr + "(", l - 1, r, ans);
            if (r > 0) dfs(currStr + ")", l, r - 1, ans);
        }
    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            int n = 3;
            handler.generateParenthesis(n);
        }

        public List<String> generateParenthesis(int n) {
            List<String> res = new ArrayList<>();
            dfs("", n, n, res);
            return res;
        }

        private void dfs(String s, int l, int r, List<String> res) {
            if (l == 0 && r == 0) {
                res.add(s);
                return;
            }
            if (l > r) {
                return;
            }
            if (l > 0) {
                dfs(s + "(", l - 1, r, res);
            }
            if (r > 0) {
                dfs(s + ")", l, r - 1, res);
            }
        }
    }
}
