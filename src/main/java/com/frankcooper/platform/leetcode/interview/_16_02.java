package com.frankcooper.platform.leetcode.interview;

import java.util.*;

public class _16_02 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        class WordsFrequency {

            Map<String, Integer> counter = new HashMap<>();

            public WordsFrequency(String[] book) {
                for (String b : book) {
                    counter.put(b, counter.getOrDefault(b, 0) + 1);
                }
            }

            public int get(String word) {
                return counter.getOrDefault(word, 0);
            }
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
