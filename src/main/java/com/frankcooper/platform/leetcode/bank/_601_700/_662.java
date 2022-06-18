package com.frankcooper.platform.leetcode.bank._601_700;

import java.util.*;

import com.frankcooper.struct.TreeNode;

public class _662 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        public int widthOfBinaryTree(TreeNode root) {
            if (root == null) return 0;
            LinkedList<TreeNode> q = new LinkedList<>();
            q.offer(root);
            int res = 0;
            while (!q.isEmpty()) {
                int size = q.size();
                res = Math.max(res, q.getLast().val - q.getFirst().val + 1);
                for (int i = 0; i < size; i++) {
                    TreeNode cur = q.poll();
                    if (cur.left != null) {
                        cur.left.val = 2 * cur.val + 1;//存下来当前父节点的左孩子节点的下标索引
                        q.offer(cur.left);
                    }
                    if (cur.right != null) {
                        cur.right.val = 2 * cur.val + 2;//存下来当前父节点的右孩子节点的下标索引
                        q.offer(cur.right);
                    }
                }
            }
            return res;
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
