package com.frankcooper.bank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class _46 {


    static class _1st{
        public static void main(String[] args) {
            _1st handler = new _1st();
            handler.permute(new int[]{1,2,3});
        }


        public List<List<Integer>> permute(int[] nums) {
            List<List<Integer>> res = new ArrayList<>();
            if (nums == null || nums.length == 0) return res;
            boolean[] vis = new boolean[nums.length];
            dfs(nums, res, new ArrayList<>(), vis);
            return res;
        }

        /**
         * @param nums      源数组
         * @param res   结果集
         * @param sub 每一层的子结果集
         * @param vis   记录当前的元素是否被访问过
         */
        private void dfs(int[] nums, List<List<Integer>> res, List<Integer> sub, boolean[] vis) {
            //当子结果集的大小等于源数组的长度时，即源数组整个已经访问结束，排列结束，开始收集结果
            if (sub.size() == nums.length) {
                res.add(new ArrayList<>(sub));
                return;
            }
            //for loop 整个源数组
            for (int i = 0; i < nums.length; i++) {
                if (vis[i]) continue;//当前元素被访问过，跳过
                vis[i] = true;//记录被访问过
                sub.add(nums[i]);//添加到子结果集
                dfs(nums, res, sub, vis);//进入下一层深搜
                sub.remove(sub.size() - 1);//从当前的子结果集移除
                vis[i] = false;//从被访问过的列表中移除
            }
        }
    }








    public List<List<Integer>> permute2nd(int[] nums) {
        List<List<Integer>> resList = new ArrayList<>();
        if (nums == null || nums.length == 0) return resList;
        dfs2nd(nums, resList, new ArrayList<>());
        return resList;
    }

    private void dfs2nd(int[] nums, List<List<Integer>> resList, List<Integer> levelList) {
        if (levelList.size() == nums.length) {
            resList.add(new ArrayList<>(levelList));
            return;
        }
        for (int num : nums) {
            if (levelList.contains(num)) continue;
            levelList.add(num);
            dfs2nd(nums, resList, levelList);
            levelList.remove(levelList.size() - 1);
        }
    }

    public List<List<Integer>> permute3rd(int[] nums) {
        List<List<Integer>> resList = new ArrayList<>();
        if (nums == null || nums.length == 0) return resList;
        List<Integer> levelList = new ArrayList<>();
        int n = nums.length;
        for (int num : nums) levelList.add(num);
        backtrace(resList, levelList, n, 0);
        return resList;
    }

    private void backtrace(List<List<Integer>> resList, List<Integer> levelList, int n, int index) {
        if (index == n) {
            resList.add(new ArrayList<>(levelList));
            return;
        }
        for (int i = index; i < n; i++) {
            Collections.swap(levelList, index, i);
            backtrace(resList, levelList, n, index + 1);
            Collections.swap(levelList, index, i);
        }
    }


}
