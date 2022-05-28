package com.frankcooper.bank._901_1000;

import java.util.*;

import org.junit.Assert;

public class _908 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        public int smallestRangeI(int[] nums, int k) {
            int minn = 10010, maxx = -10010;
            for (int x : nums) {
                minn = Math.min(minn, x);
                maxx = Math.max(maxx, x);
            }
            return maxx - minn <= 2 * k ? 0 : maxx - minn - 2 * k;
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        public int smallestRangeI(int[] nums, int k) {
            int minn = Arrays.stream(nums).min().getAsInt();
            int maxx = Arrays.stream(nums).max().getAsInt();
            return Math.max(0, maxx - minn - 2 * k);
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
