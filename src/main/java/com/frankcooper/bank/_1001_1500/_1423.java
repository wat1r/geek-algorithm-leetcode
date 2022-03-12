package com.frankcooper.bank._1001_1500;

public class _1423 {

    static _1423 handler = new _1423();

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 1};
        int k = 3;
//        handler.maxScore(nums, k);
    }

    public int maxScore1st(int[] nums, int k) {
        int n = nums.length;
        int l = 0, r = n - k - 1;
        int totalSum = 0;//记录总的和
        int currSum = 0;//记录当前滑动窗口的和
        for (int i = 0; i < n; i++) {
            if (i <= r) currSum += nums[i];
            totalSum += nums[i];
        }
        int minSum = currSum;//记录滑动的过程中，滑动窗口的最小值
        while (r < n - 1) {
            //先将右窗口扩大，再将左窗口缩小
            currSum = currSum - nums[l++] + nums[++r];
            minSum = Math.min(minSum, currSum);
        }
        return totalSum - minSum;//反向思考
    }

    public int maxScore(int[] nums, int k) {
        int n = nums.length;
        int[] pre = new int[n + 1];
        //前缀和，多放一个下标，pre[i]表示前i个数字的和，即[0...i-1]
        for (int i = 0; i < n; i++) pre[i + 1] = pre[i] + nums[i];
        int ans = -1;
        for (int i = 0; i <= k; i++) {
            ans = Math.max(ans, pre[i] + pre[n] - pre[n - k + i]);
        }
        return ans;
    }

    class _1st {


        public int maxScore(int[] nums, int k) {
            int l = 0, n = nums.length, r = n - k - 1;
            int total = 0, curr = 0;
            for (int i = 0; i < n; i++) {
                if (i <= r) curr += nums[i];
                total += nums[i];
            }
            int minv = curr;
            while (r < n - 1) {
                curr = curr - nums[l++] + nums[++r];
                minv = Math.min(minv, curr);
            }
            return total - minv;
        }

    }


}
