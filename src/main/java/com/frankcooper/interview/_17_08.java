package com.frankcooper.interview;

import java.util.*;

import org.junit.Assert;

public class _17_08 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        public int bestSeqAtIndex(int[] height, int[] weight) {

            int n = height.length;
            int[][] arr = new int[n][2];//[0] 存height， [1]存 weight
            for (int i = 0; i < n; i++) arr[i] = new int[]{height[i], weight[i]};
            //按height升序，height相同时按weight降序
            Arrays.sort(arr, (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);


            return 0;
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
