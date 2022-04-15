package com.frankcooper.bank.week;

import java.util.*;

import org.junit.Assert;

public class Week262 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        public List<Integer> twoOutOfThree(int[] nums1, int[] nums2, int[] nums3) {
            Set<Integer> set1 = new HashSet();
            for (int x : nums1) set1.add(x);
            Set<Integer> set2 = new HashSet();
            for (int x : nums2) set2.add(x);
            Set<Integer> set3 = new HashSet();
            for (int x : nums3) set3.add(x);
            List<Integer> res = new ArrayList<>();
            for (int i = 1; i <= 100; i++) {
                int cnt = 0;
                if (set1.contains(i)) cnt++;
                if (set2.contains(i)) cnt++;
                if (set3.contains(i)) cnt++;

                if (cnt >= 2) res.add(i);
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
