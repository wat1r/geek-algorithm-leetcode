package com.frankcooper.interview;

import java.util.*;

import org.junit.Assert;

public class _16_21 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[] arr1 = {1, 2, 3};
            int[] arr2 = {4, 5, 6};
            handler.findSwapValues(arr1, arr2);
        }


        public int[] findSwapValues(int[] arr1, int[] arr2) {
            int sum1 = 0, sum2 = 0;
            for (int x : arr1) sum1 += x;
            for (int x : arr2) sum2 += x;
            Map<Integer, Integer> map = new HashMap<>();
            for (int x : arr2) {
                int k = 2 * x + (sum1 - sum2);//这里处理成2倍处理
                map.put(k, x);
            }
            for (int x : arr1) if (map.containsKey(2 * x)) return new int[]{x, map.get(2 * x)};
            return new int[]{};
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }


        public int[] findSwapValues(int[] arr1, int[] arr2) {
            int sum1 = 0, sum2 = 0;
            for (int x : arr1) sum1 += x;
            for (int x : arr2) sum2 += x;
            int d = sum1 - sum2;
            Arrays.sort(arr1);
            Arrays.sort(arr2);
            int i = 0, j = 0, n1 = arr1.length, n2 = arr2.length;
            while (i < n1 && j < n2) {
                int t = 2 * (arr1[i] - arr2[j]);
                if (t == d) return new int[]{arr1[i], arr2[j]};
                else if (t > d) j++;
                else i++;
            }
            return new int[]{};
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
