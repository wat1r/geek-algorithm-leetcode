package com.frankcooper.platform.leetcode.bank._301_400;

import com.frankcooper.struct.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date 2020/9/8
 * @Author Frank Cooper
 * @Description
 */
public class _376 {
    static _376 handler = new _376();

    public static void main(String[] args) {
        int[] nums = {1, 7, 4, 9, 2, 5};
        nums = new int[]{0, 0};
        nums = new int[]{0, 0, 0};
        nums = new int[]{1, 1, 7, 4, 9, 2, 5};
        handler.wiggleMaxLength(nums);
    }


    public int wiggleMaxLength1st(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return 1;
        int prevDiff = nums[1] - nums[0];
        int res = prevDiff == 0 ? 1 : 2;
        for (int i = 2; i < nums.length; i++) {
            int nextDiff = nums[i] - nums[i - 1];
            if ((prevDiff <= 0 && nextDiff > 0) || (prevDiff >= 0 && nextDiff < 0)) {
                res++;
                prevDiff = nextDiff;
            }
        }
        return res;
    }


    public int wiggleMaxLength(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        List<Integer> list = new ArrayList<>();
        list.add(nums[0]);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                continue;
            }
            if (i < nums.length) list.add(nums[i]);//
        }
        int res = 2;
        Integer[] arr = new Integer[list.size()];
        list.toArray(arr);
        if (arr.length <= 2) return arr.length;
        for (int i = 1; i + 1 < arr.length; i++) {
            int prev = arr[i - 1], curr = arr[i], next = arr[i + 1];
            if ((prev < curr && curr > next) || (prev > curr && curr < next)) {
                res++;
            }
        }
        return res;
    }


    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }

        List<List<Integer>> res = new ArrayList<>();
        int target;

        public List<List<Integer>> binaryTreePathSum(TreeNode root, int target) {
            this.target = target;
            dfs(root, new ArrayList<>(), 0);
            return res;
        }


        private void dfs(TreeNode root, List<Integer> list, int sum) {
            if (root == null) return;
            sum += root.val;
            list.add(root.val);
            //当前节点是叶子节点
            if (root.left == null && root.right == null) {
                if (sum == target) {
                    res.add(new ArrayList<>(list));
                }
                sum -= list.get(list.size() - 1);
                list.remove(list.size() - 1);
                return;
            }
            //非叶子节点
            dfs(root.left, list, sum);
            dfs(root.right, list, sum);
            list.remove(list.size() - 1);
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        List<List<Integer>> res = new ArrayList<>();

        public List<List<Integer>> binaryTreePathSum(TreeNode root, int target) {
            dfs(root, new ArrayList<>(), target);
            return res;
        }

        private void dfs(TreeNode root, List<Integer> list, int cur) {
            if (root == null) return;
            //当前节点是叶子节点
            list.add(root.val);
            if (root.left == null && root.right == null) {
                if (cur == root.val) {
                    res.add(new ArrayList<>(list));
                }
                list.remove(list.size() - 1);
                return;
            }
            dfs(root.left, list, cur - root.val);
            dfs(root.right, list, cur - root.val);
            list.remove(list.size() - 1);
        }
    }

}
