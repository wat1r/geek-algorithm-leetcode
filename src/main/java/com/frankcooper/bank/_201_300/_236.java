package com.frankcooper.bank._201_300;

import java.util.*;

import com.frankcooper.io.TreeNodeIOUtils;
import com.frankcooper.struct.TreeNode;
import org.junit.Assert;

public class
_236 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            TreeNode root = TreeNodeIOUtils.transform("[3,5,1,6,2,0,8,null,null,7,4]");
            TreeNode p = new TreeNode(5);
            TreeNode q = new TreeNode(1);
            TreeNode ancestor = handler.lowestCommonAncestor(root, p, q);
            System.out.printf("%d", ancestor.val);
        }


        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if (root == null || root == p || root == q) return root;
            TreeNode left = lowestCommonAncestor(root.left, p, q);
            TreeNode right = lowestCommonAncestor(root.right, p, q);
            if (left != null && right != null) return root;
            return left != null ? left : right;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        /**
         * 作者：jyd
         * 链接：https://leetcode-cn.com/problems/er-cha-shu-de-zui-jin-gong-gong-zu-xian-lcof/solution/mian-shi-ti-68-ii-er-cha-shu-de-zui-jin-gong-gon-7/
         * 来源：力扣（LeetCode）
         * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
         *
         * @param root
         * @param p
         * @param q
         * @return
         */

        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if (root == null || root == p || root == q) return root;
            TreeNode left = lowestCommonAncestor(root.left, p, q);
            TreeNode right = lowestCommonAncestor(root.right, p, q);
            if (left != null && right != null) return root;
            return left == null ? right : left;
        }

    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }

        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            //终止条件
            if (root == null || root == p || root == q) return root;
            TreeNode left = lowestCommonAncestor(root.left, p, q);
            TreeNode right = lowestCommonAncestor(root.right, p, q);
            if (left != null && right != null) return root;//case 4
            if (left != null) return left;//case 2
            if (right != null) return right;//case 3
            return null;//case 1
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
