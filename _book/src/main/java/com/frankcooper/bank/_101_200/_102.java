package com.frankcooper.platform.leetcode.bank._101_200;

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
}
