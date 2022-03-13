package com.frankcooper.bank._1_100;

public class _53 {


    public static void main(String[] args) {
        _53 handler = new _53();
        handler.maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4});
    }

    public int maxSubArray(int[] nums) {
        int currSum = nums[0], maxSum = currSum;
        for (int i = 1; i < nums.length; i++) {
            currSum = Math.max(nums[i], currSum + nums[i]);
            maxSum = Math.max(maxSum, currSum);
        }
        return maxSum;
    }


    static class _1st {
        public int maxSubArray(int[] nums) {
            int cur = nums[0], res = cur;
            for (int i = 1; i < nums.length; i++) {
                cur = Math.max(nums[i], nums[i] + cur);
                res = Math.max(res, cur);
            }
            return res;
        }
    }

    static class _2nd {

        public static void main(String[] args) {

        }


        public int maxSubArray(int[] nums) {
            int n = nums.length;
            //f[i] 表：以 nums[i] 结尾的连续子数组的最大和
            int[] f = new int[n];
            f[0] = nums[0];
            int res = f[0];
            for (int i = 1; i < n; i++) {
                if (f[i - 1] > 0) {
                    f[i] = f[i - 1] + nums[i];
                } else {
                    f[i] = nums[i];
                }
                res = Math.max(res, f[i]);
            }
            return res;
        }

    }

    static class _3rd {
        public int maxSubArray(int[] nums) {
            int n = nums.length;
            int[] f = new int[n];
            f[0] = nums[0];
            int res = f[0];
            for (int i = 1; i < n; i++) {
                f[i] = Math.max(f[i-1]+nums[i],nums[i]);
                res = Math.max(res, f[i]);
            }
            return res;
        }
    }


}
