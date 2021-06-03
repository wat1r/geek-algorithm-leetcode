package com.frankcooper.bank._501_1000;

import java.util.*;

import org.junit.Assert;

public class _525 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[] nums = new int[]{0, 1, 0, 0, 1, 0, 0, 1, 1};
            Assert.assertEquals(8, handler.findMaxLength(nums));
        }


        public int findMaxLength(int[] nums) {
            //0 相当于-1 -1 +1 cnt值维持在0附近波动
            //k 表示计数0的次数，v表示当前计数下，0的最早的下标索引
            //哈希表表示每一个前缀和第一次出现时的下标索引
            Map<Integer, Integer> map = new HashMap<>();
            int res = 0, cnt = 0;
            map.put(0, -1);
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] == 1) cnt++;
                else if (nums[i] == 0) cnt--;
                if (map.containsKey(cnt)) {
                    int prev = map.get(cnt);
                    res = Math.max(res, i - prev);
                } else {
                    map.put(cnt, i);
                }
            }
            return res;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            int[] nums = new int[]{0, 1, 0, 0, 1, 0, 0, 1, 1};
            Assert.assertEquals(8, handler.findMaxLength(nums));
        }


        public int findMaxLength(int[] nums) {
            int res = 0, n = nums.length;
            int[] preSum = new int[n + 1];
            for (int i = 0; i < n; i++) {
                preSum[i + 1] = preSum[i] + (nums[i] == 0 ? -1 : 1);
            }
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i <= n; i++) {
                if (map.containsKey(preSum[i])) {
                    res = Math.max(res, i - map.get(preSum[i]));
                } else {
                    map.put(preSum[i], i);
                }
            }
            return res;
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
