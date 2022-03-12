package com.frankcooper.bank._1001_1500;

import com.alibaba.fastjson.JSONObject;

import java.util.Arrays;

public class _1406 {

    static _1406 handler = new _1406();

    public static void main(String[] args) {
        int[] stones = new int[]{1, 2, 3, 7};
        stones = new int[]{1, 2, 3, -1, -2, -3, 7};
        handler.stoneGameIII(stones);
    }


    public String stoneGameIII1st(int[] stones) {
        int n = stones.length;
        int[] suffixSum = new int[n];
        suffixSum[n - 1] = stones[n - 1];
        //后缀和
        for (int i = n - 2; i >= 0; i--) suffixSum[i] = suffixSum[i + 1] + stones[i];
        //多冗余一位 f[n] 表示在区间 [n...n]范围内当前玩家所能拿到的最多的石子的数量，这个区间不存在
        int[] f = new int[n + 1];
        f[n] = 0;
        for (int i = n - 1; i >= 0; --i) {
            int bestJ = f[i + 1];//找到拿走1 2 3 堆石子，另外一个玩家所能拿到的最小的石子数量
            for (int j = i + 2; j <= i + 3 && j <= n; j++) {
                bestJ = Math.min(bestJ, f[j]);
            }
            f[i] = suffixSum[i] - bestJ;
        }
        int total = suffixSum[0];
        return f[0] * 2 == total ? "Tie" : (f[0] * 2 > total ? "Alice" : "Bob");
    }


    public String stoneGameIII2nd(int[] stones) {
        int n = stones.length;
        int[] suffixSum = new int[n];
        suffixSum[n - 1] = stones[n - 1];
        for (int i = n - 2; i >= 0; --i) suffixSum[i] = suffixSum[i + 1] + stones[i];
        int[] f = new int[n + 1];
        Arrays.fill(f, Integer.MIN_VALUE);
        f[n] = 0;
        for (int i = n - 1; i >= 0; i--) {
            int sum = 0;
            for (int j = i + 1; j <= i + 3 && j <= n; j++) {
                sum += stones[j - 1];
                f[i] = Math.max(f[i], sum - f[j]);
            }
        }
        return f[0] == 0 ? "Tie" : (f[0] > 0 ? "Alice" : "Bob");
    }


    public String stoneGameIII3rd(int[] stones) {
        int n = stones.length;
        int[] f = new int[n + 1];
        for (int i = n - 1; i >= 0; --i) {
            f[i] = Integer.MIN_VALUE;
            for (int k = 0, take = 0; k < 3 && i + k < n; ++k) {
                take += stones[i + k];
                f[i] = Math.max(f[i], take - f[i + k + 1]);
            }
        }
        return f[0] == 0 ? "Tie" : (f[0] > 0 ? "Alice" : "Bob");
    }
//1, 2, 3, -1, -2, -3, 7

    public String stoneGameIII(int[] stones) {
        int n = stones.length, f[] = new int[4];
        for (int i = n - 1; i >= 0; i--) {
            f[i % 4] = Integer.MIN_VALUE;
            for (int k = 0, take = 0; k < 3 && k + i < n; k++) {
                System.out.println(JSONObject.toJSONString(f));
                System.out.printf("%d,%d,%d,%d,%d,%d,%d\n", i, k, i % 4, i + k, i + k + 1, take, f[i % 4]);
//                System.out.printf("i:%d,k:%d,i整除4:%d,i+k:%d,i+k+1:%d , take :%d, f[i整除4] :%d\n", i, k, i % 4, i + k, i + k + 1, take, f[i % 4]);
                take += stones[i + k];
                f[i % 4] = Math.max(f[i % 4], take - f[(i + k + 1) % 4]);
            }
        }
        return f[0] == 0 ? "Tie" : (f[0] > 0 ? "Alice" : "Bob");
    }


}
