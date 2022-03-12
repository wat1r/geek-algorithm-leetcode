package com.frankcooper.bank._701_1000;


/**
 * @Date 2020/7/1
 * @Author Frank Cooper
 * @Description 718. 最长重复子数组 Maximum Length of Repeated Subarray
 */
public class _718 {

    static _718 handler = new _718();

    public static void main(String[] args) {
        int[] A = {1, 2, 3, 2, 1}, B = {3, 2, 1, 4, 7};
//        handler.findLength2nd(A, B);
        handler.findLength3rd(A, B);

    }


    public int findLength(int[] A, int[] B) {
        if (A == null || B == null || A.length == 0 || B.length == 0) return 0;
        int res = 0;
        int m = A.length, n = B.length;
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (A[i - 1] == B[j - 1]) dp[i][j] = dp[i - 1][j - 1] + 1;
                else dp[i][j] = 0;
                res = Math.max(res, dp[i][j]);
            }
        }
        return res;
    }


    public int findLength2nd(int[] A, int[] B) {
        if (A == null || B == null || A.length == 0 || B.length == 0) return 0;
        int res = 0;
        int m = A.length, n = B.length;
        int[][] dp = new int[m + 1][n + 1];
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (A[i] == B[j]) dp[i][j] = dp[i + 1][j + 1] + 1;
                else dp[i][j] = 0;
                res = Math.max(res, dp[i][j]);
            }
        }
        return res;
    }

    public int findLength3rd(int[] A, int[] B) {
        if (A == null || B == null || A.length == 0 || B.length == 0) return 0;
        int res = 0;
        int m = A.length, n = B.length;
        int[] dp = new int[n + 1];
//        Stack<Integer> stack = new Stack<>();
        for (int i = 1; i <= m; i++) {
            for (int j = n; j >= 1; j--) {
                if (A[i - 1] == B[j - 1]) {
                    dp[j] = dp[j - 1] + 1;
                } else {
                    dp[j] = 0;
                }
//                stack.push(dp[j]);
                res = Math.max(res, dp[j]);
            }
//            System.out.println(JSON.toJSONString(stack));
//            stack.clear();
        }
        return res;
    }
}
