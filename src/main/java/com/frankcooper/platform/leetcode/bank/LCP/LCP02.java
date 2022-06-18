package com.frankcooper.platform.leetcode.bank.LCP;

public class LCP02 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

//            System.out.println(Math.pow(2, -1));

            int[] cont = new int[]{3, 2, 0, 2};
            handler.fraction(cont);

        }


        public int[] fraction(int[] cont) {
            int low = cont[cont.length - 1], high = 1;
            for (int i = cont.length - 2; i >= 0; --i) {
                System.out.printf("high:%d,low:%d\n", high, low);
                high += cont[i] * low;
                int t = low;
                low = high;
                high = t;
            }
            return new int[]{low, high};
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
