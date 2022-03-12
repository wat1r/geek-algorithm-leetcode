package com.frankcooper.bank._1501_2000;

import com.frankcooper.utils.PrintUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

/*
import java.util.*;
import org.junit.Assert;
*/
public class _1738 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[][] matrix = PrintUtils.processSymbol("[[5,2],[1,6]]");
            int k = 1;
            handler.kthLargestValue(matrix, k);
        }

        public int kthLargestValue(int[][] matrix, int k) {
            int R = matrix.length, C = matrix[0].length;
            Integer[] arr = new Integer[R * C];
            Arrays.fill(arr, 0);
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    int t = i * C + j;
                    arr[t] ^= matrix[i][j];
                    if (i - 1 >= 0) {
                        int up = (i - 1) * C + j;
                        arr[t] ^= arr[up];
                    }
                    if (j - 1 >= 0) {
                        int left = i * C + j - 1;
                        arr[t] ^= arr[left];
                    }
                    if (i - 1 >= 0 && j - 1 >= 0) {
                        int corner = (i - 1) * C + j - 1;
                        arr[t] ^= arr[corner];
                    }
                }
            }
            Arrays.sort(arr, Collections.reverseOrder());
            for (int i = 0; i < arr.length; i++) {
                if (i == k - 1) return arr[i];
            }
            return 0;
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }


        public int kthLargestValue(int[][] matrix, int k) {
            int R = matrix.length, C = matrix[0].length;
            int[][] arr = new int[R + 1][C + 1];
            PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);
            for (int i = 1; i <= R; i++) {
                for (int j = 1; j <= C; j++) {
                    arr[i][j] = arr[i - 1][j - 1] ^ arr[i - 1][j] ^ arr[i][j - 1] ^ matrix[i - 1][j - 1];
                    pq.offer(arr[i][j]);
                }
            }
            while (k-- > 1) pq.poll();
            return pq.peek();
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
