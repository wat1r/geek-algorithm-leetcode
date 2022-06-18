package com.frankcooper.platform.leetcode.bank._201_300;

/*import java.util.*;
import org.junit.Assert;*/
public class _231 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }

        public boolean isPowerOfTwo(int n) {
            if (n <= 0) return false;
            while (n > 1) {
                if (n % 2 == 0) {
                    n /= 2;
                } else {
                    return false;
                }
            }
            return true;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }


        //2的n次幂与 去掉一位后，除了当前位，其他位都会翻转
        public boolean isPowerOfTwo(int n) {
            return n > 0 && (n & (n - 1)) == 0;
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }

        public boolean isPowerOfTwo(int n) {
            if (n <= 0) return false;
            if (n == 1) return true;
            return (n % 2) == 0 && isPowerOfTwo(n / 2);
        }

    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }

        public boolean isPowerOfTwo(int n) {
            if (n <= 0) return false;
            while (n > 1) {
                if ((n & 1) == 1) return false;
                else n /= 2;
            }
            return true;
        }
    }
}
