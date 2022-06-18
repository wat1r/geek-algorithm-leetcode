package com.frankcooper.platform.leetcode.bank._201_300;

import java.util.*;

public class _274 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        public int hIndex(int[] citations) {
            Arrays.sort(citations);
            int h = 0, i = citations.length - 1;
            while (i >= 0 && citations[i] > h) {
                i--;
                h++;
            }
            return h;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }


        class Solution {
            public int hIndex(int[] citations) {
                int n = citations.length;
                int[] arr = new int[n + 1];
                for (int i = 0; i < n; i++) {
                    arr[Math.min(citations[i], n)]++;
                }
                int h = n;
                for (int s = arr[n]; h > s; s += arr[h]) {
                    h--;
                }
                return h;
            }
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
