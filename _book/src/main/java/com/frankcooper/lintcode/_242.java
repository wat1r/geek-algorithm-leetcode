package com.frankcooper.lintcode;

import java.util.*;

import com.frankcooper.struct.ListNode;
import com.frankcooper.struct.TreeNode;
import org.junit.Assert;

public class _242 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            TreeNode r1 = new TreeNode(1);
            TreeNode r2 = new TreeNode(2);
            TreeNode r3 = new TreeNode(3);
            TreeNode r4 = new TreeNode(4);
            r1.left = r2;
            r1.right = r3;
            r2.left = r4;
            handler.binaryTreeToLists(r1);

        }


        public List<ListNode> binaryTreeToLists(TreeNode root) {
            List<ListNode> res = new ArrayList<>();
            if (root == null) return res;
            Queue<TreeNode> q = new LinkedList<>();
            q.offer(root);
            while (!q.isEmpty()) {
                ListNode head = null;
                ListNode cur = null;
                int size = q.size();
                for (int i = 0; i < size; i++) {
                    TreeNode node = q.poll();
                    if (i == 0) {
                        head = new ListNode(node.val);
                        cur = head;
                    } else {
                        cur.next = new ListNode(node.val);
                        cur = cur.next;
                    }
                    if (node.left != null) q.offer(node.left);
                    if (node.right != null) q.offer(node.right);
                }
                res.add(head);
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
