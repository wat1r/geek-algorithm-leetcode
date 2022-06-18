package com.frankcooper.platform.leetcode.bank._101_200;

import java.util.LinkedList;
import java.util.Queue;

public class _116 {

    static class _1st {
        public Node connect(Node root) {
            if (root == null || root.left == null) return root;
            root.left.next = root.right;
            if (root.next != null) root.right.next = root.next.left;
            connect(root.left);
            connect(root.right);
            return root;

        }
    }


    static class _2nd {
        public Node connect(Node root) {
            if (root == null) return null;
            LinkedList<Node> q = new LinkedList<>();
            q.offer(root);
            while (!q.isEmpty()) {
                int size = q.size();
                Node pre = null;
                for (int i = 0; i < size; i++) {
                    Node curr = q.poll();
                    if (pre != null) pre.next = curr;
                    pre = curr;
                    if (curr.left != null) q.offer(curr.left);
                    if (curr.right != null) q.offer(curr.right);
                }
            }
            return root;
        }
    }


    /**
     * https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node/solution/bfshe-di-gui-zui-hou-liang-chong-ji-bai-liao-100-2/
     */
    static class _4th {
        public Node connect(Node root) {
            if (root == null) return root;
            Node curr = root;
            while (curr != null) {
                Node dummy = new Node(-1);
                Node pre = dummy;
                while (curr != null && curr.left != null) {
                    pre.next = curr.left;
                    pre = pre.next;
                    pre.next = curr.right;
                    pre = pre.next;
                    curr = curr.next;
                }
                curr = dummy.next;
            }
            return root;
        }
    }


    static class _3rd {
        public Node connect(Node root) {
            if (root == null) return root;
            Queue<Node> q = new LinkedList<>();
            q.offer(root);
            while (!q.isEmpty()) {
                int size = q.size();
                Node prev = null;
                for (int i = 0; i < size; i++) {
                    Node cur = q.poll();
                    if (cur.left != null) q.offer(cur.left);
                    if (cur.right != null) q.offer(cur.right);
                    if (prev != null) {
                        prev.next = cur;
                        prev = cur;
                    }
                    if (prev == null) prev = cur;


                }
            }
            return root;
        }
    }

    static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    ;

}
