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
                if(map1.containsKey(-sum2))  res += map1.get(-sum2);
            }
        }
        return res;
    }

}
