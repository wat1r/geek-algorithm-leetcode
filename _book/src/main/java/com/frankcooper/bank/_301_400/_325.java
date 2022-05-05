package com.frankcooper.bank._301_400;

import java.util.*;

import org.junit.Assert;

public class _325 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            Assert.assertEquals(4, handler.maxSubArrayLen(new int[]{1, -1, 5, -2, 3}, 3));
        }


        public int maxSubArrayLen(int[] nums, int k) {
            int n = nums.length;
            int[] pre = new int[n + 1];
            //k: 存储当前的前缀和的值pre[i], v:存储当前前缀和下最小的下标索引
            Map<Integer, Integer> map = new HashMap<>();
            map.put(pre[0], 0);
            for (int i = 1; i <= n; i++) {
                pre[i] = pre[i - 1] + nums[i - 1];
                if (!map.containsKey(pre[i])) {
                    map.put(pre[i], i);
                }
            }
            int maxLen = 0;
            //遍历到的为 pre[i] ,如果在 map 中存在 pre[i]-k ，说明存在一个长度为 k 的子数组，
            //现在我们得找到这个子数组的起始索引，即 map.get(pre[i]-k)，
            // 于是我们统计从 map.get(sum[i]-k) 到 i-1 长度,并更新 maxLen
            //倒序遍历 [0...i]的长度如果比maxLen小，没必要继续遍历
            for (int i = n; i > maxLen; i--) {
                int t = pre[i] - k;
                if (map.containsKey(t)) {
                    maxLen = Math.max(maxLen, i - map.get(t));
                }
            }
            return maxLen;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
