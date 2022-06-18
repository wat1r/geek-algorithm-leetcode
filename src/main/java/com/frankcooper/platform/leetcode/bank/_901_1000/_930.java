package com.frankcooper.platform.leetcode.bank._901_1000;

public class _930 {


    static _930 handler = new _930();

    public static void main(String[] args) {
        int[] A = {1, 0, 1, 0, 1};
        int S = 2;
        handler.numSubarraysWithSum(A, S);
        A = new int[]{0, 0, 0, 0, 0};
        S = 0;
        handler.numSubarraysWithSum(A, S);

    }


    public int numSubarraysWithSum(int[] A, int S) {
        int n = A.length, l = 0, r = 0, cnt = 0, sum = 0;
        for (; r < n; r++) {
            sum += A[r];
            while (l < r && sum > S) {
                sum -= A[l++];
            }
            if (sum == S) cnt++;
            for (int i = l; sum == S && i < r && A[i] == 0; i++) cnt++;
        }
        return cnt;
    }


    /**
     * hashmap
     */
    class _1st {
        public int numSubarraysWithSum(int[] A, int S) {
            int psum = 0, res = 0, count[] = new int[A.length + 1];
            count[0] = 1;
            for (int i : A) {
                psum += i;
                if (psum >= S)
                    res += count[psum - S];
                count[psum]++;
            }
            return res;
        }
    }

}
