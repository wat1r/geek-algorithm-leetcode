package com.frankcooper.bank._801_900;

import com.frankcooper.struct.TreeNode;

public class _814 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        public TreeNode pruneTree(TreeNode root) {
            return dfs(root) ? root : null;
        }


        public boolean dfs(TreeNode root) {
            if (root == null) return false;
            boolean leftFlag = dfs(root.left);
            boolean rightFlag = dfs(root.right);
            if (!leftFlag) root.left = null;
            if (!rightFlag) root.right = null;
            return root.val == 1 || leftFlag || rightFlag;
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
