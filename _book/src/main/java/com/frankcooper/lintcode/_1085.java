package com.frankcooper.lintcode;

import java.util.*;

import com.frankcooper.struct.TreeNode;
import org.junit.Assert;

public class _1085 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }

        int res = 0;

        public int longestUnivaluePath(TreeNode root) {
            dfs(root);
            return res;
        }


        private int dfs(TreeNode root) {
            if (root == null) return 0;
            int lv = dfs(root.left);
            int rv = dfs(root.right);
            int t = 0;
            if (root.left != null && root.left.val == root.val
                    && root.right != null && root.right.val == root.val) {
                res = Math.max(res, lv + rv + 2);
            }
            if (root.left != null && root.left.val == root.val) t = lv + 1;
            if (root.right != null && root.right.val == root.val) t = Math.max(t, rv + 1);
            res = Math.max(res, t);
            return t;
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
