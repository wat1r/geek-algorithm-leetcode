package com.frankcooper.platform.leetcode.bank._1001_1500;

/*import java.util.*;
import org.junit.Assert;*/
public class _1164 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        /**
         * with t as (select  * from (select * ,
         * rank() over (partition by product_id order by change_date desc, new_price desc) rk
         * from
         * Products
         * where change_date <='2019-08-16' ) a where a.rk<=1)
         *
         * select
         * # *
         * product_id, if(change_date <='2019-08-16',new_price,10) price
         * from
         * t
         * union all
         * select  distinct product_id, 10 price
         * from
         * Products
         * where product_id not in (select product_id from t )
         */

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        /**
         * select p1.product_id, ifnull(p2.new_price,10) as price
         * from (select distinct product_id from products) p1
         * left join
         * (select product_id , new_price
         * from
         * products
         * where (product_id, change_date) in (
         *  select product_id, max(change_date)
         *     from products
         *     where change_date <= '2019-08-16'
         *     group by product_id )
         * ) p2
         * on p1.product_id = p2.product_id
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
