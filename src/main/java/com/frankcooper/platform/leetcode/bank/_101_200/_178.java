package com.frankcooper.platform.leetcode.bank._101_200;

/*import java.util.*;
import org.junit.Assert;*/
public class _178 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


//        https://leetcode-cn.com/problems/rank-scores/

        /**
         * `Rank` 注意这个关键字
         *
         *SELECT a.Score AS Score
         * 	,(
         * 		SELECT count(DISTINCT b.Score)
         * 		FROM Scores b
         * 		WHERE b.Score >= a.Score
         * 		) AS `Rank`
         * FROM Scores a
         * ORDER BY a.Score DESC
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
