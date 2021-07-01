package com.frankcooper.bank._601_700;

import java.util.*;

import com.frankcooper.struct.TreeNode;

public class _623 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        public TreeNode addOneRow(TreeNode root, int val, int depth) {
            if (root == null) return null;
            if (depth == 1) {
                TreeNode newNode = new TreeNode(val);
                newNode.left = root;
                return newNode;
            }
            if (depth == 2) {
                TreeNode newLeft = new TreeNode(val);
                TreeNode newRight = new TreeNode(val);
                newLeft.left = root.left;
                root.left = newLeft;
                newRight.right = root.right;
                root.right = newRight;
                return root;
            }
            addOneRow(root.left, val, depth - 1);
            addOneRow(root.right, val, depth - 1);
            return root;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        class Node {
            TreeNode node;
            int d;

            public Node(TreeNode node, int d) {
                this.node = node;
                this.d = d;
            }
        }

        public TreeNode addOneRow(TreeNode root, int val, int depth) {
            if (depth == 1) {
                TreeNode newNode =new TreeNode(val);
                newNode.left= root;
                return newNode;
            }
            Stack<Node> stk = new Stack<>();
            stk.push(new Node(root, 1));
            while (!stk.isEmpty()) {
                Node cur = stk.pop();
                if (cur.node == null) continue;
                if (cur.d == depth - 1) {
                    TreeNode t = cur.node.left;
                    cur.node.left = new TreeNode(val);
                    cur.node.left.left = t;
                    t = cur.node.right;
                    cur.node.right = new TreeNode(val);
                    cur.node.right.right = t;
                } else {
                    stk.push(new Node(cur.node.left, cur.d + 1));
                    stk.push(new Node(cur.node.right, cur.d + 1));
                }
            }
            return root;
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
