package com.frankcooper.platform.leetcode.bank._1001_1500;

/*import java.util.*;
import org.junit.Assert;*/
public class _1374 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }


        public String generateTheString(int n) {
            StringBuilder res = new StringBuilder();
            if (n % 2 == 1) {
                while (n-- > 0) res.append("a");
            } else {
                res.append("a");
                --n;
                while (n-- > 0) res.append("b");
            }
            return res.toString();
        }


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
