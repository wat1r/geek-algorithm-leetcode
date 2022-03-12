package com.frankcooper.bank._501_600;

import com.frankcooper.struct.TreeNode;

//543. 二叉树的直径  543. Diameter of Binary Tree  Easy
public class _543 {
    public static void main(String[] args) {

    }


    /*
        ##### 方法1：DFS
     */
    int res = 1;

    public int diameterOfBinaryTree(TreeNode root) {
        dfs(root);
        return res - 1;
    }

    public int dfs(TreeNode root) {
        if (root == null) return 0;//如果达到叶子节点的子节点，返回0
        int leftDepth = dfs(root.left);//遍历左子树
        int rightDepth = dfs(root.right);//遍历右子树
        res = Math.max(res, leftDepth + rightDepth + 1);//计算最大直径
        return Math.max(leftDepth, rightDepth) + 1;//返回最大的深度
    }


    static class _2nd {
        int ans = 1;

        public int diameterOfBinaryTree(TreeNode root) {
            dfs(root);
            return ans - 1;
        }

        public int dfs(TreeNode root) {
            if (root == null) return 0;
            int L = dfs(root.left);
            int R = dfs(root.right);
            ans = Math.max(ans, L + R + 1);
            return Math.max(L, R) + 1;
        }
    }
}
