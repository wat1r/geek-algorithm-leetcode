package com.frankcooper.interview;

import java.util.*;

import org.junit.Assert;

public class _17_11 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        public int findClosest(String[] words, String word1, String word2) {
            HashMap<String, List<Integer>> map = new HashMap<>();
            for (int i = 0; i < words.length; i++) {
                String cur = words[i];
                map.putIfAbsent(cur, new ArrayList<>());
                map.get(cur).add(i);
            }
            List<Integer> list1 = map.get(word1);
            List<Integer> list2 = map.get(word2);
            int dist = Integer.MAX_VALUE / 2;
            for (int i = 0; i < list1.size(); i++) {
                for (int j = 0; j < list2.size(); j++) {
                    dist = Math.min(dist, Math.abs(list1.get(i) - list2.get(j)));
                }
            }
            return dist;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        public int findClosest(String[] words, String word1, String word2) {
            boolean f1 = false, f2 = false;//标记是否都找到word1 和 word2
            int i1 = 0, i2 = 0;//分别记录word1和Word2的下标索引
            int dist = Integer.MAX_VALUE / 2;
            for (int i = 0; i < words.length; i++) {
                String cur = words[i];
                if (cur.equals(word1)) {
                    i1 = i;
                    f1 = true;
                } else if (cur.equals(word2)) {
                    i2 = i;
                    f2 = true;
                }
                if (f1 && f2) {//都找到了，更新dist
                    dist = Math.min(dist, Math.abs(i1 - i2));
                }
            }
            return dist;
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }

        public int findClosest(String[] words, String word1, String word2) {
            int j = 0, k = 0;
            int dist = 100010;
            for (int i = 0; i < words.length; i++) {
                if (word1.equals(words[i])) {
                    j = i;
                } else if (word2.equals(words[i])) {
                    k = i;
                }
                if (j > 0 && k > 0) {
                    dist = Math.min(dist, Math.abs(j - k));
                }
            }
            return dist;
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
