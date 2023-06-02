package com.frankcooper.platform.leetcode.bank._1001_1500;

import java.util.*;

import org.junit.Assert;

public class _1130 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[] arr = {6, 2, 4};
            handler.mctFromLeafValues(arr);

        }

        public int mctFromLeafValues(int[] arr) {
            int n = arr.length;
            int[][] dp = new int[n][n];
            for (int i = 0; i < n; i++) {
                Arrays.fill(dp[i], Integer.MAX_VALUE / 4);
            }
            int[][] mval = new int[n][n];
            for (int j = 0; j < n; j++) {
                mval[j][j] = arr[j];
                dp[j][j] = 0;
                for (int i = j - 1; i >= 0; i--) {
                    mval[i][j] = Math.max(arr[i], mval[i + 1][j]);
                    for (int k = i; k < j; k++) {
                        dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j] + mval[i][k] * mval[k + 1][j]);
                    }
                }
            }
            return dp[0][n - 1];
        }

//        作者：力扣官方题解
//        链接：https://leetcode.cn/problems/minimum-cost-tree-from-leaf-values/solutions/2285433/xie-zhi-de-zui-xiao-dai-jie-sheng-cheng-26ozf/
//        来源：力扣（LeetCode）
//        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            int[] arr = {6, 2, 4};
            handler.mctFromLeafValues(arr);
        }


        public int mctFromLeafValues(int[] arr) {
            int res = 0;
            Deque<Integer> stk = new ArrayDeque<>();
            for (int x : arr) {
                while (!stk.isEmpty() && stk.peek() <= x) {
                    int y = stk.pop();
                    if (stk.isEmpty() || stk.peek() > x) {
                        res += y * x;
                    } else {
//                        stk.push(stk.pop() * y);
                        res += stk.peek() * y;
                    }
                }
                stk.push(x);
            }
            while (stk.size() >= 2) {
                int x = stk.pop();
                res += x * stk.peek();
            }
            return res;
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
            int[] arr = {6, 2, 4};
            handler.mctFromLeafValues(arr);
        }

        public int mctFromLeafValues(int[] arr) {
            int idx = 0, ans = 0;
            int[] stack = new int[arr.length + 1];
            stack[idx++] = Integer.MAX_VALUE;
            for (int num : arr) {
                while (stack[idx - 1] < num) {
                    ans += stack[--idx] * Math.min(stack[idx - 1], num);
                }
                stack[idx++] = num;
            }
            while (idx > 2) ans += stack[--idx] * stack[idx - 1];
            return ans;
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
