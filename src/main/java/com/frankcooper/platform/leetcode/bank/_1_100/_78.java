package com.frankcooper.platform.leetcode.bank._1_100;

import java.util.ArrayList;
import java.util.List;

public class _78 {


    public static void main(String[] args) {
//        _1st handler = new _1st();
        _2nd handler = new _2nd();
        int[] nums = new int[]{1, 2, 3};
        handler.subsets(nums);
    }

    static class _1st {
        /**
         * 打印顺序
         * 123
         * 12
         * 13
         * 1
         * 23
         * 2
         * 3
         */
        List<List<Integer>> res = new ArrayList<>();

        public List<List<Integer>> subsets(int[] nums) {
            if (nums == null || nums.length == 0) return res;
            dfs(new ArrayList<>(), nums, 0);
            return res;
        }

        private void dfs(List<Integer> sub, int[] nums, int idx) {
            if (idx == nums.length) {
                res.add(new ArrayList<>(sub));
                sub.forEach(System.out::print);
                System.out.println();
                return;
            }
            sub.add(nums[idx]);
            dfs(sub, nums, idx + 1);
            sub.remove(sub.size() - 1);
            dfs(sub, nums, idx + 1);
        }


    }

    static class _2nd {

        /**
         * 打印顺序
         * <p>
         * 1
         * 12
         * 123
         * 13
         * 2
         * 23
         * 3
         */
        List<List<Integer>> res = new ArrayList<>();


        public List<List<Integer>> subsets(int[] nums) {
            if (nums == null || nums.length == 0) return res;
            res.add(new ArrayList<>());
            dfs(new ArrayList<>(), nums, 0);
            return res;
        }


        private void dfs(List<Integer> sub, int[] nums, int idx) {
            for (int i = idx; i < nums.length; i++) {
                sub.add(nums[i]);
                res.add(new ArrayList<>(sub));
//                sub.forEach(System.out::print);
//                System.out.println();
                dfs(sub, nums, i + 1);
                sub.remove(sub.size() - 1);
            }
        }
    }


    static class _2nd_1 {
        List<List<Integer>> res = new ArrayList<>();

        public List<List<Integer>> subsets(int[] nums) {
            res.add(new ArrayList<>());
            dfs(nums, new ArrayList<>(), 0);
            return res;

        }


        private void dfs(int[] nums, List<Integer> sub, int idx) {
            for (int i = idx; i < nums.length; i++) {
                sub.add(nums[i]);
                res.add(new ArrayList<>(sub));
                dfs(nums, sub, i + 1);
                sub.remove(sub.size() - 1);
            }
        }
    }
}
