package com.frankcooper.bank._1501_2000;

import java.util.*;

import com.frankcooper.utils.PrintUtils;
import org.junit.Assert;

public class _1744 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

            int[] candiesCount = {7, 4, 5, 3, 8};
            int[][] queries = PrintUtils.processSymbol("[[0,2,2],[4,2,4],[2,13,1000000000]]");
//            handler.canEat(candiesCount, queries);
            candiesCount = new int[]{7, 4, 5, 3, 8};
            queries = PrintUtils.processSymbol("[[0,2,2],[4,2,4],[2,13,1000000000]]");
            handler.canEat(candiesCount, queries);

        }

        public boolean[] canEat(int[] candiesCount, int[][] queries) {
            int n = queries.length;
            boolean[] ans = new boolean[n];
            int m = candiesCount.length;
            long[] preSum = new long[m];
            preSum[0] = candiesCount[0];
            for (int i = 1; i < m; i++) preSum[i] = preSum[i - 1] + candiesCount[i];
            int idx = 0;
            for (int[] q : queries) {
                int type = q[0], day = q[1] + 1, cap = q[2];
                long lower = (type > 0 ? preSum[type - 1] : 0) / cap + 1;
                long upper = preSum[type];
                ans[idx++] = (day >= lower && day <= upper);

            }
            return ans;
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
