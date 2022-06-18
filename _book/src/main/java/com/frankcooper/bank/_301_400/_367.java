package com.frankcooper.platform.leetcode.bank._301_400;

/*import java.util.*;
import org.junit.Assert;*/
public class _367 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }


        public boolean isPerfectSquare(int num) {
            if (num == 1) return true;
            long l = 1, r = num / 2;
            while (l <= r) {//不需要判断l *  l的结果
                long mid = l + (r - l) / 2;
                long t = mid * mid;
                if (t == num) return true;
                else if (t > num) {
                    r = mid - 1;
                } else if (t < num) {
                    l = mid + 1;
                }
            }
            return false;

        }
    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        public boolean isPerfectSquare(int num) {
            if (num == 1) return true;
            long l = 1, r = num / 2;
            while (l < r) {//不需要判断l *  l的结果
                long mid = l + (r - l) / 2;
                long t = mid * mid;
                if (t == num) return true;
                else if (t > num) {
                    r = mid;
                } else if (t < num) {
                    l = mid + 1;
                }
            }
            return l * l == num;

        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }

        public boolean isPerfectSquare(int num) {
            if (num == 1) return true;
            long l = 1, r = num / 2;
            while (l < r) {//不需要判断l *  l的结果
                long mid = l + (r - l) / 2;
                long t = mid * mid;
                if (t == num) return true;
                else if (t > num) {
                    r = mid - 1;
                } else if (t < num) {
                    l = mid + 1;
                }
            }
            return l * l == num;

        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }


        public boolean isPerfectSquare(int num) {
            if (num == 1) return true;
            long l = 1, r = num >> 1;
            while (l < r) {
                long mid = l + (r - l) / 2;
                long t = mid * mid;
                if (t == num) return true;
                else if (t > num) r = mid;
                else l = mid + 1;
            }
            return l * l == num;
        }

    }
}
