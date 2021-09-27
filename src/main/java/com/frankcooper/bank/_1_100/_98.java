package com.frankcooper.bank._1_100;

import com.frankcooper.struct.TreeNode;

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

    static class _2nd {
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


}
