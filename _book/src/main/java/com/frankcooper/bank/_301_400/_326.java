package com.frankcooper.platform.leetcode.bank._301_400;

/*import java.util.*;
import org.junit.Assert;*/
public class _326 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        public boolean isPowerOfThree(int n) {
            if (n <= 0) return false;
            while (n % 3 == 0) {
                n /= 3;
            }
            return n == 1;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        public boolean isPowerOfThree(int n) {
            if (n <= 0 || n == 2) return false;
            if (n == 1) return true;
            return isPowerOfThree(n / 3) && n % 3 == 0;
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }

        public boolean isPowerOfThree(int n) {
            if (n == 0 || n == 2) return false;
            if (n == 1) return true;
            return n % 3 == 0 ? isPowerOfThree(n / 3) : false;
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }

        public boolean isPowerOfThree(int n) {
            if (n <= 0) return false;
            while (n % 3 == 0) n /= 3;
            return n == 1;
        }
    }
}
