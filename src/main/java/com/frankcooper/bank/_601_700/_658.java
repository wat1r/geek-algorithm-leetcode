package com.frankcooper.bank._601_700;

import java.util.*;

import org.junit.Assert;

public class _658 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }


        public List<Integer> findClosestElements(int[] arr, int k, int x) {
            int l = 0, r = arr.length - k;
            while (l < r) {
                int mid = l + (r - l) / 2;
                if (x - arr[mid] <= arr[mid + k] - x) {
                    r = mid;
                } else if (x - arr[mid] > arr[mid + k] - x) {
                    l = mid + 1;
                }
            }
            List<Integer> res = new ArrayList<>();
            for (int i = l; i < l + k; i++) {
                res.add(arr[i]);
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
