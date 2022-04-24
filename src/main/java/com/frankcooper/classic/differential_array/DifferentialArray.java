package com.frankcooper.classic.differential_array;

import java.util.Arrays;

public class DifferentialArray {
    public static void main(String[] args) {
        int[] arr = {1, 3, 7, 5, 2};
        add(2, 4, 5);
        add(1, 3, 2);
        add(0, 2, -3);
        for (int i = 1; i < 5; i++) d[i] += d[i - 1];
        System.out.println(Arrays.toString(d));
        for (int i = 0; i < 5; i++) arr[i] += d[i];
        System.out.println(Arrays.toString(arr));
    }

    //差分数组
    static int[] d = new int[6];

    private static void add(int l, int r, int v) {
        d[l] += v;
        d[r + 1] -= v;
    }


}
