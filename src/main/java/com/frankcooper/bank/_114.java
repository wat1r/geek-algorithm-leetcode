package com.frankcooper.bank;

import com.frankcooper.struct.TreeNode;

import java.util.Stack;

/**
 * Created by FrankCooper
 * Date 2020/8/2 11:12
 * Description
 */
public class _114 {

    /**
     * https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list/solution/114-er-cha-shu-zhan-kai-wei-lian-biao-by-ming-zhi-/
     */
    static class _1st {
        public void flatten(TreeNode root) {
            if (root == null) return;
            flatten(root.left);
            flatten(root.right);
            TreeNode tmp = root.right;
            root.right = root.left;
            root.left = null;
            while (root.right != null) {
                root = root.right;
            }
            root.right = tmp;
        }
    }


    public void flatten(TreeNode root) {
        while (root != null) {
            if (root.left != null) {
                TreeNode pre = root.left;
                while (pre.right != null) pre = pre.right;//找到左子树的最右侧的节点
                pre.right = root.right;//前驱节点接上处理节点的右子树
                root.right = root.left;//处理节点的右节点挂上左子树
                root.left = null;//处理节点左子树设置为null
            }
            root = root.right;//继续转到下一个节点

        }
    }


    public void flatten4th(TreeNode root) {
        TreeNode curr = root;
        while (curr != null) {
            if (curr.left != null) {
                TreeNode next = curr.left;
                TreeNode pre = next;
                while (pre.right != null) pre = pre.right;
                pre.right = curr.right;
                curr.left = null;
                curr.right = next;
            }
            curr = curr.right;
        }
    }


    public void flatten3rd(TreeNode root) {
        if (root == null) return;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode pre = null;
        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            if (pre != null) {
                pre.right = curr;
                pre.left = null;
            }
            if (curr.right != null) stack.push(curr.right);
            if (curr.left != null) stack.push(curr.left);
            pre = curr;
        }

    }


    TreeNode pre = null;

    public void flatten2nd(TreeNode root) {
        if (root == null) return;
        flatten2nd(root.right);
        flatten2nd(root.left);
        root.right = pre;
        root.left = null;
        pre = root;
    }


    public void flatten1st(TreeNode root) {
        while (root != null) {
            if (root.left == null) root = root.right;
            else {
                TreeNode curr = root.left;
                while (curr.right != null) curr = curr.right;
                curr.right = root.right;
                root.right = root.left;
                root.left = null;
                root = root.right;
            }
        }
    }

}
