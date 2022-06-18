package com.frankcooper.platform.leetcode.bank._1_100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Date 2020/8/6
 * @Author Frank Cooper
 * @Description
 */
public class _18 {


    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> results = new ArrayList<>();
        if (nums == null || nums.length < 4) return results;
        Arrays.sort(nums);//排序
        int n = nums.length;
        for (int a = 0; a < n; a++) {
            //重复的a 跳过
            if (a > 0 && nums[a] == nums[a - 1]) continue;
            for (int b = a + 1; b < n; b++) {
                //重复的b 跳过
                if (b > a + 1 && nums[b] == nums[b - 1]) continue;
                //固定a b 后  选取 c d两个指针
                int c = b + 1, d = n - 1;
                while (c < d) {
                    int sum = nums[a] + nums[b] + nums[c] + nums[d];
                    //找到了
                    if (sum == target) {
                        results.add(Arrays.asList(nums[a], nums[b], nums[c], nums[d]));
                        //跳过c d 重复
                        while (c < d && nums[c + 1] == nums[c]) c++;
                        while (c < d && nums[d - 1] == nums[d]) d--;
                        c++;
                        d--;
                    } else if (sum < target) c++;//滑动
                    else if (sum > target) d--;//滑动
                }
            }
        }
        return results;
    }

}
