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
        int left = 0, right = 0;
        int res = 0;
        int oddCnt = 0;
        while (left < nums.length && right < nums.length) {
            if (nums[right] % 2 == 1) {
                oddCnt++;
            }
            if (oddCnt == k) {
                res++;
                if (nums[left] % 2 == 1) {
                    oddCnt--;
                    left++;
                }
            }
            right++;
        }

        return res;
    }
}
