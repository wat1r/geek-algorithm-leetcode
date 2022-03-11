package com.frankcooper.bank._601_700;

import java.util.HashMap;
import java.util.Map;

public class _697 {

    public static void main(String[] args) {
        _697 handler = new _697();
        int[] nums = new int[]{1, 2, 2, 3, 1};
        handler.findShortestSubArray(nums);
    }


    public int findShortestSubArray(int[] nums) {
        Map<Integer, Integer> counter = new HashMap<>();
        Map<Integer, Integer> left = new HashMap<>();
        Map<Integer, Integer> right = new HashMap<>();
        int maxDegree = 0;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (!left.containsKey(num)) {
                left.put(num, i);
            }
            right.put(num, i);
            counter.put(num, counter.getOrDefault(num, 0) + 1);
            maxDegree = Math.max(maxDegree, counter.get(num));
        }
        int ans = 50005;
        System.out.printf("%d\n", maxDegree);
        for (int num : counter.keySet()) {
            if (maxDegree == counter.get(num)) {
                ans = Math.min(ans, right.get(num) - left.get(num) + 1);
            }
        }
        return ans;
    }
}
