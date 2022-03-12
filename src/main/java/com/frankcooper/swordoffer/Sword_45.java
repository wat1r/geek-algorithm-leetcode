package com.frankcooper.swordoffer;

import java.util.*;

import org.junit.Assert;

public class Sword_45 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        public String minNumber(int[] nums) {
            String[] arr = new String[nums.length];
            for (int i = 0; i < nums.length; i++) arr[i] = String.valueOf(nums[i]);
            Arrays.sort(arr, (a, b) -> (a + b).compareTo(b + a));
            StringBuilder ans = new StringBuilder();
            for (String a : arr) ans.append(a);
            return ans.toString();
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
