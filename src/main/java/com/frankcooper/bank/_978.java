package com.frankcooper.bank;

public class _978 {


    static _978 handler = new _978();


    public static void main(String[] args) {

        int[] A = {9, 4, 2, 10, 7, 8, 8, 1, 9};
        handler.maxTurbulenceSize(A);

    }


    public int maxTurbulenceSize(int[] A) {
        if (A == null || A.length == 0) return 0;
        int i = 0, j = 1;
        int res = 0;
        while (i < j && j < A.length) {
            int k = i;
            while (k < j) {
                if (k % 2 == 0 && A[k] > A[k + 1]) {
                    if (k - 1 >= 0 && A[k - 1] < A[k]) {
                        k++;
                    } else {
                        k++;
                    }
                } else if (k % 2 == 1 && A[k] > A[k + 1]) {
                    if (k - 1 >= 0 && A[k - 1] < A[k]) {
                        k++;
                    } else {
                        k++;
                    }
                } else {
                    i = k;
                }
            }
            res = Math.max(res, j - i + 1);
            j++;
        }
        return res;
    }
}


