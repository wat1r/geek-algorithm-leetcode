package com.frankcooper.platform.leetcode.bank._501_600;

/*import java.util.*;
import org.junit.Assert;*/
public class _585 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        /**
         * SELECT
         *     SUM(insurance.TIV_2016) AS TIV_2016
         * FROM
         *     insurance
         * WHERE
         *     insurance.TIV_2015 IN
         *     (
         *       SELECT
         *         TIV_2015
         *       FROM
         *         insurance
         *       GROUP BY TIV_2015
         *       HAVING COUNT(*) > 1
         *     )
         *     AND CONCAT(LAT, LON) IN
         *     (
         *       SELECT
         *         CONCAT(LAT, LON)
         *       FROM
         *         insurance
         *       GROUP BY LAT , LON
         *       HAVING COUNT(*) = 1
         *     )
         */
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
