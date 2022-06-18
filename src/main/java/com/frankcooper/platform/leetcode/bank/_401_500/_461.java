package com.frankcooper.platform.leetcode.bank._401_500;

public class _461 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int x = 1, y = 4;
            handler.hammingDistance(x, y);
        }


        public int hammingDistance(int x, int y) {
            int z = x ^ y;
            int res = 0;
            while (z != 0) {
                if ((z & 1) == 1) res++;//计算每个低位的1的个数
                z >>= 1;//抹掉1位
            }
            return res;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }


        public int hammingDistance(int x, int y) {
            int z = x ^ y;
            int res = 0;
            while (z != 0) {
                res++;
//                z = z & (z - 1);
                z -= z & (-z);
            }
            return res;
        }


    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }


        public int hammingDistance(int x, int y) {
            int res = 0;
            while (x != 0 || y != 0) {
                int bitx = x & 1, bity = y & 1;//去到x y的低位的结果，判断是否相等
                if (bitx != bity) res++;
                x >>= 1;
                y >>= 1;
            }
            return res;
        }

    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }

        public int hammingWeight(int n) {
            int ans = 0;
            while (n != 0) {
                ans += n & 1;
                n >>>= 1;
            }
            return ans;
        }
    }
}
