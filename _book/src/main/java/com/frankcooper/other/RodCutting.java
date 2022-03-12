package com.frankcooper.other;

public class RodCutting {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[] lengths = {1, 2, 3, 4, 5};//每一根木棒的长度
            int[] prices = {2, 6, 7, 10, 13};//每一根木棒的利润
            int rodlength = 5;
            System.out.println(handler.rodCutting(lengths, prices, rodlength));

        }


        Integer[][] cache;
        int[] lengths;
        int[] prices;

        public int rodCutting(int[] lengths, int[] prices, int rodlength) {
            this.lengths = lengths;
            this.prices = prices;
            cache = new Integer[lengths.length][rodlength + 1];//
            return helper(rodlength, 0);
        }


        private int helper(int rodlength, int curIdx) {
            if (curIdx >= lengths.length) return 0;
            if (cache[curIdx][rodlength] != null) return cache[curIdx][rodlength];
            int choose = 0;//选
            if (lengths[curIdx] <= rodlength) {
                choose = prices[curIdx] + helper(rodlength - lengths[curIdx], curIdx);
            }
            int non_choose = helper(rodlength, curIdx + 1);//不选
            return cache[curIdx][rodlength] = Math.max(choose, non_choose);
        }


    }

    static class _2nd {

        public static void main(String[] args) {
            _2nd handler = new _2nd();
            int[] lengths = {1, 2, 3, 4, 5};//每一根木棒的长度
            int[] prices = {2, 6, 7, 10, 13};//每一根木棒的利润
            int rodlength = 5;
            System.out.println(handler.rodCutting(lengths, prices, rodlength));
        }


        public int rodCutting(int[] lengths, int[] prices, int n) {
            int m = lengths.length;
            int[][] f = new int[m][n + 1];
            for (int i = 0; i < m; i++) {
                for (int j = 1; j <= n; j++) {
                    int choose = 0, non_choose = 0;
                    if (lengths[i] <= j) choose = prices[i] + f[i][j - lengths[i]];
                    if (i > 0) non_choose = f[i - 1][j];
                    f[i][j] = Math.max(choose, non_choose);
                }
            }
            return f[m - 1][n];
        }


    }


}
