package com.frankcooper.platform.leetcode.bank._201_300;

import com.frankcooper.struct.TreeNode;

/*import java.util.*;
import org.junit.Assert;*/
public class _235 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }

        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if (root.val < p.val && root.val < q.val) root = lowestCommonAncestor(root.right, p, q);
            else if (root.val > p.val && root.val > q.val) root = lowestCommonAncestor(root.left, p, q);
            return root;

        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if (root.val > Math.max(p.val, q.val)) {
                return lowestCommonAncestor(root.left, p, q);
            } else if (root.val < Math.min(p.val, q.val)) {
                return lowestCommonAncestor(root.right, p, q);
            } else {
                return root;
            }

        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }

        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if (root == null || root == p || root == q) return root;
            TreeNode l = lowestCommonAncestor(root.left, p, q);
            TreeNode r = lowestCommonAncestor(root.right, p, q);
            if (l != null && r != null) return root;
            if (l != null) return l;
            if (r != null) return r;
            return null;
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
