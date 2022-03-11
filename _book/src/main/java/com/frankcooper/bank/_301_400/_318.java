package com.frankcooper.bank._301_400;

import java.util.*;

import org.junit.Assert;

public class _318 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            String[] words = {"abcw", "baz", "foo", "bar", "xtfn", "abcdef"};
            handler.maxProduct(words);

        }

        public int maxProduct(String[] words) {
            int res = 0;
            Map<Integer, Integer> map = new HashMap<>();
            for (String word : words) {
                int mask = 0, size = word.length();
                for (char c : word.toCharArray()) {
                    mask |= 1 << (c - 'a');
                }
                map.put(mask,Math.max(size,map.getOrDefault(mask,0)));
                for (Map.Entry<Integer, Integer> e : map.entrySet()) {
                    if ((mask & e.getKey()) == 0) {
                        res= Math.max(res,size* e.getValue());
                    }
                }
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
