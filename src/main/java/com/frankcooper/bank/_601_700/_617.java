package com.frankcooper.bank._601_700;

import com.frankcooper.struct.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class _617 {


    static class _1st {
        public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
//            if (root1 == null || root2 == null) return root1 == null ? root2 : root1;
            return dfs(root1, root2);
        }

        private TreeNode dfs(TreeNode root1, TreeNode root2) {
            if (root1 == null || root2 == null) return root1 == null ? root2 : root1;
            root1.val += root2.val;
            root1.left = dfs(root1.left, root2.left);
            root1.right = dfs(root1.right, root2.right);
            return root1;
        }

    }


    static class _2nd {
        public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
            if (root1 == null || root2 == null) return root1 == null ? root2 : root1;
            Queue<TreeNode> q = new LinkedList<>();
            q.offer(root1);
            q.offer(root2);
            while (!q.isEmpty()) {
                TreeNode r1 = q.poll();
                TreeNode r2 = q.poll();
                r1.val += r2.val;
                if (r1.left != null && r2.left != null) {
                    q.offer(r1.left);
                    q.offer(r2.left);
                } else if (r1.left == null) {
                    r1.left = r2.left;
                }

                if (r1.right != null && r2.right != null) {
                    q.offer(r1.right);
                    q.offer(r2.right);
                } else if (r1.right == null) {
                    r1.right = r2.right;
                }
            }
            return root1;
        }
    }

    static class _3rd {
        public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
            return dfs(root1, root2);
        }

        public TreeNode dfs(TreeNode root1, TreeNode root2) {
            if (root1 == null || root2 == null) return root1 == null ? root2 : root1;
            root1.val += root2.val;
            root1.left = dfs(root1.left, root2.left);
            root1.right = dfs(root1.right, root2.right);
            return root1;
        }
    }
}
