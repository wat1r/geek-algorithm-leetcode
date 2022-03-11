package com.frankcooper.swordoffer;

import java.util.*;

import com.frankcooper.utils.PrintUtils;
import org.junit.Assert;

public class Sword_56_II {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int x = 10;
            PrintUtils.toBinaryString(x, 8);
            PrintUtils.toBinaryString(x ^ 1, 8);
            System.out.printf("%d\n", x ^ 1);
            PrintUtils.toBinaryString(~x, 8);
            System.out.printf("%d\n", ~x);


        }


        public int singleNumber(int[] nums) {
            int one = 0, two = 0;
            for (int x : nums) {
                one = one ^ x & ~two;
                two = two ^ x & ~one;
            }
            return one;
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }


        public int singleNumber(int[] nums) {
            int[] counts = new int[32];
            for (int num : nums) {
                for (int j = 0; j < 32; j++) {
                    counts[j] += num & 1;
                    num >>>= 1;
                }
            }
            int res = 0, m = 3;
            for (int i = 0; i < 32; i++) {
                res <<= 1;
                res |= counts[31 - i] % m;
            }
            return res;
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
