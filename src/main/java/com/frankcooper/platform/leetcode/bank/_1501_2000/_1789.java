package com.frankcooper.platform.leetcode.bank._1501_2000;

/*import java.util.*;
import org.junit.Assert;*/
public class _1789 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        /**
         * SELECT employee_id AS 'employee_id',
         *     department_id AS 'department_id'
         * FROM Employee
         * WHERE primary_flag = 'Y'
         * UNION               #联合查询，自动去重
         * SELECT employee_id AS 'employee_id',
         *     department_id AS 'department_id'
         * FROM Employee
         * GROUP BY employee_id
         * HAVING COUNT(department_id) = 1
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
