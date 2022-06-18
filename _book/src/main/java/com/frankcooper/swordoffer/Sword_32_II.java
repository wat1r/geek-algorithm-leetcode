package com.frankcooper.platform.leetcode.swordoffer;

import com.frankcooper.struct.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Sword_32_II {


    public static void main(String[] args) {
        Sword_32_II handler = new Sword_32_II();
        TreeNode l1 = new TreeNode(1);
        TreeNode l2 = new TreeNode(2);
        TreeNode l3 = new TreeNode(3);
        TreeNode l4 = new TreeNode(4);
        TreeNode l5 = new TreeNode(5);
        l1.left = l2;
        l1.right = l3;
        l2.left = l4;
        l3.right = l5;
        handler.levelOrder(l1);

    }


    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int level = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> sub = new ArrayList<>();
            for (int i = 0; i < size; ++i) {
                TreeNode curr = q.poll();
                if (level % 2 == 0) sub.add(curr.val);
                else sub.add(0, curr.val);
                if (curr.left != null) q.offer(curr.left);
                if (curr.right != null) q.offer(curr.right);
            }
            res.add(new ArrayList<>(sub));
            level++;
        }
        return res;
    }
}
