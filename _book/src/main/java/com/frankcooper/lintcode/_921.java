package com.frankcooper.lintcode;

import com.frankcooper.struct.TreeNode;

public class _921 {

    /**
     * same with _250
     */
    static class _1st {
        int ans = 0;

        public int countUnivalSubtrees(TreeNode root) {
            if (root == null) return ans;
            if (helper(root, root.val)) ans++;
            countUnivalSubtrees(root.left);
            countUnivalSubtrees(root.right);
            return ans;
        }


        private boolean helper(TreeNode root, int val) {
            if (root == null) return true;
            return root.val == val && helper(root.left, val) && helper(root.right, val);
        }


    }

    static class _2nd {

        int ans = 0;

        public int countUnivalSubtrees(TreeNode root) {
            helper(root);
            return ans;
        }


        private boolean helper(TreeNode root) {
            if (root == null) return true;
            if (root.left == null && root.right == null) {
                ans++;
                return true;
            }
            boolean l = helper(root.left);
            boolean r = helper(root.right);
            if (l && r && (root.left == null || root.left.val == root.val)
                    && (root.right == null || root.right.val == root.val)) {
                ans++;
                return true;
            }
            return false;
        }
    }

}
