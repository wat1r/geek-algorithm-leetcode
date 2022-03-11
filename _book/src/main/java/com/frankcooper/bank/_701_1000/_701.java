package com.frankcooper.bank._701_1000;

import java.util.*;

import com.frankcooper.struct.TreeNode;
import org.junit.Assert;

public class _701 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }


        public TreeNode insertIntoBST(TreeNode root, int val) {
            if (root == null) return new TreeNode(val);
            if (root.val > val) root.left = insertIntoBST(root.left, val);
            else if (root.val < val) root.right = insertIntoBST(root.right, val);
            return root;
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
