package com.frankcooper.bank._1001_1500;

import java.util.*;

import org.junit.Assert;

public class _1051 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        public int heightChecker(int[] heights) {
            int n = heights.length;
            int[] arr = new int[n];
            System.arraycopy(heights, 0, arr, 0, n);
            Arrays.sort(arr);
            int res = 0;
            for (int i = 0; i < n; i++) {
                if (heights[i] != arr[i]) res++;
            }
            return res;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            int[] heights = {1, 1, 4, 2, 1, 3};
            Assert.assertEquals(3, handler.heightChecker(heights));
        }


        public int heightChecker(int[] heights) {
            int[] buckets = new int[110];
            for (int h : heights) buckets[h]++;
            int res = 0;
            //i为buckets的自增下标，j为heights的自增下标
            for (int i = 1, j = 0; i < buckets.length; i++) {
                //桶里还有元素的时候一直循环
                while (buckets[i]-- > 0) {
                    //如果出现当前的元素和桶里的元素（i）不一致，说明该元素heights[j]位置错了，需要统计
                    if (heights[j++] != i) res++;
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
