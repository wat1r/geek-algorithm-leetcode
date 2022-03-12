package com.frankcooper.bank._501_600;

import java.util.*;

import com.frankcooper.struct.TreeNode;

public class _513 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        public int findBottomLeftValue(TreeNode root) {
            Queue<TreeNode> q = new LinkedList<>();
            q.offer(root);
            int res = 0;
            while (!q.isEmpty()) {
                int size = q.size();
                for (int i = 0; i < size; i++) {
                    TreeNode cur = q.poll();
                    if (i == 0) res = cur.val;
                    if (cur.left != null) q.offer(cur.left);
                    if (cur.right != null) q.offer(cur.right);
                }
            }
            return res;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        public int findBottomLeftValue(TreeNode root) {
            Queue<TreeNode> q = new LinkedList<>();
            q.offer(root);
            int res = 0;
            while (!q.isEmpty()) {
                int size = q.size();
                for (int i = 0; i < size; i++) {
                    TreeNode cur = q.poll();
                    res = cur.val;
                    if (cur.right != null) q.offer(cur.right);
                    if (cur.left != null) q.offer(cur.left);
                }
            }
            return res;
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }

        int maxDepth = -1, res = 0;

        public int findBottomLeftValue(TreeNode root) {
            dfs(root, 0);
            return res;
        }

        private void dfs(TreeNode root, int depth) {
            if (root == null) return;
            dfs(root.left, depth + 1);
            if (depth > maxDepth) {
                maxDepth = depth;
                res = root.val;
            }
            dfs(root.right, depth + 1);
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
