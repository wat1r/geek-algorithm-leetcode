package com.frankcooper.platform.leetcode.bank._401_500;

import com.frankcooper.struct.TreeNode;

public class _450 {

    static class _1st {
        /**
         * https://leetcode-cn.com/problems/delete-node-in-a-bst/solution/shan-chu-er-cha-sou-suo-shu-zhong-de-jie-dian-by-l/
         */

        public TreeNode deleteNode(TreeNode root, int key) {
            if (root == null) return null;
            if (key > root.val) root.right = deleteNode(root.right, key);
            if (key < root.val) root.left = deleteNode(root.left, key);
            if (key == root.val) {
                if (root.left == null && root.right == null) root = null;
                else if (root.right != null) {
                    root.val = successor(root);
                    root.right = deleteNode(root.right, root.val);
                } else if (root.right == null) {
                    root.val = predecessor(root);
                    root.left = deleteNode(root.left, root.val);
                }
            }
            return root;
        }


        public int successor(TreeNode root) {
            root = root.right;
            while (root.left != null) root = root.left;
            return root.val;
        }

        public int predecessor(TreeNode root) {
            root = root.left;
            while (root.right != null) root = root.right;
            return root.val;
        }
    }


    static class _2nd {
        public TreeNode deleteNode(TreeNode root, int key) {
            if (root == null) return null;//节点为空，返回
            //根据二叉树的性质分 <  > =
            if (root.val < key) root.right = deleteNode(root.right, key);
            else if (root.val > key) root.left = deleteNode(root.left, key);
            else {
                //当前节点的左子树为空，返回当前的右子树
                if (root.left == null) return root.right;
                //当前节点的右子树为空，返回当前的左子树
                if (root.right == null) return root.left;
                //左右子树都不为空，找到右孩子的最左节点 记为p
                TreeNode node = root.right;
                while (node.left != null) {
                    node = node.left;
                }
                //将当前节点在左子树挂在p的孩子上
                node.left = root.left;
                //当前节点的右子树替换掉当前节点，完成当前节点的删除
                root = root.right;
            }
            return root;
        }
    }


}
