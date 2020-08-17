package com.frankcooper.bank;

import com.frankcooper.struct.TreeNode;

/**
 * @Date 2020/8/17
 * @Author Frank Cooper
 * @Description
 */
public class _110 {

    public boolean isBalanced(TreeNode root) {
        if (root != null) {
            if (Math.abs(getHeight(root.left) - Math.abs(getHeight(root.right))) > 1) return false;
            if (!isBalanced(root.left)) return false;
            if (!isBalanced(root.right)) return false;
        }
        return true;
    }

    private int getHeight(TreeNode root) {
        if (root == null) return 0;
        return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
    }



}
