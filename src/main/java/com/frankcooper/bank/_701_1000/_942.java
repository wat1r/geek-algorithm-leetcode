package com.frankcooper.bank._701_1000;

import java.util.*;

import org.junit.Assert;

public class _942 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        public int[] diStringMatch(String s) {
            int n = s.length();
            int[] arr = new int[n + 1];
            int l = 0, r = n;
            int i = 0;
            while (i < n) {
                if (s.charAt(i) == 'I') arr[i] = l++;
                else if (s.charAt(i) == 'D') arr[i] = r--;
                i++;
            }
            arr[i] = l;
            return arr;

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
