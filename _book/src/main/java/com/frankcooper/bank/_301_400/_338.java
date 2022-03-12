package com.frankcooper.bank._301_400;

public class _338 {

    static class _1st {

        public static void main(String[] args) {
            _1st handler = new _1st();
            handler.countBits(2);
        }

        public int[] countBits(int num) {
            int[] res = new int[num + 1];
            for (int i = 0; i <= num; i++) {
                res[i] = count(i);
            }

            return res;
        }

        private int count(int i) {
            int ans = 0;
            while (i != 0) {
                ans += i & 1;
                i >>= 1;
            }
            return ans;
        }
    }

    static class _2nd {

        public static void main(String[] args) {
            _2nd handler = new _2nd();
            handler.countBits(5);
        }
        public int[] countBits(int num) {
            int[] bits = new int[num + 1];
            int highBit = 0;
            for (int i = 1; i <= num; i++) {
                if ((i & (i - 1)) == 0) {
                    highBit = i;
                }
                bits[i] = bits[i - highBit] + 1;
            }
            return bits;
        }
    }
}
