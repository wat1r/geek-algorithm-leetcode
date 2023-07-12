package com.frankcooper.platform.leetcode.bank.bi_weekly;

//import java.util.*;
//
//import com.frankcooper.utils.PrintUtils;
//import org.junit.Assert;

import java.util.Arrays;

public class BiWeek105 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }

        public int buyChoco(int[] prices, int money) {
            Arrays.sort(prices);
            int t = prices[0] + prices[1];
            return t <= money ? money - t : money;
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
