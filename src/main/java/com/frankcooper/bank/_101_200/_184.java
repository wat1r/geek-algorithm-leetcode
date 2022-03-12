package com.frankcooper.bank._101_200;

/*import java.util.*;
import org.junit.Assert;*/
public class _184 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }

/**
 * SELECT
 *     Department.name AS 'Department',
 *     Employee.name AS 'Employee',
 *     Salary
 * FROM
 *     Employee
 *         JOIN
 *     Department ON Employee.DepartmentId = Department.Id
 * WHERE
 *     (Employee.DepartmentId , Salary) IN
 *     (   SELECT
 *             DepartmentId, MAX(Salary)
 *         FROM
 *             Employee
 *         GROUP BY DepartmentId
 * 	)
 * ;
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
