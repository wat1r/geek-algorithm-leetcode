package com.frankcooper.platform.leetcode.bank.week;

import java.util.*;

/**
 * Created by FrankCooper
 * Date 2021/1/16 22:21
 * Description
 */
public class Week218 {


    /**
     * 1678
     */
    class _1st {
        public String interpret(String command) {
            StringBuilder sb = new StringBuilder();
            int n = command.length();
            for (int i = 0; i < n; i++) {
                if (command.charAt(i) == 'G') sb.append("G");
                else if ((i + 3) < n && command.substring(i, i + 4).equals("(al)")) {
                    sb.append("al");
                    i += 3;
                } else if ((i + 1) < n && command.substring(i, i + 2).equals("()")) {
                    sb.append("o");
                    i += 1;
                }
            }
            return sb.toString();
        }

    }


    class _2nd {
        public int maxOperations(int[] nums, int k) {
            Map<Integer, Integer> hash = new HashMap<>();
            for (int x : nums) hash.put(x, hash.getOrDefault(x, 0) + 1);
            int ans = 0;
            for (Map.Entry<Integer, Integer> e : hash.entrySet()) {
                int x = e.getKey(), y = k - x;
                if (hash.containsKey(x) && hash.containsKey(y)) {
                    int cnt = Math.min(hash.get(x), hash.get(y));
                    if (x == y) cnt /= 2;
                    ans += cnt;
                    hash.put(x, hash.get(x) - cnt);
                    hash.put(y, hash.get(y) - cnt);
                }
            }
            return ans;
        }
    }




}
