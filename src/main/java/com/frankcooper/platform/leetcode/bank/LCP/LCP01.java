package com.frankcooper.platform.leetcode.bank.LCP;

/**
 * @author: Frank Cooper
 * @date: 2021/4/7 19:55
 * @description:
 */
public class LCP01 {
    static class _1st {
        public int game(int[] guess, int[] answer) {
            int res = 0;
            for (int i = 0; i < guess.length; i++) {
                if (guess[i] == answer[i]) res++;
            }
            return res;
        }
    }
}
