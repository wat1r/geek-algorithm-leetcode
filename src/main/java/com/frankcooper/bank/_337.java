package com.frankcooper.bank;

import com.frankcooper.struct.TreeNode;

/**
 * @Date 2020/8/5
 * @Author Frank Cooper
 * @Description
 */
public class _337 {

    public static void main(String[] args) {

    }


    public int rob(TreeNode root) {
        if (root == null) return 0;
        int first = root.val;
        if (root.left != null) first += rob(root.left.left) + rob(root.left.right);
        if (root.right != null) first += rob(root.right.left) + rob(root.right.right);
        int second = rob(root.left) + rob(root.right);
        return Math.max(first, second);
    }


    public int rob1st(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return root.val;
        return Math.max(dfs(root, true), dfs(root, false));

    }

    private int dfs(TreeNode root, boolean visit) {
        if (root.left == null && root.right == null) return visit ? root.val : 0;
        int leftValue = 0, rightValue = 0;
        if (visit) {
            if (root.left != null) leftValue = dfs(root.left, false);
            if (root.right != null) rightValue = dfs(root.right, false);
            return root.val + leftValue + rightValue;
        } else {
            if (root.left != null) leftValue = Math.max(dfs(root.left, true), dfs(root.left, false));
            if (root.right != null) rightValue = Math.max(dfs(root.right, true), dfs(root.right, false));
            return leftValue + rightValue;
        }
    }

}
