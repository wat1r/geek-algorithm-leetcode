package com.frankcooper.bank._501_700;

public class _643 {


    static _643 handler = new _643();

    public static void main(String[] args) {
        int[] nums = {1, 12, -5, -6, 50, 3};
        int k = 4;
        nums = new int[]{-1};
        k = 1;
        System.out.println(Double.MIN_VALUE);
        System.out.println(Double.NEGATIVE_INFINITY);

        handler.findMaxAverage(nums, k);
    }


    /**
     * Double.MIN_VALUE 这是个正数
     * 要用Double负无穷 用 Double.NEGATIVE_INFINITY
     * @param nums
     * @param k
     * @return
     */
    public double findMaxAverage(int[] nums, int k) {
        int l = 0, r = 0, sum = 0;
        double ans = -10005.0;
        for (; r < nums.length; r++) {
            sum += nums[r];
            if (r - l + 1 > k) sum -= nums[l++];
            if (r - l + 1 == k) {
                ans = Math.max(ans, (double) (sum) / k);
            }
        }
        return ans;
    }


}
