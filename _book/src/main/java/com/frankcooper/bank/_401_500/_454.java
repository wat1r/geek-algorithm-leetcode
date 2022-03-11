package com.frankcooper.bank._401_500;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Handler;

/**
 * @Date 2020/8/7
 * @Author Frank Cooper
 * @Description
 */
public class _454 {


    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        int res = 0;
        Map<Integer, Integer> map1 = new HashMap<>();
        for (int a : A) {
            for (int b : B) {
                int sum1 = a + b;
                map1.put(sum1, map1.getOrDefault(sum1, 0) + 1);
            }
        }
        for (int c : C) {
            for (int d : D) {
                int sum2 = c + d;
                if (map1.containsKey(-sum2)) res += map1.get(-sum2);
            }
        }
        return res;
    }

    static class _2nd {

        public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
            int res = 0;
            Map<Integer, Integer> map = new HashMap<>();
            for (int a : nums1) {
                for (int b : nums2) {
                    int sum1 = a + b;
                    map.put(sum1, map.getOrDefault(sum1, 0) + 1);
                }
            }
            for (int c : nums3) {
                for (int d : nums4) {
                    int sum2 = c + d;
                    if (map.containsKey(-sum2)) res += map.get(-sum2);
                }
            }
            return res;
        }
    }

}
