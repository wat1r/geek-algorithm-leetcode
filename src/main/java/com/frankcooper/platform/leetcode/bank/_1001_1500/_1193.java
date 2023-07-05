package com.frankcooper.platform.leetcode.bank._1001_1500;

/*import java.util.*;
import org.junit.Assert;*/
public class _1193 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        /**
         * select DATE_FORMAT(trans_date , '%Y-%m') as month  ,
         * country ,
         * count(id) as trans_count ,
         * sum(if(state ='approved',1,0)) as approved_count  ,
         * sum(amount) trans_total_amount ,
         * sum(if(state ='approved',amount,0)) approved_total_amount
         * from Transactions
         * group by DATE_FORMAT(trans_date , '%Y-%m')  ,country
         * order by trans_date  asc
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
