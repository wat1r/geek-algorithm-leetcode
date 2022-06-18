package com.frankcooper.platform.leetcode.interview;

import com.frankcooper.struct.ListNode;
import com.frankcooper.struct.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class _04_03 {

    static class _1st {
        public ListNode[] listOfDepth(TreeNode tree) {
            int n = depth(tree);
            ListNode[] ans = new ListNode[n];
            int level = 0;
            Queue<TreeNode> q = new LinkedList<>();
            q.offer(tree);
            while (!q.isEmpty()) {
                int size = q.size();
                ListNode head = null;
                ListNode p = null;
                for (int i = 0; i < size; i++) {
                    TreeNode curr = q.poll();
                    if (curr.left != null) q.offer(curr.left);
                    if (curr.right != null) q.offer(curr.right);
                    System.out.printf("%d\n", curr.val);
                    ListNode newNode = new ListNode(curr.val);
                    if (i == 0) head = newNode;
                    if(p==null)  p = newNode;
                    else {
                        p.next = newNode;
                        p = p.next;
                    }
                }
                System.out.printf("level:%d\n", level);
                ans[level++] = head;
            }
            return ans;
        }

        private int depth(TreeNode root) {
            if (root == null) return 0;
            return Math.max(depth(root.left), depth(root.right)) + 1;
        }
    }
}
