package com.frankcooper.bank._701_1000;

import java.util.ArrayList;
import java.util.List;

public class _989 {


    static _989 handler = new _989();


    public static void main(String[] args) {
//        handler.addToArrayForm(new int[]{1, 2, 0, 0}, 34);
        handler.addToArrayForm(new int[]{9, 9, 9, 9, 9, 9, 9, 9, 9, 9}, 1);
//        int[] A = {1, 2, 6, 3, 0, 7, 1, 7, 1, 9, 7, 5, 6, 6, 4, 4, 0, 0, 6, 3};
//        int K = 516;
//        handler.addToArrayForm(A, K);

    }


    public List<Integer> addToArrayForm(int[] A, int K) {
        List<Integer> ans = new ArrayList<>();
        int carry = 0, sum = 0;
        for (int i = A.length - 1; i >= 0; --i) {
            sum = (K % 10 + A[i] + carry);
            K /= 10;
            ans.add(0, sum % 10);
            carry = sum / 10;
        }
        K += carry;
        while (K > 0) {
            ans.add(0, K % 10);
            K /= 10;
        }
        return ans;
    }
}
