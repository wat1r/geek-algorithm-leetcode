package com.frankcooper.platform.lintcode;

import java.util.*;

import org.junit.Assert;

public class _114 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        //
        public int uniquePaths(int m, int n) {
            int[][] f = new int[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (i == 0 || j == 0) {
                        f[i][j] = 1;
                    } else {
                        f[i][j] = f[i - 1][j] + f[i][j - 1];
                    }

                }
            }
            return f[m - 1][n - 1];
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
