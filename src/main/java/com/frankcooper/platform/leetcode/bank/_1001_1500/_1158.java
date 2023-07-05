package com.frankcooper.platform.leetcode.bank._1001_1500;

/*import java.util.*;
import org.junit.Assert;*/
public class _1158 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        /*
        select user_id buyer_id,join_date ,ifnull(cnt , 0) orders_in_2019
        from
        (select *
        from Users a
        left join
        (select buyer_id, count(order_id) cnt
        from Orders
        where  year(order_date) = '2019'
        group by buyer_id ) b
        on a.user_id = b.buyer_id) t
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
