package com.frankcooper.test;

public class CacheWorkspace {
    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[] piles = {1000000000};
            int h = 2;
//            handler.minEatingSpeed(piles, h);
            piles = new int[]{312884470};
            h = 968709470;
            handler.minEatingSpeed(piles, h);
        }

        public int minEatingSpeed(int[] piles, int h) {
            int r = 0;
            for (int x : piles) r = Math.max(r, x);
            int l = 1;
            while (l < r) {
                int mid = l + (r - l) / 2;
                System.out.printf("%d\n", mid);
                if (check(piles, mid, h)) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }
            return l;
        }

        private boolean check(int[] piles, int k, int h) {
            int times = 0;
            for (int i = 0; i < piles.length; i++) {
                times += (piles[i] % k == 0 ? 0 : 1) + piles[i] / k;
                if (times > h) return false;
            }
            return true;
        }
    }
}
