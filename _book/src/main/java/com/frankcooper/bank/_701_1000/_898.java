package com.frankcooper.bank._701_1000;

import java.util.*;

import org.junit.Assert;

public class _898 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

            int[] arr = new int[]{1, 1, 2};
            Assert.assertEquals(3, handler.subarrayBitwiseORs(arr));


        }

        public int subarrayBitwiseORs(int[] arr) {

            Set<Integer> set = new HashSet<>();
            int prev = 0;
            for (int i = 0; i < arr.length; i++) {
                int cur = 0;
                for (int j = i; j >= 0; j--) {
                    cur |= arr[j];
                    set.add(cur);
                    if ((cur & prev) == prev) {
                        break;
                    }
                }
                prev |= arr[i];
            }
            return set.size();
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }


        public int subarrayBitwiseORs(int[] arr) {

            Set<Integer> set = new HashSet<>();
            for (int i = 0; i < arr.length; i++) {
                set.add(arr[i]);
                for (int j = i - 1; j >= 0; j--) {
                    if ((arr[j] | arr[i]) == arr[j]) break;
                    arr[j] |= arr[i];
                    set.add(arr[j]);
                }
            }
            return set.size();
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
