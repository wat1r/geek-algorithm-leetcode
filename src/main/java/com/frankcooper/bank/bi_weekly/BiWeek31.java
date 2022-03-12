package com.frankcooper.bank.bi_weekly;

import java.util.HashMap;
import java.util.Map;

public class BiWeek31 {


    /**
     * 1525
     */
    static class _3rd {


        public static void main(String[] args) {
            _3rd h = new _3rd();
            h.numSplits("aacaba");
        }


        public int numSplits(String s) {
            int n = s.length();
            Map<Character, Integer> counter = new HashMap<>();
            int[] left = new int[n];
            int[] right = new int[n];
            for (int i = 0; i < n; i++) {
                char c = s.charAt(i);
                counter.put(c, counter.getOrDefault(c, 0) + 1);
                left[i] = counter.size();
            }
            counter.clear();
            for (int i = n - 1; i >= 0; i--) {
                char c = s.charAt(i);
                counter.put(c, counter.getOrDefault(c, 0) + 1);
                right[i] = counter.size();
            }
            int ans = 0;
            for (int i = 0; i < n-1; i++) {
                if (left[i] == right[i + 1]) ans++;
            }
            return ans;
        }
    }

}
