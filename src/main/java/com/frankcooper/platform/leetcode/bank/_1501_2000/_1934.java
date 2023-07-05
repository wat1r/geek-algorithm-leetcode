package com.frankcooper.platform.leetcode.bank._1501_2000;

/*import java.util.*;
import org.junit.Assert;*/
public class _1934 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        /**
         *
         select a.user_id ,
         ifnull( round( sum( b.action = 'confirmed' ) / count( b.action ), 2 ), 0.00 ) AS confirmation_rate
         from Signups a
         left join Confirmations b
         on a.user_id= b.user_id
         group by a.user_id
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
