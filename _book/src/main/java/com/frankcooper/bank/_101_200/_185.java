package com.frankcooper.platform.leetcode.bank._101_200;

/*import java.util.*;
import org.junit.Assert;*/
public class _185 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }



        /**
         *SELECT d.name AS 'Department', e1.name AS 'Employee' ,e1.salary AS 'Salary'
         * FROM Employee e1
         * LEFT JOIN Department d
         *     ON e1.departmentId = d.id
         * WHERE 3 >
         *     (SELECT count( DISTINCT e2.salary )
         *     FROM Employee e2
         *     WHERE e2.salary > e1.salary
         *             AND e1.departmentId = e2.departmentId )
         */


    }

    static class _2nd {
        /**
         * SELECT B.Name AS Department,
         *          A.Name AS Employee,
         *          A.Salary
         * FROM
         *     (SELECT DENSE_RANK()
         *         OVER (partition by DepartmentId
         *     ORDER BY  Salary desc) AS ranking,DepartmentId,Name,Salary
         *     FROM Employee) AS A
         * JOIN Department AS B
         *     ON A.DepartmentId=B.id
         * WHERE A.ranking<=3
         */

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
