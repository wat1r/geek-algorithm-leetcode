package com.frankcooper.bank;

import com.frankcooper.struct.TreeNode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @Date 2020/7/30
 * @Author Frank Cooper
 * @Description
 */
public class _111 {
    static _111 handler = new _111();

    public static void main(String[] args) {

    }

    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;
        int tmp = Integer.MAX_VALUE;
        if (root.left != null) tmp = Math.min(tmp, minDepth(root.left));
        if (root.right != null) tmp = Math.min(tmp, minDepth(root.right));
        return tmp + 1;
    }

    public int minDepth2nd(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int level = 0;
        while (!queue.isEmpty()) {
            level++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                if (curr.left == null && curr.right == null) return level;
                if (curr.left != null) queue.offer(curr.left);
                if (curr.right != null) queue.offer(curr.right);
            }
        }
        return level;
    }



//
//    public static int minDepth3rd(TreeNode root) {
//        if (root == null) return 0;
//        Stack<Pair<TreeNode, Integer>> stack = new Stack<>();
//        stack.add(new Pair<TreeNode, Integer>(root, 1));
//        int depth = Integer.MAX_VALUE;
//        while (!stack.isEmpty()) {
//            Pair<TreeNode, Integer> pair = stack.pop();
//            TreeNode Node = pair.getKey();
//            if (Node.left == null && Node.right == null)
//                depth = Math.min(depth, pair.getValue());
//            if (Node.right != null) stack.add(new Pair<TreeNode, Integer>(Node.right, pair.getValue() + 1));
//            if (Node.left != null) stack.add(new Pair<TreeNode, Integer>(Node.left, pair.getValue() + 1));
//        }
//        return depth;
//    }
}
