package com.frankcooper.bank._401_500;

import java.util.*;

import org.junit.Assert;

public class _476 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            handler.findComplement(5);
        }


        public int findComplement(int num) {
            int cnt = count(num);
            int res = 0;
            System.out.printf("%d\n", cnt);
            for (int i = 0; i < 32; i++) {
                if (cnt == 0) return res;//到达最高位的1的时候，结束
                if ((num & 1) == 1) cnt--;
                else res |= 1 << i;
                num >>= 1;
                System.out.printf("%d\n", num);
            }
            return res;
        }

        public int count(int x) {
            int res = 0;
            while (x != 0) {
                res += x & 1;
                x >>>= 1;
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
