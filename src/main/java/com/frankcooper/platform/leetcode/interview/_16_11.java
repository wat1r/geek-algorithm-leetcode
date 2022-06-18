package com.frankcooper.platform.leetcode.interview;

import java.util.*;

public class _16_11 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        /**
         * f(i) = shorter * (k - i) + longer * i
         * = shorter * k + (longer - shorter) * i
         *
         * @param shorter
         * @param longer
         * @param k
         * @return
         */
        public int[] divingBoard(int shorter, int longer, int k) {
            if (k == 0) return new int[]{};
            if (shorter == longer) return new int[]{k * shorter};
            int[] ans = new int[k + 1];
            for (int i = 0; i <= k; i++) {
                ans[i] = shorter * k + (longer - shorter) * i;
            }
            return ans;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        public int[] divingBoard(int shorter, int longer, int k) {
            if (k == 0) return new int[]{};
            if (shorter == longer) return new int[]{k * shorter};
            int[] ans = new int[k + 1];
            Arrays.fill(ans, shorter * k);
            for (int i = 0; i <= k; i++) ans[i] += i * (longer - shorter);
            return ans;
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
