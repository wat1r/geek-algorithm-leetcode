package com.frankcooper.bank._101_200;

import java.util.*;

import org.junit.Assert;

public class _180 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        /**
         *
         * 好理解的一种方式，三表
         *select distinct a.num  ConsecutiveNums from
         *
         * Logs a,
         * Logs b,
         * Logs c
         * where
         * a.id = b.id-1
         * and
         * b.id = c.id-1
         * and
         * a.num = b.num
         * and
         * b.num = c.num
         */


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        /**
         * 窗口函数
         *
         * SELECT DISTINCT Num ConsecutiveNums
         * FROM(
         * SELECT *,
         *       ROW_NUMBER() OVER (PARTITION BY Num ORDER BY Id) rownum,
         *       ROW_NUMBER() OVER (ORDER BY Id) id2
         * FROM LOGS
         * ) t
         * GROUP BY (id2-rownum),Num
         * HAVING COUNT(*)>=3
         */
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
