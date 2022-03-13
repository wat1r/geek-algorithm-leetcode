package com.frankcooper.bank._1_100;

/*import java.util.*;
import org.junit.Assert;*/
public class _8 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        public int myAtoi(String str) {
            str = str.trim();
            if (str == null || str.length() == 0) return 0;
            char firstChar = str.charAt(0);
            int sign = 1, start = 0;
            long res = 0;
            if (firstChar == '+') {
                sign = 1;
                start++;
            } else if (firstChar == '-') {
                sign = -1;
                start++;
            }
            for (int i = start; i < str.length(); i++) {
                if (!Character.isDigit(str.charAt(i))) return (int) (res * sign);
                res = res * 10 + str.charAt(i) - '0';
                if (sign == 1 && res > Integer.MAX_VALUE) return Integer.MAX_VALUE;
                if (sign == -1 && res > Integer.MAX_VALUE) return Integer.MIN_VALUE;
            }
            return (int) (res * sign);
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
