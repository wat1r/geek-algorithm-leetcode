package com.frankcooper.platform.leetcode.bank._301_400;

import org.junit.Assert;

import java.util.*;
import java.util.Arrays;
import java.util.List;

/**
 * @Date 2020/9/13
 * @Author Frank Cooper
 * @Description
 */
public class _368 {


    static _368 handler = new _368();

    public static void main(String[] args) {
        int[] nums = {1, 2, 4, 8};
        nums = new int[]{2, 4, 7, 8, 9, 14, 16};
        nums = new int[]{2, 4, 5, 7, 8, 9, 14, 16};


//        handler.largestDivisibleSubset1st(nums);
        handler.largestDivisibleSubset(nums);
    }


    public List<Integer> largestDivisibleSubset(int[] nums) {
        if (nums == null || nums.length == 0) return new ArrayList<>();
        //排序
        Arrays.sort(nums);
        int n = nums.length;
        //dp[i]表示以当前元素nums[i]结尾形成的整除子集的最长长度
        int[] dp = new int[n];
        //最大的子集长度大小，最大的子集的以nums[i]结尾的数字的索引i
        int maxSubsetSize = -1, maxSubsetIdx = -1;
        for (int i = 0; i < n; ++i) {
            //当前这一个i下的这一轮的子集大小
            int subsetSize = 0;
            for (int k = 0; k < i; ++k) {
                //可以整除+当前的大小小于以nums[k]结束的子集的大小，可以将nums[i]追加到子集中
                if (nums[i] % nums[k] == 0 && subsetSize < dp[k]) {
                    subsetSize = dp[k];
                }
            }
            //更新当前子集的大小，以nums[i]结尾的子集
            //更新最大的子集长度大小，最大的子集的以nums[i]结尾的数字的索引i
            dp[i] = subsetSize + 1;
            if (maxSubsetSize < dp[i]) {
                maxSubsetSize = dp[i];
                maxSubsetIdx = i;
            }
        }
        //开始往回找，因为已经记录下最大的子集的最末尾的数nums[maxSubsetIdx]
        int currNum = nums[maxSubsetIdx], currSize = maxSubsetSize;
        LinkedList<Integer> ans = new LinkedList<>();
        for (int i = maxSubsetIdx; i >= 0; i--) {
            if (currSize == 0) break;
            //需要满足的两个条件，见图
            if (currNum % nums[i] == 0 && currSize == dp[i]) {
                ans.addFirst(nums[i]);
                currNum = nums[i];
                currSize--;
            }
        }
        return ans;
    }


    public List<Integer> largestDivisibleSubset1st(int[] nums) {
        if (nums == null || nums.length == 0) return new ArrayList<>();
        //排序
        Arrays.sort(nums);
        int n = nums.length;
        ArrayList<ArrayList> resList = new ArrayList<>();
        for (int num : nums) resList.add(new ArrayList());
        List<Integer> ans = new ArrayList();
        for (int i = 0; i < n; ++i) {
            //当前处理的以nums[i]结尾的集合
            List currList = resList.get(i);
            List<Integer> levelList = new ArrayList<>();
            for (int k = 0; k < i; ++k) {
                //求最大且要判断当前元素是否与nums[k]是否整除
                if (nums[i] % nums[k] == 0 && levelList.size() < resList.get(k).size()) {
                    levelList = resList.get(k);
                }
            }
            currList.addAll(levelList);
            currList.add(nums[i]);
            //记录下最大的
            if (currList.size() > ans.size()) ans = currList;
        }
        return ans;
    }


    static class _1st {

        public static void main(String[] args) {
            _1st handler = new _1st();
            int[] nums = new int[]{1, 2, 3};
            Assert.assertEquals(handler.largestDivisibleSubset(nums), Arrays.asList(1, 2));
        }

        public List<Integer> largestDivisibleSubset(int[] nums) {
            List<Integer> res = new ArrayList<>();
            if (nums == null || nums.length == 0) return res;
            Arrays.sort(nums);
            List<List<Integer>> list = new ArrayList<>();
            for (int x : nums) list.add(new ArrayList<>());
            for (int i = 0; i < nums.length; i++) {
                List<Integer> cur = list.get(i);//当前处理的i这个list
                List<Integer> target = new ArrayList<>();
                for (int j = 0; j < i; j++) {
                    if (nums[i] % nums[j] == 0 && target.size() < list.get(j).size()) {
                        target = list.get(j);
                    }
                }
                cur.addAll(target);
                cur.add(nums[i]);
                if (cur.size() > res.size()) res = cur;
            }
            return res;
        }
    }

    static class _2nd{
        //dp[i]表示以当前元素nums[i]结尾形成的整除子集的最长长度
    }

}
