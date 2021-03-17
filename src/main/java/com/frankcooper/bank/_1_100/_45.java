package com.frankcooper.bank._1_100;


//45. 跳跃游戏 II 45. Jump Game II Hard
public class _45 {
    public static void main(String[] args) {

        System.out.println();
        System.out.println("test");
    }


    public int jump(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = 0;
        for (int i = 1; i < n; i++) {
            dp[i] = Integer.MAX_VALUE;
            for (int j = 0; j < i; j++) {
                if (nums[j] >= (i - j)) {
                    dp[i] = Math.min(dp[i], dp[j] + 1);
                }
            }
        }
        return dp[n - 1];
    }
}
