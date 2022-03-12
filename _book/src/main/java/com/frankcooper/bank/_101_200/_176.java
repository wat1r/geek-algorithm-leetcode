package com.frankcooper.bank._101_200;

/*import java.util.*;
import org.junit.Assert;*/
public class _176 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        /**
         * 下面的子查询，是为了防止没有第二大元素的情况下返回null
         *需要去重 distinct
         *SELECT (
         * 		SELECT DISTINCT Salary
         * 		FROM Employee
         * 		ORDER BY Salary DESC limit 1 offset 1
         * 		) SecondHighestSalary
         */

        /**
         * 也可以
         *SELECT (
         * 		SELECT Salary
         * 		FROM Employee
         * 		GROUP BY Salary
         * 		ORDER BY Salary DESC limit 1 offset 1
         * 		) SecondHighestSalary
         */


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        /**
         * 使用ifnull:
         * SELECT ifnull((
         * 			SELECT Salary
         * 			FROM Employee
         * 			GROUP BY Salary
         * 			ORDER BY Salary DESC limit 1 offset 1
         * 			), NULL) SecondHighestSalary
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
