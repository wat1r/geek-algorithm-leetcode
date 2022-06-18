package com.frankcooper.platform.nowcoder.swordoffer;

import com.frankcooper.struct.TreeNode;

/*import java.util.*;
import org.junit.Assert;*/
public class JZ86 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        public int lowestCommonAncestor(TreeNode root, int o1, int o2) {
            TreeNode res = helper(root, o1, o2);
            return res.val;
        }

        public TreeNode helper(TreeNode root, int p, int q) {
            if (root == null || root.val == p || root.val == q) return root;
            TreeNode left = helper(root.left, p, q);
            TreeNode right = helper(root.right, p, q);
            if (left != null && right != null) return root;
            if (left != null) return left;
            if (right != null) return right;
            return null;
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
