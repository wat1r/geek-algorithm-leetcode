package com.frankcooper.bank._101_200;

import com.frankcooper.struct.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Created by FrankCooper
 * Date 2020/8/8 21:20
 * Description
 */
public class _144 {

    static class _1st {
        /*递归*/
        List<Integer> res = new ArrayList<>();

        public List<Integer> preorderTraversal(TreeNode root) {
            dfs(root);
            return res;
        }

        private void dfs(TreeNode root) {
            if (root == null) return;
            res.add(root.val);
            dfs(root.left);
            dfs(root.right);
        }
    }


    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        TreeNode curr = root;
        while (curr != null) {
            if (curr.left == null) {
                System.out.print(curr.val + " ");
                result.add(curr.val);
                curr = curr.right;
            } else {
                TreeNode predecessor = getPredecessor(curr);
                if (predecessor.right == null) {
                    predecessor.right = curr;
                    System.out.print(curr.val + " ");
                    result.add(curr.val);
                    curr = curr.left;
                } else if (predecessor.right == curr) {
                    predecessor.right = null;
                    curr = curr.right;
                }
            }
        }
        return result;
    }


    public void preOrderByMorris(TreeNode root) {
        TreeNode curr = root;
        while (curr != null) {
            if (curr.left == null) {
                System.out.print(curr.val + " ");
                curr = curr.right;
            } else {
                TreeNode predecessor = getPredecessor(curr);
                if (predecessor.right == null) {
                    predecessor.right = curr;
                    System.out.print(curr.val + " ");
                    curr = curr.left;
                } else if (predecessor.right == curr) {
                    predecessor.right = null;
                    curr = curr.right;
                }
            }
        }
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


    static class _2nd {
        List<Integer> res = new ArrayList<>();

        public List<Integer> preorderTraversal(TreeNode root) {
            if (root == null) return res;
            dfs(root);
            return res;

        }

        private void dfs(TreeNode root) {
            if (root != null) res.add(root.val);
            if (root.left != null) dfs(root.left);
            if (root.right != null) dfs(root.right);
        }
    }


    static class _3rd {
        List<Integer> res = new ArrayList<>();

        public List<Integer> preorderTraversal(TreeNode root) {
            Stack<TreeNode> stk = new Stack<>();
            TreeNode cur = root;
            while (cur != null || !stk.isEmpty()) {
                if (cur != null) {
                    res.add(cur.val);
                    stk.push(cur);
                    cur = cur.left;
                } else {
                    TreeNode tmp = stk.pop();
                    cur = tmp.right;
                }
            }
            return res;
        }
    }


}
