package com.frankcooper.bank;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class _128 {
    static _128 handler = new _128();

    public static void main(String[] args) {
        handler.longestConsecutive(new int[]{0, -1});
    }


    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        Arrays.sort(nums);
        int n = nums.length;
        int max = 1, cur = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i] != nums[i - 1]) {
                if (nums[i - 1] + 1 == nums[i]) cur++;
                else {
                    max = Math.max(max, cur);
                    cur = 1;
                }
            }
        }
        return Math.max(max, cur);
    }

    public int longestConsecutive2nd(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) set.add(num);
        int max = 0;
        for (int num : nums) {
            if (!set.contains(num - 1)) {//判断set不包含当前元素-1的值，跳过已经计算的最长递增序列
                int curNum = num;
                int curCnt = 1;
                while (set.contains(curNum + 1)) {
                    curNum += 1;
                    curCnt += 1;
                }
                max = Math.max(max,curCnt);
            }
        }

        return max;
    }
}
