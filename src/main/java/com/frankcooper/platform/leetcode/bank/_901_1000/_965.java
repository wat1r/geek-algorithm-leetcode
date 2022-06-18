package com.frankcooper.platform.leetcode.bank._901_1000;

import com.frankcooper.struct.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class _965 {


    static class _1st {
        public boolean isUnivalTree(TreeNode root) {
            if (root == null) return false;
            return dfs(root, root.val);
        }

        private boolean dfs(TreeNode root, int val) {
            if (root == null) return true;
            if (root.val != val) return false;
            return dfs(root.left, val) && dfs(root.right, val);
        }
    }


    static class _1st_1 {
        public boolean isUnivalTree(TreeNode root) {
            return dfs(root, root.val);
        }

        private boolean dfs(TreeNode root, int val) {
            if (root == null) return true;
            if (root.val != val) return false;
            return dfs(root.left, val) && dfs(root.right, val);
        }
    }

    static class _1st_2 {
        public boolean isUnivalTree(TreeNode root) {
            if (root == null) return true;
            if (root.left != null) {
                if (root.val != root.left.val || !isUnivalTree(root.left)) return false;
            }
            if (root.right != null) {
                if (root.val != root.right.val || !isUnivalTree(root.right)) return false;
            }
            return true;
        }
    }

    static class _1st_3 {
        public boolean isUnivalTree(TreeNode root) {
            Queue<TreeNode> q = new LinkedList<>();
            q.offer(root);
            while (!q.isEmpty()) {
                TreeNode cur = q.poll();
                if (cur.val != root.val) return false;
                if (cur.left != null) q.offer(cur.left);
                if (cur.right != null) q.offer(cur.right);
            }
            return true;
        }
    }

    static class _1st_4 {
        public boolean isUnivalTree(TreeNode root) {
            if (root == null) return true;
            if (root.left != null && root.left.val != root.val) return false;
            if (root.right != null && root.right.val != root.val) return false;
            return isUnivalTree(root.left) && isUnivalTree(root.right);
        }
    }

}
