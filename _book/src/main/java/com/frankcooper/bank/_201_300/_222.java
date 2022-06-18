package com.frankcooper.platform.leetcode.bank._201_300;

import com.frankcooper.struct.TreeNode;

public class _222 {

    static class _1st {

        int ans = 0;

        public int countNodes(TreeNode root) {
            dfs(root);
            return ans;
        }


        private void dfs(TreeNode root) {
            if (root == null) return;
            dfs(root.left);
            ans++;
            dfs(root.right);
        }


    }
}
