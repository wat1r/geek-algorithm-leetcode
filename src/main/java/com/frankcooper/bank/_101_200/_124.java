package com.frankcooper.bank._101_200;

import java.util.*;

import com.frankcooper.struct.TreeNode;
import org.junit.Assert;

public class _124 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        int res = Integer.MIN_VALUE;

        public int maxPathSum(TreeNode root) {
            dfs(root);
            return res;
        }

        public int dfs(TreeNode root) {
            if (root == null) return 0;
            int lval = Math.max(dfs(root.left), 0);
            int rval = Math.max(dfs(root.right), 0);
            res = Math.max(res, root.val + lval + rval);
            return root.val + Math.max(lval, rval);
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
