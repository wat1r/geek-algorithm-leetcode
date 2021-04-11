package com.frankcooper.bank.LCP;

import java.util.*;

import org.junit.Assert;

public class LCPSpring21Group {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }

        public int storeWater(int[] bucket, int[] vat) {
            int n = bucket.length;
            int[] cnt = new int[n];
            Integer INF = 10_005;
            for (int i = 0; i < n; i++) {
                if (bucket[i] == 0) cnt[i] = INF;
                else if (vat[i] == 0) cnt[i] = 1;
                else cnt[i] = (vat[i] % bucket[i] == 0) ? vat[i] / bucket[i] : vat[i] / bucket[i] + 1;
            }



            return 0;
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
