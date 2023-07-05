package com.frankcooper.platform.leetcode.bank._1001_1500;

/*import java.util.*;
import org.junit.Assert;*/
public class _1321 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        /**
         * SELECT DISTINCT visited_on,
         *        sum_amount AS amount,
         *        ROUND(sum_amount/7, 2) AS average_amount
         * -- 以上是破解【绊子1】并计算平均值，少用一次窗口函数提高运行速度
         * FROM (
         *     SELECT visited_on,
         *        SUM(amount) OVER ( ORDER BY visited_on ROWS 6 PRECEDING ) AS sum_amount
         *     -- 以下是计算每天的金额总量，破解【绊子2】
         *     FROM (
         *         SELECT visited_on,
         *             SUM(amount) AS amount
         *         FROM Customer
         *         GROUP BY visited_on
         *          ) TT
         *      ) LL
         * -- 最后手动只要覆盖完整7天的数据，破解【绊子1】
         * WHERE DATEDIFF(visited_on, (SELECT MIN(visited_on) FROM Customer)) >= 6
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
