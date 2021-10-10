package com.frankcooper.binary_tree;

import com.frankcooper.io.TreeNodeIOUtils;
import com.frankcooper.struct.TreeNode;
import org.junit.Assert;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author: wangzhou(Frank Cooper)
 * @date: 2021/9/27 14:20
 * @description:
 */
public class BinaryTree {

    static class _1st_1 {
        public static void main(String[] args) {
            _1st_1 handler = new _1st_1();
            TreeNode root = TreeNodeIOUtils.transform("[1,2,3,4,5,null,null]");
            Assert.assertEquals(3, handler.maxDepth(root));
        }


        public int maxDepth(TreeNode root) {
            return root == null ? 0 : Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
        }
    }

    static class _1st_2 {
        public static void main(String[] args) {
            _1st_2 handler = new _1st_2();
            TreeNode root = TreeNodeIOUtils.transform("[1,2,3,4,5,null,null]");
            Assert.assertEquals(3, handler.maxDepth(root));
        }

        public int maxDepth(TreeNode root) {
            if (root == null) return 0;
            int depth = 0;
            Queue<TreeNode> q = new LinkedList<>();
            q.offer(root);
            q.offer(null);
            while (!q.isEmpty()) {
                TreeNode cur = q.poll();
                if (cur == null) depth++;
                if (cur != null) {
                    if (cur.left != null) q.offer(cur.left);
                    if (cur.right != null) q.offer(cur.right);
                } else if (!q.isEmpty()) q.offer(null);
            }
            return depth;
        }
    }

    static class _1st_3 {
        public int maxDepth(TreeNode root) {
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
    }


    static class _2nd_1 {
        public static void main(String[] args) {
            _2nd_1 handler = new _2nd_1();
            TreeNode root = TreeNodeIOUtils.transform("[1,2,3,4,5,null,null]");
            handler.printLevelOrder(root);
        }

        public void printLevelOrder(TreeNode root) {
            int height = height(root);
            for (int i = 1; i <= height; i++) {
                printCurrentLevel(root, i);
            }
        }

        private int height(TreeNode root) {
            if (root == null)
                return 0;
            else {
                /* compute  height of each subtree */
                int lheight = height(root.left);
                int rheight = height(root.right);
                /* use the larger one */
                if (lheight > rheight)
                    return (lheight + 1);
                else return (rheight + 1);
            }
        }


        private void printCurrentLevel(TreeNode root, int level) {
            if (root == null)
                return;
            if (level == 1)
                System.out.print(root.val + " ");
            else if (level > 1) {
                printCurrentLevel(root.left, level - 1);
                printCurrentLevel(root.right, level - 1);
            }
        }
    }


}
