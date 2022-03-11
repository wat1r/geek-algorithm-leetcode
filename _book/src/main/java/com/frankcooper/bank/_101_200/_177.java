package com.frankcooper.bank._101_200;

/*import java.util.*;
import org.junit.Assert;*/
public class _177 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        /**
         *
         * 类似176题，推广到一般情况
         * CREATE FUNCTION getNthHighestSalary (N INT)
         * RETURNS INT
         *
         * BEGIN
         * 	SET N = N - 1;
         *
         * 	RETURN (
         * 			# Write your MySQL query statement below.SELECT (
         * 					SELECT Salary
         * 					FROM Employee
         * 					GROUP BY Salary
         * 					ORDER BY Salary DESC limit 1 offset N
         * 					) SecondHighestSalary
         * 			);
         * END
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
