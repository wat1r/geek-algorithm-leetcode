package com.frankcooper.platform.lintcode;

import com.frankcooper.struct.TreeNode;

public class _1181 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        //1.经过根节点，左右子树的最大深度相加
        //2.不经过根节点，找出根节点的左子树或者根节点的右子树作为根节点的最长路径
        int maxv = 0;

        public int diameterOfBinaryTree(TreeNode root) {
            // write your code here
            dfs(root);
            return maxv;
        }

        private int dfs(TreeNode root) {
            if (root == null) return 0;
            int left = dfs(root.left);
            int right = dfs(root.right);
            maxv = Math.max(maxv, left + right);
            return Math.max(left, right) + 1;
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
