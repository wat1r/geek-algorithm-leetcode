package com.frankcooper.bank._701_1000;

import java.util.Arrays;

public class _977 {


    public int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        for (int i = 0; i < n; i++) res[i] = nums[i] * nums[i];
        Arrays.sort(res);
        return res;
    }
}
