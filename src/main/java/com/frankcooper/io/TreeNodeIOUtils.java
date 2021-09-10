package com.frankcooper.io;

import com.frankcooper.struct.TreeNode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class TreeNodeIOUtils {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        //[3,9,20,null,null,15,7]
        while ((line = in.readLine()) != null) {
            TreeNode root = Wrapper.stringToTreeNode(line);
            Wrapper.prettyPrintTree(root);
        }

    }


    public static TreeNode transform(String arrStr) {
        TreeNode root = Wrapper.stringToTreeNode(arrStr);
        Wrapper.prettyPrintTree(root);
        return root;
    }


    static class Wrapper {
        /**
         * Definition for a binary tree node.
         * public class TreeNode {
         * int val;
         * TreeNode left;
         * TreeNode right;
         * TreeNode(int x) { val = x; }
         * }
         */

        public static String treeNodeToString(TreeNode root) {
            if (root == null) {
                return "[]";
            }

            String output = "";
            Queue<TreeNode> nodeQueue = new LinkedList<>();
            nodeQueue.add(root);
            while (!nodeQueue.isEmpty()) {
                TreeNode node = nodeQueue.remove();

                if (node == null) {
                    output += "null, ";
                    continue;
                }

                output += String.valueOf(node.val) + ", ";
                nodeQueue.add(node.left);
                nodeQueue.add(node.right);
            }
            return "[" + output.substring(0, output.length() - 2) + "]";
        }

        public static TreeNode stringToTreeNode(String input) {
            input = input.trim();
            input = input.substring(1, input.length() - 1);
            if (input.length() == 0) {
                return null;
            }

            String[] parts = input.split(",");
            String item = parts[0];
            TreeNode root = new TreeNode(Integer.parseInt(item));
            Queue<TreeNode> nodeQueue = new LinkedList<>();
            nodeQueue.add(root);

            int index = 1;
            while (!nodeQueue.isEmpty()) {
                TreeNode node = nodeQueue.remove();

                if (index == parts.length) {
                    break;
                }

                item = parts[index++];
                item = item.trim();
                if (!item.equals("null")) {
                    int leftNumber = Integer.parseInt(item);
                    node.left = new TreeNode(leftNumber);
                    nodeQueue.add(node.left);
                }

                if (index == parts.length) {
                    break;
                }

                item = parts[index++];
                item = item.trim();
                if (!item.equals("null")) {
                    int rightNumber = Integer.parseInt(item);
                    node.right = new TreeNode(rightNumber);
                    nodeQueue.add(node.right);
                }
            }
            return root;
        }

        public static void prettyPrintTree(TreeNode node, String prefix, boolean isLeft) {
            if (node == null) {
                System.out.println("Empty tree");
                return;
            }

            if (node.right != null) {
                prettyPrintTree(node.right, prefix + (isLeft ? "│   " : "    "), false);
            }

            System.out.println(prefix + (isLeft ? "└── " : "┌── ") + node.val);

            if (node.left != null) {
                prettyPrintTree(node.left, prefix + (isLeft ? "    " : "│   "), true);
            }
        }

        public static void prettyPrintTree(TreeNode node) {
            prettyPrintTree(node, "", true);
        }
    }

}


