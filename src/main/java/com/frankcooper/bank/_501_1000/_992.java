package com.frankcooper.bank._501_1000;

import java.util.HashSet;
import java.util.Set;

public class _992 {


    public static void main(String[] args) {
        _1st handler = new _1st();
        int[] A = {1, 2, 1, 2, 3};
        int K = 2;
        A = new int[]{1, 2, 1, 3, 4};
        K = 3;
        handler.subarraysWithKDistinct(A, K);
    }


    static class _1st {
        public int subarraysWithKDistinct(int[] A, int K) {
            int l = 0, r = 0, n = A.length;
            int res = 0;
            while (l < n) {
                Set<Integer> cntSet = new HashSet<>();
                for (r = l; r < n; r++) {
                    cntSet.add(A[r]);
                    if (cntSet.size() > K) break;
                    if (cntSet.size() == K) {
                        res++;
                    }
                }
                l++;
            }
            return res;
        }

    }

}
