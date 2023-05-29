package com.frankcooper.platform.leetcode.bank._601_700;

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
     *
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

    static class _1st {
        public static void main(String[] args) {

        }

        //前缀和+滑动窗口
        public double findMaxAverage(int[] nums, int k) {
            int sum = 0;
            for (int i = 0; i < k; i++) {
                sum += nums[i];
            }
            int maxSum = sum;
            for (int i = k; i < nums.length; i++) {
                sum = sum - nums[i - k] + nums[i];
                maxSum = Math.max(maxSum, sum);
            }
            System.out.println(maxSum);
            return (double) maxSum / k;
        }

    }


}
