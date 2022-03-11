package com.frankcooper.bank.bi_weekly;

import java.util.*;

import com.frankcooper.utils.PrintUtils;
import org.junit.Assert;

public class BiWeek53 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
//            Assert.assertEquals(1, handler.countGoodSubstrings("xyzzaz"));
            Assert.assertEquals(4, handler.countGoodSubstrings("aababcabc"));

        }


        public int countGoodSubstrings(String s) {
            int res = 0;
            if (s.length() == 0 || s.length() < 3) return res;
            char x = ' ', y = ' ', z = ' ';
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (i == 0) x = c;
                if (i == 1) y = c;
                if (i == 2) z = c;
                if (i >= 3 && x != y && y != z && x != z) {
//                    System.out.printf("%s%s%s\n", x, y, z);
                    res++;
                }
                if (i >= 3) {
                    x = y;
                    y = z;
                    z = c;
                }
            }
            if (x != y && y != z && x != z) res++;
            return res;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }


        public int minPairSum(int[] nums) {
            Arrays.sort(nums);
            int res = 0;
            int l = 0, r = nums.length - 1;
            while (l < r) {
                res = Math.max(res, nums[l++] + nums[r--]);
            }
            return res;
        }

    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();

            int[][] grid = PrintUtils.processSymbol("[[3,4,5,1,3],[3,3,4,2,3],[20,30,200,40,10],[1,5,5,4,1],[4,3,2,2,5]]");
            handler.getBiggestThree(grid);
        }


        public int[] getBiggestThree(int[][] grid) {

            PriorityQueue<Integer> pq = new PriorityQueue<>(3, (a, b) -> b - a);
            int m = grid.length, n = grid[0].length;
            int c = (Math.min(m, n) + 1) / 2;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    for (int k = 0; k <= c && i + k < m && j - k >= 0 && j + k < n; k++) {
                        int sum = 0;
                        if (k == 0) {
                            pq.offer(grid[i][j]);
                            continue;
                        }
                        int x = i, y = j, t = 0;
                        System.out.printf("x:%d,y:%d,c:%d\n", x, y, c);
                        sum += grid[x][y];
                        while (t < k) {
                            sum += grid[++x][--y];
                            t++;
                        }
//                        t = k;
                        while (t > 0) {
                            sum += grid[++x][++y];
                            t--;
                        }
                        while (t < k) {
                            sum += grid[--x][++y];
                            t++;
                        }
//                        t = k;
                        while (t > 0) {
                            if (x - 1 != i && y - 1 != j) sum += grid[--x][--y];
                            t--;
                        }
                        pq.offer(sum);

                    }
                }
            }

            return null;
        }


//        private boolean inArea(int i, int j, int m, int n, int c) {
//            return (i+c)<m && j
//        }

    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
