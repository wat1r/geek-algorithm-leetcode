package com.frankcooper.platform.leetcode.bank._801_900;

import org.junit.Assert;

public class _875 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[] piles = {3, 6, 7, 11};
            int H = 8;
//            Assert.assertEquals(handler.minEatingSpeed(piles, H), 4);
//            piles = new int[]{30, 11, 23, 4, 20};
//            H = 5;
//            Assert.assertEquals(handler.minEatingSpeed(piles, H), 30);
//            piles = new int[]{30, 11, 23, 4, 20};
//            H = 6;
//            Assert.assertEquals(handler.minEatingSpeed(piles, H), 23);
            piles = new int[]{312884470};
            H = 968709470;
            Assert.assertEquals(handler.minEatingSpeed(piles, H), 1);
        }

        public int minEatingSpeed(int[] piles, int h) {
            int l = 0, r = 1_000_000_000;
            while (l < r) {
                int m = l + (r - l) / 2;
                if (!possible(piles, h, m)) {
                    l = m + 1;
                } else {
                    r = m;
                }
            }
            return l;
        }


        private boolean possible(int[] piles, int h, int t) {
            System.out.printf("t:%d\n", t);
            /**
             * [312884470]
             * 968709470
             * 处理t 到0的情况，这个case过不去
             */
            if (t == 0) return false;
            for (int i = 0; i < piles.length; i++) {
                h -= piles[i] % t == 0 ? piles[i] / t : piles[i] / t + 1;
                if (h < 0) return false;
            }
            return true;
        }

        /**
         *
         * 向上取整
         *  public boolean possible(int[] piles, int H, int K) {
         *         int time = 0;
         *         for (int p: piles)
         *             time += (p-1) / K + 1;
         *         return time <= H;
         *     }
         *
         * 作者：LeetCode
         * 链接：https://leetcode-cn.com/problems/koko-eating-bananas/solution/ai-chi-xiang-jiao-de-ke-ke-by-leetcode/
         * 来源：力扣（LeetCode）
         * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
         */
    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        public int minEatingSpeed(int[] piles, int h) {
            int r = 0;
            for (int x : piles) r = Math.max(r, x);
            int l = 1;
            while (l < r) {
                int mid = l + (r - l) / 2;
//                System.out.printf("%d\n", mid);
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


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }

        public int minEatingSpeed(int[] piles, int h) {
            int l = 1, r = (int) 1e9;
            while (l < r) {
                int mid = l + r >> 1;
                if (check(piles, mid, h)) r = mid;
                else l = mid + 1;
            }
            return r;
        }

        boolean check(int[] p, int k, int h) {
            int time = 0;
//            for (int x : p) ans += Math.ceil(x * 1.0 / k);
            for (int x : p) time += (x - 1) / k + 1;
            return time <= h;
        }

    }
}
