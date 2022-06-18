package com.frankcooper.platform.leetcode.interview;

import com.frankcooper.struct.TreeNode;

public class _04_12 {
    static class _1st {
        public int pathSum(TreeNode root, int sum) {
            if (root == null) return 0;

            return dfs(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
        }

        public int dfs(TreeNode root, int num) {
            if (root == null) return 0;
            int res = 0;
            if (root.val == num) res++;
            res += dfs(root.left, num - root.val);
            res += dfs(root.right, num - root.val);
            return res;

        }
    }
}
