package com.frankcooper.platform.leetcode.bank._1_100;

import com.frankcooper.struct.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FrankCooper
 * Date 2020/8/8 8:46
 * Description
 */
public class _99 {
    static _99 handler = new _99();

    public static void main(String[] args) {
//        handler.testOne();
        handler.testTwo();
    }


    private void testOne() {

        TreeNode _1 = new TreeNode(1);
        TreeNode _3 = new TreeNode(3);
        _1.left = _3;
        _1.right = null;
        TreeNode _2 = new TreeNode(2);
        _3.left = null;
        _3.right = _2;
        _2.left = null;
        _2.right = null;
        recoverTree1st(_1);
    }


    private void testTwo() {

        TreeNode _4 = new TreeNode(4);
        TreeNode _2 = new TreeNode(2);
        TreeNode _6 = new TreeNode(6);
        _4.left = _2;
        _4.right = _6;
        TreeNode _1 = new TreeNode(1);
        TreeNode _7 = new TreeNode(7);
        _2.left = _1;
        _2.right = _7;
//        _1.left=null;
//        _1.right=null;
//        _7.left = null;
//        _7.right = null;
        TreeNode _5 = new TreeNode(5);
        TreeNode _3 = new TreeNode(3);
        _6.left = _5;
        _6.right = _3;
        recoverTree1st(_4);


    }


    public void recoverTree1st(TreeNode root) {
        if (root == null) return;
        List<TreeNode> list = new ArrayList<>();
        //iterator the treenode with mid order
        helper(root, list);
        TreeNode first = null, second = null;
        int n = list.size();
        //find the treenode
        for (int i = 0; i < n - 1; i++) {
            TreeNode pre = list.get(i);
            TreeNode next = list.get(i + 1);
            if (pre.val > next.val) {
                if (first == null) first = pre;
                second = next;
            }
        }
        //swap two treenode value
        swap(first, second);
    }


    private void helper(TreeNode root, List<TreeNode> list) {
        if (root.left != null) helper(root.left, list);
        list.add(root);
        System.out.print(root.val + ",");
        if (root.right != null) helper(root.right, list);
    }


    private void swap(TreeNode m, TreeNode n) {
        int tmp = m.val;
        m.val = n.val;
        n.val = tmp;
    }


    public void recoverTree(TreeNode root) {
        TreeNode first = null, second = null;
        TreeNode pre = null;
        TreeNode curr = root;
        while (curr != null) {
            if (curr.left == null) {//步骤1
                /************************/
                if (pre != null && pre.val > curr.val) {
                    if (first == null) first = pre;
                    second = curr;
                }
                pre = curr;
                /************************/
                curr = curr.right;
            } else {//步骤2
                TreeNode predecessor = getPredecessor(curr);
                if (predecessor.right == null) {//步骤2.a
                    predecessor.right = curr;
                    curr = curr.left;
                } else if (predecessor.right == curr) {//步驟2.b
                    /************************/
                    if (pre != null && pre.val > curr.val) {
                        if (first == null) first = pre;
                        second = curr;
                    }
                    pre = curr;
                    /************************/
                    predecessor.right = null;
                    curr = curr.right;
                }
            }

        }
        swap(first, second);
    }


    private TreeNode getPredecessor(TreeNode curr) {
        TreeNode predecessor = curr;
        if (curr.left != null) {
            predecessor = curr.left;
            while (predecessor.right != null && predecessor.right != curr) {
                predecessor = predecessor.right;
            }
        }
        return predecessor;
    }

    static class _3rd {
        public void recoverTree(TreeNode root) {

        }


    }


}
