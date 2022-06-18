package com.frankcooper.platform.leetcode.bank._101_200;

import com.frankcooper.struct.TreeNode;

import java.util.*;

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


    static class _1st {
        public boolean isBalanced(TreeNode root) {
            if (root != null) {
                if (Math.abs(getHeight(root.left) - getHeight(root.right)) > 1) return false;
                if (!isBalanced(root.left)) return false;
                return isBalanced(root.right);
            }
            return true;
        }


        private int getHeight(TreeNode root) {
            return root == null ? 0 : Math.max(getHeight(root.left), getHeight(root.right)) + 1;
        }
    }

    static class _2nd {
        public boolean isBalanced(TreeNode root) {
            if (root == null) return true;
            Deque<TreeNode> stack = new ArrayDeque<>();
            while (root != null || !stack.isEmpty()) {
                if (root != null) {
                    stack.push(root);
                    root = root.left;
                } else {
                    TreeNode cur = stack.pop();
                    int lh = getHeight(cur.left);
                    int rh = getHeight(cur.right);
                    if (Math.abs(lh - rh) > 1) return false;
                    root = cur.right;
                }
            }
            return true;
        }

        public int getHeight(TreeNode node) {
            int res = 0;
            if (node == null) return res;
            Queue<TreeNode> que = new LinkedList<>();
            que.offer(node);
            while (!que.isEmpty()) {
                int size = que.size();
                res++;
                while (size-- > 0) {
                    TreeNode cur = que.poll();
                    if (cur.left != null) que.offer(cur.left);
                    if (cur.right != null) que.offer(cur.right);
                }
            }
            return res;
        }

    }

}
