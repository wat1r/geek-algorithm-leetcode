package com.frankcooper.platform.leetcode.bank._601_700;

/*import java.util.*;
import org.junit.Assert;*/
public class _602 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        /**
         * select ids as id , cnt as num
         * from
         * (select ids , count(*) cnt
         * from
         * ( select requester_id as ids from RequestAccepted
         *         union all
         * select accepter_id from RequestAccepted ) as a
         * group by ids) as b
         * order by cnt  desc
         * limit 1
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
