package com.frankcooper.bank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class _47 {

    static _47 handler = new _47();

    public static void main(String[] args) {

        handler.permuteUnique(new int[]{1, 1, 2});

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
