package com.frankcooper.interview;

import java.util.*;

import org.junit.Assert;

public class _17_24 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        public int[] getMaxMatrix(int[][] mat) {
            int[] res = new int[4];
            int N = mat.length, M = mat[0].length;
            int[] b = new int[M];
            int maxv = Integer.MIN_VALUE;
            int sum = 0;
            int r1 = 0, c1 = 0;
            for (int i = 0; i < N; i++) {
                Arrays.fill(b, 0);
                for (int j = i; j < N; j++) {
                    sum = 0;
                    for (int k = 0; k < M; k++) {
                        b[k] += mat[j][k];
                        if (sum > 0) {
                            sum += b[k];
                        } else {
                            sum = b[k];
                            r1 = i;
                            c1 = k;
                        }
                        if (sum > maxv) {
                            maxv = sum;
                            res[0] = r1;
                            res[1] = c1;
                            res[2] = j;
                            res[3] = k;
                        }

                    }
                }
            }

            return res;
        }


    }

    static class _2nd {



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
