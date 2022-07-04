package com.frankcooper.platform.leetcode.bank._501_600;

import java.util.*;

import com.frankcooper.io.ListNodeIOUtils;
import com.frankcooper.io.TreeNodeIOUtils;
import com.frankcooper.struct.ListNode;
import com.frankcooper.struct.TreeNode;

public class _515 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        public List<Integer> largestValues(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            if (root == null) return res;
            Queue<TreeNode> q = new LinkedList<>();
            q.offer(root);
            while (!q.isEmpty()) {
                int size = q.size();
                int t = Integer.MIN_VALUE;
                for (int i = 0; i < size; i++) {
                    TreeNode cur = q.poll();
                    t = Math.max(t, cur.val);
                    if (cur.left != null) q.offer(cur.left);
                    if (cur.right != null) q.offer(cur.right);
                }
                res.add(t);
            }
            return res;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        public List<Integer> largestValues(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            Queue<TreeNode> q = new LinkedList<>();
            if (root != null) q.offer(root);
            while (!q.isEmpty()) {
                int size = q.size();
                int t = Integer.MIN_VALUE;
                for (int i = 0; i < size; i++) {
                    TreeNode cur = q.poll();
                    t = Math.max(t, cur.val);
                    if (cur.left != null) q.offer(cur.left);
                    if (cur.right != null) q.offer(cur.right);
                }
                res.add(t);
            }
            return res;
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
            TreeNode root = TreeNodeIOUtils.transform("[1,3,2,5,3,null,9]");
            handler.largestValues(root);
        }

        public List<Integer> largestValues(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            if (root == null) return res;
            dfs(root, 0, res);
            return res;
        }

        //depth表当前处理的节点的深度
        private void dfs(TreeNode root, int depth, List<Integer> res) {
            if (depth == res.size()) {//第一次来到该深度的节点
                res.add(root.val);
            } else {//非第一次来到该层
                res.set(depth, Math.max(root.val, res.get(depth)));
            }
            if (root.left != null) dfs(root.left, depth + 1, res);
            if (root.right != null) dfs(root.right, depth + 1, res);
        }


    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
