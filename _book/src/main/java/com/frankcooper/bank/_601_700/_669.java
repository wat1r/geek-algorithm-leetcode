package com.frankcooper.platform.leetcode.bank._601_700;

import java.util.*;

import com.frankcooper.struct.TreeNode;
import org.junit.Assert;

public class _669 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        public TreeNode trimBST(TreeNode root, int low, int high) {
            if (root == null) return null;
            if (root.val < low) return trimBST(root.right, low, high);
            if (root.val > high) return trimBST(root.left, low, high);
            root.left = trimBST(root.left, low, high);
            root.right = trimBST(root.right, low, high);
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
