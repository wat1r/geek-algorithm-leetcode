package com.frankcooper.nowcoder.swordoffer;

/*import java.util.*;
import org.junit.Assert;*/
public class JZ63 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        //空间压缩DP
        public int maxProfit(int[] prices) {
            if (prices == null || prices.length == 0) return 0;
            //f_0是当天手里无股票， f_1是当天手里有股票
            int f_0 = 0, f_1 = -prices[0];
            int n = prices.length;
            for (int i = 0; i < n; i++) {
                f_0 = Math.max(f_0, f_1 + prices[i]);
                f_1 = Math.max(f_1, -prices[i]);
            }
            return f_0;
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
