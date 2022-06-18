package com.frankcooper.platform.lintcode;

import com.frankcooper.struct.TreeNode;

import java.util.*;

public class _448 {

    static class _1st {
        public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
            List<TreeNode> list = new ArrayList<>();
            Stack<TreeNode> q = new Stack<>();
//            q.offer(root);
            while (!q.isEmpty() || root != null) {
                if (root != null) {
                    q.push(root);
                    root = root.left;
                } else {
                    root = q.pop();

//                    list.add(root);
                    root = root.right;
                }
            }
            for (int i = 0; i < list.size() - 1; i++) {
                System.out.printf("%d\n", list.get(i).val);
                if (list.get(i).val == p.val) return list.get(i + 1);
            }
            return null;
        }
    }


    /**
     * 中序遍历，标记
     */
    static class _2nd {
        public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
            List<TreeNode> list = new ArrayList<>();
            Stack<TreeNode> q = new Stack<>();
            boolean f = false;
            while (!q.isEmpty() || root != null) {
                while (root != null) {
                    q.push(root);
                    root = root.left;
                }
                root = q.pop();
                if (f) return root;
                if (root.val == p.val) f = true;
                root = root.right;
            }
            return null;
        }
    }
}
