package com.frankcooper.bank._501_700;

import java.util.HashMap;
import java.util.Map;

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


    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        /**
         * 两遍遍历，判断区间是否符合要求计数
         *
         * @param nums
         * @param k
         * @return
         */
        public int subarraySum(int[] nums, int k) {
            int res = 0;
            for (int i = 0; i < nums.length; i++) {
                int sum = 0;
                for (int j = i; j >= 0; --j) {
                    sum += nums[j];
                    if (sum == k) res++;
                }
            }
            return res;
        }
    }


    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();

        }

        public int subarraySum(int[] nums, int k) {
            int res = 0;
            Map<Integer, Integer> map = new HashMap<>();//k是和 v是出现的次数
            map.put(0, 1);
            int pre = 0;
            for (int i = 0; i < nums.length; i++) {
                pre += nums[i];
                int t = pre - k;
                if (map.containsKey(t)) res += map.get(t);
                map.put(pre, map.getOrDefault(pre, 0) + 1);
            }
            return res;
        }
    }


}
