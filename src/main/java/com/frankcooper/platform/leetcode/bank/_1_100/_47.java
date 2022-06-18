package com.frankcooper.platform.leetcode.bank._1_100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class _47 {

    static _47 handler = new _47();

    public static void main(String[] args) {

        handler.permuteUnique(new int[]{1, 1, 2});

    }


    static class _1st {

        public static void main(String[] args) {
            _1st handler = new _1st();
            int[] nums = new int[]{1, 1, 1, 2};
            nums = new int[]{3, 3, 0, 3};
            nums = new int[]{1, 1, 2};
            handler.permuteUnique(nums);
        }


        List<List<Integer>> res = new ArrayList<>();

        public List<List<Integer>> permuteUnique(int[] nums) {
            if (nums == null || nums.length == 0) return res;
            Arrays.sort(nums);
            dfs(new ArrayList<>(), nums, new boolean[nums.length]);
            return res;
        }

        private void dfs(List<Integer> sub, int[] nums, boolean[] vis) {
            if (sub.size() == nums.length) {
                sub.forEach(System.out::print);
                System.out.println();
                res.add(new ArrayList<>(sub));
                return;
            }
            for (int i = 0; i < nums.length; i++) {
                if (vis[i]) continue;
                //!vis[i-1] 防止{0,3,3,3}这种排列
                if (i > 0 && nums[i - 1] == nums[i] && vis[i - 1]) continue;
                vis[i] = true;
                sub.add(nums[i]);
                dfs(sub, nums, vis);
                sub.remove(sub.size() - 1);
                vis[i] = false;

            }
        }
    }


    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> resList = new ArrayList<>();
        if (nums == null || nums.length == 0) return resList;
        Arrays.sort(nums);
        boolean[] visited = new boolean[nums.length];
        dfs(nums, resList, new ArrayList<>(), visited);
        return resList;
    }

    private void dfs(int[] nums, List<List<Integer>> resList, List<Integer> levelList, boolean[] visited) {
        if (levelList.size() == nums.length) {
//            System.out.println(String.format("collect:%s",JSON.toJSONString(levelList)));
            resList.add(new ArrayList<>(levelList));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i] || ((i - 1) >= 0 && visited[i - 1] && nums[i] == nums[i - 1])) {
//                System.out.println(JSON.toJSONString(levelList));
                continue;
            }
            visited[i] = true;
            levelList.add(nums[i]);
            dfs(nums, resList, levelList, visited);
            levelList.remove(levelList.size() - 1);
            visited[i] = false;
        }
    }
}
