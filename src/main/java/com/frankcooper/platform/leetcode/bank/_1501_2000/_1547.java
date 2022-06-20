package com.frankcooper.platform.leetcode.bank._1501_2000;

import java.util.Arrays;

/*import java.util.*;
import org.junit.Assert;*/
public class _1547 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        public int minCost(int n, int[] _cuts) {
            Arrays.sort(_cuts);
            int m = _cuts.length;
            int[] cuts = new int[m + 2];
            cuts[0] = 0;
            System.arraycopy(_cuts, 0, cuts, 1, m);
            cuts[m + 1] = n;
            int[][] f = new int[m + 2][m + 2];
            for (int i = m; i >= 1; i--) {
                for (int j = i; j <= m; j++) {
                    f[i][j] = (i == j ? 0 : Integer.MAX_VALUE);
                    for (int k = i; k <= j; k++) {
                        f[i][j] = Math.min(f[i][j], f[i][k - 1] + f[k + 1][j]);
                    }
                    f[i][j] += cuts[j + 1] - cuts[i - 1];
                }
            }
            return f[1][m];
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
