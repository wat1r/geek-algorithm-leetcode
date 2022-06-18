package com.frankcooper.platform.leetcode.bank._101_200;


public class _191 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

//            Integer.toBinaryString()


        }


        public int hammingWeight(int n) {
            int ans = 0;
            while (n != 0) {
                ans += n & 1;
                n >>>= 1;//无符号右移 相当于 /2
            }
            return ans;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }


        public int hammingWeight(int n) {
            int res = 0;
            while (n != 0) {
                res++;
//                n &= n - 1;
                n -= n & (-n);
            }
            return res;
        }

    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();


        }

        public int hammingWeight(int n) {
            int res = 0;
            while (n != 0) {
                res += (n & 1);
                n >>>= 1;
            }
            return res;
        }


    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }


    }
}
