package com.frankcooper.bank;

public class _1004 {


    public int longestOnes(int[] A, int K) {

        int ans = 0;
        for (int i = 0, j = 0, zero = 0; i < A.length; i++) {
            if (A[i] == 0) zero++;
            while (zero > K) {
                if (A[j++] == 0) zero--;
            }
            ans = Math.max(ans, i - j + 1);
        }
        return ans;
    }


}
