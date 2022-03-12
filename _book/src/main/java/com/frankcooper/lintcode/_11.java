package com.frankcooper.lintcode;

import java.util.*;

import com.frankcooper.struct.TreeNode;
import org.junit.Assert;

public class _11 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }

        //WA
        public List<Integer> searchRange(TreeNode root, int k1, int k2) {
            // write your code here
            List<Integer> res = new ArrayList<>();
            if (root == null) return res;
            Queue<TreeNode> q = new LinkedList<>();
            q.offer(root);
            while (!q.isEmpty()) {
                TreeNode x = q.poll();
                if (x.val >= k1 && x.val <= k2) res.add(x.val);
                if (x.left != null) q.offer(x.left);
                if (x.right != null) q.offer(x.right);
            }
            return res;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }


        List<Integer> res = new ArrayList<>();

        public List<Integer> searchRange(TreeNode root, int k1, int k2) {
            dfs(root, k1, k2);
            return res;
        }

        private void dfs(TreeNode root, int k1, int k2) {
            if (root == null) return;
            if (root.val >= k1) dfs(root.left, k1, k2);
            if (root.val >= k1 && root.val <= k2) res.add(root.val);
            if (root.val <= k2) dfs(root.right, k1, k2);
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
