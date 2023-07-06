package com.frankcooper.platform.leetcode.bank._1001_1500;

/*import java.util.*;
import org.junit.Assert;*/
public class _1070 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        /**
         * select customer_id
         * from Customer
         * group by customer_id
         * having count(distinct product_key ) in
         * (select count(distinct product_key ) from Product )
         */


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        /**
         * select
         *     product_id,
         *     year first_year,
         *     quantity,
         *     price
         * from Sales
         * where (product_id,year)
         * in (select product_id,min(year) from Sales group by product_id)
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
