package com.frankcooper.bank.week;

public class Week219 {

    public static void main(String[] args) {
        _1st handler = new _1st();
        handler.numberOfMatches(7);
    }


    /**
     * 1688
     */
    static class _1st {
        public int numberOfMatches(int n) {
            int ans = 0;
            while (n > 1) {
                if (n % 2 == 0) {
                    ans += n / 2;
                    n /= 2;
                } else {
                    ans += (n - 1) / 2;
                    n = (n - 1) / 2 + 1;
                }
            }
            return ans;
        }
    }


    /**
     * 1689
     */
    static class _2nd {
        public int minPartitions(String n) {
            int ans = 0;
            for (char c : n.toCharArray()) ans = Math.max(ans, c - '0');
            return ans;
        }
    }


    /**
     * 1690
     */
    static class _3rd {
        public int stoneGameVII(int[] stones) {


            return 0;
        }
    }

}
