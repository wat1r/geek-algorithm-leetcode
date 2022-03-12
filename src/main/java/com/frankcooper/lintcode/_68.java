package com.frankcooper.lintcode;

import java.util.*;

import com.frankcooper.struct.TreeNode;
import org.junit.Assert;

public class _68 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }

        List<Integer> res = new ArrayList<>();

        public List<Integer> postorderTraversal(TreeNode root) {
            dfs(root);
            return res;
        }

        private void dfs(TreeNode root) {
            if(root ==null) return;
            dfs(root.left);
            dfs(root.right);
            res.add(root.val);
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
