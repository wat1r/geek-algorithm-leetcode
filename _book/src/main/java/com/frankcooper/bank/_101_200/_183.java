package com.frankcooper.platform.leetcode.bank._101_200;

/*import java.util.*;
import org.junit.Assert;*/
public class _183 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }

        /**
         * 方法1
         * select Name Customers from Customers where Id not in(select CustomerId from Orders)
         * 方法2
         * select a.Name as Customers
         * from Customers as a
         * left join Orders as b
         * on a.Id=b.CustomerId
         * where b.CustomerId is null;
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
