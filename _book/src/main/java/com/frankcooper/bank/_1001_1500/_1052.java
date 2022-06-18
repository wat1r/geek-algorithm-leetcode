package com.frankcooper.platform.leetcode.bank._1001_1500;

public class _1052 {

    static class _1st {
        public int maxSatisfied(int[] customers, int[] grumpy, int X) {
            int leftSum = 0, currSum = 0, rightSum = 0;
            int N = customers.length;
            for (int i = 0; i < X; i++) {
                currSum += customers[i];
            }
            for (int i = X; i < N; i++) {
                if (grumpy[i] == 0) rightSum += customers[i];
            }
            int left = 1, right = X;
            int ans = leftSum + currSum + rightSum;
            System.out.printf("%d\n", ans);
            while (right < N) {
                if (grumpy[left - 1] == 0) leftSum += customers[left - 1];
                if (grumpy[right] == 0) rightSum -= customers[right];
                currSum += customers[right] - customers[left - 1];
                ans = Math.max(ans, leftSum + currSum + rightSum);
                left++;
                right++;
            }
            return ans;
        }
    }
}
