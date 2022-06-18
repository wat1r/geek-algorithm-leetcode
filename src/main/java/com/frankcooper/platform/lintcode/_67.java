package com.frankcooper.platform.lintcode;

import java.util.*;

import com.frankcooper.struct.TreeNode;

public class _67 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }

        List<Integer> res = new ArrayList<>();

        public List<Integer> inorderTraversal(TreeNode root) {
            dfs(root);
            return res;

        }

        private void dfs(TreeNode root) {
            if (root == null) return;
            dfs(root.left);
            res.add(root.val);
            dfs(root.right);
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
