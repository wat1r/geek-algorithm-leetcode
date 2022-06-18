package com.frankcooper.platform.leetcode.interview;

import com.frankcooper.struct.TreeNode;

import java.util.*;

public class _04_05 {

    static class _1st {
        public boolean isValidBST(TreeNode root) {
            Stack<TreeNode> stk = new Stack<>();
            List<Integer> list = new ArrayList<>();
            while (!stk.isEmpty() || root != null) {
                while (root != null) {
                    stk.push(root);
                    root = root.left;
                }
                root = stk.pop();
                list.add(root.val);
                root = root.right;
            }
            for (int i = 1; i < list.size(); i++) {
                if (list.get(i) <= list.get(i - 1)) return false;
            }
            return true;
        }
    }


    static class _2nd {

        public static void main(String[] args) {
            System.out.println(Integer.MAX_VALUE);
        }

        public boolean isValidBST(TreeNode root) {
            return helper(root, Long.MIN_VALUE, Long.MAX_VALUE);
        }

        private boolean helper(TreeNode root, long lo, long hi) {
            if (root == null) return true;
            int v = root.val;
            if (lo >= v || hi <= v) return false;
            return helper(root.left, lo, v) && helper(root.right, v, hi);
        }
    }
}
