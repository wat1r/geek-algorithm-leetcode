package com.frankcooper.bank._101_200;

import com.frankcooper.struct.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Date 2020/7/30
 * @Author Frank Cooper
 * @Description
 */
public class _104 {
    public static void main(String[] args) {

    }


    public int maxDepth(TreeNode root) {
        return root == null ? 0 : 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

    public int maxDepth2nd(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                if (curr.left != null) queue.offer(curr.left);
                if (curr.right != null) queue.offer(curr.right);
            }
            level++;
        }
        return level;
    }

    static class _1st {
        public int maxDepth(TreeNode root) {
            return root == null ? 0 : Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
        }
    }
}
