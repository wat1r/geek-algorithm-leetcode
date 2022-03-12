package com.frankcooper.bank._701_1000;

public class _978 {


    static _978 handler = new _978();


    public static void main(String[] args) {

        int[] A = {9, 4, 2, 10, 7, 8, 8, 1, 9};
        handler.maxTurbulenceSize(A);


    }


    public int maxTurbulenceSize(int[] A) {
        int n = A.length, l = 0;
        int res = 1;
        for (int r = 1; r < n; r++) {
            int c = Integer.compare(A[r - 1], A[r]);
            if (r == n - 1 || c * Integer.compare(A[r], A[r + 1]) != -1) {
                if (c != 0) {
                    res = Math.max(res, r - l + 1);
                }
                l = r;
            }
        }
        return res;
    }


    public int maxTurbulenceSize1st(int[] A) {
        if (A == null || A.length == 0) return 0;
        int res = 1;
        int up = 1, down = 1;
        for (int i = 1; i < A.length; i++) {
            if (A[i] > A[i - 1]) {
                up = down + 1;
                down = 1;
            } else if (A[i] < A[i - 1]) {
                down = up + 1;
                up = 1;
            } else {
                up = 1;
                down = 1;
            }
            res = Math.max(res, Math.max(up, down));
        }
        return res;
    }
}


