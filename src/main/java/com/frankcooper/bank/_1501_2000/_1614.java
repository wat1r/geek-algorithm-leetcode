package com.frankcooper.bank._1501_2000;

/*import java.util.*;
import org.junit.Assert;*/
public class _1614 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        public int maxDepth(String s) {
            int res = 0, cnt = 0;
            for (char c : s.toCharArray()) {
                if (c == '(') cnt++;
                else if (c == ')') cnt--;
                res = Math.max(res, cnt);
            }
            return res;
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
