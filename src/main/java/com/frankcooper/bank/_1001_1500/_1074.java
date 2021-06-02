package com.frankcooper.bank._1001_1500;

import java.util.*;

import org.junit.Assert;

public class _1074 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        public int numSubmatrixSumTarget(int[][] matrix, int target) {
            int res = 0;
            int m = matrix.length, n = matrix[0].length;
            for (int i = 0; i < m; i++) {
                int[] sum = new int[n];
                for (int j = i; j < m; j++) {
                    for (int k = 0; k < n; k++) {
                        sum[k] += matrix[j][k];
                    }
                    res +=subarraySum(sum,target);
                }
            }
            return res;
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
