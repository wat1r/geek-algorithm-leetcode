package com.frankcooper.bank._701_1000;

public class _896 {

    static class _1st {
        public boolean isMonotonic(int[] A) {
            int delta = 0;
            for (int i = 1; i < A.length; i++) {
                int pre = A[i - 1], curr = A[i];
                if (curr > pre) {
                    if (delta < 0) return false;
                    delta = 1;
                } else if (curr < pre) {
                    if (delta > 0) return false;
                    delta = -1;
                }
            }
            return true;
        }
    }
}
