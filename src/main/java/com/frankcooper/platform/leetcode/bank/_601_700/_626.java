package com.frankcooper.platform.leetcode.bank._601_700;

/*import java.util.*;
import org.junit.Assert;*/
public class _626 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        /**
         *
         select
         (case
         when MOD(a.id,2)!=0 and b.total !=id then id +1
         when MOD(a.id,2)!=0 and b.total =id then id
         else id -1
         end
         ) as id,
         student
         from
         Seat a,
         (select count(*) total from Seat ) b
         order by id asc
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
