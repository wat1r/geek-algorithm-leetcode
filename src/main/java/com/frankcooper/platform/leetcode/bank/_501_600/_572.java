package com.frankcooper.platform.leetcode.bank._501_600;

import java.util.*;

import com.frankcooper.struct.TreeNode;
import org.junit.Assert;

public class _572 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }


        public boolean isSubtree(TreeNode root, TreeNode subRoot) {
            if (root == null || subRoot == null) {
                return root == null && subRoot == null;
            }
            //注意，如果比较的是root，则比较相同的树:isSameTree
            //如果比较的是root左右子节点，则比较的是不是子树 ：isSubtree
            return isSameTree(root, subRoot) ||
                    isSubtree(root.left, subRoot) ||
                    isSubtree(root.right, subRoot);

        }

        private boolean isSameTree(TreeNode source, TreeNode target) {
            if (source == null || target == null) {
                return source == null && target == null;
            }
            return source.val == target.val &&
                    isSameTree(source.left, target.left) &&
                    isSameTree(source.right, target.right);
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
