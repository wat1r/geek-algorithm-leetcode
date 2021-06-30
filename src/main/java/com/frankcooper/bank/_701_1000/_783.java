package com.frankcooper.bank._701_1000;

import com.frankcooper.struct.TreeNode;

public class _783 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }

        int res =10005;

        public int minDiffInBST(TreeNode root) {
            inOrder(root);
            return res;
        }

        int prev = -1;

        public void inOrder(TreeNode root) {
            if (root == null) return;
            inOrder(root.left);
            if (prev == -1) prev = root.val;
            else {
                int cur = root.val;
                res = Math.min(res, Math.abs(prev - cur));
                prev = cur;
            }
            inOrder(root.right);
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
