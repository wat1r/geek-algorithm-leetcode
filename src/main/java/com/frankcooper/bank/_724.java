package com.frankcooper.bank;

public class _724 {

    static _724 handler = new _724();

    public static void main(String[] args) {
//        handler.pivotIndex(new int[]{1, 7, 3, 6, 5, 6});
        handler.pivotIndex(new int[]{-1, -1, -1, 0, 1, 1});

    }


    public int pivotIndex(int[] nums) {
        int n = nums.length;
        int[] preSum = new int[n + 1];
        for (int i = 0; i < n; i++) preSum[i + 1] = preSum[i] + nums[i];
        for (int i = 0; i < n; i++) {
            int left = preSum[i] - preSum[0];
            int right = preSum[n] - preSum[i + 1];
            if (left == right) return i;
        }
        return -1;
    }
}
