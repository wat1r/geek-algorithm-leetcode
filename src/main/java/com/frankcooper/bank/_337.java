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


    class _3rd {


        public int rob(TreeNode root) {
            int[] f = dfs(root);
            return Math.max(f[0], f[1]);
        }

        private int[] dfs(TreeNode node) {
            if (node == null) return new int[]{0, 0};
            int[] fLeft = dfs(node.left);
            int[] fRight = dfs(node.right);
            int[] f = new int[2];
            f[0] = Math.max(fLeft[0], fLeft[1]) + Math.max(fRight[0], fRight[1]);
            f[1] = fLeft[0] + fRight[0] + node.val;
            return f;
        }

    }


    public int rob2nd(TreeNode root) {
        if (root == null) return 0;
        int first = root.val;
        if (root.left != null) first += rob2nd(root.left.left) + rob2nd(root.left.right);
        if (root.right != null) first += rob2nd(root.right.left) + rob2nd(root.right.right);
        int second = rob2nd(root.left) + rob2nd(root.right);
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
