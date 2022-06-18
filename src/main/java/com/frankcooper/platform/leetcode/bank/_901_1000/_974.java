package com.frankcooper.platform.leetcode.bank._901_1000;

public class _974 {

    static _974 handler = new _974();

    public static void main(String[] args) {
        int[] A = {4, 5, 0, -2, -3, 1};
        int K = 5;
//        handler.subarraysDivByK1st(A, K);
        handler.subarraysDivByK(A, K);


    }


    /**
     * @param A
     * @param K
     * @return
     */
    public int subarraysDivByK1st(int[] A, int K) {
        int n = A.length;
        int[] pre = new int[n + 1];
        for (int i = 0; i < n; i++) {
            pre[i + 1] = pre[i] + A[i];
        }
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                if ((pre[i] - pre[j]) % K == 0) ans++;
            }
        }
        return ans;
    }

    public int subarraysDivByK(int[] A, int K) {

        int sum = 0, ans = 0, n = A.length;
        int[] map = new int[K];
        map[0] = 1;
        for (int i = 1; i <= n; i++) {
            sum += A[i - 1];
            int key = (sum % K + K) % K;
            ans += map[key];
            map[key]++;
        }
        return ans;
    }


}
