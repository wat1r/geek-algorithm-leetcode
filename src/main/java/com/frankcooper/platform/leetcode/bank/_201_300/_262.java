package com.frankcooper.platform.leetcode.bank._201_300;

/*import java.util.*;
import org.junit.Assert;*/
public class _262 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }
        //不能使用left join
        /**
         * select  T.request_at as 'Day',
         * round(sum(if(T.STATUS ='completed',0,1))/
         * count(T.STATUS),2) as `Cancellation Rate`
         * from Trips T
         *  join Users U1 on T.client_id = U1.users_id  and U1.banned ='NO'
         * join Users U2 on T.driver_id = U2.users_id  and U2.banned ='NO'
         * WHERE T.request_at BETWEEN '2013-10-01' AND '2013-10-03'
         * group by T.request_at
         * order by T.request_at  asc
         */


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }
        /**
         * SELECT T.request_at AS `Day`,
         * 	ROUND(
         * 			SUM(
         * 				IF(T.STATUS = 'completed',0,1)
         * 			)
         * 			/
         * 			COUNT(T.STATUS),
         * 			2
         * 	) AS `Cancellation Rate`
         * FROM trips AS T LEFT JOIN
         * (
         * 	SELECT users_id
         * 	FROM users
         * 	WHERE banned = 'Yes'
         * ) AS A ON (T.Client_Id = A.users_id)
         * LEFT JOIN (
         * 	SELECT users_id
         * 	FROM users
         * 	WHERE banned = 'Yes'
         * ) AS A1
         * ON (T.Driver_Id = A1.users_id)
         * WHERE A.users_id IS NULL AND A1.users_id IS NULL AND T.request_at BETWEEN '2013-10-01' AND '2013-10-03'
         * GROUP BY T.request_at
         *
         * 作者：jason
         * 链接：https://leetcode.cn/problems/trips-and-users/solutions/20037/san-chong-jie-fa-cong-nan-dao-yi-zong-you-gua-he-n/
         * 来源：力扣（LeetCode）
         * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
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
