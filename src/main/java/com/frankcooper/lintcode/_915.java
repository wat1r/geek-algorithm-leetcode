package com.frankcooper.lintcode;

import java.util.*;

import com.frankcooper.struct.TreeNode;
import org.junit.Assert;

public class _915 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        TreeNode prev;
        TreeNode p;

        //中序遍历：首先遍历左子树，然后访问根节点，最后遍历右子树（左->根->右）
        public TreeNode inorderPredecessor(TreeNode root, TreeNode p) {
            this.p = p;
            dfs(root);
            return prev;
        }


        private void dfs(TreeNode root) {
            if (root == null) return;
            if (p.val > root.val) {
                prev = root;
                dfs(root.right);
            } else {
                dfs(root.left);
            }
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();

        }

        TreeNode predecessor;

        public TreeNode inorderPredecessor(TreeNode root, TreeNode p) {
            dfs(root, p);
            return predecessor;
        }

        private void dfs(TreeNode root, TreeNode p) {
            if (root == null) return;
            dfs(root.left, p);
            if (root.val < p.val) predecessor = root;
            dfs(root.right, p);
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }


        public TreeNode inorderPredecessor(TreeNode root, TreeNode p) {
            TreeNode predecessor = null;
            while (root != null) {
                if (root.val >= p.val) {
                    root = root.left;
                } else {
                    predecessor = root;
                    root = root.right;
                }
            }
            return predecessor;
        }

    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
