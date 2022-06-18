package com.frankcooper.platform.leetcode.bank._1_100;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class _1 {


    //方法1:双指针
    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length <= 1) {
            return null;
        }
        //1. 备份原数组，下面要对原数组进行Sort
        int[] backup = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            backup[i] = nums[i];
        }
        //2. 对原数组Sort
        Arrays.sort(nums);
        //3. 找到等于target的两个num值，因为已经排序好了，头尾两个指针做逻辑
        int num1 = -1, num2 = -1;
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum == target) {
                num1 = nums[left];
                num2 = nums[right];
                break;
            } else if (sum > target) {
                right--;
            } else {
                left++;
            }
        }
        //4. 找到和等于target的两个数，来找他们的索引，借助backup数组
        int[] res = new int[2];
        res[0] = -1;
        res[1] = -1;
        for (int i = 0; i < backup.length; i++) {
            if (backup[i] == num1 || backup[i] == num2) {
                if (res[0] == -1) {
                    res[0] = i;
                } else {
                    //5 .因为是排序的，num2比num1大，当进到else的逻辑，if的逻辑肯定进了，可以break掉结束
                    res[1] = i;
                    break;
                }
            }
        }
        return res;
    }


    static class _1st {
        public int[] twoSum(int[] nums, int target) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                int t = target - nums[i];
                if (map.containsKey(t)) {
                    return new int[]{map.get(t), i};
                }
                map.put(nums[i], i);
            }
            return null;
        }
    }
}


