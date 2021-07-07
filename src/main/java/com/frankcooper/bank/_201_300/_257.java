package com.frankcooper.bank._201_300;

import java.util.*;

import com.frankcooper.struct.TreeNode;
import org.junit.Assert;

public class _257 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        List<String> res = new ArrayList<>();

        public List<String> binaryTreePaths(TreeNode root) {
            dfs(root, "");
            return res;
        }

        private void dfs(TreeNode root, String path) {
            if (root == null) {
                return;
            }
            if (root.left == null && root.right == null) {
                res.add(path + root.val);
                return;
            }
            dfs(root.left, path + root.val + "->");
            dfs(root.right, path + root.val + "->");
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            TreeNode r1 = new TreeNode(1);
            TreeNode r2 = new TreeNode(2);
            TreeNode r3 = new TreeNode(3);
            TreeNode r4 = new TreeNode(4);
            r1.left = r2;
            r1.right = r3;
            r2.left = r4;
            handler.binaryTreePaths(r1);

        }


        public List<String> binaryTreePaths(TreeNode root) {
            List<String> res = new ArrayList<>();
            Stack<TreeNode> nodeStk = new Stack<>();
            Stack<String> pathStk = new Stack<>();
            nodeStk.push(root);
            pathStk.push(root.val + "");
            while (!nodeStk.isEmpty()) {
                TreeNode curNode = nodeStk.pop();
                String curPath = pathStk.pop();
                if (curNode.left == null && curNode.right == null) {
                    res.add(curPath);
                }
                if (curNode.right != null) {
                    nodeStk.push(curNode.right);
                    pathStk.push(curPath + "->" + curNode.right.val);
                }
                if (curNode.left != null) {
                    nodeStk.push(curNode.left);
                    pathStk.push(curPath + "->" + curNode.left.val);
                }
            }
            return res;
        }

    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }

        public List<String> binaryTreePaths(TreeNode root) {
            List<String> res = new ArrayList<>();
            Queue<TreeNode> nodeQ = new LinkedList<>();
            Queue<String> pathQ = new LinkedList<>();
            nodeQ.offer(root);
            pathQ.offer(root.val + "");
            while (!nodeQ.isEmpty()) {
                TreeNode curNode = nodeQ.poll();
                String curPath = pathQ.poll();
                if (curNode.left == null && curNode.right == null) {
                    res.add(curPath);
                }
                if (curNode.right != null) {
                    nodeQ.offer(curNode.right);
                    pathQ.offer(curPath + "->" + curNode.right.val);
                }
                if (curNode.left != null) {
                    nodeQ.offer(curNode.left);
                    pathQ.offer(curPath + "->" + curNode.left.val);
                }
            }
            return res;
        }


    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
            TreeNode r1 = new TreeNode(1);
            TreeNode r2 = new TreeNode(2);
            TreeNode r3 = new TreeNode(3);
            TreeNode r4 = new TreeNode(4);
            r1.left = r2;
            r1.right = r3;
            r2.left = r4;
            handler.binaryTreePaths(r1);
        }

        public List<String> binaryTreePaths(TreeNode root) {
            List<String> res = new ArrayList<>();
            if (root == null) return res;
            if (root.left == null && root.right == null) {
                res.add(root.val + "");
                return res;
            }
            for (String left : binaryTreePaths(root.left)) {
                res.add(root.val + "->" + left);
            }
            for (String right : binaryTreePaths(root.right)) {
                res.add(root.val + "->" + right);
            }
            return res;
        }
    }
}
