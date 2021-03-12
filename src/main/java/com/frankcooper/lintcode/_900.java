package com.frankcooper.lintcode;

import com.frankcooper.struct.TreeNode;

public class _900 {
    /**
     * 用270
     */

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }

        int ans = 0;

        public int closestValue(TreeNode root, double target) {
            dfs(root, target);
            return ans;
        }

        private void dfs(TreeNode root, double target) {
            if (root == null) return;
            if (Math.abs(root.val * 1.0 - target) < Math.abs(ans * 1.0 - target)) ans = root.val;
            if (root.val == target) {
                return;
            } else if (root.val < target) dfs(root.right, target);
            else if (root.val > target) dfs(root.left, target);
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
}
