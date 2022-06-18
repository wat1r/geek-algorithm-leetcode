package com.frankcooper.platform.leetcode.interview;

import java.util.*;

import com.frankcooper.struct.TreeNode;

public class _04_09 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }

        List<List<Integer>> res = new LinkedList<>();


        public List<List<Integer>> BSTSequences(TreeNode root) {
            if (root == null) {
                res.add(new LinkedList<>());
                return res;
            }
            LinkedList<Integer> path = new LinkedList<>();
            path.add(root.val);
            dfs(root, new LinkedList<>(), path);
            return res;
        }


        private void dfs(TreeNode root, LinkedList<TreeNode> queue, LinkedList<Integer> path) {
            if (root == null) return;
            if (root.left != null) queue.offer(root.left);
            if (root.right != null) queue.offer(root.right);
            if (queue.isEmpty()) {
                res.add(new LinkedList<>(path));
                return;
            }
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.get(i);
                queue.remove(i);
                path.add(cur.val);
                dfs(cur, new LinkedList<>(queue), path);
                queue.add(i, cur);
                path.removeLast();

            }
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
