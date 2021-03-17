package com.frankcooper.bank._501_1000;

import com.frankcooper.struct.TreeNode;

public class _965 {


    static class _1st {
        public boolean isUnivalTree(TreeNode root) {
            if (root == null) return false;
            return dfs(root, root.val);
        }

        private boolean dfs(TreeNode root, int val) {
            if (root == null) return true;
            if (root.val != val) return false;
            return dfs(root.left, val) && dfs(root.right, val);
        }
    }


}
