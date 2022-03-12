package com.frankcooper.bank._401_500;

/**
 * @Date 2020/9/13
 * @Author Frank Cooper
 * @Description
 */
public class _413 {


    static _413 handler = new _413();

    public static void main(String[] args) {

    }


    public int numberOfArithmeticSlices(int[] A) {
        int n = A.length;
        int res = 0;
        int[] dp = new int[n];
        for (int i = 2; i < n; i++) {
            if (A[i] - A[i - 1] == A[i - 1] - A[i - 2]) {
                dp[i] = dp[i - 1] + 1;
                res += dp[i];
            }
        }
        return res;
    }


    int res = 0;

    public int numberOfArithmeticSlices2nd(int[] A) {
        helper(A, A.length - 1);
        return res;
    }

    private int helper(int[] A, int i) {
        if (i < 2) return 0;
        int tmp = 0;
        if (A[i] - A[i - 1] == A[i - 1] - A[i - 2]) {
            tmp += helper(A, i - 1) + 1;
            res += tmp;
        } else {
            helper(A, i - 1);
        }
        return tmp;
    }


    public int numberOfArithmeticSlices1st(int[] A) {
        if (A == null || A.length == 0) return 0;
        int n = A.length;
        int res = 0;
        for (int i = 0; i < n - 2; i++) {
            int delta = A[i + 1] - A[i];
            for (int j = i + 2; j < n; j++) {
                if (A[j] - A[j - 1] == delta) res++;
                else break;
            }
        }
        return res;
    }


}
