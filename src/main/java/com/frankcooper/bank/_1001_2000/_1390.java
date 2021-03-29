package com.frankcooper.bank._1001_2000;

import java.util.*;

import org.junit.Assert;

public class _1390 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            Assert.assertEquals(handler.sumFourDivisors(new int[]{21, 4, 7}), 32);
        }


        /**
         * 借助 507 完美数 求因数的方法
         *
         * @param nums
         * @return
         */
        public int sumFourDivisors(int[] nums) {
            int res = 0;
            for (int num : nums) {
                int cnt = 0, sum = 0;
                for (int i = 1; i * i <= num; i++) {
                    if (num % i == 0) {
                        cnt++;
                        sum += i;
                        if (i * i != num) {
                            cnt++;
                            sum += num / i;
                        }
                    }
                }
                if (cnt == 4) res += sum;
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
