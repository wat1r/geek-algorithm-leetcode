package com.frankcooper.bank._1001_1500;

import java.util.*;

import com.frankcooper.utils.PrintUtils;
import org.junit.Assert;

public class _1337 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

            int[][] mat = PrintUtils.processSymbol("[[1,1,0,0,0],[1,1,1,1,0],[1,0,0,0,0],[1,1,0,0,0],[1,1,1,1,1]]");
            int k = 3;
            Assert.assertArrayEquals(new int[]{2, 0, 3}, handler.kWeakestRows(mat, k));
        }


        public int[] kWeakestRows(int[][] mat, int k) {
            int m = mat.length, n = mat[0].length;
            int[] sum = new int[m];
            PriorityQueue<int[]> pq = new PriorityQueue<>(k, (a, b) -> (b[1] - a[1]) == 0 ? b[0] - a[0] : b[1] - a[1]);
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) sum[i] += mat[i][j];
                if (!pq.isEmpty() && pq.peek()[1] > sum[i]) {
                    pq.poll();
                }
                pq.offer(new int[]{i, sum[i]});
                if (!pq.isEmpty() && pq.size() > k) pq.poll();
            }
            int[] ans = new int[k];
            while (!pq.isEmpty() && k > 0) {
                --k;
                ans[k] = pq.poll()[0];
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
