package com.frankcooper.platform.leetcode.bank._501_600;

import com.frankcooper.struct.TreeNode;

public class _563 {

    static class _1st {
        int ans = 0;

        public int findTilt(TreeNode root) {
            helper(root);
            return ans;

        }

        public int helper(TreeNode root) {
            if (root == null) return 0;
            int l = helper(root.left);
            int r = helper(root.right);
            ans += Math.abs(l - r);
            return l + r + root.val;
        }
    }


    static class _2nd {

        int res = 0;

        public int findTilt(TreeNode root) {
            dfs(root);
            return res;
        }


        private int dfs(TreeNode root) {
            if (root == null) return 0;
            int l = dfs(root.left);
            int r = dfs(root.right);
            res += Math.abs(l - r);
            return l + r + root.val;
        }
    }
}
