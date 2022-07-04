package com.frankcooper.platform.leetcode.bank._1001_1500;

import java.util.*;

import org.junit.Assert;

public class _1200 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        public List<List<Integer>> minimumAbsDifference(int[] arr) {
            Arrays.sort(arr);
            List<List<Integer>> res = new ArrayList<>();
            int minn = Integer.MAX_VALUE;
            for (int i = 0; i < arr.length - 1; i++) {
                int d = arr[i + 1] - arr[i];
                if (d < minn) {
                    res.clear();
                    minn = d;
                    List<Integer> sub = new ArrayList<>();
                    sub.add(arr[i]);
                    sub.add(arr[i + 1]);
                    res.add(sub);
                } else if (d == minn) {
                    List<Integer> sub = new ArrayList<>();
                    sub.add(arr[i]);
                    sub.add(arr[i + 1]);
                    res.add(sub);
                }
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
