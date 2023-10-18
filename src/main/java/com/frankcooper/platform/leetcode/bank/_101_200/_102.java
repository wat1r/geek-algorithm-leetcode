package com.frankcooper.platform.leetcode.bank._101_200;

import com.frankcooper.io.TreeNodeIOUtils;
import com.frankcooper.platform.leetcode.bank._1_100._2;
import com.frankcooper.struct.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class _102 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> sub = new ArrayList<>();
            for (int i = 0; i < size; ++i) {
                TreeNode curr = q.poll();
                sub.add(curr.val);
                if (curr.left != null) q.offer(curr.left);
                if (curr.right != null) q.offer(curr.right);
            }
            res.add(new ArrayList<>(sub));
        }
        return res;
    }

    static class _1st {
        public List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> res = new ArrayList<>();
            if (root == null) return res;
            Queue<TreeNode> q = new LinkedList<>();
            q.add(root);
            while (!q.isEmpty()) {
                int size = q.size();
                List<Integer> sub = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    TreeNode cur = q.poll();
                    sub.add(cur.val);
                    if (cur.left != null) q.add(cur.left);
                    if (cur.right != null) q.add(cur.right);
                }
                res.add(sub);
            }
            return res;
        }
    }

    static class _2nd {

        public static void main(String[] args) {
            _2nd handler = new _2nd();
            TreeNode root = TreeNodeIOUtils.transform("[3,9,20,null,null,15,7]");
            handler.levelOrder(root);
        }


        public List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> res = new ArrayList<>();
            if (root == null) {
                return res;
            }

            dfs(root, 1, res);
            return res;
        }


        public void dfs(TreeNode root, int index, List<List<Integer>> res) {
            if (index > res.size()) {
                res.add(new ArrayList<>());
            }
            res.get(index - 1).add(root.val);
            if (root.left != null) {
                dfs(root.left, index + 1, res);
            }
            if (root.right != null) {
                dfs(root.right, index + 1, res);
            }
        }
    }
}
