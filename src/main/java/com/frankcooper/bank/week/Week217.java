package com.frankcooper.bank.week;

import java.util.Stack;

public class Week217 {


    Week217 handler = new Week217();

    public static void main(String[] args) {
        _2nd s = new _2nd();

        int[] nums = {2, 4, 3, 3, 5, 4, 9, 6};
        int k = 4;
        nums = new int[]{3, 5, 2, 6};
        k = 2;
        s.mostCompetitive1st(nums, k);
    }


    class _1st {
        public int maximumWealth(int[][] accounts) {
            int max = 0;
            for (int[] account : accounts) {
                int sum = 0;
                for (int i : account) sum += i;
                max = Math.max(max, sum);
            }
            return max;
        }
    }


    static class _2nd {
        public int[] mostCompetitive1st(int[] nums, int k) {

            Stack<Integer> stack = new Stack<>();
            int n = nums.length;
            stack.add(-1);
            for (int i = 0; i < n; i++) {
                while (nums[i] < stack.peek() && (n - i) > (k - stack.size() + 1)) {
                    stack.pop();
                }
                if (stack.size() < (k + 1)) {
                    stack.add(nums[i]);
                }
            }
            int[] res = new int[k];
            while (k > 0) res[--k] = stack.pop();
            return res;
        }


        /**
         * 优先队列
         */
    }

}
