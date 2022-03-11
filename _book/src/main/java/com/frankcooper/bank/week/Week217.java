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

    /**
     * https://leetcode-cn.com/problems/minimum-moves-to-make-array-complementary/solution/javaonde-chai-fen-shu-zu-by-liusandao/
     https://leetcode-cn.com/problems/minimum-moves-to-make-array-complementary/solution/chai-fen-cgo-by-zhe-shi-shui-a/
     */
    static class _3d {
        class Solution {
            public int minMoves(int[] nums, int limit) {
                int[] diff = new int[limit * 2 + 1];
                for (int i = 0; i < nums.length / 2; i++) {
                    int max = Math.max(nums[i], nums[nums.length - i - 1]);
                    int min = Math.min(nums[i], nums[nums.length - i - 1]);
                    if (min == 1) {
                        diff[2] += 1;
                    } else {
                        diff[2] += 2;
                        diff[min + 1] -= 1;
                    }
                    diff[max + min] -= 1;
                    if (max + min + 1 < diff.length) {
                        diff[max + min + 1] += 1;
                    }
                    if (max + limit + 1 < diff.length) {
                        diff[max + limit + 1] += 1;
                    }
                }
                int now = 0, res = nums.length / 2;
                for (int i = 2; i < diff.length; i++) {
                    now += diff[i];
                    res = Math.min(res, now);
                }
                return res;
            }
        }


    }

}
