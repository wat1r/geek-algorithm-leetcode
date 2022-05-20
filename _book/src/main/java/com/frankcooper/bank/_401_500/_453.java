package com.frankcooper.bank._401_500;

import java.util.*;

import org.junit.Assert;

public class _453 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }

        public int minMoves(int[] nums) {
            int minn = Integer.MAX_VALUE;
            for (int x : nums) minn = Math.min(minn, x);
            int res = 0;
            for (int x : nums) res += x - minn;
            return res;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        public int minMoves(int[] nums) {
            int minn = (int) 1e9 + 10;
            for (int x : nums) minn = Math.min(minn, x);
            int res = 0;
            for (int x : nums) res += x - minn;
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
