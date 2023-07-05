package com.frankcooper.platform.leetcode.bank._1001_1500;

/*import java.util.*;
import org.junit.Assert;*/
public class _1341 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        /**
         * (select b.name as results
         * from MovieRating a
         * join Users b
         * on a.user_id = b.user_id
         * group by a.user_id
         * order by count(a.user_id) desc, b.name
         * limit 1 )
         * union all
         * (
         *   # 2020年2月份平均评分最高且字典序较小的电影名
         *     select
         *         Movies.title as results
         *     FROM MovieRating
         *         JOIN Movies ON MovieRating.movie_id = Movies.movie_id
         *     WHERE
         *         MovieRating.created_at >= '2020-02-01'
         *         AND MovieRating.created_at < '2020-03-01'
         *     GROUP BY MovieRating.movie_id
         *     ORDER BY
         *         avg(MovieRating.rating) desc,
         *         Movies.title
         *     LIMIT 1
         * )
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
