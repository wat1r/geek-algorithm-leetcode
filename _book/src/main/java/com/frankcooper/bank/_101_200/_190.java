package com.frankcooper.bank._101_200;

public class _190 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
//            handler.reverseBits(43261596);
//            handler.reverseBits(4294967293);
        }


        public int reverseBits(int n) {
//            System.out.println(PrintUtils.toBinaryString(n, 32));
            int ans = 0;
            int total = 31, bit = 0;
            while (n != 0) {//注意这里n可能是小于0的数
                ans |= (n & 1) << (total - bit);
                bit++;
                n >>>= 1;
            }
//            System.out.println(PrintUtils.toBinaryString(ans, 32));
            return ans;
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
