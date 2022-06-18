package com.frankcooper.platform.leetcode.bank._601_700;

import com.frankcooper.struct.TreeNode;

public class _655 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        private int getDepth(TreeNode root) {
            if (root == null) return 0;
            return Math.max(getDepth(root.left), getDepth(root.right)) + 1;
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
