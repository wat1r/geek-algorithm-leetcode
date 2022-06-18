package com.frankcooper.platform.leetcode.bank._301_400;

public class _370 {


    static _370 handler = new _370();

    public static void main(String[] args) {

        int length = 5;
        int[][] updates = {{1, 3, 2}, {2, 4, 3}, {0, 2, -2}};
        handler.getModifiedArray(length, updates);
    }


    public int[] getModifiedArray(int length, int[][] updates) {
        int[] arr = new int[length];
        for (int[] update : updates) {
            int start = update[0], end = update[1], val = update[2];
            arr[start] += val;
            if (end < length - 1) arr[end + 1] -= val;
        }
        for (int i = 1; i < length; i++) {
            arr[i] += arr[i - 1];
        }
        return arr;
    }

}
