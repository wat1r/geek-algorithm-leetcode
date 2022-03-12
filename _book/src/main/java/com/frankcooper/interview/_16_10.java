package com.frankcooper.interview;

import java.util.*;

import org.junit.Assert;

public class _16_10 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        public int maxAliveYear(int[] birth, int[] death) {
            int[] arr = new int[101];
            for (int i = 0; i < birth.length; i++) {
                int s = birth[i], e = death[i];
                for (int j = s; j <= e; j++) {
                    arr[j - 1900]++;
                }
            }
            int cnt = 0, y = 0;
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] > cnt) {
                    cnt = arr[i];
                    y = i + 1900;
                }
            }
            return y;
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
