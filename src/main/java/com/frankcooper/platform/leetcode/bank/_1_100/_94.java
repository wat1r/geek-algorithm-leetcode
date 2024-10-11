package com.frankcooper.platform.leetcode.bank._1_100;

import com.frankcooper.io.TreeNodeIOUtils;
import com.frankcooper.struct.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by FrankCooper
 * Date 2020/8/8 14:27
 * Description
 */
public class _94 {

    public static void main(String[] args) {
        List<Integer> list1 = new ArrayList<>(), res = new ArrayList<>();
//        res.addAll(list1.subList())
    }


    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        TreeNode curr = root;
        while (curr != null) {
            if (curr.left == null) {//步骤1
                System.out.print(curr.val + " ");
                result.add(curr.val);
                curr = curr.right;
            } else {//步骤2
                TreeNode predecessor = getPredecessor(curr);
                if (predecessor.right == null) {//步骤2.a
                    predecessor.right = curr;
                    curr = curr.left;
                } else if (predecessor.right == curr) {//步驟2.b
                    predecessor.right = null;
                    System.out.print(curr + " ");
                    result.add(curr.val);
                    curr = curr.right;
                }
            }
        }
        return result;
    }


    public void inOrderByMorris(TreeNode root) {
        TreeNode curr = root;
        while (curr != null) {
            if (curr.left == null) {//步骤1
                System.out.print(curr.val + " ");
                curr = curr.right;
            } else {//步骤2
                TreeNode predecessor = getPredecessor(curr);
                if (predecessor.right == null) {//步骤2.a
                    predecessor.right = curr;
                    curr = curr.left;
                } else if (predecessor.right == curr) {//步驟2.b
                    predecessor.right = null;
                    System.out.print(curr + " ");
                    curr = curr.right;
                }
            }
        }
    }


    private TreeNode getPredecessor(TreeNode curr) {
        TreeNode predecessor = curr;
        if (curr.left != null) {
            predecessor = curr.left;
            while (predecessor.right != null && predecessor.right != curr) {
                predecessor = predecessor.right;
            }
        }
        return predecessor;
    }


    static class _3rd {

        public static void main(String[] args) {
            _3rd handler = new _3rd();
            TreeNode root = TreeNodeIOUtils.transform("[1,null,2,3]");
            handler.inorderTraversal(root);
        }


        public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            Stack<TreeNode> stk = new Stack<>();
            TreeNode cur = root;
            while (cur != null || !stk.isEmpty()) {
                if (cur != null) {

                    stk.push(cur);
                    cur = cur.left;
                } else {
                    cur = stk.pop();
                    res.add(cur.val);
                    cur = cur.right;
                }
            }
            return res;
        }
    }


    static class _4th {
        List<Integer> inoderList = new ArrayList<>();

        public List<Integer> inorderTraversal(TreeNode root) {
            if (root == null) return inoderList;
            inorderTraversal(root.left);
            inoderList.add(root.val);
            inorderTraversal(root.right);
            return inoderList;
        }
    }

    static class _5th {
        public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            Stack<TreeNode> stk = new Stack<>();
            TreeNode cur = root;
            while (cur != null || !stk.isEmpty()) {
                while (cur != null) {
                    stk.push(cur);
                    cur = cur.left;
                }
                cur = stk.pop();
                res.add(cur.val);
                cur = cur.right;
            }
            return res;
        }
    }

}
