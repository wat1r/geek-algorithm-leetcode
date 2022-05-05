package com.frankcooper.bank._1501_2000;

/*import java.util.*;
import org.junit.Assert;*/
public class _1672 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        public int maximumWealth(int[][] accounts) {
            int res = 0;
            for (int i = 0; i < accounts.length; i++) {
                int sum = 0;
                for (int j = 0; j < accounts[0].length; j++) {
                    sum += accounts[i][j];
                }
                res = Math.max(res, sum);
            }
            return res;
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
