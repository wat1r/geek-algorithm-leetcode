package com.frankcooper.platform.leetcode.bank._601_700;

import com.frankcooper.struct.TreeNode;

public class _654 {

    static class _1st {
        public TreeNode constructMaximumBinaryTree(int[] nums) {
            return dfs(nums, 0, nums.length);
        }


        public TreeNode dfs(int[] nums, int start, int end) {
            if (start == end) return null;
            int rootIdx = start;
            for (int i = start; i < end; i++) {
                if (nums[i] > nums[rootIdx]) rootIdx = i;
            }
            TreeNode root = new TreeNode(nums[rootIdx]);
            root.left = dfs(nums, start, rootIdx);//注意下标
            root.right = dfs(nums, rootIdx + 1, end);
            return root;
        }
    }

    static class _2nd {
        public TreeNode constructMaximumBinaryTree(int[] nums) {
            return dfs(nums, 0, nums.length);
        }

        private TreeNode dfs(int[] nums, int start, int end) {
            if (start == end) return null;
            int rootIdx = start;
            for (int i = start; i < end; i++) {
                if (nums[i] > nums[rootIdx]) rootIdx = i;
            }
            TreeNode root = new TreeNode(nums[rootIdx]);
            root.left = dfs(nums, start, rootIdx);
            root.right = dfs(nums, rootIdx + 1, end);
            return root;

        }
    }
}
