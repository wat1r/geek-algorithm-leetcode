package com.frankcooper.platform.leetcode.bank._1001_1500;

public class _1442 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        public int countTriplets(int[] arr) {
            int tmp = 0, res = 0;
            for (int i = 0; i < arr.length - 1; i++) {
                tmp = arr[i];
                for (int k = i + 1; k < arr.length; k++) {
                    tmp ^= arr[k];
                    if (tmp == 0) {
                        res += k - i;
                    }
                }
            }
            return res;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }


        public int countTriplets(int[] arr) {
            int t = 0, res = 0;
            for (int i = 0; i < arr.length - 1; i++) {
                t = arr[i];
                for (int k = i + 1; k < arr.length; k++) {
                    t ^= arr[k];
                    if (t == 0) {
                        res += (k - i);
                    }
                }
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
