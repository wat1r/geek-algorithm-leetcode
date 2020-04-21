package com.frankcooper.bank;

public class _1248 {

    static _1248 handler = new _1248();

    public static void main(String[] args) {
        int[] nums = {1, 1, 2, 1, 1};
        int k = 3;
        nums = new int[]{2, 2, 2, 1, 2, 2, 1, 2, 2, 2};
        k = 2;
        handler.numberOfSubarrays(nums, k);

    }

    /**
     * 当达到k的值后，后面有偶数就不断right++
     *
     * @param nums
     * @param k
     * @return
     */
    public int numberOfSubarrays(int[] nums, int k) {
        int len = nums.length, res = 0, index = 0, arr[] = new int[len + 2];
        for (int i = 0; i < len; i++) {
            // 奇数
            if ((nums[i] & 1) == 1) {
                arr[++index] = i;
            }
        }
        // 左边界
        arr[0] = -1;
        // 右边界
        arr[index + 1] = len;
        for (int i = 1; i + k < index + 2; i++) {
            res += (arr[i] - arr[i - 1]) * (arr[i + k] - arr[i + k - 1]);
        }
        return res;
    }
}
