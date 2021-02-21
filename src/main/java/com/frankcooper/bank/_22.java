package com.frankcooper.bank;

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
}
