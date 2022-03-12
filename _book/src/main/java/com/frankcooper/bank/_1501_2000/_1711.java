package com.frankcooper.bank._1501_2000;

import java.util.*;

import org.junit.Assert;

public class _1711 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

            handler.countPairs(null);

        }


        public int countPairs(int[] deliciousness) {
            int MOD = (int) 1e9 + 7;
            int res = 0;
            Map<Integer, Integer> map = new HashMap<>();
            for (int x : deliciousness) {
                for (int i = 0; i <= 21; i++) {
                    int y = (1 << i) - x;
                    if (map.containsKey(y)) {
                        res = (res + map.get(y)) % MOD;
                    }
                }
                map.put(x, map.getOrDefault(x, 0) + 1);
            }
            return res;
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
