package com.frankcooper.bank._201_300;

import java.util.*;

import com.frankcooper.struct.TreeNode;
import org.junit.Assert;

public class _230 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }

        List<Integer> list = new ArrayList<>();

        public int kthSmallest(TreeNode root, int k) {
            dfs(root);
            return list.get(k - 1);
        }

        private void dfs(TreeNode root) {
            if (root == null) return;
            dfs(root.left);
            list.add(root.val);
            dfs(root.right);
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
