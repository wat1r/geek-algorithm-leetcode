package com.frankcooper.bank._1001_1500;

import java.util.*;

import org.junit.Assert;

public class _1588 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }

        public int sumOddLengthSubarrays(int[] arr) {
            int n = arr.length;
            int[] preSum = new int[n + 1];
            for (int i = 0; i < n; i++) {
                preSum[i + 1] = preSum[i] + arr[i];
            }
            int res = 0;
            for (int i = 0; i < n; i++) {
                for (int k = 1; i + k <= n; k += 2) {
                    res += preSum[i + k] - preSum[i];
                }
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
