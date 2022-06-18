package com.frankcooper.platform.leetcode.bank._1_100;

import com.frankcooper.io.TreeNodeIOUtils;
import com.frankcooper.struct.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class _98 {


    public static void main(String[] args) {
        _1st handler = new _1st();
        TreeNode t5 = new TreeNode(5);
        TreeNode t1 = new TreeNode(1);
        TreeNode t4 = new TreeNode(4);
        t5.left = t1;
        t5.right = t4;
        TreeNode t3 = new TreeNode(3);
        TreeNode t6 = new TreeNode(5);
        t4.left = t3;
        t4.right = t6;
        handler.isValidBST(t5);
    }

    static class _1st {

        TreeNode pre;

        public boolean isValidBST(TreeNode root) {
            if (root == null) return true;
            if (!isValidBST(root.left)) {
                return false;
            }
            if (pre != null && pre.val >= root.val) {
                return false;
            }
            pre = root;
            if (!isValidBST(root.right)) {
                return false;
            }
            return true;
        }
    }

    //中序遍历记录前一个节点的值，比较前一个节点和当前节点
    static class _2nd {


        public static void main(String[] args) {
            _2nd handler = new _2nd();
            TreeNode root = TreeNodeIOUtils.transform("[5,1,4,null,null,3,6]");
            handler.isValidBST(root);

        }


        TreeNode prev = null;

        public boolean isValidBST(TreeNode root) {
            if (root == null) return true;
            if (!isValidBST(root.left)) {
                return false;
            }
            if (prev != null && prev.val >= root.val) return false;
            prev = root;
            if (!isValidBST(root.right)) {
                return false;
            }
            return true;
        }
    }


    static class _3rd {
        TreeNode prev = null;

        public boolean isValidBST(TreeNode root) {
            if (root == null) return true;
            if (!isValidBST(root.left)) {
                return false;
            }
            if (prev != null && prev.val >= root.val) {
                return false;
            }

            prev = root;
            return isValidBST(root.right);


        }
    }

    static class _4th {
        TreeNode prev = null;

        public boolean isValidBST(TreeNode root) {
            if (root == null) return true;
            if (!isValidBST(root.left)) return false;
            if (prev != null && prev.val >= root.val) return false;
            prev = root;
            if (!isValidBST(root.right)) return false;
            return true;
        }
    }

    static class _5th {
        public static void main(String[] args) {
            _5th handler = new _5th();
            TreeNode root = TreeNodeIOUtils.transform("[5,1,4,null,null,3,6]");
            handler.isValidBST(root);
        }


        public boolean isValidBST(TreeNode root) {
            Deque<TreeNode> stk = new ArrayDeque<>();
            TreeNode prev = null;
            while (root != null || !stk.isEmpty()) {
                while (root != null) {
                    stk.push(root);
                    root = root.left;
                }
                root = stk.pop();
                if (prev != null && prev.val >= root.val) return false;
                prev = root;
                root = root.right;
            }
            return true;
        }
    }

}
