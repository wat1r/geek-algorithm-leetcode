package com.frankcooper.bank._1_100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _90 {


    static class _1st {

        public static void main(String[] args) {
            _1st handler = new _1st();
            int[] nums = new int[]{1, 2, 2};
            handler.subsetsWithDup(nums);
        }

        List<List<Integer>> res = new ArrayList<>();


        public List<List<Integer>> subsetsWithDup(int[] nums) {
            if (nums == null || nums.length == 0) return res;
            Arrays.sort(nums);
            res.add(new ArrayList<>());
            dfs(new ArrayList<>(), nums, 0);
            return res;
        }


        private void dfs(List<Integer> sub, int[] nums, int idx) {
            for (int i = idx; i < nums.length; i++) {
                if (i > idx && nums[i - 1] == nums[i]) continue;
                sub.add(nums[i]);
                res.add(new ArrayList<>(sub));
                sub.forEach(System.out::print);
                System.out.println();
                dfs(sub, nums, i + 1);
                sub.remove(sub.size() - 1);
            }
        }
    }

    static class _2nd {
        List<List<Integer>> res = new ArrayList<>();

        public List<List<Integer>> subsetsWithDup(int[] nums) {
            if (nums == null || nums.length == 0) return res;
            Arrays.sort(nums);
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
            while (idx < nums.length - 1 && nums[idx] == nums[idx + 1]) idx++;
            dfs(sub, nums, idx + 1);
        }

    }


    static class _3rd {
        List<List<Integer>> res = new ArrayList<>();

        public List<List<Integer>> subsetsWithDup(int[] nums) {
            Arrays.sort(nums);
            dfs(nums, 0, new ArrayList<>());
            return res;
        }


        public void dfs(int[] nums, int idx, List<Integer> sub) {
            res.add(new ArrayList<>(sub));
            for (int i = idx; i < nums.length; i++) {
                if (i > idx && nums[i - 1] == nums[i]) continue;//同层去除重复元素
                sub.add(nums[i]);
                dfs(nums, i + 1, sub);
                sub.remove(sub.size() - 1);
            }
        }
    }
}
