package com.frankcooper.bank._1501_2000;

public class _1720 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        /**
         * encoded[i-1] XOR arr[i-1] = arr[i]
         *
         * @param encoded
         * @param first
         * @return
         */

        public int[] decode(int[] encoded, int first) {
            int[] arr = new int[encoded.length + 1];
            arr[0] = first;
            for (int i = 1; i < arr.length; i++) {
                arr[i] = encoded[i-1] ^ arr[i-1];
            }
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
