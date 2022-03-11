package com.frankcooper.bank._1001_1500;

import java.util.HashMap;

public class _1128 {


    public int numEquivDominoPairs(int[][] dominoes) {
        int ans = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int[] d : dominoes) {
            int k = 0;
            if (d[0] < d[1]) k = d[0] * 10 + d[1];
            else k = d[1] * 10 + d[0];
            ans += map.getOrDefault(k, 0);
            map.put(k, map.getOrDefault(k, 0) + 1);
        }
        return ans;
    }


}
