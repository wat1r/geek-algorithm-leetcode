package com.frankcooper.bank._701_1000;

import java.util.*;

import com.frankcooper.struct.TreeNode;
import org.junit.Assert;

public class _979 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        int res = 0;

        public int distributeCoins(TreeNode root) {
            dfs(root);
            return res;
        }


        private int dfs(TreeNode root) {
            if (root == null) return 0;
            int l = dfs(root.left);
            int r = dfs(root.right);
            res += Math.abs(l) + Math.abs(r);
            return root.val + l + r - 1;
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
