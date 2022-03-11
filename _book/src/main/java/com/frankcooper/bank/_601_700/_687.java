package com.frankcooper.bank._601_700;

import java.util.*;

import com.frankcooper.struct.TreeNode;
import org.junit.Assert;

public class _687 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            TreeNode n1 = new TreeNode(1);
            TreeNode n2 = new TreeNode(4);
            TreeNode n3 = new TreeNode(5);
            n1.left = n2;
            n1.right = n3;
            TreeNode n4 = new TreeNode(4);
            TreeNode n5 = new TreeNode(4);
            n2.left = n4;
            n2.right = n5;
            TreeNode n6 = new TreeNode(5);
            n3.right = n6;
            Assert.assertEquals(2, handler.longestUnivaluePath(n1));


        }


        int res = 0;

        public int longestUnivaluePath(TreeNode root) {
            dfs(root);
            return res;
        }


        //返回以root节点为起点的最长同值路径 ，root -> 左子树 root--> 右子树
        private int dfs(TreeNode root) {
            if (root == null) return 0;
            int t = 0;
            int l = dfs(root.left);//root左子树的最长同值路径
            int r = dfs(root.right);//root右子树的最长同值路径
            //左子树+根+右子树 形成一条同值路径，根和左子树  边为1 根和右子树 边为1 所以+2
            if (root.left != null && root.val == root.left.val
                    && root.right != null && root.val == root.right.val) {
                res = Math.max(res, l + r + 2);
            }
            //从左子树中
            if (root.left != null && root.val == root.left.val) t = l + 1;
            //从右子树中，注意t的值，需要与左子树的结果比较
            if (root.right != null && root.val == root.right.val) t = Math.max(t, r + 1);
            res = Math.max(res, t);//记录全局
            return t;//返回最长路径值
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
