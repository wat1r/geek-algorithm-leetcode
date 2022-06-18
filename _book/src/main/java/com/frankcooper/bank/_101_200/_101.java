package com.frankcooper.platform.leetcode.bank._101_200;

import com.frankcooper.struct.TreeNode;

/**
 * @Date 2020/8/7
 * @Author Frank Cooper
 * @Description
 */
public class _101 {

    public boolean isSymmetric(TreeNode root) {
        if (root == null) return false;
        return helper(root.left, root.right);
    }


    public boolean helper(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if (left == null || right == null) return false;
        return left.val == right.val && helper(left.left, right.right) && helper(left.right, right.left);
    }


    static class _2nd {
        public boolean isSymmetric(TreeNode root) {
            if (root == null) return false;
            return helper(root.left, root.right);
        }

        public boolean helper(TreeNode l, TreeNode r) {
            if (l == null && r == null) return true;
            if (l == null || r == null) return false;
            return l.val == r.val && helper(l.left, r.right) && helper(l.right, r.left);
        }
    }
}
