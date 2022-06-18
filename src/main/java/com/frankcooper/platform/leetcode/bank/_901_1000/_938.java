package com.frankcooper.platform.leetcode.bank._901_1000;

import com.frankcooper.struct.TreeNode;

import java.util.*;

public class _938 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }

        int res = 0;

        public int rangeSumBST(TreeNode root, int low, int high) {
            inOrder(root, low, high);
            return res;
        }

        private void inOrder(TreeNode root, int lo, int hi) {
            if (root == null) return;
            if (root.val >= lo && root.val <= hi) res += root.val;
            inOrder(root.left, lo, hi);
            inOrder(root.right, lo, hi);
        }
    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        List<Integer> list = new ArrayList<>();

        public int rangeSumBST(TreeNode root, int low, int high) {
            helper(root);
            int res = 0;
            for (int x : list) if (x >= low && x <= high) res += x;
            return res;
        }

        private void helper(TreeNode root) {
            if (root == null) return;
            if (root.left != null) helper(root.left);
            list.add(root.val);
            if (root.right != null) helper(root.right);
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
