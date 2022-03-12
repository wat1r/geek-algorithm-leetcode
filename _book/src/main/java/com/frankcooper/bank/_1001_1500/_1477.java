package com.frankcooper.bank._1001_1500;

import java.util.Arrays;

public class _1477 {


    public int minSumOfLengths(int[] arr, int target) {

        int n = arr.length;
        int[] f = new int[n];
        Arrays.fill(f, Integer.MAX_VALUE / 2);
        int ans = Integer.MAX_VALUE;
        for (int i = 0, j = 0, sum = 0; j < n; j++) {
            sum += arr[j];
            while (i <= j && sum > target) sum -= arr[i++];
            if (sum == target) {
                f[j] = j - i + 1;
                if (i != 0) ans = Math.min(ans, f[i - 1] + (j - i + 1));
            }
            if (j != 0) f[j] = Math.min(f[j], f[j - 1]);
        }
        return ans > arr.length ? -1 : ans;
    }

}
