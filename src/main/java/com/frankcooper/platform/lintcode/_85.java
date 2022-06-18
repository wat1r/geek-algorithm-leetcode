package com.frankcooper.platform.lintcode;

import com.frankcooper.struct.TreeNode;

public class _85 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }

        public TreeNode insertNode(TreeNode root, TreeNode node) {
            return dfs(root, node);
        }

        private TreeNode dfs(TreeNode root, TreeNode node) {
            if (root == null) return node;
            if (root.val > node.val) root.left = dfs(root.left, node);
            else root.right = dfs(root.right, node);
            return root;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
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
