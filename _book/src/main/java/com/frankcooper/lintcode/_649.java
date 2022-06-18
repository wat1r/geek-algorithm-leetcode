package com.frankcooper.platform.lintcode;

import com.frankcooper.struct.TreeNode;

public class _649 {

    /**
     * same with _156
     */

    static class _1st {
        public TreeNode upsideDownBinaryTree(TreeNode root) {
            if (root == null || (root.left == null && root.right == null)) return root;
            TreeNode newRoot = upsideDownBinaryTree(root.left);
            root.left.left = root.right;
            root.left.right = root;
            root.left = null;
            root.right = null;
            return newRoot;
        }
    }
}
