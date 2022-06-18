package com.frankcooper.platform.leetcode.bank.week;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Week222 {


    public static void main(String[] args) {
        _2nd handler = new _2nd();
//        handler.countPairs(new int[]{1, 3, 5, 7, 9});
        handler.countPairs(new int[]{1, 1, 1, 3, 3, 3, 7});
    }

    /**
     * 1710
     */
    class _1st {
        public int maximumUnits(int[][] boxTypes, int truckSize) {
            Arrays.sort(boxTypes, (o1, o2) -> o2[1] - o1[1]);
            int ans = 0;
            for (int i = 0; i < boxTypes.length && truckSize > 0; i++) {
                int curr = Math.min(boxTypes[i][0], truckSize);
                ans += curr * boxTypes[i][1];
                truckSize -= curr;
            }
            return ans;
        }
    }

    /**
     * 1711
     */
    static class _2nd {
        public int countPairs(int[] ds) {
            int MOD = 1_000_000_007;
            int ans = 0;
            Map<Integer, Integer> cache = new HashMap<>();
            for (int x : ds) {
                for (int i = 0; i <= 21; i++) {
                    int y = (1 << i) - x;
                    if (cache.containsKey(y)) {
                        ans = (ans + cache.get(y)) % MOD;
                    }
                }
                cache.put(x, cache.getOrDefault(x, 0) + 1);
            }
            return ans;
        }
    }



}
