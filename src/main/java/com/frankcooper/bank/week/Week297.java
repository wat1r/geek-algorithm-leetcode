package com.frankcooper.bank.week;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

public class Week297 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        public double calculateTax(int[][] brackets, int income) {
            double pay = 0.0;
            for (int i = 0; i < brackets.length; i++) {
                if (income >= brackets[i][0]) {
                    pay += (brackets[i][0] - ((i > 0) ? brackets[i - 1][0] : 0)) * brackets[i][1] * 0.01;
                } else {
                    pay += (income - ((i > 0) ? brackets[i - 1][0] : 0)) * brackets[i][1] * 0.01;
                    break;
                }
            }
            return pay;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();

            int[][] grid = {{5, 3}, {4, 0}, {2, 1}};
            int[][] moveCost = {{9, 8}, {1, 5}, {10, 12}, {18, 6}, {2, 4}, {14, 3}};
            handler.minPathCost(grid, moveCost);
        }


        //TLE
        int m, n;
        int minSum = Integer.MAX_VALUE;
        int[][] grid;
        int[][] moveCost;

        public int minPathCost(int[][] grid, int[][] moveCost) {
            m = grid.length;
            n = grid[0].length;
            this.grid = grid;
            this.moveCost = moveCost;
            for (int c = 0; c < n; c++) {
                build(0, c, 0);
            }
            return minSum;

        }


        private void build(int x, int y, int sum) {
            if (minSum != Integer.MAX_VALUE) {
                if (sum >= minSum) return;
            }
            if (x == m - 1) {
                sum += grid[x][y];
                minSum = Math.min(minSum, sum);
                return;
            }
            sum += grid[x][y];
            for (int j = 0; j < n; j++) {
                int i = grid[x][y];
                build(x + 1, j, sum + moveCost[i][j]);
            }
        }


    }

    static class _2nd_1 {
        public static void main(String[] args) {
            _2nd_1 handler = new _2nd_1();

        }

        //动态规划
        public int minPathCost(int[][] grid, int[][] moveCost) {
            int m = grid.length, n = grid[0].length;
            int[][] dp = new int[m][n];//dp[i][j]表示grid[i][j]为结尾，累积的路径的最小代价
            for (int i = 0; i < m; i++) Arrays.fill(dp[i], Integer.MAX_VALUE);
            //初始化:第一行的路径
            for (int j = 0; j < n; j++) dp[0][j] = grid[0][j];
            //一般化
            for (int i = 1; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    for (int k = 0; k < n; k++) {
                        //从[i-1]行的每一个位置grid[i-1][k]跳到当前位置grid[i][j]
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][k] + moveCost[grid[i - 1][k]][j] + grid[i][j]);
                    }
                }
            }
            int res = Integer.MAX_VALUE;
            for (int j = 0; j < n; j++) res = Math.min(res, dp[m - 1][j]);
            return res;
        }


    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
            int[] cookies = new int[]{8, 15, 10, 20, 8};
            int k = 2;
//            handler.subsets(cookies);
            handler.distributeCookies(cookies, k);
            System.out.println();
        }

        int n;//cookies的大小
        int totalMax = Integer.MAX_VALUE;
        int k;//孩子的数量

        public int distributeCookies(int[] cookies, int k) {
            n = cookies.length;
            this.k = k;
            //让大的饼干在前面
            cookies = IntStream.of(cookies)          // 变为 IntStream
                    .boxed()           // 装盒变为 Stream<Integer>
                    .sorted(Comparator.reverseOrder()) // 按自然序相反排序
                    .mapToInt(Integer::intValue)       // 变为 IntStream
                    .toArray();        // 又变回 int[]
            int[] arr = new int[k];
            backtrace(0, arr, cookies);
            return totalMax;

        }


        /**
         * @param index   当前处理到的cookies数据的下标
         * @param arr     当前k个小孩，每人一个袋子，每个袋子里的饼干的总数
         * @param cookies 饼干的数组
         */
        private void backtrace(int index, int[] arr, int[] cookies) {
            if (index == n) {//处理的下标来到cookies数组的最后，说明这时候所有的饼干都分完了
                int maxx = 0;//记录当前这种分法下的最大饼干
                for (int x : arr) maxx = Math.max(maxx, x);
                totalMax = Math.min(totalMax, maxx);//全局的最小饼干数量
                return;
            }
            //统计当前的小孩还没有分到饼干的数量
            int empty = 0;
            for (int x : arr) {
                if (x == 0) empty++;
            }
            //剩余的可供分配的饼干的数量是 n-index个，但是要分饼干的小孩有empty个，不能保证剩下的小孩每人都分到饼干
            if (empty > n - index) return;
            for (int i = 0; i < k; i++) {
                //规定index为0的饼干即第一块饼干分配给第一个小孩
                if (i > 0 && index == 0) {
                    return;
                }
                //当前小孩的数量和上一个小孩的数量相等（饼干数量），此种结果重复了
                if (i > 0 && arr[i] == arr[i - 1]) continue;
                //当前小孩拿的饼干，比之前获取到的全局最小值还要大，说明当前的这种方案下，肯定不如totalMax的方案下的分配方式
                if (arr[i] + cookies[index] > totalMax) continue;
                arr[i] += cookies[index];
                backtrace(index + 1, arr, cookies);
                arr[i] -= cookies[index];
            }
        }


    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
