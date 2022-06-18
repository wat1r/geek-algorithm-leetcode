package com.frankcooper.platform.leetcode.bank._401_500;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Date 2020/8/25
 * @Author Frank Cooper
 * @Description
 */
public class _491 {

    static _491 handler = new _491();

    public static void main(String[] args) {
        int[] nums = {4, 6, 7, 7};
        handler.findSubsequences(nums);
    }


    List<List<Integer>> resList = new ArrayList<>();

    public List<List<Integer>> findSubsequences(int[] nums) {
        dfs(nums, -1);
        for (List<Integer> subList : resList) {
            for (int num : subList) System.out.print(num + " ");
            System.out.println();
        }

        return resList;
    }

    List<Integer> subList = new ArrayList<>();

    private void dfs(int[] nums, int currIdx) {
        if (subList.size() >= 2) resList.add(new ArrayList<>(subList));
        Set<Integer> set = new HashSet<>();
        for (int i = currIdx + 1; i < nums.length; ++i) {
            if (set.contains(nums[i])) continue;
            set.add(nums[i]);
            if (currIdx == -1 || nums[i] >= nums[currIdx]) {
                subList.add(nums[i]);
                dfs(nums, i);
                subList.remove(subList.size() - 1);
            }
        }
    }


    class _2nd {
        List<List<Integer>> resList = new ArrayList<>();
        List<Integer> segList = new ArrayList<>();
        int n;

        public List<List<Integer>> findSubsequences(int[] nums) {
            n = nums.length;
            dfs(nums, -1);
            return resList;
        }

        private void dfs(int[] nums, int currIdx) {
            if (segList.size() >= 2) {
                resList.add(new ArrayList<>(segList));
            }
//        Set<Integer> set = new HashSet<>();
            for (int i = currIdx + 1; i < n; i++) {
//            if (set.contains(nums[i])) continue;
//            set.add(nums[i]);
                if (currIdx == -1 || nums[i] >= nums[currIdx]) {
                    segList.add(nums[i]);
                    dfs(nums, i);
                    segList.remove(segList.size() - 1);
                }
            }
        }

    }


    class _1st {

        List<List<Integer>> resList = new ArrayList<>();
        List<Integer> segList = new ArrayList<>();
        int n;

        public List<List<Integer>> findSubsequences(int[] nums) {
            n = nums.length;
            dfs(nums, 0, Integer.MIN_VALUE);
            return resList;
        }

        private void dfs(int[] nums, int currIdx, int last) {
            if (currIdx == n) {
                if (segList.size() > 2) resList.add(new ArrayList<>(segList));
                return;
            }
            if (nums[currIdx] >= last) {
                segList.add(nums[currIdx]);
                dfs(nums, currIdx + 1, nums[currIdx]);
                segList.remove(segList.size() - 1);
            }

            if (nums[currIdx] != last) {
                dfs(nums, currIdx + 1, last);
            }
        }
    }
}
