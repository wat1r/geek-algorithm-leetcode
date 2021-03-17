package com.frankcooper.bank._501_1000;

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
}
