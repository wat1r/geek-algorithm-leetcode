package com.frankcooper.platform.leetcode.bank._301_400;

/*import java.util.*;
import org.junit.Assert;*/
public class _400 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            handler.findNthDigit(365);

        }


        public int findNthDigit(int n) {
            long num = n;
            long size = 1;
            long max = 9;
            while (n > 0) {
                long remain = num - max * size;
                if (remain > 0) {
                    num = remain;
                    size++;
                    max *= 10;
                } else {
                    long cnt = num / size;
                    long idx = num % size;
                    if (idx == 0) {
                        return (int) (((long) Math.pow(10, size - 1) + cnt - 1) % 10);
                    } else {
                        return (int) (((long) Math.pow(10, size - 1) + cnt) / ((long) Math.pow(10, (size - idx))) % 10);
                    }
                }
            }
            return 0;

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
