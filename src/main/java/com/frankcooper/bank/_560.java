package com.frankcooper.bank;

import java.util.HashMap;

public class _560 {


    public static void main(String[] args) {

    }


    public int subarraySum(int[] nums, int k) {
        int n = nums.length;
        //多放一个位置 pre[i]表示前i个数的前缀和即 nums[0...i-1]这个范围内的前缀和
        int[] pre = new int[n + 1];
        for (int i = 0; i < n; i++) {
            pre[i + 1] = pre[i] + nums[i];
        }
        int res = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                if (pre[i] - pre[j] == k) res++;
            }
        }
        return res;
    }


    public int subarraySum2nd(int[] nums, int k) {
        int n = nums.length;
        HashMap<Integer, Integer> preSumMap = new HashMap<>();
        preSumMap.put(0, 1);
        int res = 0, sumEnd = 0;
        for (int i = 0; i < n; i++) {
            sumEnd += nums[i];
            int remain = sumEnd - k;
            if (preSumMap.containsKey(remain)) res += preSumMap.get(remain);
            preSumMap.put(sumEnd, preSumMap.getOrDefault(sumEnd, 0) + 1);
        }
        return res;
    }



}
