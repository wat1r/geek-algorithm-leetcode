package com.frankcooper.platform.lintcode;

import com.frankcooper.struct.TreeNode;

public class _475 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        public int maxPathSum2(TreeNode root) {
            if(root == null) return 0;
            int res = dfs(root);
            return Math.max(res,root.val);
        }


        private int dfs(TreeNode root) {
            if (root == null) return 0;
            int l = dfs(root.left);
            int r = dfs(root.right);
            return Math.max(l + root.val, r + root.val);
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
